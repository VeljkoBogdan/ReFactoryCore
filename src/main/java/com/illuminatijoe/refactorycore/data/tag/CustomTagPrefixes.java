package com.illuminatijoe.refactorycore.data.tag;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.illuminatijoe.refactorycore.data.materials.ConglomerateMaterials;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class CustomTagPrefixes {

    public static MaterialIconType curvedPlateIconType = new MaterialIconType("curved_plate");

    public static final TagPrefix curvedPlate = new TagPrefix("curved_plate")
            .defaultTagPath("curved_plates/%s")
            .unformattedTagPath("curved_plates")
            .langValue("Curved %s Plate")
            .materialAmount(GTValues.M)
            .materialIconType(curvedPlateIconType)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(mat -> mat.hasFlag(MaterialFlags.GENERATE_PLATE));

    public static MaterialIconType conglomerateIconType = new MaterialIconType("conglomerate");

    public static final TagPrefix conglomerate = new TagPrefix("conglomerate")
            .idPattern("conglomerate_%s")
            .defaultTagPath("conglomerates/%s")
            .unformattedTagPath("conglomerates")
            .langValue("%s Conglomerate")
            .materialAmount(GTValues.M * 9)
            .materialIconType(conglomerateIconType)
            .unificationEnabled(true)
            .generateBlock(true)
            .generationCondition(mat -> mat == Hematite || mat == Malachite ||
                    mat == Cassiterite || mat == ConglomerateMaterials.CobbledLimestone);

    public static void init() {}
}
