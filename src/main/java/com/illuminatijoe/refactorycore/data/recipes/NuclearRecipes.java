package com.illuminatijoe.refactorycore.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CENTRIFUGE_RECIPES;
import static com.illuminatijoe.refactorycore.data.ReFactoryItems.*;
import static com.illuminatijoe.refactorycore.data.materials.NuclearMaterials.NUCLEAR_WASTE;
import static com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes.NUCLEAR_REACTOR;

public class NuclearRecipes {

    public static final int PLUTONIUM_TEMP = 1700;
    public static final int URANIUM_TEMP = 1500;
    public static final int THORIUM_TEMP = 2400;

    public static void init(Consumer<FinishedRecipe> provider) {
        var plutonium = NUCLEAR_REACTOR.recipeBuilder("plutonium_fission");
        plutonium.data.putFloat("heat", PLUTONIUM_TEMP);
        plutonium.inputItems(PLUTONIUM_FUEL_ROD, 1)
                .outputItems(dust, NUCLEAR_WASTE, 4)
                .duration(20 * 300)
                .EUt(-GTValues.V[GTValues.EV] * 32)
                .save(provider);

        var uranium = NUCLEAR_REACTOR.recipeBuilder("uranium_fission");
        uranium.data.putFloat("heat", URANIUM_TEMP);
        uranium.inputItems(URANIUM_FUEL_ROD, 1)
                .outputItems(dust, NUCLEAR_WASTE, 4)
                .duration(20 * 300)
                .EUt(-GTValues.V[GTValues.EV] * 16)
                .save(provider);

        var thorium = NUCLEAR_REACTOR.recipeBuilder("thorium_fission");
        thorium.data.putFloat("heat", THORIUM_TEMP);
        thorium.inputItems(THORIUM_FUEL_ROD, 1)
                .outputItems(dust, NUCLEAR_WASTE, 2)
                .duration(20 * 300)
                .EUt(-GTValues.V[GTValues.EV] * 16)
                .save(provider);

        CENTRIFUGE_RECIPES.recipeBuilder("nuclear_waste_recycling")
                .inputItems(dust, NUCLEAR_WASTE, 4)
                .outputItems(dust, Uranium238, 2)
                .outputItems(dustSmall, Plutonium239, 2)
                .chancedOutput(dustSmall, Caesium, 5000, 300)
                .chancedOutput(dustSmall, Strontium, 4500, 300)
                .chancedOutput(dustSmall, Iodine, 3000, 100)
                .chancedOutput(Krypton.getFluid(100), 3000, 100)
                .duration(20 * 30)
                .EUt(GTValues.VA[GTValues.EV])
                .save(provider);
    }
}
