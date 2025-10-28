package com.illuminatijoe.refactorycore;

import com.illuminatijoe.refactorycore.api.ReFactoryRegistries;
import com.illuminatijoe.refactorycore.api.capabilities.recipe.LPRecipeCapability;
import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes;
import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipes;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.addon.events.KJSRecipeKeyEvent;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.integration.kjs.recipe.components.ContentJS;

import net.minecraft.data.recipes.FinishedRecipe;

import com.mojang.datafixers.util.Pair;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;

import java.util.function.Consumer;

@SuppressWarnings("unused")
@GTAddon
public class ReFactoryCoreGTAddon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return ReFactoryRegistries.REGISTRATE;
    }

    @Override
    public void initializeAddon() {}

    @Override
    public String addonModId() {
        return ReFactoryCore.MOD_ID;
    }

    @Override
    public void registerTagPrefixes() {
        // CustomTagPrefixes.init();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        ReFactoryCoreRecipeTypes.init();
        ReFactoryCoreRecipes.init(provider);
    }

    // If you have custom ingredient types, uncomment this & change to match your capability.
    // KubeJS WILL REMOVE YOUR RECIPES IF THESE ARE NOT REGISTERED.

    public static final ContentJS<Integer> LP_IN = new ContentJS<>(NumberComponent.ANY_INT,
            LPRecipeCapability.CAP, false);
    public static final ContentJS<Integer> LP_OUT = new ContentJS<>(NumberComponent.ANY_INT,
            LPRecipeCapability.CAP, true);

    @Override
    public void registerRecipeKeys(KJSRecipeKeyEvent event) {
        event.registerKey(LPRecipeCapability.CAP, Pair.of(LP_IN, LP_OUT));
    }
}
