package com.illuminatijoe.refactorycore.machines.part;

import com.illuminatijoe.refactorycore.machines.trait.NotifiableAuraContainer;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import com.lowdragmc.lowdraglib.gui.widget.ImageWidget;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;

import com.mojang.math.MethodsReturnNonnullByDefault;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AuraHatchPartMachine extends TieredIOPartMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(AuraHatchPartMachine.class,
            TieredIOPartMachine.MANAGED_FIELD_HOLDER);

    @Persisted
    @DescSynced
    private final NotifiableAuraContainer auraContainer;

    public AuraHatchPartMachine(IMachineBlockEntity holder, int tier, IO io) {
        super(holder, tier, io);
        this.auraContainer = new NotifiableAuraContainer(this, io);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public int tintColor(int index) {
        return (index == 2) ? GTValues.VC[getTier()] : super.tintColor(index);
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 128, 63);

        group.addWidget(new ImageWidget(4, 4, 120, 55, GuiTextures.DISPLAY));
        group.addWidget(new LabelWidget(8, 8,
                Component
                        .translatable(
                                "gui.refactorycore.aura_hatch.label." + (this.io == IO.IN ? "import" : "export"))));

        group.addWidget(
                new LabelWidget(8, 28,
                        () -> I18n.get("gui.refactorycore.aura_hatch.aura",
                                FormattingUtil.formatNumbers(auraContainer.getAuraAround())))
                        .setClientSideWidget());

        group.setBackground(GuiTextures.BACKGROUND_INVERSE);
        return group;
    }
}
