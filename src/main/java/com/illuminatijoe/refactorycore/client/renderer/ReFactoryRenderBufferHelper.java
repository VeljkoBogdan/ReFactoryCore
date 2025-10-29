package com.illuminatijoe.refactorycore.client.renderer;

import com.gregtechceu.gtceu.client.util.RenderBufferHelper;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

@OnlyIn(Dist.CLIENT)
public class ReFactoryRenderBufferHelper {

    public static void renderSolidSphere(PoseStack poseStack, MultiBufferSource buffer, TextureAtlasSprite sprite,
                                         float cx, float cy, float cz,
                                         float radius, int slices, int stacks,
                                         float r, float g, float b, float a) {
        float uMin = sprite.getU0(), uMax = sprite.getU1(), vMin = sprite.getV0(), vMax = sprite.getV1();

        VertexConsumer vc = buffer.getBuffer(ReFactoryRenderTypes.getLightRingTextured(sprite.atlasLocation()));

        float dPhi = (Mth.TWO_PI / Math.max(3, slices));
        float dTheta = (float) (Math.PI / Math.max(2, stacks));

        // pack color from floats (assume r,g,b,a are 0..1)
        int ai = (int) (Mth.clamp(a, 0f, 1f) * 255.0F) & 0xFF;
        int ri = (int) (Mth.clamp(r, 0f, 1f) * 255.0F) & 0xFF;
        int gi = (int) (Mth.clamp(g, 0f, 1f) * 255.0F) & 0xFF;
        int bi = (int) (Mth.clamp(b, 0f, 1f) * 255.0F) & 0xFF;
        int color = (ai << 24) | (ri << 16) | (gi << 8) | bi;

        int lightmapUV = 0x00F000F0;

        for (int i = 0; i < stacks; i++) {
            float th0 = i * dTheta;
            float th1 = (i + 1) * dTheta;
            float sin0 = Mth.sin(th0), cos0 = Mth.cos(th0);
            float sin1 = Mth.sin(th1), cos1 = Mth.cos(th1);

            for (int j = 0; j <= slices; j++) {
                float ph = j * dPhi;
                float cosp = Mth.cos(ph), sinp = Mth.sin(ph);

                // texture U based on azimuth
                float u = (float) j / slices;
                float texU = Mth.lerp(u, uMin, uMax);

                float x0 = cx + radius * sin0 * cosp;
                float y0 = cy + radius * cos0;
                float z0 = cz + radius * sin0 * sinp;

                float x1 = cx + radius * sin1 * cosp;
                float y1 = cy + radius * cos1;
                float z1 = cz + radius * sin1 * sinp;

                // texture V based on latitude
                float v0 = (float) i / stacks;
                float v1 = (float) (i + 1) / stacks;
                float texV0 = Mth.lerp(v0, vMin, vMax);
                float texV1 = Mth.lerp(v1, vMin, vMax);

                // normals (sphere centered at cx,cy,cz)
                float nx0 = (x0 - cx) / radius;
                float ny0 = (y0 - cy) / radius;
                float nz0 = (z0 - cz) / radius;
                float nx1 = (x1 - cx) / radius;
                float ny1 = (y1 - cy) / radius;
                float nz1 = (z1 - cz) / radius;

                // write vertices
                RenderBufferHelper.vertex(vc, poseStack.last(), x0, y0, z0, color, texU, texV0, lightmapUV, nx0, ny0,
                        nz0);
                RenderBufferHelper.vertex(vc, poseStack.last(), x1, y1, z1, color, texU, texV1, lightmapUV, nx1, ny1,
                        nz1);
            }
        }
    }
}
