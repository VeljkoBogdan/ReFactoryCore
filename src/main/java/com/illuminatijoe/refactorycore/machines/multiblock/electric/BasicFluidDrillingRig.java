package com.illuminatijoe.refactorycore.machines.multiblock.electric;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import net.minecraft.network.chat.Component;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.DUMMY_RECIPES;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.registerTieredMultis;
import static com.illuminatijoe.refactorycore.api.ReFactoryRegistries.REGISTRATE;

public class BasicFluidDrillingRig {

    public static final MultiblockMachineDefinition[] BASIC_FLUID_DRILLING_RIG = registerTieredMultis(REGISTRATE,
            "basic_fluid_drilling_rig", BasicFluidDrillMachine::new, (tier, builder) -> builder
                    .rotationState(RotationState.ALL)
                    .langValue("%s Fluid Drilling Rig %s".formatted(VLVH[tier], VLVT[tier]))
                    .recipeType(DUMMY_RECIPES)
                    .tooltips(
                            Component.translatable("gtceu.machine.fluid_drilling_rig.description"),
                            Component.translatable("gtceu.machine.fluid_drilling_rig.depletion",
                                    FormattingUtil.formatNumbers(100.0 / BasicFluidDrillMachine.getDepletionChance())),
                            Component.translatable("gtceu.universal.tooltip.energy_tier_range", GTValues.VNF[tier],
                                    GTValues.VNF[tier + 1]),
                            Component.translatable("gtceu.machine.fluid_drilling_rig.production",
                                    BasicFluidDrillMachine.getRigMultiplier(),
                                    FormattingUtil.formatNumbers(BasicFluidDrillMachine.getRigMultiplier() * 1.5)))
                    .appearanceBlock(BasicFluidDrillMachine::getCasingState)
                    .pattern((definition) -> FactoryBlockPattern.start()
                            .aisle("XXX", "#F#", "#F#", "#F#", "###", "###", "###")
                            .aisle("XXX", "FCF", "FCF", "FCF", "#F#", "#F#", "#F#")
                            .aisle("XSX", "#F#", "#F#", "#F#", "###", "###", "###")
                            .where('S', controller(blocks(definition.get())))
                            .where('X', blocks(BasicFluidDrillMachine.getCasingState()).setMinGlobalLimited(3)
                                    .or(abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1)
                                            .setMaxGlobalLimited(2))
                                    .or(abilities(PartAbility.EXPORT_FLUIDS).setMaxGlobalLimited(1)))
                            .where('C', blocks(BasicFluidDrillMachine.getCasingState()))
                            .where('F', blocks(BasicFluidDrillMachine.getFrameState()))
                            .where('#', any())
                            .build())
                    .workableCasingModel(BasicFluidDrillMachine.getBaseTexture(),
                            GTCEu.id("block/multiblock/fluid_drilling_rig"))
                    .register(),
            LV);

    public static void init() {}
}
