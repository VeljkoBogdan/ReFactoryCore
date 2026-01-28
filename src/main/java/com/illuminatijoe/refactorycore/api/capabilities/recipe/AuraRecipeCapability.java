package com.illuminatijoe.refactorycore.api.capabilities.recipe;

import com.illuminatijoe.refactorycore.api.capabilities.recipe.lookup.MapAuraIngredient;

import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.content.SerializerInteger;
import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.AbstractMapIngredient;

import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.utils.LocalizationUtils;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.List;

public class AuraRecipeCapability extends RecipeCapability<Integer> {

    public static final AuraRecipeCapability CAP = new AuraRecipeCapability();

    protected AuraRecipeCapability() {
        super("aura", 0x22a0a0ff, true, 10, SerializerInteger.INSTANCE);
    }

    @Override
    public Integer copyInner(Integer content) {
        return content;
    }

    @Override
    public Integer copyWithModifier(Integer content, ContentModifier modifier) {
        return modifier.apply(content);
    }

    public List<AbstractMapIngredient> convertToMapIngredient(Object ingredient) {
        List<AbstractMapIngredient> ingredients = new ObjectArrayList<>(1);
        if (ingredient instanceof Integer aura) ingredients.add(new MapAuraIngredient(aura));
        return ingredients;
    }

    @Override
    public boolean isRecipeSearchFilter() {
        return true;
    }

    @Override
    public void addXEIInfo(WidgetGroup group, int xOffset, GTRecipe recipe, List<Content> contents, boolean perTick,
                           boolean isInput, MutableInt yOffset) {
        int aura = contents.stream().map(Content::getContent).mapToInt(AuraRecipeCapability.CAP::of).sum();

        int xPos = 3 - xOffset;
        int yPos = yOffset.addAndGet(10);

        if (isInput) {
            group.addWidget(
                    new LabelWidget(xPos, yPos, LocalizationUtils.format("refactorycore.recipe.aura_in", aura)));
        } else {
            group.addWidget(
                    new LabelWidget(xPos, yPos, LocalizationUtils.format("refactorycore.recipe.aura_out", aura)));
        }
    }
}
