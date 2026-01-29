package com.illuminatijoe.refactorycore.machines.trait;

import com.illuminatijoe.refactorycore.api.capabilities.IAuraContainer;
import com.illuminatijoe.refactorycore.api.capabilities.recipe.AuraRecipeCapability;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.ConditionalSubscriptionHandler;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;

import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.core.BlockPos;

import de.ellpeck.naturesaura.api.aura.chunk.IAuraChunk;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotifiableAuraContainer extends NotifiableRecipeHandlerTrait<Integer> implements IAuraContainer {

    public static final int INPUT_RADIUS = 32;
    public static final int OUTPUT_RADIUS = 32;

    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NotifiableAuraContainer.class,
            NotifiableRecipeHandlerTrait.MANAGED_FIELD_HOLDER);

    @Getter
    private final IO handlerIO;
    private final ConditionalSubscriptionHandler conditionalSubscriptionHandler;
    @Getter
    @DescSynced
    private int currentAuraAround;

    public NotifiableAuraContainer(MetaMachine machine, IO io) {
        super(machine);
        this.handlerIO = io;
        currentAuraAround = -1;

        conditionalSubscriptionHandler = new ConditionalSubscriptionHandler(
                machine,
                this::tickAuraSync,
                () -> machine.getLevel() != null);
    }

    private void tickAuraSync() {
        if (this.machine.getOffsetTimer() % 20 != 0) return;

        int aura = IAuraChunk.getAuraInArea(machine.getLevel(), machine.getPos(), INPUT_RADIUS);
        if (aura != currentAuraAround) {
            currentAuraAround = aura;
            notifyListeners();
        }
    }

    public int getAuraAround() {
        return IAuraChunk.getAuraInArea(machine.getLevel(), machine.getPos(), INPUT_RADIUS);
    }

    @Override
    public List<Integer> handleRecipeInner(IO io, GTRecipe recipe, List<Integer> left, boolean simulate) {
        int requested = left.stream().mapToInt(Integer::intValue).sum();

        if (requested <= 0) return null;

        int handled;
        if (io == IO.IN && canDrain()) {
            handled = drainAura(requested, simulate);
        } else if (io == IO.OUT && canStore()) {
            handled = storeAura(requested, simulate);
        } else return left;

        int remaining = requested - handled;
        return remaining <= 0 ? null : List.of(remaining);
    }

    @Override
    public @NotNull List<Object> getContents() {
        return List.of(0);
    }

    @Override
    public double getTotalContentAmount() {
        return 0;
    }

    @Override
    public RecipeCapability<Integer> getCapability() {
        return AuraRecipeCapability.CAP;
    }

    @Override
    public int drainAura(int amount, boolean simulate) {
        if (!canDrain() || machine.getLevel() == null) return 0;

        int available = IAuraChunk.getAuraInArea(machine.getLevel(), machine.getPos(), INPUT_RADIUS);
        int toDrain = Math.min(amount, available);
        if (toDrain <= 0) return 0;

        if (!simulate) {
            BlockPos spot = IAuraChunk.getHighestSpot(
                    machine.getLevel(),
                    machine.getPos(),
                    INPUT_RADIUS,
                    machine.getPos());
            IAuraChunk chunk = IAuraChunk.getAuraChunk(machine.getLevel(), spot);
            chunk.drainAura(spot, toDrain);
        }

        return toDrain;
    }

    @Override
    public int storeAura(int amount, boolean simulate) {
        if (!canStore() || machine.getLevel() == null) return 0;

        int aura = IAuraChunk.getAuraInArea(machine.getLevel(), machine.getPos(), OUTPUT_RADIUS);
        int capacity = (IAuraChunk.DEFAULT_AURA * 2) - aura;
        if (capacity <= 0) return 0;

        int toStore = Math.min(capacity, amount);

        if (!simulate) {
            var spot = IAuraChunk.getLowestSpot(machine.getLevel(), machine.getPos(), OUTPUT_RADIUS, machine.getPos());
            IAuraChunk chunk = IAuraChunk.getAuraChunk(machine.getLevel(), spot);
            chunk.storeAura(spot, toStore);
        }

        return toStore;
    }

    @Override
    public boolean canDrain() {
        return handlerIO == IO.IN;
    }

    @Override
    public boolean canStore() {
        return handlerIO == IO.OUT;
    }

    @Override
    public void onMachineLoad() {
        super.onMachineLoad();
        conditionalSubscriptionHandler.initialize(this.machine.getLevel());
        conditionalSubscriptionHandler.updateSubscription();
    }
}
