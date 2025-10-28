package com.illuminatijoe.refactorycore.api.capabilities.recipe.lookup;

import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.AbstractMapIngredient;

import java.util.Collections;
import java.util.List;

public class MapLPIngredient extends AbstractMapIngredient {

    public final int essence;

    public MapLPIngredient(int essence) {
        this.essence = essence;
    }

    @Override
    protected int hash() {
        return MapLPIngredient.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MapLPIngredient;
    }

    @Override
    public String toString() {
        return "MapLPIngredient{lp=" + essence + "}";
    }

    public static List<AbstractMapIngredient> convertToMapIngredient(Integer essence) {
        return Collections.singletonList(new MapLPIngredient(essence));
    }
}
