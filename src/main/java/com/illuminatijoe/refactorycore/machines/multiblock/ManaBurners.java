package com.illuminatijoe.refactorycore.machines.multiblock;

import com.illuminatijoe.refactorycore.ReFactoryCore;
import com.illuminatijoe.refactorycore.data.ReFactoryBlocks;
import com.illuminatijoe.refactorycore.data.machines.ReFactoryMachineUtils;
import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.common.data.GTBlocks;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.illuminatijoe.refactorycore.api.ReFactoryRegistries.REGISTRATE;

public class ManaBurners {

    public static final MultiblockMachineDefinition HIGH_VOLTAGE_MANA_BURNER = ReFactoryMachineUtils.registerManaBurner(
            REGISTRATE,
            "hv_mana_burner", HV,
            ReFactoryCoreRecipeTypes.MANA_BURNER_FUELS,
            ReFactoryBlocks.MANA_BURNER_CASING_STAINLESS_STEEL,
            GTBlocks.CASING_STAINLESS_STEEL_GEARBOX,
            ReFactoryCore.id("block/casings/clean/clean_mana_burner_casing"),
            GTCEu.id("block/multiblock/generator/large_steam_turbine"));

    public static final MultiblockMachineDefinition EXTREME_VOLTAGE_MANA_BURNER = ReFactoryMachineUtils
            .registerManaBurner(
                    REGISTRATE,
                    "ev_mana_burner", EV,
                    ReFactoryCoreRecipeTypes.MANA_BURNER_FUELS,
                    ReFactoryBlocks.MANA_BURNER_CASING_TITANIUM,
                    GTBlocks.CASING_TITANIUM_GEARBOX,
                    ReFactoryCore.id("block/casings/stable/stable_mana_burner_casing"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"));

    public static final MultiblockMachineDefinition INSANE_VOLTAGE_MANA_BURNER = ReFactoryMachineUtils
            .registerManaBurner(
                    REGISTRATE,
                    "iv_mana_burner", IV,
                    ReFactoryCoreRecipeTypes.MANA_BURNER_FUELS,
                    ReFactoryBlocks.MANA_BURNER_CASING_TUNGSTENSTEEL,
                    GTBlocks.CASING_TUNGSTENSTEEL_GEARBOX,
                    ReFactoryCore.id("block/casings/robust/robust_mana_burner_casing"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"));

    public static final MultiblockMachineDefinition LUDICROUS_VOLTAGE_MANA_BURNER = ReFactoryMachineUtils
            .registerManaBurner(
                    REGISTRATE,
                    "luv_mana_burner", LuV,
                    ReFactoryCoreRecipeTypes.MANA_BURNER_FUELS,
                    ReFactoryBlocks.MANA_BURNER_CASING_PALLADIUM_RHODIUM,
                    GTBlocks.CASING_TUNGSTENSTEEL_GEARBOX,
                    ReFactoryCore.id("block/casings/vigorous/vigorous_mana_burner_casing"),
                    GTCEu.id("block/multiblock/generator/large_steam_turbine"));

    public static void init() {};
}
