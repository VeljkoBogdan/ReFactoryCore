package com.illuminatijoe.refactorycore.client.renderer;

import com.illuminatijoe.refactorycore.ReFactoryCore;

import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import org.joml.Quaternionf;

import java.util.function.BiFunction;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BloodforgeRender extends DynamicRender<WorkableElectricMultiblockMachine, BloodforgeRender> {

    public static final ResourceLocation BLOOD_BALL_TEXTURE = ReFactoryCore.id("block/blood");

    public static final BloodforgeRender INSTANCE = new BloodforgeRender();
    public static final Codec<BloodforgeRender> CODEC = Codec.unit(INSTANCE);
    public static final DynamicRenderType<WorkableElectricMultiblockMachine, BloodforgeRender> TYPE = new DynamicRenderType<>(
            CODEC);

    private static long baseTick = Long.MIN_VALUE;

    public static DynamicRenderType<WorkableElectricMultiblockMachine, BloodforgeRender> getRenderType() {
        return TYPE;
    }

    private static final BiFunction<Direction, Direction, AABB> renderBoundCache = Util.memoize((front, upwards) -> {
        Direction up = RelativeDirection.UP.getRelative(front, upwards, false);
        Direction back = RelativeDirection.BACK.getRelative(front, upwards, false);
        Direction left = RelativeDirection.LEFT.getRelative(front, upwards, false);
        Direction down = RelativeDirection.DOWN.getRelative(front, upwards, false);

        BlockPos.MutableBlockPos minPos = new BlockPos.MutableBlockPos().move(left, 3).move(down, 1).move(back, 1);
        BlockPos.MutableBlockPos maxPos = new BlockPos.MutableBlockPos().move(left, -3).move(up, 5).move(back, 7);

        return new AABB(minPos, maxPos);
    });

    @Override
    public DynamicRenderType<WorkableElectricMultiblockMachine, BloodforgeRender> getType() {
        return TYPE;
    }

    @Override
    public int getViewDistance() {
        return 256;
    }

    @Override
    public AABB getRenderBoundingBox(WorkableElectricMultiblockMachine multi) {
        if (multi.isFormed()) {
            AABB bounds = renderBoundCache.apply(multi.getFrontFacing(), multi.getUpwardsFacing());
            return bounds.move(multi.getPos());
        }
        return super.getRenderBoundingBox(multi);
    }

    @Override
    public void render(WorkableElectricMultiblockMachine machine, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (!machine.isFormed()) return;
        Level level = machine.getLevel();
        if (level == null) return;

        long gameTime = level.getGameTime();
        if (baseTick == Long.MIN_VALUE) baseTick = gameTime;

        double worldTime = gameTime + (double) partialTick;
        float t = (float) (gameTime - baseTick) + partialTick;

        int period = machine.getRecipeLogic().isWorking() ? 16 : 34;
        float beat = heartbeat((float) ((worldTime % period) / period));

        poseStack.pushPose();

        Direction front = machine.getFrontFacing();
        Direction upwards = machine.getUpwardsFacing();
        boolean flipped = machine.isFlipped();
        Direction up = RelativeDirection.UP.getRelative(front, upwards, flipped);
        Direction back = RelativeDirection.BACK.getRelative(front, upwards, flipped);

        Vec3i u = up.getNormal(), b = back.getNormal();
        poseStack.translate(0.5 + 5 * u.getX() + 2 * b.getX(),
                0.5 + 5 * u.getY() + 2 * b.getY(),
                0.5 + 5 * u.getZ() + 2 * b.getZ());

        renderBloodBall(poseStack, buffer, t, beat);

        poseStack.popPose();
    }

    @OnlyIn(Dist.CLIENT)
    public void renderBloodBall(PoseStack poseStack, MultiBufferSource bufferSource, float t, float beat) {
        poseStack.pushPose();

        float trembleAmplitude = 0.04f;
        float trembleSpeed = 4f;
        float trembleModSpeed = 0.2f;
        float variableTremble = 1f +
                trembleAmplitude * Mth.sin(t * trembleSpeed) * Mth.sin(t * trembleModSpeed);

        float trembleBaseSpeed = 1f;
        float baseTremble = 1f + Mth.sin(t * trembleBaseSpeed) * trembleAmplitude;

        float breathing = 1f + Mth.sin(t / 20) / 10;
        float s = breathing * (1f + 0.18f * beat) * variableTremble * baseTremble;
        poseStack.scale(s, s, s);
        poseStack.mulPose(new Quaternionf().rotateXYZ(t / 20, Mth.sin(t / 20),
                Mth.cos(Mth.HALF_PI + t / 40)));

        TextureAtlasSprite sprite = Minecraft.getInstance()
                .getTextureAtlas(TextureAtlas.LOCATION_BLOCKS)
                .apply(BLOOD_BALL_TEXTURE);

        ReFactoryRenderBufferHelper.renderBloodSphere(poseStack, bufferSource, sprite,
                1.25f, t, beat,
                0.7f, 0.04f, 0.04f, 1.0f);

        poseStack.popPose();
    }

    private static float gauss(float x, float mean, float sigma) {
        float d = (x - mean) / sigma;
        return (float) Math.exp(-d * d);
    }

    private static float heartbeat(float phase) {
        return gauss(phase, 0.08f, 0.045f) + 0.6f * gauss(phase, 0.28f, 0.05f);
    }
}
