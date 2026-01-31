package com.illuminatijoe.refactorycore.machines;

import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes;
import com.illuminatijoe.refactorycore.machines.multiblock.other.APBFMachine;
import com.illuminatijoe.refactorycore.machines.multiblock.steam.WeakSteamParallelMultiblockMachine;
import com.illuminatijoe.refactorycore.machines.part.AuraHatchPartMachine;
import com.illuminatijoe.refactorycore.machines.part.LPHatchPartMachine;
import com.illuminatijoe.refactorycore.machines.part.ReFactoryPartAbilities;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderHelper;
import com.gregtechceu.gtceu.common.block.BoilerFireboxType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;

import net.minecraft.network.chat.Component;

import java.util.List;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.blocks;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.createWorkableCasingMachineModel;
import static com.illuminatijoe.refactorycore.api.ReFactoryRegistries.REGISTRATE;

public class ReFactoryMachines {

    public static final MachineDefinition[] LP_IMPORT_HATCH = registerLPHatch(
            "lp_input_hatch", "LP Input Hatch",
            IO.IN, GTMachineUtils.MULTI_HATCH_TIERS, ReFactoryPartAbilities.IMPORT_LP);
    public static final MachineDefinition[] LP_EXPORT_HATCH = registerLPHatch(
            "lp_output_hatch", "LP Output Hatch",
            IO.OUT, GTMachineUtils.MULTI_HATCH_TIERS, ReFactoryPartAbilities.EXPORT_LP);

    public static final MachineDefinition[] AURA_IMPORT_HATCH = registerAuraHatch(
            "aura_input_hatch", "Aura Input Hatch",
            IO.IN, ReFactoryPartAbilities.IMPORT_AURA);
    public static final MachineDefinition[] AURA_EXPORT_HATCH = registerAuraHatch(
            "aura_export_hatch", "Aura Export Hatch",
            IO.OUT, ReFactoryPartAbilities.EXPORT_AURA);

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
                    .andThen(b -> b.addDynamicRenderer(() -> DynamicRenderHelper
                            .makeBoilerPartRender(BoilerFireboxType.BRONZE_FIREBOX, CASING_BRONZE_BRICKS))))
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

    public static MachineDefinition[] registerLPHatch(String name, String displayName, IO io, int[] tiers,
                                                      PartAbility... abilities) {
        return GTMachineUtils.registerTieredMachines(REGISTRATE, name,
                (holder, tier) -> new LPHatchPartMachine(holder, tier, io),
                (tier, builder) -> builder
                        .langValue(GTValues.VNF[tier] + ' ' + displayName)
                        .abilities(abilities)
                        .rotationState(RotationState.ALL)
                        .modelProperty(GTMachineModelProperties.IS_FORMED, false)
                        .overlayTieredHullModel("lp_hatch")
                        .tooltipBuilder((item, tooltip) -> {
                            if (io == IO.IN) {
                                tooltip.add(Component.translatable("tooltip.refactorycore.lp_hatch.input",
                                        LPHatchPartMachine.getMaxConsumption(tier)));
                            } else {
                                tooltip.add(Component.translatable("tooltip.refactorycore.lp_hatch.output",
                                        LPHatchPartMachine.getMaxCapacity(tier)));
                            }
                        })
                        .register(),
                tiers);
    }

    public static MachineDefinition[] registerAuraHatch(String name, String displayName, IO io,
                                                        PartAbility... abilities) {
        return GTMachineUtils.registerTieredMachines(REGISTRATE, name,
                (holder, tier) -> new AuraHatchPartMachine(holder, tier, io),
                (tier, builder) -> builder
                        .tooltips(List.of(
                                Component.translatable("tooltip.refactorycore.aura_hatch." +
                                        (io == IO.IN ? "import" : "export") + ".0"),
                                Component.translatable("tooltip.refactorycore.aura_hatch." +
                                        (io == IO.IN ? "import" : "export") + ".1")))
                        .langValue(GTValues.VNF[tier] + ' ' + displayName)
                        .abilities(abilities)
                        .rotationState(RotationState.ALL)
                        .modelProperty(GTMachineModelProperties.IS_FORMED, false)
                        .overlayTieredHullModel(io == IO.IN ? "aura_import_hatch" : "aura_export_hatch")
                        .register(),
                MV);
    }

    public static void init() {}
}
