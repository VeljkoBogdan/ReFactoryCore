package com.illuminatijoe.refactorycore.client.renderer;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;

public class ReFactoryRenderUtils {

    public static DynamicRender<?, ?> createBloodforgeRender() {
        return BloodforgeRender.INSTANCE;
    }
}
