package com.illuminatijoe.refactorycore.api.capabilities;

import wayoftime.bloodmagic.core.data.SoulNetwork;

import java.util.UUID;

public interface ILPContainer {

    SoulNetwork getNetwork();

    UUID getOwner();

    void setOwner(UUID player);
}
