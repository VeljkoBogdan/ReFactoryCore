package com.illuminatijoe.refactorycore.integration.kubejs;

import com.illuminatijoe.refactorycore.api.capabilities.recipe.AuraRecipeCapability;
import com.illuminatijoe.refactorycore.api.capabilities.recipe.LPRecipeCapability;

import com.gregtechceu.gtceu.integration.kjs.recipe.components.ContentJS;

import dev.latvian.mods.kubejs.recipe.component.NumberComponent;

public class ReFactoryRecipeComponent {

    public static final ContentJS<Integer> LP_IN = new ContentJS<>(NumberComponent.ANY_INT,
            LPRecipeCapability.CAP, false);
    public static final ContentJS<Integer> LP_OUT = new ContentJS<>(NumberComponent.ANY_INT,
            LPRecipeCapability.CAP, true);
    public static final ContentJS<Integer> AURA_IN = new ContentJS<>(NumberComponent.ANY_INT,
            AuraRecipeCapability.CAP, false);
    public static final ContentJS<Integer> AURA_OUT = new ContentJS<>(NumberComponent.ANY_INT,
            AuraRecipeCapability.CAP, true);
}
