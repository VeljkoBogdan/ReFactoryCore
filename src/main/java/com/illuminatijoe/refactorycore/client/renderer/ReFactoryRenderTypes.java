package com.illuminatijoe.refactorycore.client.renderer;

import net.minecraft.Util;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import java.util.function.Function;

public class ReFactoryRenderTypes extends RenderType {

    private static final Function<ResourceLocation, RenderType> LIGHT_RING_TEXTURED = Util
            .memoize((texture) -> RenderType.create("light_ring_textured",
                    DefaultVertexFormat.POSITION_COLOR_TEX,
                    VertexFormat.Mode.TRIANGLE_STRIP, 256, false, false,
                    RenderType.CompositeState.builder()
                            .setCullState(NO_CULL)
                            .setShaderState(POSITION_COLOR_TEX_SHADER)
                            .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                            .setTextureState(new TextureStateShard(texture, false, false))
                            .createCompositeState(false)));

    public ReFactoryRenderTypes(String pName, VertexFormat pFormat, VertexFormat.Mode pMode, int pBufferSize,
                                boolean pAffectsCrumbling, boolean pSortOnUpload, Runnable pSetupState,
                                Runnable pClearState) {
        super(pName, pFormat, pMode, pBufferSize, pAffectsCrumbling, pSortOnUpload, pSetupState, pClearState);
    }

    public static RenderType getLightRingTextured(ResourceLocation texture) {
        return LIGHT_RING_TEXTURED.apply(texture);
    }
}
