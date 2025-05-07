package com.illuminatijoe.refactorycore.data.datagen;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.gregtechceu.gtceu.data.lang.LangHandler.replace;

public class ReFactoryLangGen {

    public static void init(RegistrateLangProvider provider) {
        replace(provider, "block.gtceu.steam_blender", "Steam Blender");
    }
}
