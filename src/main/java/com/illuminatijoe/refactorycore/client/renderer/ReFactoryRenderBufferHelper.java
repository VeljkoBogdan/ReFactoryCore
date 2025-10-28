package com.illuminatijoe.refactorycore.client.renderer;

import com.gregtechceu.gtceu.client.renderer.GTRenderTypes;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class ReFactoryRenderBufferHelper {

    public static void renderSolidSphere(PoseStack poseStack, MultiBufferSource buffer,
                                         float cx, float cy, float cz,
                                         float radius, int slices, int stacks,
                                         float r, float g, float b, float a) {
        Matrix4f mat = poseStack.last().pose();
        VertexConsumer vc = buffer.getBuffer(GTRenderTypes.getLightRing());

        float dPhi = (Mth.TWO_PI / Math.max(3, slices));
        float dTheta = (float) (Math.PI / Math.max(2, stacks));

        for (int i = 0; i < stacks; i++) {
            float th0 = i * dTheta;
            float th1 = (i + 1) * dTheta;
            float sin0 = Mth.sin(th0), cos0 = Mth.cos(th0);
            float sin1 = Mth.sin(th1), cos1 = Mth.cos(th1);

            // one triangle strip per latitude band; <= closes seam
            for (int j = 0; j <= slices; j++) {
                float ph = j * dPhi;
                float cosp = Mth.cos(ph), sinp = Mth.sin(ph);

                // band top (th0)
                float x0 = cx + radius * sin0 * cosp;
                float y0 = cy + radius * cos0;
                float z0 = cz + radius * sin0 * sinp;

                // band bottom (th1)
                float x1 = cx + radius * sin1 * cosp;
                float y1 = cy + radius * cos1;
                float z1 = cz + radius * sin1 * sinp;

                // order chosen for typical backface cull; swap if it looks inside-out
                vc.vertex(mat, x0, y0, z0).color(r, g, b, a).endVertex();
                vc.vertex(mat, x1, y1, z1).color(r, g, b, a).endVertex();
            }
        }
    }
}
