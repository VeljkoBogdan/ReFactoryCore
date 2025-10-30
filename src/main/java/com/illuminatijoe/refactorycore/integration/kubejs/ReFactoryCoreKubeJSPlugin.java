package com.illuminatijoe.refactorycore.integration.kubejs;

import com.illuminatijoe.refactorycore.ReFactoryCore;
import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes;
import com.illuminatijoe.refactorycore.machines.ReFactoryMachines;

import com.gregtechceu.gtceu.api.registry.GTRegistries;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RecipeComponentFactoryRegistryEvent;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.ClassFilter;

public class ReFactoryCoreKubeJSPlugin extends KubeJSPlugin {

    @Override
    public void initStartup() {
        super.initStartup();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void registerClasses(ScriptType type, ClassFilter filter) {
        super.registerClasses(type, filter);
        filter.allow("com.illuminatijoe.refactorycore");
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        super.registerBindings(event);
        event.add("ReFactoryRecipeTypes", ReFactoryCoreRecipeTypes.class);
        event.add("ReFactoryMachines", ReFactoryMachines.class);

        event.add("ReFactoryCore", ReFactoryCore.class);
    }

    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        for (var entry : GTRegistries.RECIPE_TYPES.entries()) {
            event.register(entry.getKey(), ReFactoryRecipeSchema.SCHEMA);
        }
    }

    @Override
    public void registerRecipeComponents(RecipeComponentFactoryRegistryEvent event) {
        event.register("lpInput", ReFactoryRecipeComponent.LP_IN);
        event.register("lpOutput", ReFactoryRecipeComponent.LP_OUT);
    }
}
