package com.illuminatijoe.refactorycore.machines.multiblock;

import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes;
import com.illuminatijoe.refactorycore.machines.multiblock.electric.AuraAltarMultiblockMachine;
import com.illuminatijoe.refactorycore.machines.part.ReFactoryPartAbilities;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.models.GTMachineModels;

import net.minecraft.network.chat.Component;

import de.ellpeck.naturesaura.blocks.ModBlocks;

import java.util.List;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.illuminatijoe.refactorycore.api.ReFactoryRegistries.REGISTRATE;

public class AuraAltar {

    public static final MachineDefinition AURA_ALTAR = REGISTRATE
            .multiblock("aura_altar", AuraAltarMultiblockMachine::new)
            .tooltips(List.of(
                    Component.translatable("tooltip.refactory.aura_altar.0"),
                    Component.translatable("tooltip.refactory.aura_altar.1")))
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(ReFactoryCoreRecipeTypes.AURA_ALTAR)
            .partAppearance(
                    (iMultiController, iMultiPart, direction) -> GTBlocks.CASING_TITANIUM_STABLE.getDefaultState())
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH,
                    GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK),
                    GTRecipeModifiers.BATCH_MODE)
            .pattern(definition -> FactoryBlockPattern.start()
                    // spotless:off
                    .aisle("abbabba", "#######", "#######", "#######")
                    .aisle("bcccccb", "#ababa#", "#a#a#a#", "#e###e#")
                    .aisle("bcccccb", "#bbbbb#", "#######", "#######")
                    .aisle("accccca", "#ababa#", "#a#a#a#", "#e###e#")
                    .aisle("bcccccb", "#bbbbb#", "#######", "#######")
                    .aisle("bcccccb", "#ababa#", "#a#a#a#", "#e###e#")
                    .aisle("abbabba", "#######", "#######", "#######")
                    .where('a', blocks(GTBlocks.CASING_STEEL_SOLID.get()))
                    .where('b', blocks(ModBlocks.INFUSED_BRICK)
                            .or(autoAbilities(ReFactoryCoreRecipeTypes.AURA_ALTAR))
                            .or(ability(PartAbility.INPUT_ENERGY)).setExactLimit(1)
                            .or(ability(ReFactoryPartAbilities.IMPORT_AURA).setExactLimit(1)))
                    .where('c', blocks(ModBlocks.INFUSED_STONE))
                    .where('#', air())
                    .where('e', blocks(ModBlocks.INFUSED_IRON_BLOCK))
                    // spotless:on
                    .build())
            .model(GTMachineModels.createWorkableCasingMachineModel(
                    GTCEu.id("block/casings/solid/machine_casing_stable_titanium"),
                    GTCEu.id("block/multiblock/power_substation")))
            .register();

    public static void init() {}
}
