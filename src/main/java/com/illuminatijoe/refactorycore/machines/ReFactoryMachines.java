package com.illuminatijoe.refactorycore.machines;

import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;
import com.illuminatijoe.refactorycore.api.ReFactoryRegistries;
import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes;
import com.illuminatijoe.refactorycore.machines.multiblock.other.APBFMachine;
import com.illuminatijoe.refactorycore.machines.multiblock.steam.WeakSteamParallelMultiblockMachine;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderHelper;
import com.gregtechceu.gtceu.common.block.BoilerFireboxType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;

import net.minecraft.network.chat.Component;

import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.createWorkableCasingMachineModel;
import static com.illuminatijoe.refactorycore.api.ReFactoryRegistries.REGISTRATE;

public class ReFactoryMachines {

    public static final MachineDefinition STEAM_CENTRIFUGE = REGISTRATE
            .multiblock("steam_separator", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .recipeType(GTRecipeTypes.CENTRIFUGE_RECIPES)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXX", "XXX", " X ")
                    .aisle("XXX", "X#X", "XXX")
                    .aisle("XXX", "XSX", " X ")
                    .where('S', Predicates.controller(blocks(definition.getBlock())))
                    .where('#', Predicates.air())
                    .where(' ', Predicates.any())
                    .where('X', blocks(CASING_BRONZE_BRICKS.get()).setMinGlobalLimited(6)
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1)))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    GTCEu.id("block/machines/centrifuge"))
            .tooltips(Component.translatable("tooltip.gtceu.steam_separator.0"))
            .register();

    public static final MachineDefinition STEAM_ALLOY_SMELTER = REGISTRATE
            .multiblock("steam_foundry", SteamParallelMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .recipeType(GTRecipeTypes.ALLOY_SMELTER_RECIPES)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .addOutputLimit(ItemRecipeCapability.CAP, 1)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("FFF", "XXX", "XXX")
                    .aisle("FFF", "X#X", "XXX")
                    .aisle("FFF", "XSX", "XXX")
                    .where('S', Predicates.controller(blocks(definition.getBlock())))
                    .where('F', blocks(FIREBOX_BRONZE.get())
                            .or(Predicates.abilities(PartAbility.STEAM).setExactLimit(1)))
                    .where('#', Predicates.air())
                    .where(' ', Predicates.any())
                    .where('X', blocks(CASING_BRONZE_BRICKS.get()).setMinGlobalLimited(6)
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setPreviewCount(1)))
                    .build())
            .model(createWorkableCasingMachineModel(
                            GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                            GTCEu.id("block/machines/alloy_smelter"))
                    .andThen(b -> b.addDynamicRenderer(() ->
                            DynamicRenderHelper.makeBoilerPartRender(BoilerFireboxType.BRONZE_FIREBOX, CASING_BRONZE_BRICKS))))
            .tooltips(Component.translatable("tooltip.gtceu.steam_foundry.0"))
            .register();

    public static final MultiblockMachineDefinition MB_STEAM_MIXER = REGISTRATE
            .multiblock("steam_blender", WeakSteamParallelMultiblockMachine::new)
            .tooltips(Component.translatable("tooltip.gtceu.steam_blender.0"),
                    Component.translatable("tooltip.gtceu.steam_blender.1"))
            .rotationState(RotationState.ALL)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .recipeType(GTRecipeTypes.MIXER_RECIPES)
            .recipeModifier(WeakSteamParallelMultiblockMachine::recipeModifier, true)
            .addOutputLimit(ItemRecipeCapability.CAP, 1)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("#CC#", "#CC#", "#CC#")
                    .aisle("CCCC", "CPPC", "CPPC")
                    .aisle("CCCC", "CPPC", "CPPC")
                    .aisle("#CC#", "#OC#", "#CC#")
                    .where('O', Predicates.controller(blocks(definition.getBlock())))
                    .where('#', Predicates.any())
                    .where('P', blocks(CASING_BRONZE_PIPE.get()))
                    .where('C', blocks(CASING_BRONZE_BRICKS.get())
                            .or(Predicates.abilities(PartAbility.STEAM)
                                    .setPreviewCount(1)
                                    .setMaxGlobalLimited(1)
                                    .setMinGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS)
                                    .setPreviewCount(1)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS)
                                    .setPreviewCount(1)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS)
                                    .setPreviewCount(1)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS)
                                    .setPreviewCount(1)
                                    .setMaxGlobalLimited(1)))
                    .build())
            .model(createWorkableCasingMachineModel(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    GTCEu.id("block/machines/mixer")))
            .register();

    public static final MultiblockMachineDefinition MB_STEAM_LATHE = REGISTRATE
            .multiblock("steam_borer", WeakSteamParallelMultiblockMachine::new)
            .tooltips(Component.translatable("tooltip.gtceu.steam_borer.0"),
                    Component.translatable("tooltip.gtceu.steam_borer.1"))
            .rotationState(RotationState.ALL)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .recipeType(GTRecipeTypes.LATHE_RECIPES)
            .recipeModifier(WeakSteamParallelMultiblockMachine::recipeModifier, true)
            .addOutputLimit(ItemRecipeCapability.CAP, 1)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCCC", "CCCC", "CCCC")
                    .aisle("CCCC", "CPPC", "CCCC")
                    .aisle("OCCC", "CCCC", "CCCC")
                    .where('O', Predicates.controller(blocks(definition.getBlock())))
                    .where('P', blocks(CASING_BRONZE_PIPE.get()))
                    .where('C', blocks(CASING_BRONZE_BRICKS.get())
                            .or(Predicates.abilities(PartAbility.STEAM)
                                    .setPreviewCount(1)
                                    .setMinGlobalLimited(1)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS)
                                    .setPreviewCount(1)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS)
                                    .setPreviewCount(1)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS)
                                    .setPreviewCount(1)
                                    .setMaxGlobalLimited(1)))
                    .build())
            .model(createWorkableCasingMachineModel(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    GTCEu.id("block/machines/lathe")))
            .register();

    public static final MultiblockMachineDefinition MB_STEAM_EXTRACTOR = REGISTRATE
            .multiblock("steam_large_extractor", WeakSteamParallelMultiblockMachine::new)
            .tooltips(Component.translatable("tooltip.gtceu.steam_large_extractor.0"),
                    Component.translatable("tooltip.gtceu.steam_large_extractor.1"))
            .rotationState(RotationState.ALL)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .recipeType(GTRecipeTypes.EXTRACTOR_RECIPES)
            .recipeModifier(WeakSteamParallelMultiblockMachine::recipeModifier, true)
            .addOutputLimit(ItemRecipeCapability.CAP, 1)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCC", "CCC", "CCC")
                    .aisle("CCC", "CPC", "CPC")
                    .aisle("COC", "CCC", "CCC")
                    .where('O', Predicates.controller(blocks(definition.getBlock())))
                    .where('P', blocks(CASING_BRONZE_PIPE.get()))
                    .where('C', blocks(CASING_BRONZE_BRICKS.get())
                            .or(Predicates.abilities(PartAbility.STEAM)
                                    .setPreviewCount(1)
                                    .setMinGlobalLimited(1)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS)
                                    .setPreviewCount(1)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS)
                                    .setPreviewCount(1)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS)
                                    .setPreviewCount(1)
                                    .setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS)
                                    .setPreviewCount(1)
                                    .setMaxGlobalLimited(1)))
                    .build())
            .model(createWorkableCasingMachineModel(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    GTCEu.id("block/machines/extractor")))
            .register();

    public static final MultiblockMachineDefinition ADVANCED_PRIMITIVE_BLAST_FURNACE = REGISTRATE
            .multiblock("advanced_primitive_blast_furnace", APBFMachine::new)
            .tooltips(Component.translatable("tooltip.gtceu.advanced_primitive_blast_furnace.0"),
                    Component.translatable("tooltip.gtceu.advanced_primitive_blast_furnace.1"),
                    Component.translatable("tooltip.gtceu.advanced_primitive_blast_furnace.2"))
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(CASING_STEEL_SOLID)
            .recipeType(ReFactoryCoreRecipeTypes.ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES)
            .recipeModifier(APBFMachine::recipeModifier, true)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("FFF", "CCC", "CCC", "CCC", "CCC")
                    .aisle("FFF", "C#C", "C#C", "C#C", "C#C")
                    .aisle("FFF", "COC", "CCC", "CCC", "CCC")
                    .where('O', Predicates.controller(blocks(definition.getBlock())))
                    .where('F', blocks(FIREBOX_STEEL.get()).setMinGlobalLimited(6)
                            .or(Predicates.abilities(PartAbility.STEAM_IMPORT_ITEMS).setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.STEAM_EXPORT_ITEMS).setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setMaxGlobalLimited(1)))
                    .where('#', Predicates.air())
                    .where('C', blocks(CASING_STEEL_SOLID.get()))
                    .build())
            .model(createWorkableCasingMachineModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/multiblock/primitive_blast_furnace"))
                    .andThen(modelBuilder -> modelBuilder.addDynamicRenderer(
                            () -> DynamicRenderHelper.makeBoilerPartRender(
                                    BoilerFireboxType.STEEL_FIREBOX, FIREBOX_STEEL))))
            .register();

    public static final MachineDefinition BRONZE_TANK_VALVE = GTMachineUtils.registerTankValve(
            REGISTRATE, "bronze_tank_valve", "Bronze Tank Valve", true,
            (builder, overlay) -> builder.workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"), overlay));
    public static final MultiblockMachineDefinition BRONZE_MULTIBLOCK_TANK = GTMachineUtils.registerMultiblockTank(
            REGISTRATE, "bronze_multiblock_tank", "Bronze Multiblock Tank", 500 * 1000,
            CASING_BRONZE_BRICKS, BRONZE_TANK_VALVE::getBlock, null,
            (builder, overlay) -> builder.workableCasingModel(
                    GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"), overlay));

    public static final MachineDefinition[] HYDRATOR = GTMachineUtils.registerSimpleMachines(
            REGISTRATE,
            "hydrator", ReFactoryCoreRecipeTypes.HYDRATOR_RECIPES, GTMachineUtils.hvCappedTankSizeFunction);

    public static void init() {}
}
