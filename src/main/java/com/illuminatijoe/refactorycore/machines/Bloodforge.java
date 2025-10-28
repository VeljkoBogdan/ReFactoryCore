package com.illuminatijoe.refactorycore.machines;

import com.illuminatijoe.refactorycore.client.renderer.ReFactoryRenderUtils;
import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes;
import com.illuminatijoe.refactorycore.machines.part.ReFactoryPartAbilities;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.common.data.GCYMBlocks;
import com.gregtechceu.gtceu.common.data.GTBlocks;
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
                    (iMultiController, iMultiPart, direction) -> GTBlocks.CASING_TITANIUM_STABLE.getDefaultState())
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH,
                    GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.PERFECT_OVERCLOCK),
                    GTRecipeModifiers.BATCH_MODE)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("C       C", "CC  A  CC", "    A    ", "    A    ", "    A    ", "         ", "         ",
                            "         ", "    A    ", "    A    ")
                    .aisle("    A    ", "C   A   C", " CCCCCCC ", "  C   C  ", "  C   C  ", "  C   C  ", " DDDDDDD ",
                            " CC A CC ", "    A    ", "         ")
                    .aisle("    A    ", "         ", " CC C CC ", " CR   RC ", " CR   RC ", " CRRDRRC ", " DR A RD ",
                            " CR A RC ", "         ", "         ")
                    .aisle("         ", "         ", " C CCC C ", "         ", "         ", "  R   R  ", " D     D ",
                            "    R    ", "         ", "         ")
                    .aisle(" AA   AA ", "AA     AA", "ACCCCCCCA", "A   S   A", "A       A", "  D   D  ", " DA   AD ",
                            " AARARAA ", "AA  A  AA", "A       A")
                    .aisle("         ", "         ", " C CCC C ", "         ", "         ", "  R   R  ", " D     D ",
                            "    R    ", "         ", "         ")
                    .aisle("    A    ", "         ", " CC C CC ", " CR   RC ", " CR   RC ", " CRRDRRC ", " DR A RD ",
                            " CR A RC ", "         ", "         ")
                    .aisle("    A    ", "C   A   C", " CCCCCCC ", "  C   C  ", "  C   C  ", "  C   C  ", " DDDDDDD ",
                            " CC A CC ", "    A    ", "         ")
                    .aisle("C       C", "CC  A  CC", "  AAAAA  ", "  A O A  ", "    A    ", "         ", "         ",
                            "         ", "    A    ", "    A    ")
                    .where('C', Predicates.blocks(BloodMagicBlocks.BLANK_RUNE.get())
                            .or(Predicates.abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setExactLimit(1))
                            .or(Predicates.autoAbilities(ReFactoryCoreRecipeTypes.BLOODFORGE)))
                    .where('R', Predicates.blocks(GTBlocks.COIL_RTMALLOY.get()))
                    .where('D', Predicates.blocks(GCYMBlocks.CASING_NONCONDUCTING.get()))
                    .where('O', Predicates.controller(Predicates.blocks(definition.get())))
                    .where(' ', Predicates.any())
                    .where('A', Predicates.blocks(GTBlocks.CASING_TITANIUM_STABLE.get()))
                    .where('S', Predicates.abilities(ReFactoryPartAbilities.IMPORT_LP).setExactLimit(1))
                    .build())
            .model(GTMachineModels.createWorkableCasingMachineModel(
                    GTCEu.id("block/casings/solid/machine_casing_stable_titanium"),
                    GTCEu.id("block/multiblock/power_substation"))
                    .andThen(modelBuilder -> {
                        if (Platform.isClient()) {
                            modelBuilder.addDynamicRenderer(ReFactoryRenderUtils::createBloodforgeRender);
                        }
                    }))
            .register();

    public static void init() {}
}
