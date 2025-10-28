package com.illuminatijoe.refactorycore.data;

import com.illuminatijoe.refactorycore.ReFactoryCore;
import com.illuminatijoe.refactorycore.api.ReFactoryRegistries;

import com.gregtechceu.gtceu.api.sound.SoundEntry;

public class ReFactorySounds {

    public static final SoundEntry BLOODFORGE = ReFactoryRegistries.REGISTRATE.sound(ReFactoryCore.id("bloodforge"))
            .build();

    public static void init() {}
}
