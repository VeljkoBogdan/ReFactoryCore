package com.illuminatijoe.refactorycore.data.recipes;

import com.illuminatijoe.refactorycore.api.capabilities.recipe.LPRecipeCapability;

import com.gregtechceu.gtceu.api.GTValues;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes.ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES;
import static com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes.BLOODFORGE;

public class ReFactoryCoreRecipes {

    public static final int REDUCED_DURATION = (int) (20 * 0.75);

    public static void init(Consumer<FinishedRecipe> provider) {
        ReFactoryCoreMetaTileEntityLoader.init(provider);

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

//        BLOODFORGE.recipeBuilder("dirt_from_dirt")
//                .inputItems(Items.DIRT)
//                .input(LPRecipeCapability.CAP, 100)
//                .outputItems(Items.DIRT)
//                .duration(400)
//                .EUt(GTValues.VA[GTValues.HV])
//                .save(provider);
    }
}
