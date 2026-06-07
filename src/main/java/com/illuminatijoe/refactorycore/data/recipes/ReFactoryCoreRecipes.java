package com.illuminatijoe.refactorycore.data.recipes;

import com.illuminatijoe.refactorycore.data.materials.ConglomerateMaterials;
import com.illuminatijoe.refactorycore.data.materials.NuclearMaterials;
import com.illuminatijoe.refactorycore.data.tag.CustomTagPrefixes;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.BENDER_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MACERATOR_RECIPES;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MIXER_RECIPES;
import static com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes.*;

public class ReFactoryCoreRecipes {

    public static final int REDUCED_DURATION = (int) (20 * 0.75);

    public static void init(Consumer<FinishedRecipe> provider) {
        ReFactoryCoreMetaTileEntityLoader.init(provider);
        NuclearRecipes.init(provider);
        FakeRecipes.init(provider);
        registerTagPrefixRecipes(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_coal_gem")
                .inputItems(ingot, Iron)
                .inputItems(gem, Coal, 2)
                .outputItems(ingot, Steel)
                .outputItems(dustTiny, DarkAsh, 2)
                .duration(90 * REDUCED_DURATION)
                .save(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_coal_dust")
                .inputItems(ingot, Iron)
                .inputItems(dust, Coal, 2)
                .outputItems(ingot, Steel)
                .outputItems(dustTiny, DarkAsh, 2)
                .duration(90 * REDUCED_DURATION)
                .save(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_charcoal_gem")
                .inputItems(ingot, Iron)
                .inputItems(gem, Charcoal, 2)
                .outputItems(ingot, Steel)
                .outputItems(dustTiny, DarkAsh, 2)
                .duration(90 * REDUCED_DURATION)
                .save(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_charcoal_dust")
                .inputItems(ingot, Iron)
                .inputItems(dust, Charcoal, 2)
                .outputItems(ingot, Steel)
                .outputItems(dustTiny, DarkAsh, 2)
                .duration(120 * REDUCED_DURATION)
                .save(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_coke_gem")
                .inputItems(ingot, Iron)
                .inputItems(gem, Coke)
                .outputItems(ingot, Steel)
                .chancedOutput(dust, Ash, "1/9", 0)
                .duration(50 * REDUCED_DURATION)
                .save(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_coke_dust")
                .inputItems(ingot, Iron)
                .inputItems(dust, Coke)
                .outputItems(ingot, Steel)
                .chancedOutput(dust, Ash, "1/9", 0)
                .duration(50 * REDUCED_DURATION)
                .save(provider);

        // Block variants
        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_coal_block")
                .inputItems(block, Iron)
                .inputItems(block, Coal, 2)
                .outputItems(block, Steel)
                .outputItems(dust, DarkAsh, 2)
                .duration(810 * REDUCED_DURATION)
                .save(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_charcoal_block")
                .inputItems(block, Iron)
                .inputItems(block, Charcoal, 2)
                .outputItems(block, Steel)
                .outputItems(dust, DarkAsh, 2)
                .duration(810 * REDUCED_DURATION)
                .save(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_coke_block")
                .inputItems(block, Iron)
                .inputItems(block, Coke)
                .outputItems(block, Steel)
                .outputItems(dust, Ash)
                .duration(450 * REDUCED_DURATION)
                .save(provider);

        // Wrought Iron variants
        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_coal_gem_wrought")
                .inputItems(ingot, WroughtIron)
                .inputItems(gem, Coal, 2)
                .outputItems(ingot, Steel)
                .outputItems(dustTiny, DarkAsh, 2)
                .duration(40 * REDUCED_DURATION)
                .save(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_coal_dust_wrought")
                .inputItems(ingot, WroughtIron)
                .inputItems(dust, Coal, 2)
                .outputItems(ingot, Steel)
                .outputItems(dustTiny, DarkAsh, 2)
                .duration(40 * REDUCED_DURATION)
                .save(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_charcoal_gem_wrought")
                .inputItems(ingot, WroughtIron)
                .inputItems(gem, Charcoal, 2)
                .outputItems(ingot, Steel)
                .outputItems(dustTiny, DarkAsh, 2)
                .duration(40 * REDUCED_DURATION)
                .save(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_charcoal_dust_wrought")
                .inputItems(ingot, WroughtIron)
                .inputItems(dust, Charcoal, 2)
                .outputItems(ingot, Steel)
                .outputItems(dustTiny, DarkAsh, 2)
                .duration(40 * REDUCED_DURATION)
                .save(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_coke_gem_wrought")
                .inputItems(ingot, WroughtIron)
                .inputItems(gem, Coke)
                .outputItems(ingot, Steel)
                .chancedOutput(dust, Ash, "1/9", 0)
                .duration(20 * REDUCED_DURATION)
                .save(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_coke_dust_wrought")
                .inputItems(ingot, WroughtIron)
                .inputItems(dust, Coke)
                .outputItems(ingot, Steel)
                .chancedOutput(dust, Ash, "1/9", 0)
                .duration(20 * REDUCED_DURATION)
                .save(provider);

        // Wrought Iron block variants
        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_coal_block_wrought")
                .inputItems(block, WroughtIron)
                .inputItems(block, Coal, 2)
                .outputItems(block, Steel)
                .outputItems(dust, DarkAsh, 2)
                .duration(360 * REDUCED_DURATION)
                .save(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_charcoal_block_wrought")
                .inputItems(block, WroughtIron)
                .inputItems(block, Charcoal, 2)
                .outputItems(block, Steel)
                .outputItems(dust, DarkAsh, 2)
                .duration(360 * REDUCED_DURATION)
                .save(provider);

        ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder("steel_from_coke_block_wrought")
                .inputItems(block, WroughtIron)
                .inputItems(block, Coke)
                .outputItems(block, Steel)
                .outputItems(dust, Ash)
                .duration(180 * REDUCED_DURATION)
                .save(provider);

        COOLING_TOWER.recipeBuilder("hot_steam_cooling")
                .inputFluids(NuclearMaterials.HOT_STEAM.getFluid(16000))
                .outputFluids(Water.getFluid(16000))
                .duration(20 * 2)
                .EUt(GTValues.VA[GTValues.MV])
                .save(provider);

        COOLING_TOWER.recipeBuilder("hot_sodium_potassium_cooling")
                .inputFluids(NuclearMaterials.HOT_SODIUM_POTASSIUM.getFluid(12000))
                .outputFluids(SodiumPotassium.getFluid(12000))
                .duration(20 * 2)
                .EUt(GTValues.VA[GTValues.MV])
                .save(provider);

        COOLING_TOWER.recipeBuilder("hot_flinak_cooling")
                .inputFluids(NuclearMaterials.HOT_FLINAK.getFluid(8000))
                .outputFluids(NuclearMaterials.FLINAK.getFluid(8000))
                .duration(20 * 2)
                .EUt(GTValues.VA[GTValues.MV])
                .save(provider);

        MIXER_RECIPES.recipeBuilder("melting_flux_from_charcoal")
                .inputItems(dust, Charcoal)
                .inputItems(dust, QuartzSand)
                .outputItems(dust, ConglomerateMaterials.MeltingFlux, 4)
                .duration(100)
                .EUt(GTValues.VA[GTValues.ULV])
                .save(provider);

        MIXER_RECIPES.recipeBuilder("melting_flux_from_coal")
                .inputItems(dust, Coal)
                .inputItems(dust, QuartzSand)
                .outputItems(dust, ConglomerateMaterials.MeltingFlux, 8)
                .duration(100)
                .EUt(GTValues.VA[GTValues.ULV])
                .save(provider);

        MIXER_RECIPES.recipeBuilder("melting_flux_from_limestone")
                .inputItems(dust, ConglomerateMaterials.CobbledLimestone)
                .inputItems(dust, QuartzSand)
                .outputItems(dust, ConglomerateMaterials.MeltingFlux, 16)
                .duration(100)
                .EUt(GTValues.VA[GTValues.ULV])
                .save(provider);

        MIXER_RECIPES.recipeBuilder("melting_flux_from_borax")
                .inputItems(dust, Borax)
                .inputItems(dust, QuartzSand)
                .outputItems(dust, ConglomerateMaterials.MeltingFlux, 64)
                .duration(100)
                .EUt(GTValues.VA[GTValues.ULV])
                .save(provider);

        MACERATOR_RECIPES.recipeBuilder("limestone_conglomerate_macerating")
                .inputItems(CustomTagPrefixes.conglomerate, ConglomerateMaterials.CobbledLimestone)
                .outputItems(dust, ConglomerateMaterials.CobbledLimestone, 9)
                .duration(200)
                .EUt(GTValues.VA[GTValues.ULV])
                .save(provider);
    }

    private static void registerTagPrefixRecipes(Consumer<FinishedRecipe> provider) {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (material.hasFlag(MaterialFlags.GENERATE_PLATE)) {
                BENDER_RECIPES.recipeBuilder("curved_plate_" + material.getName())
                        .inputItems(plate, material)
                        .outputItems(CustomTagPrefixes.curvedPlate, material)
                        .duration(100)
                        .EUt(GTValues.VA[GTValues.ULV])
                        .save(provider);
            }
        }
    }
}
