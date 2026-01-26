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

    public static final BlockEntry<Block> MANA_BURNER_CASING_STAINLESS_STEEL = createCasingBlock(
            "clean_mana_burner_casing",
            ReFactoryCore.id("block/casings/clean/clean_mana_burner_casing"));
    public static final BlockEntry<Block> MANA_BURNER_CASING_TITANIUM = createCasingBlock(
            "stable_mana_burner_casing",
            ReFactoryCore.id("block/casings/stable/stable_mana_burner_casing"));
    public static final BlockEntry<Block> MANA_BURNER_CASING_TUNGSTENSTEEL = createCasingBlock(
            "robust_mana_burner_casing",
            ReFactoryCore.id("block/casings/robust/robust_mana_burner_casing"));
    public static final BlockEntry<Block> MANA_BURNER_CASING_PALLADIUM_RHODIUM = createCasingBlock(
            "vigorous_mana_burner_casing",
            ReFactoryCore.id("block/casings/vigorous/vigorous_mana_burner_casing"));

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
