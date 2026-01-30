package com.illuminatijoe.refactorycore.data.datagen;

import com.illuminatijoe.refactorycore.machines.trait.NotifiableAuraContainer;

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
        replace(provider, "block.gtceu.nuclear_reactor", "Nuclear Reactor");
        replace(provider, "block.gtceu.fake_nuclear_reactor", "Fake Nuclear Reactor");

        // Recipes
        replace(provider, "gtceu.advanced_primitive_blast_furnace", "Advanced Blasting");
        replace(provider, "gtceu.hydrator", "Hydrating");
        replace(provider, "gtceu.bloodforge", "Blood Forging");
        replace(provider, "gtceu.mana_burner", "Mana Burning");
        replace(provider, "gtceu.nuclear_reactor", "Nuclear Fission");
        replace(provider, "gtceu.fake_nuclear_reactor", "Nuclear Cooling");

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
        replace(provider, "tooltip.refactorycore.taint_amount", "Current Taint: %s");
        replace(provider, "tooltip.refactorycore.aura_altar.0", "§7Uses Aura to catalyze magical items");
        replace(provider, "tooltip.refactorycore.aura_altar.1", "§bConsumes the aura needed according to the recipe");
        replace(provider, "tooltip.refactorycore.aura_hatch.import.0",
                "§bPulls Aura from the environment and provides it to the multiblock");
        replace(provider, "tooltip.refactorycore.aura_hatch.export.0",
                "§bExtracts Aura from the multiblock and releases it into the environment");
        replace(provider, "tooltip.refactorycore.aura_hatch.import.1",
                "§ePulls Aura from the highest Aura spot in a " + NotifiableAuraContainer.INPUT_RADIUS +
                        " block radius");
        replace(provider, "tooltip.refactorycore.aura_hatch.export.1",
                "§eExtracts Aura to the lowest Aura spot in a " + NotifiableAuraContainer.OUTPUT_RADIUS +
                        " block radius");
        replace(provider, "tooltip.refactorycore.plutonium_temp", "§7Fission Temperature: §4%sK§n");
        replace(provider, "tooltip.refactorycore.uranium_temp", "§7Fission Temperature: §4%sK§n");
        replace(provider, "tooltip.refactorycore.thorium_temp", "§7Fission Temperature: §4%sK§n");

        replace(provider, "tooltip.refactorycore.nuclear_reactor.0",
                "Generates insane amounts of EU using nuclear fuel");
        replace(provider, "tooltip.refactorycore.nuclear_reactor.1", "Consumes coolant each second to function");
        replace(provider, "tooltip.refactorycore.nuclear_reactor.2",
                "The amount of coolant required scales with the fuel's fission temperature");
        replace(provider, "tooltip.refactorycore.nuclear_reactor.3", "The exact formula is: %s");
        replace(provider, "tooltip.refactorycore.nuclear_reactor.4", "Coolants:");
        replace(provider, "refactorycore.misc.nuclear_coolant_list", "%s: %s mB");

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
        replace(provider, "gui.refactorycore.aura_hatch.label.import", "Aura Input Hatch");
        replace(provider, "gui.refactorycore.aura_hatch.label.export", "Aura Output Hatch");
        replace(provider, "gui.refactorycore.aura_hatch.aura", "Current Aura Around: %s");
        replace(provider, "gui.refactorycore.nuclear_reactor.heat", "Current Heat: %s");
        replace(provider, "gui.refactorycore.nuclear_reactor.consume_amount", "Consumes: %s");
        replace(provider, "gui.refactorycore.nuclear_reactor.current_coolant", "Coolant: %s");

        // recipe
        provider.add("refactorycore.recipe.lp_in", "LP Input: %s");
        provider.add("refactorycore.recipe.aura_in", "Aura Input: %s");
        provider.add("refactorycore.recipe.lp_out", "LP Output: %s");
        provider.add("refactorycore.recipe.aura_out", "Aura Output: %s");
        provider.add("refactorycore.recipe_logic.insufficient_coolant", "Not enough coolant");

        // materials
        provider.add("material.gtceu.flinak", "FLiNaK");
        provider.add("material.gtceu.hot_flinak", "Hot FLiNaK");
        provider.add("material.gtceu.hot_steam", "Hot Steam");
        provider.add("material.gtceu.hot_sodium_potassium", "Hot Sodium Potassium");
        provider.add("material.gtceu.nuclear_waste", "Nuclear Waste");
    }
}
