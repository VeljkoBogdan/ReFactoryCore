package com.illuminatijoe.refactorycore.machines.multiblock.other;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;

import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class CoolingTowerMultiblockMachine extends WorkableElectricMultiblockMachine {

    private final List<BlockPos> vents = new ArrayList<>();

    public CoolingTowerMultiblockMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    private void buildClientVents() {
        vents.clear();

        var pos = this.getPos();
        int yPos = pos.getY() + 15;

        for (int pz = -4; pz <= 4; pz++) {
            for (int px = -4; px <= 4; px++) {
                if (Vector2i.distance(0, 0, px, pz) <= 4.5f)
                    vents.add(new BlockPos(pos.getX() + px, yPos, pos.getZ() + pz));
            }
        }
    }

    @Override
    public void clientTick() {
        super.clientTick();

        if (!isActive()) return;

        if (vents.isEmpty()) {
            buildClientVents();
            if (vents.isEmpty()) return;
        }

        for (var vent : vents) {
            if (GTValues.RNG.nextInt() % 2 == 0) continue;
            float xPos = vent.getX() + 0.5F;
            float yPos = vent.getY() + 0.25F;
            float zPos = vent.getZ() + 0.5F;

            float ySpd = 0.5F * GTValues.RNG.nextFloat();
            float zSpd = 0.1F * GTValues.RNG.nextFloat();
            getLevel().addParticle(ParticleTypes.CLOUD, xPos, yPos + 2, zPos, 0, ySpd, zSpd);
        }
    }
}
