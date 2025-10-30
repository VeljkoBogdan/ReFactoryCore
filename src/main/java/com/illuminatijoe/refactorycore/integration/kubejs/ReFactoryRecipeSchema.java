package com.illuminatijoe.refactorycore.integration.kubejs;

import com.illuminatijoe.refactorycore.api.capabilities.recipe.LPRecipeCapability;

import com.gregtechceu.gtceu.integration.kjs.recipe.GTRecipeSchema;

import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import lombok.experimental.Accessors;

import static com.gregtechceu.gtceu.integration.kjs.recipe.GTRecipeSchema.*;

public interface ReFactoryRecipeSchema {

    @Accessors(chain = true, fluent = true)
    class ReFactoryRecipeJS extends GTRecipeSchema.GTRecipeJS {

        public GTRecipeSchema.GTRecipeJS lpInput(int essence) {
            this.input(LPRecipeCapability.CAP, essence);
            return this;
        }

        public GTRecipeSchema.GTRecipeJS lpOutput(int essence) {
            this.output(LPRecipeCapability.CAP, essence);
            return this;
        }
    }

    RecipeSchema SCHEMA = new RecipeSchema(ReFactoryRecipeJS.class, ReFactoryRecipeJS::new,
            DURATION, DATA, CONDITIONS,
            ALL_INPUTS, ALL_TICK_INPUTS, ALL_OUTPUTS, ALL_TICK_OUTPUTS,
            INPUT_CHANCE_LOGICS, OUTPUT_CHANCE_LOGICS, TICK_INPUT_CHANCE_LOGICS, TICK_OUTPUT_CHANCE_LOGICS, CATEGORY)
            .constructor((recipe, schemaType, keys, from) -> recipe.id(from.getValue(recipe, ID)), ID)
            .constructor(DURATION, CONDITIONS, ALL_INPUTS, ALL_OUTPUTS, ALL_TICK_INPUTS, ALL_TICK_OUTPUTS);
}
