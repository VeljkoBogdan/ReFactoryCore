package com.illuminatijoe.refactorycore.data.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Potassium;

public class NuclearMaterials {

    // Coolants
    public static Material FLINAK;

    // Hot coolants
    public static Material HOT_STEAM;
    public static Material HOT_SODIUM_POTASSIUM;
    public static Material HOT_FLINAK;

    // Other
    public static Material NUCLEAR_WASTE;

    public static void register() {
        FLINAK = new Material.Builder(GTCEu.id("flinak"))
                .color(0x60ebca)
                .components(Fluorine, 1, Lithium, 1, Sodium, 1, Potassium, 1)
                .iconSet(MaterialIconSet.FLUID)
                .fluid()
                .buildAndRegister();

        HOT_FLINAK = new Material.Builder(GTCEu.id("hot_flinak"))
                .color(0xbfffdb)
                .components(FLINAK, 1)
                .iconSet(MaterialIconSet.FLUID)
                .fluid()
                .buildAndRegister();

        HOT_STEAM = new Material.Builder(GTCEu.id("hot_steam"))
                .color(0xffffff)
                .iconSet(MaterialIconSet.FLUID)
                .gas()
                .buildAndRegister();

        HOT_SODIUM_POTASSIUM = new Material.Builder(GTCEu.id("hot_sodium_potassium"))
                .color(0x96ffb9)
                .iconSet(MaterialIconSet.FLUID)
                .fluid()
                .buildAndRegister();

        NUCLEAR_WASTE = new Material.Builder(GTCEu.id("nuclear_waste"))
                .color(0x45322a)
                .secondaryColor(0x164026)
                .iconSet(MaterialIconSet.RADIOACTIVE)
                .dust()
                .buildAndRegister();
    }
}
