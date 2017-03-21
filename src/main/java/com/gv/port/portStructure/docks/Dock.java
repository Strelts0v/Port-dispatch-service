package com.gv.port.portStructure.docks;

import com.gv.port.portStructure.storages.Storage;

/**
 * represents dock of port, provides methods to download of unload supplies to port storage
 */
public class Dock {

    /** property - id of dock */
    private final int dockId;

    /** static property - for incrementing id for next dock */
    private static int dockIdCounter = 1;

    /** object for storing supplies in port */
    private Storage storage;

    /**
     * basic constructor
     * @param storage - object for storing supplies in port
     */
    public Dock(Storage storage){
        this.storage = storage;
        this.dockId = dockIdCounter++;
    }

    /**
     * @return id of dock
     */
    public int getDockId() {
        return dockId;
    }

    /**
     * downloads supplies from port storage
     * @param suppliesCount - count of download supplies
     * @return - true - download was succeded
     *           false - download was failed
     */
    public boolean downloadSupplies(int suppliesCount) {
        return this.storage.download(suppliesCount);
    }

    /**
     * unloads supplies to port storage
     * @param suppliesCount - count of unload supplies
     * @return - true - unload was succeded
     *           false - unload was failed
     */
    public boolean unloadSupplies(int suppliesCount) {
        return this.storage.unload(suppliesCount);
    }
}
