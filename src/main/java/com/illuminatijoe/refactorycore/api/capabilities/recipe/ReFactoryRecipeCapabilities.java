package com.illuminatijoe.refactorycore.api.capabilities.recipe;

import com.gregtechceu.gtceu.api.registry.GTRegistries;

public class ReFactoryRecipeCapabilities {

    public static final LPRecipeCapability LP = LPRecipeCapability.CAP;

    public static void init() {
        GTRegistries.RECIPE_CAPABILITIES.register(LP.name, LP);
    }
}
