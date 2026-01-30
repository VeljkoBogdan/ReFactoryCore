package com.illuminatijoe.refactorycore.machines.multiblock;

import com.illuminatijoe.refactorycore.machines.multiblock.nuclear.NuclearReactor;
import com.illuminatijoe.refactorycore.machines.single.FakeNuclearReactor;

public class MultiblockInit {

    public static void init() {
        Bloodforge.init();
        AuraAltar.init();
        ManaBurners.init();
        NuclearReactor.init();

        // dummy machines
        FakeNuclearReactor.init();
    }
}
