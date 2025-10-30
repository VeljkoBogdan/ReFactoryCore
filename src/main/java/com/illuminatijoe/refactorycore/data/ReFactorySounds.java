package com.illuminatijoe.refactorycore.data;

import com.illuminatijoe.refactorycore.ReFactoryCore;

import com.gregtechceu.gtceu.api.sound.SoundEntry;

import static com.gregtechceu.gtceu.common.registry.GTRegistration.REGISTRATE;

public class ReFactorySounds {

    public static final SoundEntry BLOODFORGE = REGISTRATE.sound(ReFactoryCore.id("bloodforge"))
            .build();

    public static void init() {}
}
