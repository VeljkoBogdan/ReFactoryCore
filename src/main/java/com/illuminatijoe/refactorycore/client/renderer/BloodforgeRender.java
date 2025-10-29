package com.illuminatijoe.refactorycore.client.renderer;

import com.illuminatijoe.refactorycore.ReFactoryCore;

import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;
import com.gregtechceu.gtceu.client.util.ModelUtils;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
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
    private static TextureAtlasSprite bloodBallSprite = null;
    private static boolean isEventListenerRegistered = false;

    public static final BloodforgeRender INSTANCE = new BloodforgeRender();
    public static final Codec<BloodforgeRender> CODEC = Codec.unit(INSTANCE);
    private static DynamicRenderType<WorkableElectricMultiblockMachine, BloodforgeRender> TYPE;

    public static DynamicRenderType<WorkableElectricMultiblockMachine, BloodforgeRender> getRenderType() {
        if (TYPE == null) TYPE = new DynamicRenderType<>(Codec.unit(new BloodforgeRender()));
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

    @SuppressWarnings("deprecation")
    public BloodforgeRender() {
        if (!isEventListenerRegistered) {
            ModelUtils.registerAtlasStitchedEventListener(true, TextureAtlas.LOCATION_BLOCKS, event -> {
                bloodBallSprite = event.getAtlas().getSprite(BLOOD_BALL_TEXTURE);
            });
            isEventListenerRegistered = true;
        }
    }

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

        float totalTick = (Minecraft.getInstance().player.tickCount + partialTick);

        poseStack.pushPose();

        Direction front = machine.getFrontFacing();
        Direction upwards = machine.getUpwardsFacing();
        boolean flipped = machine.isFlipped();
        Direction up = RelativeDirection.UP.getRelative(front, upwards, flipped);
        Direction back = RelativeDirection.BACK.getRelative(front, upwards, flipped);
        Direction.Axis leftAxis = RelativeDirection.LEFT.getRelative(front, upwards, flipped).getAxis();

        float x0ffset = 0, y0ffset = 0, z0ffset = 0;
        for (Direction.Axis axis : Direction.Axis.VALUES) {
            int upOffset = up.getNormal().get(axis);
            int backOffset = back.getNormal().get(axis);

            float offset = upOffset * (2.0f + (upOffset * 0.5f)) +
                    backOffset * (4.0f + (backOffset * 0.5f));
            switch (axis) {
                case X -> x0ffset = offset;
                case Y -> y0ffset = offset;
                case Z -> z0ffset = offset;
            }
        }
        poseStack.translate(
                x0ffset + (leftAxis == Direction.Axis.X ? 0.5f : 0.0f),
                y0ffset + (leftAxis == Direction.Axis.Y ? 0.5f : 0.0f),
                z0ffset + (leftAxis == Direction.Axis.Z ? 0.5f : 0.0f));

        renderBloodBall(poseStack, buffer, totalTick);

        poseStack.popPose();
    }

    @OnlyIn(Dist.CLIENT)
    public void renderBloodBall(PoseStack poseStack, MultiBufferSource bufferSource, float totalTick) {
        poseStack.pushPose();

        float trembleAmplitude = 0.04f;
        float trembleSpeed = 4f;
        float trembleModSpeed = 0.2f;
        float variableTremble = 1f +
                trembleAmplitude * Mth.sin(totalTick * trembleSpeed) * Mth.sin(totalTick * trembleModSpeed);

        float trembleBaseSpeed = 1f;
        float baseTremble = 1f + Mth.sin(totalTick * trembleBaseSpeed) * trembleAmplitude;

        Quaternionf rot = new Quaternionf()
                .scale(1 + Mth.sin(totalTick / 20) / 5)
                .scale(variableTremble)
                .scale(baseTremble)
                .rotateXYZ(totalTick / 20,
                        Mth.sin(totalTick / 20),
                        Mth.cos(Mth.HALF_PI + totalTick / 40));

        poseStack.mulPose(rot);

        ReFactoryRenderBufferHelper.renderSolidSphere(poseStack, bufferSource, bloodBallSprite,
                0f, 0f, 0f, 1f,
                16, 8,
                0.5f, 0.03f, 0.03f, 1.0f);

        poseStack.popPose();
    }
}
