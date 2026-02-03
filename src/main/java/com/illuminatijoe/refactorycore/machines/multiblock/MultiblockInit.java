package com.illuminatijoe.refactorycore.machines.multiblock;

import com.illuminatijoe.refactorycore.machines.multiblock.electric.AuraAltar;
import com.illuminatijoe.refactorycore.machines.multiblock.electric.Bloodforge;
import com.illuminatijoe.refactorycore.machines.multiblock.generator.ManaBurners;
import com.illuminatijoe.refactorycore.machines.multiblock.nuclear.NuclearReactor;
import com.illuminatijoe.refactorycore.machines.multiblock.other.CoolingTower;
import com.illuminatijoe.refactorycore.machines.single.FakeNuclearReactor;

public class MultiblockInit {

    public static void init() {
        Bloodforge.init();
        AuraAltar.init();
        ManaBurners.init();
        NuclearReactor.init();
        CoolingTower.init();

        // dummy machines
        FakeNuclearReactor.init();
    }
}
