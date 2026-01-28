package com.illuminatijoe.refactorycore.api.capabilities.recipe.lookup;

import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.AbstractMapIngredient;

import java.util.Collections;
import java.util.List;

public class MapAuraIngredient extends AbstractMapIngredient {

    public final int aura;

    public MapAuraIngredient(int aura) {
        this.aura = aura;
    }

    @Override
    protected int hash() {
        return MapAuraIngredient.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MapAuraIngredient;
    }

    @Override
    public String toString() {
        return "MapAuraIngredient{aura=" + aura + "}";
    }

    public static List<AbstractMapIngredient> convertToMapIngredient(Integer aura) {
        return Collections.singletonList(new MapAuraIngredient(aura));
    }
}
