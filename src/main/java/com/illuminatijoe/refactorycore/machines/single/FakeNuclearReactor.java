package com.illuminatijoe.refactorycore.machines.single;

import com.illuminatijoe.refactorycore.data.recipes.FakeRecipeTypes;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;

public class FakeNuclearReactor {

    public static final MachineDefinition[] FAKE_NUCLEAR_REACTOR = GTMachineUtils.registerSimpleMachines(
            "fake_nuclear_reactor", FakeRecipeTypes.FAKE_NUCLEAR_COOLING);

    public static void init() {}
}
