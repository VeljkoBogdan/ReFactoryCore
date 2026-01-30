package com.illuminatijoe.refactorycore.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import java.util.List;

public class CoolantMaterials {

    @SuppressWarnings("FieldMayBeFinal")
    private static List<CoolantMaterial> coolants = List.of(
            new CoolantMaterial(GTMaterials.Water, NuclearMaterials.HOT_STEAM, 2048),
            new CoolantMaterial(GTMaterials.SodiumPotassium, NuclearMaterials.HOT_SODIUM_POTASSIUM, 512),
            new CoolantMaterial(NuclearMaterials.FLINAK, NuclearMaterials.HOT_FLINAK, 128));

    public static void addMaterial(CoolantMaterial material) {
        coolants.add(material);
    }

    public static List<CoolantMaterial> getMaterials() {
        return coolants;
    }

    public static class CoolantMaterial {

        public Material input;
        public Material output;

        public float consumeAmount;

        public CoolantMaterial(Material input, Material output, float consumeAmount) {
            this.input = input;
            this.output = output;
            this.consumeAmount = consumeAmount;
        }
    }
}
