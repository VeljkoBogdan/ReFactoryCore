package com.illuminatijoe.refactorycore.machines.multiblock;

import com.illuminatijoe.refactorycore.ReFactoryCore;
import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes;
import com.illuminatijoe.refactorycore.machines.multiblock.electric.AuraAltarMultiblockMachine;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.models.GTMachineModels;

import com.illuminatijoe.refactorycore.machines.part.ReFactoryPartAbilities;
import net.minecraft.network.chat.Component;

import de.ellpeck.naturesaura.blocks.ModBlocks;

import java.util.List;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.illuminatijoe.refactorycore.api.ReFactoryRegistries.REGISTRATE;

public class AuraAltar {

    public static final MachineDefinition AURA_ALTAR = REGISTRATE
            .multiblock("aura_altar", AuraAltarMultiblockMachine::new)
            .tooltips(List.of(
                    Component.translatable("tooltip.refactorycore.aura_altar.0"),
                    Component.translatable("tooltip.refactorycore.aura_altar.1")))
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(ReFactoryCoreRecipeTypes.AURA_ALTAR)
            .partAppearance(
                    (iMultiController, iMultiPart, direction) -> ModBlocks.INFUSED_BRICK.defaultBlockState())
            .recipeModifiers(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
            .pattern(definition -> FactoryBlockPattern.start()
                    // spotless:off
                    .aisle("abbabba", "ccccccc", "ccccccc", "ccccccc")
                    .aisle("bdddddb", "cababac", "cacacac", "cecccec")
                    .aisle("bdddddb", "cbbbbbc", "ccccccc", "ccccccc")
                    .aisle("addddda", "cabbbac", "cacfcac", "ccccccc")
                    .aisle("bdddddb", "cbbbbbc", "ccccccc", "ccccccc")
                    .aisle("bdddddb", "cababac", "cacacac", "cecccec")
                    .aisle("abbabba", "ccccccc", "ccccccc", "ccccccc")
                    // spotless:on
                    .where("a", blocks(GTBlocks.CASING_STEEL_SOLID.get()))
                    .where("b", blocks(ModBlocks.INFUSED_BRICK.defaultBlockState().getBlock())
                            .or(ability(PartAbility.IMPORT_ITEMS).setMaxGlobalLimited(2))
                            .or(ability(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(2))
                            .or(ability(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(3))
                            .or(ability(PartAbility.EXPORT_FLUIDS).setMaxGlobalLimited(3))
                            .or(ability(PartAbility.INPUT_ENERGY).setExactLimit(1).setPreviewCount(1))
                            .or(ability(PartAbility.MAINTENANCE).setExactLimit(1).setPreviewCount(1))
                            .or(ability(ReFactoryPartAbilities.IMPORT_AURA).setExactLimit(1).setPreviewCount(1)))
                    .where("c", any())
                    .where("d", blocks(ModBlocks.INFUSED_STONE.defaultBlockState().getBlock()))
                    .where("e", blocks(ModBlocks.INFUSED_IRON_BLOCK.defaultBlockState().getBlock()))
                    .where("f", controller(blocks(definition.get())))
                    .build())
            .model(GTMachineModels.createWorkableCasingMachineModel(
                    ReFactoryCore.id("block/other/infused_brick"),
                    ReFactoryCore.id("block/multiblock/aura_altar")))
            .register();

    public static void init() {}
}
