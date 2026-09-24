package com.illuminatijoe.refactorycore.data;

import com.illuminatijoe.refactorycore.ReFactoryCore;

import com.gregtechceu.gtceu.common.data.models.GTModels;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import java.util.function.Supplier;

import static com.illuminatijoe.refactorycore.api.ReFactoryRegistries.REGISTRATE;

public class ReFactoryBlocks {

    // public static final BlockEntry<Block> MANA_BURNER_CASING_STAINLESS_STEEL = createCasingBlock(
    // "clean_mana_burner_casing",
    // ReFactoryCore.id("block/casings/clean/clean_mana_burner_casing"));
    // public static final BlockEntry<Block> MANA_BURNER_CASING_TITANIUM = createCasingBlock(
    // "stable_mana_burner_casing",
    // ReFactoryCore.id("block/casings/stable/stable_mana_burner_casing"));
    // public static final BlockEntry<Block> MANA_BURNER_CASING_TUNGSTENSTEEL = createCasingBlock(
    // "robust_mana_burner_casing",
    // ReFactoryCore.id("block/casings/robust/robust_mana_burner_casing"));
    // public static final BlockEntry<Block> MANA_BURNER_CASING_PALLADIUM_RHODIUM = createCasingBlock(
    // "vigorous_mana_burner_casing",
    // ReFactoryCore.id("block/casings/vigorous/vigorous_mana_burner_casing"));
    public static final BlockEntry<Block> NUCLEAR_CLADDING = createCasingBlock(
            "nuclear_cladding",
            ReFactoryCore.id("block/casings/nuclear/nuclear_cladding"));
    public static final BlockEntry<Block> CONTROL_ROD_CASING = createCasingBlock(
            "control_rod_casing",
            ReFactoryCore.id("block/casings/nuclear/control_rod_casing"));
    public static final BlockEntry<Block> HIGH_TEMPERATURE_SPACE_CASING = createCasingBlock(
            "high_temperature_space_casing",
            ReFactoryCore.id("block/casings/space/high_temperature_space_casing"));
    public static final BlockEntry<Block> CARBON_FIBER_REINFORCED_AEROGEL_PANEL = createCasingBlock(
            "carbon_fiber_reinforced_aerogel_panel",
            ReFactoryCore.id("block/casings/space/carbon_fiber_reinforced_aerogel_panel"));

    // Bloodforge
    public static final BlockEntry<Block> INFERNAL_CASING = createCasingBlock(
            "infernal_casing", ReFactoryCore.id("block/casings/blood/infernal_casing"));

    public static void register() {}

    public static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, Block::new, texture, () -> Blocks.IRON_BLOCK,
                () -> RenderType::solid);
    }

    public static BlockEntry<Block> createCasingBlock(String name,
                                                      NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                      ResourceLocation texture,
                                                      NonNullSupplier<? extends Block> properties,
                                                      Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, blockSupplier)
                .initialProperties(properties)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .addLayer(type)
                .exBlockstate(GTModels.cubeAllModel(texture))
                .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
                .item(BlockItem::new)
                .build()
                .register();
    }
}
