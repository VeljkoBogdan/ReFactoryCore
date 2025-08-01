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
        replace(provider, "block.gtceu.hydrator", "Hydrator");

        // Recipes
        replace(provider, "gtceu.advanced_primitive_blast_furnace", "Advanced Blasting");
        replace(provider, "gtceu.hydrator", "Hydrating");

        // Tooltips
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
    }
}
