package com.illuminatijoe.refactorycore.data.recipes;

import com.illuminatijoe.refactorycore.machines.ReFactoryMachines;

import com.gregtechceu.gtceu.data.recipe.misc.MetaTileEntityLoader;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.data.recipe.GTCraftingComponents.*;

public class ReFactoryCoreMetaTileEntityLoader {

    public static void init(Consumer<FinishedRecipe> provider) {
        MetaTileEntityLoader.registerMachineRecipe(provider, ReFactoryMachines.HYDRATOR, "GPG", "CHC", "MRM",
                'G', GLASS, 'P', PUMP, 'C', CIRCUIT, 'H', HULL, 'M', MOTOR, 'R', ROTOR);
    }
}
