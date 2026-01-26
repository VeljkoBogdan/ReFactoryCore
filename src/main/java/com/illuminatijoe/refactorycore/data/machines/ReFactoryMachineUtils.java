package com.illuminatijoe.refactorycore.data.machines;

import com.gregtechceu.gtceu.api.GTValues;
import com.illuminatijoe.refactorycore.machines.multiblock.generator.LargeManaBurnerMachine;

import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;

public class ReFactoryMachineUtils {

    public static MultiblockMachineDefinition registerManaBurner(
                                                                 GTRegistrate registrate,
                                                                 String name,
                                                                 int tier,
                                                                 GTRecipeType recipeType,
                                                                 Supplier<? extends Block> casing,
                                                                 Supplier<? extends Block> gear,
                                                                 ResourceLocation casingTexture,
                                                                 ResourceLocation overlayModel) {
        return registrate.multiblock(name, holder -> new LargeManaBurnerMachine(holder, tier))
                .rotationState(RotationState.ALL)
                .recipeType(recipeType)
                .generator(true)
                .recipeModifier(LargeManaBurnerMachine::recipeModifier, true)
                .appearanceBlock(casing)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("AAA", "AAA", "AAA", "AAA", "AAA")
                        .aisle("AAA", "ABA", "ABA", "ABA", "ACA")
                        .aisle("AAA", "AOA", "AAA", "AAA", "AAA")
                        .where("O", controller(blocks(definition.get())))
                        .where("B", blocks(gear.get()))
                        .where("C", ability(PartAbility.MUFFLER))
                        .where("A", blocks(casing.get()).setMinGlobalLimited(20)
                                .or(autoAbilities(definition.getRecipeTypes(), false, false,
                                        true, true, true, true))
                                .or(ability(PartAbility.OUTPUT_ENERGY,
                                        IntStream.of(MV, HV, EV, IV, LuV, ZPM, UV, UHV)
                                                .filter(t -> t >= tier)
                                                .toArray())
                                        .addTooltips(Component.translatable("gtceu.multiblock.pattern.error.limited.1",
                                                GTValues.VN[tier]))
                                        .setExactLimit(1)))
                        .build())
                .workableCasingModel(casingTexture, overlayModel)
                .tooltips(
                        Component.translatable("gtceu.universal.tooltip.base_production_eut", V[tier] * 2),
                        Component.translatable("refactorycore.multiblock.mana_burner.efficiency_tooltip", VNF[tier]))
                .register();
    }
}
