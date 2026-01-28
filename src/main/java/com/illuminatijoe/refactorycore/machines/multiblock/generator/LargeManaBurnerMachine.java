package com.illuminatijoe.refactorycore.machines.multiblock.generator;

import com.illuminatijoe.refactorycore.data.materials.ReFactoryMaterials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockDisplayText;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.ingredient.EnergyStack;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gregtechceu.gtceu.utils.GTMath;

import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fluids.FluidStack;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public class LargeManaBurnerMachine extends WorkableElectricMultiblockMachine implements ITieredMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            LargeManaBurnerMachine.class, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER);

    private static final Material CLEANER = ReFactoryMaterials.MANA_CLEANER_FLUID;

    @Getter
    private final int tier;

    @DescSynced
    private int runningTimer = 0;
    @DescSynced
    private double efficiency = 1; // [0, 1]
    @DescSynced
    private double taint = 0; // [0, 1]

    public LargeManaBurnerMachine(IMachineBlockEntity holder, int tier) {
        super(holder);
        this.tier = tier;
    }

    protected GTRecipe getCleanerRecipe() {
        FluidStack cleaner = getCleanerFluidStack();
        FluidStack dirty = new FluidStack(GTMaterials.Lava.getFluid(), cleaner.getAmount());

        return GTRecipeBuilder.ofRaw()
                .inputFluids(cleaner)
                .outputFluids(dirty)
                .buildRawRecipe();
    }

    private FluidStack getCleanerFluidStack() {
        // 10mb when clean, 210mb when at 100% taint
        int amount = 10 + (int) (taint * taint * 200);
        return CLEANER.getFluid(amount);
    }

    @Override
    public long getOverclockVoltage() {
        return GTValues.V[tier];
    }

    public static ModifierFunction recipeModifier(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {
        if (!(machine instanceof LargeManaBurnerMachine burnerMachine)) {
            return RecipeModifier.nullWrongType(LargeManaBurnerMachine.class, machine);
        }

        EnergyStack EUt = recipe.getOutputEUt();

        if (!EUt.isEmpty()) {
            // RecipeHelper.matchRecipe(burnerMachine, burnerMachine.getCleanerRecipe());

            int maxParallel = (int) (burnerMachine.getOverclockVoltage() / EUt.getTotalEU());
            int actualParallel = ParallelLogic.getParallelAmount(burnerMachine, recipe, maxParallel);

            return ModifierFunction.builder()
                    .inputModifier(ContentModifier.multiplier(actualParallel))
                    .outputModifier(ContentModifier.multiplier(actualParallel))
                    .eutMultiplier(actualParallel /* * burnerMachine.efficiency */ )
                    .parallels(actualParallel)
                    .build();
        }
        return ModifierFunction.NULL;
    }

    private void updateEfficiency() {
        // Clamp taint to [0,1]
        taint = Math.min(1.0, Math.max(taint, 0.0));
        efficiency = 1.0 - taint;
    }

    @Override
    public boolean onWorking() {
        boolean value = super.onWorking();

        // Perform the check 5 times a second
        // if (runningTimer % 4 == 0) {
        // // Consume the cleaner fluid and remove taint if the recipe goes through
        // if (RecipeHelper.handleRecipeIO(this, getCleanerRecipe(), IO.IN,
        // this.recipeLogic.getChanceCaches()).isSuccess() &&
        // RecipeHelper.handleRecipeIO(this, getCleanerRecipe(), IO.OUT,
        // this.recipeLogic.getChanceCaches()).isSuccess()) {
        // taint -= 0.01;
        // }
        //
        // updateEfficiency();
        // }

        runningTimer++;
        if (runningTimer > 72000) runningTimer %= 72000;

        return value;
    }

    @Override
    public void afterWorking() {
        super.afterWorking();
        // taint += 0.5;
        // updateEfficiency();
    }

    @Override
    public boolean regressWhenWaiting() {
        return false;
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        MultiblockDisplayText.Builder builder = MultiblockDisplayText.builder(textList, isFormed())
                .setWorkingStatus(recipeLogic.isWorkingEnabled(), recipeLogic.isActive());

        long lastEUt = recipeLogic.getLastRecipe() != null ? recipeLogic.getLastRecipe().getOutputEUt().getTotalEU() :
                0;
        builder.addEnergyProductionAmpsLine(GTValues.V[tier], 1);

        if (isActive() && isWorkingEnabled()) {
            builder.addCurrentEnergyProductionLine(lastEUt);
        }

        builder.addFuelNeededLine(getRecipeFluidInputInfo(), recipeLogic.getDuration());
        builder.addWorkingStatusLine();
        textList.add(Component.translatable("tooltip.refactorycore.taint_amount", (int) (taint * 100)));
    }

    @Nullable
    public String getRecipeFluidInputInfo() {
        // Previous Recipe is always null on first world load, so try to acquire a new recipe
        GTRecipe recipe = recipeLogic.getLastRecipe();
        if (recipe == null) {
            Iterator<GTRecipe> iterator = recipeLogic.searchRecipe();
            recipe = iterator.hasNext() ? iterator.next() : null;
            if (recipe == null) return null;
        }
        FluidStack requiredFluidInput = RecipeHelper.getInputFluids(recipe).get(0);

        int neededAmount = GTMath.saturatedCast(requiredFluidInput.getAmount());
        return ChatFormatting.RED + FormattingUtil.formatNumbers(neededAmount) + "mB";
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
