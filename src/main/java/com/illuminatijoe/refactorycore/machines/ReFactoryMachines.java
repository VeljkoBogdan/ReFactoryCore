package com.illuminatijoe.refactorycore.machines;

import com.illuminatijoe.refactorycore.machines.multiblock.steam.WeakSteamParallelMultiblockMachine;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.registry.GTRegistration;

import static com.gregtechceu.gtceu.common.data.GTBlocks.*;

public class ReFactoryMachines {

    public static final MultiblockMachineDefinition MB_STEAM_MIXER = GTRegistration.REGISTRATE
            .multiblock("steam_blender", WeakSteamParallelMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .appearanceBlock(BRONZE_HULL)
            .recipeType(GTRecipeTypes.MIXER_RECIPES)
            .recipeModifier(WeakSteamParallelMultiblockMachine::recipeModifier, true)
            .addOutputLimit(ItemRecipeCapability.CAP, 1)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("#CC#", "#CC#", "#CC#")
                    .aisle("CCCC", "CPPC", "CPPC")
                    .aisle("CCCC", "CPPC", "CPPC")
                    .aisle("#CC#", "#OC#", "#CC#")
                    .where('O', Predicates.controller(Predicates.blocks(definition.getBlock())))
                    .where('#', Predicates.any())
                    .where('P', Predicates.blocks(CASING_BRONZE_PIPE.get()))
                    .where('C', Predicates.blocks(CASING_BRONZE_BRICKS.get())
                            .or(Predicates.abilities(PartAbility.STEAM).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setPreviewCount(1)))
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    GTCEu.id("block/machines/mixer"), false)
            .register();

    public static void init() {}
}
