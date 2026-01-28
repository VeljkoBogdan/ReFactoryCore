package com.illuminatijoe.refactorycore.machines.multiblock.electric;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDisplayUIMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.config.ConfigHolder;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class AuraAltarMultiblockMachine extends WorkableElectricMultiblockMachine implements IDisplayUIMachine {

    public AuraAltarMultiblockMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);

        if (isFormed) {

        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void clientTick() {
        super.clientTick();

        if (!recipeLogic.isWorking()) return;

        var level = getLevel();
        var pos = getPos();

        for (int i = 0; i < 2; i++) {
            double angle = GTValues.RNG.nextDouble() * Math.PI * 2;
            double radius = 0.6 + GTValues.RNG.nextDouble() * 0.3;

            double x = pos.getX() + 0.5 + Math.cos(angle) * radius;
            double y = pos.getY() + 0.4 + GTValues.RNG.nextDouble() * 0.8;
            double z = pos.getZ() + 0.5 + Math.sin(angle) * radius;

            double vx = (GTValues.RNG.nextDouble() - 0.5) * 0.05;
            double vy = 0.05 + GTValues.RNG.nextDouble() * 0.05;
            double vz = (GTValues.RNG.nextDouble() - 0.5) * 0.05;

            level.addParticle(ParticleTypes.ELECTRIC_SPARK, x, y, z, vx, vy, vz);
        }
    }

    @Override
    public void animateTick(RandomSource random) {
        if (!this.isActive()) return;

        var level = getLevel();
        var pos = getPos();

        double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.8;
        double y = pos.getY() + 1.5 + random.nextDouble();
        double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.8;

        double vx = (random.nextDouble() - 0.5) * 0.02;
        double vy = random.nextDouble() * 0.03;
        double vz = (random.nextDouble() - 0.5) * 0.02;

        level.addParticle(ParticleTypes.END_ROD, x, y, z, vx, vy, vz);

        if (ConfigHolder.INSTANCE.machines.machineSounds && random.nextDouble() < 0.08) {
            level.playLocalSound(
                    x, y, z,
                    SoundEvents.AMETHYST_BLOCK_CHIME,
                    SoundSource.BLOCKS,
                    0.6F,
                    1.4F + random.nextFloat() * 0.4F,
                    false);
        }
    }
}
