package com.illuminatijoe.refactorycore.data.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;

public class ConglomerateMaterials {

    public static Material CobbledLimestone;
    public static Material MeltingFlux;

    public static void register() {
        CobbledLimestone = new Material.Builder(GTCEu.id("cobbled_limestone"))
                .color(0xA0B090).secondaryColor(0x7A8B6E)
                .iconSet(MaterialIconSet.ROUGH)
                .dust()
                .buildAndRegister();

        MeltingFlux = new Material.Builder(GTCEu.id("melting_flux"))
                .color(0xE8D5B7).secondaryColor(0xC4A882)
                .iconSet(MaterialIconSet.SAND)
                .dust()
                .buildAndRegister();
    }
}
