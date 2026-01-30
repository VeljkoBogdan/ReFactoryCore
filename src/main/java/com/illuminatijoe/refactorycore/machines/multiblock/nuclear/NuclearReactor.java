package com.illuminatijoe.refactorycore.machines.multiblock.nuclear;

import com.illuminatijoe.refactorycore.data.ReFactoryBlocks;
import com.illuminatijoe.refactorycore.data.materials.CoolantMaterials;
import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.models.GTMachineModels;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import java.util.ArrayList;
import java.util.List;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.illuminatijoe.refactorycore.api.ReFactoryRegistries.REGISTRATE;

public class NuclearReactor {

    public static final MachineDefinition NUCLEAR_REACTOR = REGISTRATE
            .multiblock("nuclear_reactor", NuclearReactorMultiblockMachine::new)
            .tooltips(buildTooltipList())
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(ReFactoryCoreRecipeTypes.NUCLEAR_REACTOR)
            .partAppearance(
                    (iMultiController, iMultiPart, direction) -> GTBlocks.CASING_STEEL_TURBINE.getDefaultState())
            .appearanceBlock(GTBlocks.CASING_STEEL_TURBINE)
            .recipeModifiers(NuclearReactorMultiblockMachine::recipeModifier)
            .pattern(definition -> FactoryBlockPattern.start()
                    // spotless:off
                    .aisle("aabbbaaaaaaa", "aabbbaaaaaaa", "aabbbaaaaaaa", "aabbbaaaaaaa", "aabbbaaaaaaa", "aaabaaaaaaaa", "aaaaaaaaaaaa", "aaaaaaaaaaaa", "aaaaaaaaaaaa", "aaaaaaaaaaaa")
                    .aisle("abbbbbaaaaaa", "abdedbaaaaaa", "abdedbaaaaaa", "abdedbaaaaaa", "abdedbaaaaaa", "abbbbbaaaaaa", "aabbbaaaaaaa", "aaaaaaaaaaaa", "aaaaaaaaaaaa", "aaaaaaaaaaaa")
                    .aisle("bbbbbbbaafff", "bdededbaafff", "bdededbaafff", "bdededbaaaaa", "bdededbaaaaa", "abbbbbaaaaaa", "abgggbaaaaaa", "aabbbaaaaaaa", "aaabaaaaaaaa", "aaabaaaaaaaa")
                    .aisle("bbbbbbbaafff", "bededehhihhf", "bededebaafhf", "bededebaaaha", "bededehihhha", "bbbbbbbaaaaa", "abgagbaaaaaa", "aababaaaaaaa", "aababaaaaaaa", "aababaaaaaaa")
                    .aisle("bbbbbbbaafff", "bdededbaafff", "bdededbaafff", "bdededbaaaaa", "bdededbaaaaa", "abbbbbaaaaaa", "abgggbaaaaaa", "aabbbaaaaaaa", "aaabaaaaaaaa", "aaabaaaaaaaa")
                    .aisle("abbbbbaaaaaa", "abdedbaaaaaa", "abdedbaaaaaa", "abdedbaaaaaa", "abdedbaaaaaa", "abbbbbaaaaaa", "aabbbaaaaaaa", "aaaaaaaaaaaa", "aaaaaaaaaaaa", "aaaaaaaaaaaa")
                    .aisle("aabbbaaaaaaa", "aabcbaaaaaaa", "aabbbaaaaaaa", "aabbbaaaaaaa", "aabbbaaaaaaa", "aaabaaaaaaaa", "aaaaaaaaaaaa", "aaaaaaaaaaaa", "aaaaaaaaaaaa", "aaaaaaaaaaaa")
                    // spotless:on
                    .where("a", any())
                    .where("b", blocks(ReFactoryBlocks.NUCLEAR_CLADDING.get()))
                    .where("c", controller(blocks(definition.get())))
                    .where("d", blocks(GTBlocks.CASING_STEEL_PIPE.get()))
                    .where("e", blocks(ReFactoryBlocks.CONTROL_ROD_CASING.get()))
                    .where("f", blocks(GTBlocks.CASING_STEEL_TURBINE.get())
                            .or(ability(PartAbility.OUTPUT_ENERGY).setMaxGlobalLimited(2).setMinGlobalLimited(1)
                                    .setPreviewCount(1))
                            .or(ability(PartAbility.IMPORT_ITEMS).setMaxGlobalLimited(2).setPreviewCount(1))
                            .or(ability(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(2).setPreviewCount(1))
                            .or(ability(PartAbility.IMPORT_FLUIDS).setMaxGlobalLimited(2).setPreviewCount(1))
                            .or(ability(PartAbility.EXPORT_FLUIDS).setMaxGlobalLimited(2).setPreviewCount(1))
                            .or(ability(PartAbility.MAINTENANCE).setExactLimit(1).setPreviewCount(1)))
                    .where("g", blocks(GTBlocks.CASING_INVAR_HEATPROOF.get()))
                    .where("h", blocks(GTBlocks.CASING_TITANIUM_PIPE.get()))
                    .where("i", blocks(GTBlocks.CASING_ENGINE_INTAKE.get()))
                    .build())
            .model(GTMachineModels.createWorkableCasingMachineModel(
                    GTCEu.id("block/casings/mechanic/machine_casing_turbine_steel"),
                    GTCEu.id("block/multiblock/power_substation")))
            .register();

    public static void init() {}

    private static List<Component> buildTooltipList() {
        List<Component> list = new ArrayList<>(List.of(
                Component.translatable("tooltip.refactorycore.nuclear_reactor.0")
                        .withStyle(ChatFormatting.YELLOW),
                Component.translatable("tooltip.refactorycore.nuclear_reactor.1")
                        .withStyle(ChatFormatting.GRAY),
                Component.translatable("tooltip.refactorycore.nuclear_reactor.2")
                        .withStyle(ChatFormatting.GRAY),
                Component.translatable("tooltip.refactorycore.nuclear_reactor.3",
                        Component.literal("amount = ((heat / 100) * coolant conversion rate)")
                                .withStyle(Style.EMPTY.applyFormats(ChatFormatting.WHITE))),
                Component.translatable("tooltip.refactorycore.nuclear_reactor.4")
                        .withStyle(ChatFormatting.GRAY)));

        for (var coolant : CoolantMaterials.getMaterials()) {
            var comp = Component.translatable("refactorycore.misc.nuclear_coolant_list",
                    coolant.input.getLocalizedName(), coolant.consumeAmount);

            list.add(comp);
        }

        return list;
    }
}
