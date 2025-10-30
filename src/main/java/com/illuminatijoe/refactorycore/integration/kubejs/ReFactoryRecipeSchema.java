package com.illuminatijoe.refactorycore.integration.kubejs;

import com.illuminatijoe.refactorycore.api.capabilities.recipe.ReFactoryRecipeCapabilities;

import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import lombok.experimental.Accessors;

import static com.gregtechceu.gtceu.integration.kjs.recipe.GTRecipeSchema.*;
import static com.gregtechceu.gtceu.integration.kjs.recipe.GTRecipeSchema.GTRecipeJS;

public interface ReFactoryRecipeSchema {

    @SuppressWarnings({ "unused", "UnusedReturnValue" })
    @Accessors(chain = true, fluent = true)
    class ReFactoryRecipeJS extends GTRecipeJS {

        public GTRecipeJS lpInput(int essence) {
            return input(ReFactoryRecipeCapabilities.LP, essence);
        }

        public GTRecipeJS lpOutput(int essence) {
            return input(ReFactoryRecipeCapabilities.LP, essence);
        }
    }

    RecipeSchema SCHEMA = new RecipeSchema(ReFactoryRecipeJS.class, ReFactoryRecipeJS::new,
            DURATION, DATA, CONDITIONS,
            ALL_INPUTS, ALL_TICK_INPUTS, ALL_OUTPUTS, ALL_TICK_OUTPUTS,
            INPUT_CHANCE_LOGICS, OUTPUT_CHANCE_LOGICS, TICK_INPUT_CHANCE_LOGICS, TICK_OUTPUT_CHANCE_LOGICS, CATEGORY)
            .constructor((recipe, schemaType, keys, from) -> recipe.id(from.getValue(recipe, ID)), ID)
            .constructor(DURATION, CONDITIONS, ALL_INPUTS, ALL_OUTPUTS, ALL_TICK_INPUTS, ALL_TICK_OUTPUTS);
}
