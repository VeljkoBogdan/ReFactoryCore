package com.illuminatijoe.refactorycore.data.elements;

import com.gregtechceu.gtceu.api.data.chemical.Element;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.IngotProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import static com.gregtechceu.gtceu.common.data.GTElements.createAndRegister;

public class ReFactoryElements {

    static {
        GTRegistries.ELEMENTS.unfreeze();
    }

    public static final Element DAWNSTONE = createAndRegister(100, 100, -1, null, "Dawnstone", "☆", false);

    public static void register() {
        GTMaterials.Zirconium.setProperty(PropertyKey.INGOT, new IngotProperty());
        GTMaterials.Strontium.setProperty(PropertyKey.INGOT, new IngotProperty());
    }
}
