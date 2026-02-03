package com.illuminatijoe.refactorycore.machines.multiblock.other;

import com.illuminatijoe.refactorycore.ReFactoryCore;
import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes;

import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.models.GTMachineModels;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

import earth.terrarium.adastra.common.registry.ModBlocks;

import java.util.List;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.illuminatijoe.refactorycore.api.ReFactoryRegistries.REGISTRATE;

public class CoolingTower {

    public static final MachineDefinition COOLING_TOWER = REGISTRATE
            .multiblock("cooling_tower", CoolingTowerMultiblockMachine::new)
            .tooltips(List.of(
                    Component.translatable("tooltip.refactorycore.cooling_tower")))
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(ReFactoryCoreRecipeTypes.COOLING_TOWER)
            .partAppearance(((iMultiController, iMultiPart, direction) -> Blocks.WHITE_CONCRETE.defaultBlockState()))
            .pattern(definition -> FactoryBlockPattern.start()
                    // spotless:off
                    .aisle("aaaaabababaaaaa", "aaaaabababaaaaa", "aaaaacccccaaaaa", "aaaaacccccaaaaa", "aaaaacccccaaaaa", "aaaaacccccaaaaa", "aaaaaacccaaaaaa", "aaaaaaacaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa")
                    .aisle("aaabaaaaaaabaaa", "aaabaaaaaaabaaa", "aaacccccccccaaa", "aaaccaaaaaccaaa", "aaaccbbbbbccaaa", "aaaccaaaaaccaaa", "aaaaccaaaccaaaa", "aaaaaccaccaaaaa", "aaaaaacccaaaaaa", "aaaaaaacaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaacccccaaaaa")
                    .aisle("aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aacccccccccccaa", "aacaaaaaaaaacaa", "aacbbbbbbbbbcaa", "aacadadadadacaa", "aaccaaaaaaaccaa", "aaaccaaaaaccaaa", "aaaaccaaaccaaaa", "aaaaaccaccaaaaa", "aaaaacccccaaaaa", "aaaaaacccaaaaaa", "aaaaaaacaaaaaaa", "aaaaaaacaaaaaaa", "aaaaaaacaaaaaaa", "aaaaaacccaaaaaa", "aaaaacccccaaaaa", "aaaaacccccaaaaa", "aaaacccccccaaaa")
                    .aisle("abaaaaaaaaaaaba", "abaaaaaaaaaaaba", "accccccccccccca", "acaaaaaaaaaaaca", "acbbbbbbbbbbbca", "acaaaaaaaaaaaca", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aaacaaaaaaacaaa", "aaaccaaaaaccaaa", "aaaacaaaaacaaaa", "aaaaacaaacaaaaa", "aaaaacccccaaaaa", "aaaaacccccaaaaa", "aaaaacccccaaaaa", "aaaaacaaacaaaaa", "aaaacaaaaacaaaa", "aaacceeeeeccaaa", "aaaccaaaaaccaaa")
                    .aisle("aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "accccccccccccca", "acaaaaaaaaaaaca", "acbbbbbbbbbbbca", "acdadadadadadca", "acaaaaaaaaaaaca", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aaacaaaaaaacaaa", "aaacaaaaaaacaaa", "aaaacaaaaacaaaa", "aaaacaaaaacaaaa", "aaaacaaaaacaaaa", "aaaacaaaaacaaaa", "aaaacaaaaacaaaa", "aaacaaaaaaacaaa", "aaaceeeeeeecaaa", "aaccaaaaaaaccaa")
                    .aisle("baaaaaaaaaaaaab", "baaaaaaaaaaaaab", "ccccccccccccccc", "caaaaaaaaaaaaac", "cbbbbbbbbbbbbbc", "caaaaaaaaaaaaac", "acaaaaaaaaaaaca", "acaaaaaaaaaaaca", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aaacaaaaaaacaaa", "aaacaaaaaaacaaa", "aaacaaaaaaacaaa", "aaacaaaaaaacaaa", "aaacaaaaaaacaaa", "aacaaaaaaaaacaa", "aaceeeeeeeeecaa", "accaaaaaaaaacca")
                    .aisle("aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "ccccccccccccccc", "caaaaaaaaaaaaac", "cbbbbbbbbbbbbbc", "cadadadadadadac", "caaaaaaaaaaaaac", "acaaaaaaaaaaaca", "acaaaaaaaaaaaca", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aaacaaaaaaacaaa", "aaacaaaaaaacaaa", "aaacaaaaaaacaaa", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aaceeeeeeeeecaa", "accaaaaaaaaacca")
                    .aisle("baaaaaaaaaaaaab", "baaaaaaaaaaaaab", "cccccccfccccccc", "caaaaaaaaaaaaac", "cbbbbbbbbbbbbbc", "caaaaaaaaaaaaac", "caaaaaaaaaaaaac", "caaaaaaaaaaaaac", "acaaaaaaaaaaaca", "acaaaaaaaaaaaca", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aaccaaaaaaaccaa", "aaccaaaaaaaccaa", "aaccaaaaaaaccaa", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aaceeeeeeeeecaa", "accaaaaaaaaacca")
                    .aisle("aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "ccccccccccccccc", "caaaaaaaaaaaaac", "cbbbbbbbbbbbbbc", "cadadadadadadac", "caaaaaaaaaaaaac", "acaaaaaaaaaaaca", "acaaaaaaaaaaaca", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aaacaaaaaaacaaa", "aaacaaaaaaacaaa", "aaacaaaaaaacaaa", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aaceeeeeeeeecaa", "accaaaaaaaaacca")
                    .aisle("baaaaaaaaaaaaab", "baaaaaaaaaaaaab", "ccccccccccccccc", "caaaaaaaaaaaaac", "cbbbbbbbbbbbbbc", "caaaaaaaaaaaaac", "acaaaaaaaaaaaca", "acaaaaaaaaaaaca", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aaacaaaaaaacaaa", "aaacaaaaaaacaaa", "aaacaaaaaaacaaa", "aaacaaaaaaacaaa", "aaacaaaaaaacaaa", "aacaaaaaaaaacaa", "aaceeeeeeeeecaa", "accaaaaaaaaacca")
                    .aisle("aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "accccccccccccca", "acaaaaaaaaaaaca", "acbbbbbbbbbbbca", "acdadadadadadca", "acaaaaaaaaaaaca", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aaacaaaaaaacaaa", "aaacaaaaaaacaaa", "aaaacaaaaacaaaa", "aaaacaaaaacaaaa", "aaaacaaaaacaaaa", "aaaacaaaaacaaaa", "aaaacaaaaacaaaa", "aaacaaaaaaacaaa", "aaaceeeeeeecaaa", "aaccaaaaaaaccaa")
                    .aisle("abaaaaaaaaaaaba", "abaaaaaaaaaaaba", "accccccccccccca", "acaaaaaaaaaaaca", "acbbbbbbbbbbbca", "acaaaaaaaaaaaca", "aacaaaaaaaaacaa", "aacaaaaaaaaacaa", "aaacaaaaaaacaaa", "aaaccaaaaaccaaa", "aaaacaaaaacaaaa", "aaaaacaaacaaaaa", "aaaaacccccaaaaa", "aaaaacccccaaaaa", "aaaaacccccaaaaa", "aaaaacaaacaaaaa", "aaaacaaaaacaaaa", "aaacceeeeeccaaa", "aaaccaaaaaccaaa")
                    .aisle("aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aacccccccccccaa", "aacaaaaaaaaacaa", "aacbbbbbbbbbcaa", "aacadadadadacaa", "aaccaaaaaaaccaa", "aaaccaaaaaccaaa", "aaaaccaaaccaaaa", "aaaaaccaccaaaaa", "aaaaacccccaaaaa", "aaaaaacccaaaaaa", "aaaaaaacaaaaaaa", "aaaaaaacaaaaaaa", "aaaaaaacaaaaaaa", "aaaaaacccaaaaaa", "aaaaacccccaaaaa", "aaaaacccccaaaaa", "aaaacccccccaaaa")
                    .aisle("aaabaaaaaaabaaa", "aaabaaaaaaabaaa", "aaacccccccccaaa", "aaaccaaaaaccaaa", "aaaccbbbbbccaaa", "aaaccaaaaaccaaa", "aaaaccaaaccaaaa", "aaaaaccaccaaaaa", "aaaaaacccaaaaaa", "aaaaaaacaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaacccccaaaaa")
                    .aisle("aaaaabababaaaaa", "aaaaabababaaaaa", "aaaaacccccaaaaa", "aaaaacccccaaaaa", "aaaaacccccaaaaa", "aaaaacccccaaaaa", "aaaaaacccaaaaaa", "aaaaaaacaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa")
                    // spotless:on
                    .where("a", any())
                    .where("b", blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, GTMaterials.Steel)))
                    .where("c", blocks(Blocks.WHITE_CONCRETE)
                            .or(ability(PartAbility.MAINTENANCE).setExactLimit(1).setPreviewCount(1))
                            .or(autoAbilities(ReFactoryCoreRecipeTypes.COOLING_TOWER)))
                    .where("d", blocks(GTBlocks.CASING_STEEL_PIPE.get()))
                    .where("e", blocks(ModBlocks.VENT.get()))
                    .where("f", controller(blocks(definition.get())))
                    .build())
            .model(GTMachineModels.createWorkableCasingMachineModel(
                    new ResourceLocation("minecraft:block/white_concrete"),
                    ReFactoryCore.id("block/multiblock/cooling_tower")))
            .register();

    public static void init() {}
}
