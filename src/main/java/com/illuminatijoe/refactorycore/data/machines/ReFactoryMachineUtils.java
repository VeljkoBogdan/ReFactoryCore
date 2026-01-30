package com.illuminatijoe.refactorycore.data.machines;

import com.illuminatijoe.refactorycore.machines.multiblock.generator.LargeManaBurnerMachine;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;

public class ReFactoryMachineUtils {

    public static MultiblockMachineDefinition registerManaBurner(
                                                                 GTRegistrate registrate,
                                                                 String name,
                                                                 int tier,
                                                                 GTRecipeType recipeType,
                                                                 Supplier<? extends Block> casing,
                                                                 Supplier<? extends Block> gear,
                                                                 ResourceLocation casingTexture,
                                                                 ResourceLocation overlayModel) {
        return registrate.multiblock(name, holder -> new LargeManaBurnerMachine(holder, tier))
                .rotationState(RotationState.ALL)
                .recipeType(recipeType)
                .generator(true)
                .recipeModifier(LargeManaBurnerMachine::recipeModifier, true)
                .appearanceBlock(casing)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("AAA", "AAA", "AAA", "AAA", "AAA")
                        .aisle("AAA", "ABA", "ABA", "ABA", "ACA")
                        .aisle("AAA", "AOA", "AAA", "AAA", "AAA")
                        .where("O", controller(blocks(definition.get())))
                        .where("B", blocks(gear.get()))
                        .where("C", ability(PartAbility.MUFFLER))
                        .where("A", blocks(casing.get()).setMinGlobalLimited(20)
                                .or(autoAbilities(definition.getRecipeTypes(), false, false,
                                        true, true, true, true))
                                .or(ability(PartAbility.OUTPUT_ENERGY,
                                        IntStream.of(MV, HV, EV, IV, LuV, ZPM, UV, UHV)
                                                .filter(t -> t >= tier)
                                                .toArray())
                                        .addTooltips(Component.translatable("gtceu.multiblock.pattern.error.limited.1",
                                                GTValues.VN[tier]))
                                        .setExactLimit(1)))
                        .build())
                .workableCasingModel(casingTexture, overlayModel)
                .tooltips(
                        Component.translatable("gtceu.universal.tooltip.base_production_eut", V[tier] * 2))
                .register();
    }

    public static void applyContents(MultiblockControllerMachine machine, Consumer<Object> contentHandler,
                                     RecipeCapability<?> capability) {
        applyContents(machine, contentHandler, capability, null);
    }

    public static void applyContents(MultiblockControllerMachine machine, Consumer<Object> contentHandler,
                                     RecipeCapability<?> capability, @Nullable IO io) {
        machine.getParts().forEach(part -> part.getRecipeHandlers().forEach(handlerList -> {
            if (io != null) {
                if (!handlerList.getHandlerIO().equals(io)) {
                    return;
                }
            }
            if (handlerList.getCapability(capability).isEmpty()) {
                return;
            }
            handlerList.getCapability(capability).forEach(iRecipeHandler -> {
                iRecipeHandler.getContents().forEach(contentHandler);
            });
        }));
    }

    public static void applyContents(MultiblockControllerMachine machine, BiConsumer<Object, IMultiPart> contentHandler,
                                     RecipeCapability<?> capability, @Nullable IO io) {
        machine.getParts().forEach(part -> part.getRecipeHandlers().forEach(handlerList -> {
            if (io != null) {
                if (!handlerList.getHandlerIO().equals(io)) {
                    return;
                }
            }
            if (handlerList.getCapability(capability).isEmpty()) {
                return;
            }
            handlerList.getCapability(capability).forEach(iRecipeHandler -> {
                iRecipeHandler.getContents().forEach(content -> contentHandler.accept(content, part));
            });
        }));
    }

    public static boolean canInputFluid(FluidStack fluidStack, WorkableMultiblockMachine machine) {
        var Recipe = GTRecipeBuilder.ofRaw().inputFluids(fluidStack).buildRawRecipe();
        return RecipeHelper.matchRecipe(machine, Recipe).isSuccess();
    }

    public static boolean canOutputFluid(FluidStack fluidStack, WorkableMultiblockMachine machine) {
        var Recipe = GTRecipeBuilder.ofRaw().outputFluids(fluidStack).buildRawRecipe();
        return RecipeHelper.matchRecipe(machine, Recipe).isSuccess();
    }

    public static boolean canInputItem(ItemStack itemStack, WorkableMultiblockMachine machine) {
        var Recipe = GTRecipeBuilder.ofRaw().inputItems(itemStack).buildRawRecipe();
        return RecipeHelper.matchRecipe(machine, Recipe).isSuccess();
    }

    public static boolean outputFluid(FluidStack fluidStack, WorkableMultiblockMachine machine) {
        var Recipe = GTRecipeBuilder.ofRaw().outputFluids(fluidStack).buildRawRecipe();
        if (RecipeHelper.matchRecipe(machine, Recipe).isSuccess()) {
            RecipeHelper.handleRecipeIO(machine, Recipe, IO.OUT, machine.getRecipeLogic().getChanceCaches());
            return true;
        }
        return false;
    }

    public static boolean inputFluid(FluidStack fluidStack, WorkableMultiblockMachine machine) {
        var Recipe = GTRecipeBuilder.ofRaw().inputFluids(fluidStack).buildRawRecipe();
        if (RecipeHelper.matchRecipe(machine, Recipe).isSuccess()) {
            RecipeHelper.handleRecipeIO(machine, Recipe, IO.IN, machine.getRecipeLogic().getChanceCaches());
            return true;
        }
        return false;
    }
}
