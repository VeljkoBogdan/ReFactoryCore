package com.illuminatijoe.refactorycore.machines.part;

import com.illuminatijoe.refactorycore.machines.trait.NotifiableLPContainer;

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

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import wayoftime.bloodmagic.util.helper.PlayerHelper;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class LPHatchPartMachine extends TieredIOPartMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(LPHatchPartMachine.class,
            TieredIOPartMachine.MANAGED_FIELD_HOLDER);

    @Persisted
    @DescSynced
    private final NotifiableLPContainer lpContainer;

    public LPHatchPartMachine(IMachineBlockEntity holder, int tier, IO io) {
        super(holder, tier, io);
        this.lpContainer = new NotifiableLPContainer(this, io, getMaxCapacity(tier), getMaxConsumption(tier));
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 128, 63);

        group.addWidget(new ImageWidget(4, 4, 120, 55, GuiTextures.DISPLAY));
        group.addWidget(new LabelWidget(8, 8,
                Component
                        .translatable("gui.refactorycore.lp_hatch.label." + (this.io == IO.IN ? "import" : "export"))));

        if (lpContainer.getOwner() == null) {
            group.addWidget(
                    new LabelWidget(8, 18, I18n.get("gui.refactorycore.lp_hatch.no_network")).setClientSideWidget());
        } else {
            group.addWidget(
                    new LabelWidget(8, 18,
                            () -> I18n.get("gui.refactorycore.lp_hatch.owner",
                                    PlayerHelper.getUsernameFromUUID(this.lpContainer.getOwner())))
                            .setClientSideWidget());
            group.addWidget(
                    new LabelWidget(8, 28,
                            () -> I18n.get("gui.refactorycore.lp_hatch.lp",
                                    FormattingUtil.formatNumbers(lpContainer.getCurrentLP())))
                            .setClientSideWidget());
        }

        group.setBackground(GuiTextures.BACKGROUND_INVERSE);
        return group;
    }

    public static int getMaxCapacity(int tier) {
        return switch (tier) {
            case GTValues.EV -> 1000000;
            case GTValues.IV -> 2000000;
            case GTValues.LuV -> 4000000;
            case GTValues.ZPM -> 16000000;
            case GTValues.UV -> 32000000;
            case GTValues.UHV -> 64000000;
            case GTValues.UEV -> 128000000;
            case GTValues.UIV -> 256000000;
            case GTValues.UXV -> 512000000;
            case GTValues.OpV -> 1024000000;
            case GTValues.MAX -> Integer.MAX_VALUE;
            default -> 0;
        };
    }

    public static int getMaxConsumption(int tier) {
        return switch (tier) {
            case GTValues.IV -> 10000;
            case GTValues.LuV -> 50000;
            case GTValues.ZPM -> 5000000;
            case GTValues.UV -> 10000000;
            case GTValues.UHV -> 25000000;
            case GTValues.UEV -> 50000000;
            case GTValues.UIV -> 125000000;
            case GTValues.UXV -> 250000000;
            case GTValues.OpV -> 500000000;
            case GTValues.MAX -> Integer.MAX_VALUE;
            default -> 0;
        };
    }

    public void attachSoulNetwork(Player player) {
        this.lpContainer.setOwner(player.getUUID());
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public int tintColor(int index) {
        return (index == 2) ? GTValues.VC[getTier()] : super.tintColor(index);
    }
}
