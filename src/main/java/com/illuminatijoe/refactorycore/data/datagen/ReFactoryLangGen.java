package com.illuminatijoe.refactorycore.data.datagen;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.gregtechceu.gtceu.data.lang.LangHandler.replace;
import static com.gregtechceu.gtceu.data.lang.MachineLang.standardTooltips;

public class ReFactoryLangGen {

    public static void init(RegistrateLangProvider provider) {
        // Blocks
        replace(provider, "block.gtceu.steam_blender", "Steam Blender");
        replace(provider, "block.gtceu.steam_borer", "Steam Borer");
        replace(provider, "block.gtceu.steam_large_extractor", "Large Steam Extractor");
        replace(provider, "block.gtceu.advanced_primitive_blast_furnace", "Advanced Blast Furnace");
        replace(provider, "block.gtceu.bronze_multiblock_tank", "Bronze Multiblock Tank");
        replace(provider, "block.gtceu.bronze_tank_valve", "Bronze Tank Valve");
        replace(provider, "block.gtceu.lv_hydrator", "Basic Hydrator");
        replace(provider, "block.gtceu.mv_hydrator", "Advanced Hydrator");
        replace(provider, "block.gtceu.hv_hydrator", "Advanced Hydrator II");
        replace(provider, "block.gtceu.ev_hydrator", "Advanced Hydrator III");
        replace(provider, "block.gtceu.iv_hydrator", "Elite Hydrator");
        replace(provider, "block.gtceu.luv_hydrator", "Elite Hydrator II");
        replace(provider, "block.gtceu.zpm_hydrator", "Elite Hydrator III");
        replace(provider, "block.gtceu.uv_hydrator", "Ultimate Hydrator");
        replace(provider, "block.gtceu.uhv_hydrator", "Epic Hydrator");
        replace(provider, "block.gtceu.uev_hydrator", "Epic Hydrator II");
        replace(provider, "block.gtceu.uiv_hydrator", "Epic Hydrator III");
        replace(provider, "block.gtceu.uxv_hydrator", "Epic Hydrator IV");
        replace(provider, "block.gtceu.opv_hydrator", "Legendary Hydrator");
        replace(provider, "block.gtceu.steam_separator", "Steam Separator");
        replace(provider, "block.gtceu.steam_foundry", "Steam Foundry");
        replace(provider, "block.gtceu.bloodforge", "§cBloodforge");

        // Recipes
        replace(provider, "gtceu.advanced_primitive_blast_furnace", "Advanced Blasting");
        replace(provider, "gtceu.hydrator", "Hydrating");
        replace(provider, "gtceu.bloodforge", "Blood Forging");

        // Tooltips
        provider.add("tooltip.refactorycore.lp_hatch.input", "Max Recipe Input: %s");
        provider.add("tooltip.refactorycore.lp_hatch.output", "Max Network Capacity: %s");
        replace(provider, "tooltip.gtceu.bloodforge.0", "§7Sanguine machinery");
        replace(provider, "tooltip.gtceu.steam_foundry.0", "Large alloy smelter");
        replace(provider, "tooltip.gtceu.steam_separator.0", "Large centrifuge");
        replace(provider, "tooltip.gtceu.steam_blender.0", "Steam multiblock mixer");
        replace(provider, "tooltip.gtceu.steam_blender.1", "§7Processes up to 8 recipes in parallel");
        replace(provider, "tooltip.gtceu.steam_borer.0", "Steam multiblock lathe");
        replace(provider, "tooltip.gtceu.steam_borer.1", "§7Processes up to 8 recipes in parallel");
        replace(provider, "tooltip.gtceu.steam_large_extractor.0", "Steam multiblock extractor");
        replace(provider, "tooltip.gtceu.steam_large_extractor.1", "§7Processes up to 8 recipes in parallel");
        replace(provider, "tooltip.gtceu.advanced_primitive_blast_furnace.0",
                "§7Much faster than the Primitive Blast Furnace");
        replace(provider, "tooltip.gtceu.advanced_primitive_blast_furnace.1",
                "§7Allows use of coal and charcoal to produce steel");
        replace(provider, "tooltip.gtceu.advanced_primitive_blast_furnace.2",
                "§7Processes up to 4 recipes in parallel");
        replace(provider, "tooltip.gtceu.hydrator", "Hydrates stuff");

        // Tiered machine tooltips
        standardTooltips(provider, "gtceu.machine", "hydrator",
                "Slowly moistens",
                "Moisturizes unmoisturized",
                "Shower in bulk");

        // GUI
        replace(provider, "gui.refactorycore.lp_hatch.label.import", "LP Input Hatch");
        replace(provider, "gui.refactorycore.lp_hatch.label.export", "LP Output Hatch");
        replace(provider, "gui.refactorycore.lp_hatch.no_network", "Not attached to a network!");
        replace(provider, "gui.refactorycore.lp_hatch.owner", "Attached to: %d");
        replace(provider, "gui.refactorycore.lp_hatch.lp", "Current LP: %s");

        // recipe
        provider.add("refactorycore.recipe.lp_in", "LP Input: %s");
        provider.add("refactorycore.recipe.lp_out", "LP Output: %s");
    }
}
