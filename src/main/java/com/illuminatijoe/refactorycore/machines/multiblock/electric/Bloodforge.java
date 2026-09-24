package com.illuminatijoe.refactorycore.machines.multiblock.electric;

import com.illuminatijoe.refactorycore.ReFactoryCore;
import com.illuminatijoe.refactorycore.client.renderer.ReFactoryRenderUtils;
import com.illuminatijoe.refactorycore.data.ReFactoryBlocks;
import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes;
import com.illuminatijoe.refactorycore.machines.part.LPHatchPartMachine;
import com.illuminatijoe.refactorycore.machines.part.ReFactoryPartAbilities;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.models.GTMachineModels;

import com.lowdragmc.lowdraglib.Platform;

import net.minecraft.network.chat.Component;

import wayoftime.bloodmagic.common.block.BloodMagicBlocks;

import static com.illuminatijoe.refactorycore.api.ReFactoryRegistries.REGISTRATE;

public class Bloodforge {

    public static final MachineDefinition BLOODFORGE = REGISTRATE
            .multiblock("bloodforge", WorkableElectricMultiblockMachine::new)
            .tooltips(Component.translatable("tooltip.gtceu.bloodforge.0"))
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(ReFactoryCoreRecipeTypes.BLOODFORGE)
            .partAppearance(
                    (c, p, d) -> {
                        if (p instanceof LPHatchPartMachine)
                            return BloodMagicBlocks.BLANK_RUNE.get().defaultBlockState();
                        return GTBlocks.CASING_TITANIUM_STABLE.getDefaultState();
                    })
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH,
                    GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.PERFECT_OVERCLOCK),
                    GTRecipeModifiers.BATCH_MODE)
            .pattern(definition -> FactoryBlockPattern.start()
                    // spotless:off
                    .aisle("aaaaaaa", "aaaaaaa", "aaaaaaa", "aaaaaaa", "aaahaaa", "aaagaaa", "aaafaaa")
                    .aisle("abbcbba", "adbbbda", "aadbdaa", "aaagaaa", "aaagaaa", "aaaaaaa", "aaaaaaa")
                    .aisle("acbbbca", "abaaaba", "aabbbaa", "aaagaaa", "aaaaaaa", "aaaaaaa", "aaaaaaa")
                    .aisle("acbbbca", "abaaaba", "aabbbaa", "aggggga", "hgaiagh", "gaaaaag", "faaaaaf")
                    .aisle("acbbbca", "abaaaba", "aabbbaa", "aaagaaa", "aaaaaaa", "aaaaaaa", "aaaaaaa")
                    .aisle("abbcbba", "adbebda", "aadbdaa", "aaaaaaa", "aaafaaa", "aaaaaaa", "aaaaaaa")
                    // spotless:on
                    .where("e", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("a", Predicates.any())
                    .where("h", Predicates.blocks(BloodMagicBlocks.SPEED_RUNE.get()))
                    .where("g", Predicates.blocks(BloodMagicBlocks.BLANK_RUNE.get()))
                    .where("f", Predicates.blocks(BloodMagicBlocks.SACRIFICE_RUNE.get()))
                    .where("c", Predicates.blocks(GTBlocks.FIREBOX_STEEL.get()))
                    .where("d", Predicates.blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, GTMaterials.Tungsten)))
                    .where("b", Predicates.blocks(ReFactoryBlocks.INFERNAL_CASING.get())
                            .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setExactLimit(1).setPreviewCount(1))
                            .or(Predicates.autoAbilities(ReFactoryCoreRecipeTypes.BLOODFORGE)))
                    .where("i", Predicates.abilities(ReFactoryPartAbilities.IMPORT_LP)
                            .setExactLimit(1)
                            .setPreviewCount(1))
                    .build())
            .model(GTMachineModels.createWorkableCasingMachineModel(
                    ReFactoryCore.id("block/casings/blood/infernal_casing"),
                    GTCEu.id("block/multiblock/power_substation"))
                    .andThen(modelBuilder -> {
                        if (Platform.isClient()) {
                            modelBuilder.addDynamicRenderer(ReFactoryRenderUtils::createBloodforgeRender);
                        }
                    }))
            .register();

    public static void init() {}
}
