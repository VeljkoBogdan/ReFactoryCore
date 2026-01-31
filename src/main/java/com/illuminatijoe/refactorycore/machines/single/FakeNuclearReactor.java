package com.illuminatijoe.refactorycore.machines.single;

import com.illuminatijoe.refactorycore.data.recipes.FakeRecipeTypes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;

import static com.illuminatijoe.refactorycore.api.ReFactoryRegistries.REGISTRATE;

public class FakeNuclearReactor {

    public static final MachineDefinition[] FAKE_NUCLEAR_REACTOR = GTMachineUtils.registerSimpleMachines(REGISTRATE,
            "fake_nuclear_reactor", FakeRecipeTypes.FAKE_NUCLEAR_COOLING, GTMachineUtils.hvCappedTankSizeFunction,
            false, GTValues.MAX);

    public static void init() {}
}
