package com.illuminatijoe.refactorycore.data.recipes;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;

import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;

public class ReFactoryCoreRecipeTypes {

    public static final GTRecipeType ADVANCED_PRIMITIVE_BLAST_FURNACE_RECIPES = GTRecipeTypes.register(
            "advanced_primitive_blast_furnace", GTRecipeTypes.MULTIBLOCK)
            .setMaxIOSize(3, 3, 0, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setMaxTooltips(1)
            .setSound(GTSoundEntries.FIRE);

    public static void init() {}
}
