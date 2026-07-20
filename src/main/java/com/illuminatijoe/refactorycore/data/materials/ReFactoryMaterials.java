package com.illuminatijoe.refactorycore.data.materials;

import com.illuminatijoe.refactorycore.ReFactoryCore;
import com.illuminatijoe.refactorycore.data.chemical.ReFactoryIconSet;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class ReFactoryMaterials {

    // public static Material MANA_CLEANER_FLUID;
    public static Material UELIBLOOM;

    public static void register() {
        // MANA_CLEANER_FLUID = new Material.Builder(GTCEu.id("mana_cleaner_fluid"))
        // .color(0xa1f542)
        // .secondaryColor(0x388eff)
        // .iconSet(MaterialIconSet.FLUID)
        // .fluid()
        // .buildAndRegister();

        UELIBLOOM = new Material.Builder(ReFactoryCore.id("uelibloom"))
                .color(0x437339)
                .secondaryColor(0xad8289)
                .iconSet(ReFactoryIconSet.BLOOM)
                .ingot()
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_GEAR)
                .buildAndRegister();
    }
}
