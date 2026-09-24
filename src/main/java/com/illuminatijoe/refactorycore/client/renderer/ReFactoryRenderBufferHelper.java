package com.illuminatijoe.refactorycore.client.renderer;

import com.gregtechceu.gtceu.client.util.RenderBufferHelper;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix3f;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public class ReFactoryRenderBufferHelper {

    private static final int SLICES = 32;
    private static final int STACKS = 16;
    private static final int LIGHTMAP = 0x00F000F0;

    private static final float[] SIN_TH = new float[STACKS + 1];
    private static final float[] COS_TH = new float[STACKS + 1];
    private static final float[] SIN_PH = new float[SLICES];
    private static final float[] COS_PH = new float[SLICES];

    private static final float[] RADII = new float[(STACKS + 1) * SLICES];
    private static final Matrix3f NORMAL_T = new Matrix3f();
    private static final Vector3f LIGHT = new Vector3f();

    static {
        for (int i = 0; i <= STACKS; i++) {
            double th = Math.PI * i / STACKS;
            SIN_TH[i] = (float) Math.sin(th);
            COS_TH[i] = (float) Math.cos(th);
        }

        SIN_TH[0] = 0f;
        SIN_TH[STACKS] = 0f;

        for (int j = 0; j < SLICES; j++) {
            double ph = 2.0 * Math.PI * j / SLICES;
            SIN_PH[j] = (float) Math.sin(ph);
            COS_PH[j] = (float) Math.cos(ph);
        }
    }

    public static void renderBloodSphere(PoseStack poseStack, MultiBufferSource buffer, TextureAtlasSprite sprite,
                                         float radius, float time, float beat,
                                         float r, float g, float b, float a) {
        PoseStack.Pose pose = poseStack.last();
        VertexConsumer vc = buffer.getBuffer(ReFactoryRenderTypes.getLightRingTextured(sprite.atlasLocation()));

        float u0 = sprite.getU0(), u1 = sprite.getU1(), v0 = sprite.getV0(), v1 = sprite.getV1();

        pose.normal().transpose(NORMAL_T);
        LIGHT.set(0.4f, 0.8f, 0.4f);
        NORMAL_T.transform(LIGHT);
        LIGHT.normalize();

        float lumpPhase = time * 0.07f;
        float lumpDrift = time * 0.05f;
        float wavePhase = time * 0.4f;
        for (int i = 0; i <= STACKS; i++) {
            float th = Mth.PI * i / STACKS;
            float pulse = 0.06f * beat * Mth.sin(th * 6f - wavePhase);
            float lumpAmp = 0.05f * SIN_TH[i] * Mth.sin(th * 4f - lumpDrift);
            for (int j = 0; j < SLICES; j++) {
                float ph = Mth.TWO_PI * j / SLICES;
                RADII[i * SLICES + j] = radius * (1f + pulse + lumpAmp * Mth.sin(ph * 3f + lumpPhase));
            }
        }

        for (int i = 0; i < STACKS; i++) {
            for (int j = 0; j <= SLICES; j++) {
                emitVertex(vc, pose, i, j, radius, beat, u0, u1, v0, v1, r, g, b, a);
                emitVertex(vc, pose, i + 1, j, radius, beat, u0, u1, v0, v1, r, g, b, a);
            }
        }
    }

    private static void emitVertex(VertexConsumer vc, PoseStack.Pose pose, int i, int j,
                                   float radius, float beat,
                                   float u0, float u1, float v0, float v1,
                                   float r, float g, float b, float a) {
        int jj = j % SLICES;

        float nx = SIN_TH[i] * COS_PH[jj];
        float ny = COS_TH[i];
        float nz = SIN_TH[i] * SIN_PH[jj];

        float rad = RADII[i * SLICES + jj];

        float diffuse = Math.max(0f, nx * LIGHT.x + ny * LIGHT.y + nz * LIGHT.z);
        float occlusion = Mth.clamp(1f + 2.5f * (rad / radius - 1f), 0.6f, 1.3f);
        float shade = (0.45f + 0.55f * diffuse) * occlusion * (0.85f + 0.5f * beat);

        int color = packColor(r * shade, g * shade, b * shade, a);
        float u = Mth.lerp((float) j / SLICES, u0, u1);
        float v = Mth.lerp((float) i / STACKS, v0, v1);

        RenderBufferHelper.vertex(vc, pose, nx * rad, ny * rad, nz * rad, color, u, v, LIGHTMAP, nx, ny, nz);
    }

    private static int packColor(float r, float g, float b, float a) {
        int ai = (int) (Mth.clamp(a, 0f, 1f) * 255.0F) & 0xFF;
        int ri = (int) (Mth.clamp(r, 0f, 1f) * 255.0F) & 0xFF;
        int gi = (int) (Mth.clamp(g, 0f, 1f) * 255.0F) & 0xFF;
        int bi = (int) (Mth.clamp(b, 0f, 1f) * 255.0F) & 0xFF;
        return (ai << 24) | (ri << 16) | (gi << 8) | bi;
    }
}
