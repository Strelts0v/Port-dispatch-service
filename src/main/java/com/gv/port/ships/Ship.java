package com.gv.port.ships;

import com.gv.port.logging.PortLogger;
import com.gv.port.portStructure.docks.Dock;

/**
 * Abstract data type specifies ship and its key properties and methods
 * for apps business logic
 */
public class Ship {

    /** uniqie identifier of ship */
    protected final int shipId;

    /** time that has ship to use dock in port */
    protected final long accessTime;

    /** count of supplies for downloading */
    protected int downloadSupplyCount;

    /** count of supplies for unloading */
    protected int unloadSupplyCount;

    /** id of priority of ship */
    protected int priority;

    /** max time for waiting dock of port*/
    protected final static int MAX_WAITING_TIME = 1000;

    /** count of millis in seconds */
    protected final static long MILLIS_IN_SECONDS = 1000;

    /**
     * constructor for initializing object of ship
     * @param shipId - uniqie identifier of ship
     * @param accessTime - time that has ship to use dock in port
     * @param downloadSupplyCount - count of supplies for downloading
     * @param unloadSupplyCount - count of supplies for unloading
     * @param priority - id of priority of ship
     */
    public Ship(int shipId, long accessTime, int downloadSupplyCount, int unloadSupplyCount, int priority) {
        this.shipId = shipId;
        this.accessTime = accessTime;
        this.downloadSupplyCount = downloadSupplyCount;
        this.unloadSupplyCount = unloadSupplyCount;
        this.priority = priority;
    }

    public int getShipId() {
        return shipId;
    }

    public long getAccessTime() {
        return accessTime;
    }

    public int getDownloadSupplyCount() {
        return downloadSupplyCount;
    }

    public int getUnloadSupplyCount() {
        return unloadSupplyCount;
    }

    public int getPriority() {
        return priority;
    }

    /**
     * transforms priority id into text.
     * @return String object of priority
     */
    public String getPriorityText(){
        switch (this.priority){
            case 1: return "LOW";
            case 2: return "MEDIUM";
            case 3: return "HIGH";
            default: return "NONE";
        }
    }

    /**
     * sets id ship to PortLogger object that holds dock
     * @param dock - dock object of port
     */
    protected void setShipIdToPortLogger(Dock dock) {
        switch (dock.getDockId()){
            case 1: PortLogger.setShipIdInFirstDock(new Integer(this.shipId).toString()); break;
            case 2: PortLogger.setShipIdInSecondDock(new Integer(this.shipId).toString()); break;
            case 3: PortLogger.setShipIdInThirdDock(new Integer(this.shipId).toString()); break;
            default: break;
        }
    }

    /**
     * resets id ship from PortLogger object to empty
     * @param dock - dock object of port
     */
    protected void resetShipIdFromPortLogger(Dock dock) {
        switch (dock.getDockId()){
            case 1: PortLogger.setShipIdInFirstDock("Empty"); break;
            case 2: PortLogger.setShipIdInSecondDock("Empty"); break;
            case 3: PortLogger.setShipIdInThirdDock("Empty"); break;
            default: break;
        }
    }
}
