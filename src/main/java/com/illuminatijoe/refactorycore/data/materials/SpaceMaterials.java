package com.illuminatijoe.refactorycore.data.materials;

import com.illuminatijoe.refactorycore.ReFactoryCore;
import com.illuminatijoe.refactorycore.data.elements.ReFactoryElements;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class SpaceMaterials {

    private static Material DAWNSTONE;
    private static Material TZM_ALLOY;
    private static Material CERAMIC_RADIATIVE_COATING;

    public static void register() {
        DAWNSTONE = new Material.Builder(ReFactoryCore.id("dawnstone"))
                .ingot()
                .color(0xfffff0)
                .secondaryColor(0xff4800)
                .element(ReFactoryElements.DAWNSTONE)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_BOLT_SCREW,
                        MaterialFlags.GENERATE_DENSE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_SMALL_GEAR)
                .register();

        TZM_ALLOY = new Material.Builder(ReFactoryCore.id("tzm_alloy"))
                .ingot()
                .dust()
                .color(0x556066)
                .components(
                        Titanium, 1,
                        Zirconium, 1,
                        Molybdenum, 1,
                        Carbon, 1)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_FRAME)
                .iconSet(MaterialIconSet.METALLIC)
                .blastTemp(7500)
                .register();

        CERAMIC_RADIATIVE_COATING = new Material.Builder(ReFactoryCore.id("ceramic_radiative_coating"))
                .dust()
                .color(0xd4d0cb)
                .flags(MaterialFlags.GENERATE_PLATE)
                .iconSet(MaterialIconSet.DULL)
                .register();
    }
}
