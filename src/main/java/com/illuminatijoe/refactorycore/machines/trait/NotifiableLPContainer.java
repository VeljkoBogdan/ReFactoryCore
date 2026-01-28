package com.illuminatijoe.refactorycore.machines.trait;

import com.illuminatijoe.refactorycore.api.capabilities.ILPContainer;
import com.illuminatijoe.refactorycore.api.capabilities.recipe.LPRecipeCapability;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.ConditionalSubscriptionHandler;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import wayoftime.bloodmagic.core.data.SoulNetwork;
import wayoftime.bloodmagic.core.data.SoulTicket;
import wayoftime.bloodmagic.util.helper.NetworkHelper;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class NotifiableLPContainer extends NotifiableRecipeHandlerTrait<Integer> implements ILPContainer {

    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NotifiableLPContainer.class,
            NotifiableRecipeHandlerTrait.MANAGED_FIELD_HOLDER);

    @Getter
    private final IO handlerIO;
    private final ConditionalSubscriptionHandler conditionalSubscriptionHandler;
    @Getter
    @Persisted
    @DescSynced
    private UUID owner;
    @Getter
    @DescSynced
    private int currentLP;
    @Persisted
    private int maxCapacity;
    @Persisted
    private int maxConsumption;

    public NotifiableLPContainer(MetaMachine machine, IO io, int maxCapacity, int maxConsumption) {
        super(machine);
        this.handlerIO = io;
        this.currentLP = -1;
        this.maxConsumption = maxConsumption;
        this.maxCapacity = maxCapacity;
        conditionalSubscriptionHandler = new ConditionalSubscriptionHandler(machine, this::queryNetwork,
                () -> owner != null);
    }

    private void queryNetwork() {
        if (this.machine.getOffsetTimer() % 20 != 0) return;

        SoulNetwork network = this.getNetwork();
        if (network == null) return;

        int essence = network.getCurrentEssence();
        if (this.currentLP == essence) return;

        this.currentLP = essence;
        this.notifyListeners();
    }

    @Override
    public List<Integer> handleRecipeInner(IO io, GTRecipe recipe, List<Integer> left, boolean simulate) {
        ILPContainer container = this;
        if (container.getOwner() == null) return null;

        int essence = left.stream().reduce(0, Integer::sum);

        if (io == IO.IN) {
            int canOutput = Math.min(this.maxConsumption, container.getNetwork().getCurrentEssence());
            if (!simulate) essence = container.getNetwork().syphon(
                    SoulTicket.block(this.machine.getLevel(), this.machine.getPos(), Math.min(canOutput, essence)),
                    false);
            essence = essence - canOutput;
        } else if (io == IO.OUT) {
            int canInput = this.maxCapacity - container.getNetwork().getCurrentEssence();
            if (!simulate) essence = container.getNetwork().add(
                    SoulTicket.block(this.machine.getLevel(), this.machine.getPos(), Math.min(canInput, essence)),
                    this.maxCapacity);
            essence = essence - canInput;
        }

        return essence <= 0 ? null : Collections.singletonList(essence);
    }

    @Override
    public @NotNull List<Object> getContents() {
        if (this.getOwner() == null) return Collections.emptyList();
        return List.of(this.getNetwork().getCurrentEssence());
    }

    @Override
    public double getTotalContentAmount() {
        if (this.getOwner() == null) return 0;
        return this.getNetwork().getCurrentEssence();
    }

    @Override
    public RecipeCapability<Integer> getCapability() {
        return LPRecipeCapability.CAP;
    }

    @Override
    public SoulNetwork getNetwork() {
        return NetworkHelper.getSoulNetwork(this.owner);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public void setOwner(UUID owner) {
        this.owner = owner;
        conditionalSubscriptionHandler.updateSubscription();
    }

    @Override
    public void onMachineLoad() {
        super.onMachineLoad();
        conditionalSubscriptionHandler.initialize(this.machine.getLevel());
    }
}
