package com.illuminatijoe.refactorycore.data.datagen;

import com.illuminatijoe.refactorycore.api.ReFactoryRegistries;

import com.tterrag.registrate.providers.ProviderType;

public class ReFactoryDatagen {

    public static void init() {
        ReFactoryRegistries.REGISTRATE.addDataGenerator(ProviderType.LANG, ReFactoryLangGen::init);
    }
}
