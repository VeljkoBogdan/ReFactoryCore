package com.illuminatijoe.refactorycore.data.recipes;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

public class FakeRecipeTypes {

    public static final GTRecipeType FAKE_NUCLEAR_COOLING = GTRecipeTypes.register(
            "fake_nuclear_cooling", GTRecipeTypes.DUMMY)
            .setMaxIOSize(0, 0, 1, 1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static void init() {}
}
