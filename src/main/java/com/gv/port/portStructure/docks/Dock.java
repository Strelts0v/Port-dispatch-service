package com.gv.port.portStructure.docks;

import com.gv.port.portStructure.storages.Storage;

public class Dock {

    private final int dockId;

    private static int dockIdCounter = 1;

    private Storage storage;

    public Dock(Storage storage){
        this.storage = storage;
        this.dockId = dockIdCounter++;
    }

    public int getDockId() {
        return dockId;
    }

    public boolean downloadSupplies(int suppliesCount) {
        return this.storage.download(suppliesCount);
    }

    public boolean unloadSupplies(int suppliesCount) {
        return this.storage.unload(suppliesCount);
    }
}
