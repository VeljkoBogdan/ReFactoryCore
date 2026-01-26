package com.illuminatijoe.refactorycore.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.HV;
import static com.gregtechceu.gtceu.api.GTValues.V;
import static com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes.MANA_BURNER_FUELS;

public class ReFactoryFuelRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        MANA_BURNER_FUELS.recipeBuilder("test")
                .inputFluids(new FluidStack(Fluids.WATER, 100))
                .duration(20 * 8)
                .EUt(-V[HV])
                .save(provider);
    }
}
