package com.illuminatijoe.refactorycore.machines.multiblock.nuclear;

import com.illuminatijoe.refactorycore.data.machines.ReFactoryMachineUtils;
import com.illuminatijoe.refactorycore.data.materials.CoolantMaterials;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;

import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class NuclearReactorMultiblockMachine extends WorkableElectricMultiblockMachine {

    @Persisted
    public float heat;

    @Persisted
    public int coolantAmount = 0;
    @Persisted
    public int consumeAmount = 0;

    @Persisted
    public Material currentCoolant;

    @Persisted
    boolean didCool = false;

    public NuclearReactorMultiblockMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    public static ModifierFunction recipeModifier(MetaMachine machine, GTRecipe recipe) {
        if (machine instanceof NuclearReactorMultiblockMachine nuclearMachine) {
            nuclearMachine.heat = recipe.data.getFloat("heat");
            return ModifierFunction.IDENTITY;
        }
        return ModifierFunction.NULL;
    }

    @Override
    public boolean onWorking() {
        if (!didCool) recipeLogic.setWaiting(
                Component.translatable("refactorycore.recipe_logic.insufficient_coolant")
                        .withStyle(ChatFormatting.RED));
        if (getOffsetTimer() % 20 != 0) return super.onWorking();
        var value = super.onWorking();

        didCool = false;

        ReFactoryMachineUtils.applyContents(this, content -> {
            if (!(content instanceof FluidStack fluidStack)) return;

            for (var coolant : CoolantMaterials.getMaterials()) {
                if (!fluidStack.getFluid().equals(coolant.input.getFluid())) continue;

                int amount = (int) ((heat / 100f) * coolant.consumeAmount);

                if (fluidStack.getAmount() < amount) return;

                if (ReFactoryMachineUtils.canInputFluid(coolant.input.getFluid(amount), this) &&
                        ReFactoryMachineUtils.canOutputFluid(coolant.output.getFluid(amount), this)) {
                    currentCoolant = coolant.input;
                    coolantAmount = fluidStack.getAmount();
                    consumeAmount = amount;

                    ReFactoryMachineUtils.inputFluid(coolant.input.getFluid(amount), this);
                    ReFactoryMachineUtils.outputFluid(coolant.output.getFluid(amount), this);

                    didCool = true;
                    break;
                }
            }
        }, FluidRecipeCapability.CAP, IO.IN);

        if (!didCool) recipeLogic.setWaiting(
                Component.translatable("refactorycore.recipe_logic.insufficient_coolant")
                        .withStyle(ChatFormatting.RED));

        return value;
    }

    @Override
    public void onLoad() {
        super.onLoad();

        GTRecipe lastRecipe = recipeLogic.getLastRecipe();
        if (lastRecipe != null && lastRecipe.data.contains("heat")) {
            heat = lastRecipe.data.getFloat("heat");
        }

        if (currentCoolant != null) {
            boolean valid = CoolantMaterials.getMaterials().stream()
                    .anyMatch(coolant -> coolant.input.equals(currentCoolant));

            if (!valid) {
                currentCoolant = null;
                coolantAmount = 0;
                consumeAmount = 0;
            }
        }

        didCool = false;
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);

        if (isActive() && isFormed()) {
            textList.add(Component.translatable("gui.refactorycore.nuclear_reactor.heat",
                    Component.literal(heat + "K")
                            .setStyle(Style.EMPTY.withColor(ChatFormatting.RED))));
            textList.add(Component.translatable("gui.refactorycore.nuclear_reactor.consume_amount",
                    Component.literal(consumeAmount + " mB/s")
                            .setStyle(Style.EMPTY.withColor(ChatFormatting.AQUA))));

            if (currentCoolant != null) {
                textList.add(Component.translatable("gui.refactorycore.nuclear_reactor.current_coolant",
                        currentCoolant.getLocalizedName().setStyle(Style.EMPTY.withColor(ChatFormatting.AQUA))));
            }
        }
    }
}
