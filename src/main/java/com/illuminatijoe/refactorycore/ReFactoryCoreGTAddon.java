package com.illuminatijoe.refactorycore;

import com.illuminatijoe.refactorycore.api.ReFactoryRegistries;
import com.illuminatijoe.refactorycore.api.capabilities.recipe.ReFactoryRecipeCapabilities;
import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes;
import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipes;
import com.illuminatijoe.refactorycore.data.recipes.ReFactoryFuelRecipes;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.addon.events.KJSRecipeKeyEvent;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.data.recipes.FinishedRecipe;

import com.mojang.datafixers.util.Pair;

import java.util.function.Consumer;

import static com.illuminatijoe.refactorycore.integration.kubejs.ReFactoryRecipeComponent.*;

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
        ReFactoryFuelRecipes.init(provider);
    }

    @Override
    public void registerRecipeCapabilities() {
        ReFactoryRecipeCapabilities.init();
    }

    // If you have custom ingredient types, uncomment this & change to match your capability.
    // KubeJS WILL REMOVE YOUR RECIPES IF THESE ARE NOT REGISTERED.

    @Override
    public void registerRecipeKeys(KJSRecipeKeyEvent event) {
        event.registerKey(ReFactoryRecipeCapabilities.LP, Pair.of(LP_IN, LP_OUT));
        event.registerKey(ReFactoryRecipeCapabilities.AURA, Pair.of(AURA_IN, AURA_OUT));
    }
}
