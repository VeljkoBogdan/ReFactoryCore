package com.illuminatijoe.refactorycore.client;

import com.illuminatijoe.refactorycore.ReFactoryCore;
import com.illuminatijoe.refactorycore.client.renderer.BloodforgeRender;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderManager;

import net.minecraftforge.eventbus.api.IEventBus;

public class ReFactoryCoreClient {

    private ReFactoryCoreClient() {}

    public static void init(IEventBus bus) {
        bus.register(ReFactoryCoreClient.class);

        DynamicRenderManager.register(ReFactoryCore.id("bloodforge_ball"), BloodforgeRender.getRenderType());
    }
}
