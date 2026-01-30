package com.illuminatijoe.refactorycore.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.illuminatijoe.refactorycore.data.materials.NuclearMaterials.*;
import static com.illuminatijoe.refactorycore.data.recipes.FakeRecipeTypes.FAKE_NUCLEAR_COOLING;

public class FakeRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        FAKE_NUCLEAR_COOLING.recipeBuilder("water_cooling")
                .inputFluids(Water.getFluid(2048))
                .outputFluids(HOT_STEAM.getFluid(2048))
                .duration(20)
                .save(provider);

        FAKE_NUCLEAR_COOLING.recipeBuilder("sodium_potassium_cooling")
                .inputFluids(SodiumPotassium.getFluid(512))
                .outputFluids(HOT_SODIUM_POTASSIUM.getFluid(512))
                .duration(20)
                .save(provider);

        FAKE_NUCLEAR_COOLING.recipeBuilder("flinak_cooling")
                .inputFluids(FLINAK.getFluid(128))
                .outputFluids(HOT_FLINAK.getFluid(128))
                .duration(20)
                .save(provider);
    }
}
