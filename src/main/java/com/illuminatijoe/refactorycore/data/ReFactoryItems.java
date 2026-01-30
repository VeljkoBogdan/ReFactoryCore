package com.illuminatijoe.refactorycore.data;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.common.item.TooltipBehavior;

import net.minecraft.network.chat.Component;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;

import static com.illuminatijoe.refactorycore.api.ReFactoryRegistries.REGISTRATE;
import static com.illuminatijoe.refactorycore.data.recipes.NuclearRecipes.*;

public class ReFactoryItems {

    public static <T extends ComponentItem> NonNullConsumer<T> attach(IItemComponent... components) {
        return item -> item.attachComponents(components);
    }

    public static final ItemEntry<ComponentItem> PLUTONIUM_FUEL_ROD = REGISTRATE
            .item("plutonium_fuel_rod", ComponentItem::create)
            .onRegister(attach(new TooltipBehavior(
                    l -> l.add(Component.translatable("tooltip.refactorycore.plutonium_temp", PLUTONIUM_TEMP)))))
            .register();

    public static final ItemEntry<ComponentItem> URANIUM_FUEL_ROD = REGISTRATE
            .item("uranium_fuel_rod", ComponentItem::create)
            .onRegister(attach(new TooltipBehavior(
                    l -> l.add(Component.translatable("tooltip.refactorycore.uranium_temp", URANIUM_TEMP)))))
            .register();

    public static final ItemEntry<ComponentItem> THORIUM_FUEL_ROD = REGISTRATE
            .item("thorium_fuel_rod", ComponentItem::create)
            .onRegister(attach(new TooltipBehavior(
                    l -> l.add(Component.translatable("tooltip.refactorycore.thorium_temp", THORIUM_TEMP)))))
            .register();

    public static void init() {}
}
