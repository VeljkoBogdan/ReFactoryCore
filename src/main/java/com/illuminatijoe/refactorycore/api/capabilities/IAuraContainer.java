package com.illuminatijoe.refactorycore.api.capabilities;

public interface IAuraContainer {

    int drainAura(int amount, boolean simulate);

    int storeAura(int amount, boolean simulate);

    boolean canDrain();

    boolean canStore();
}
