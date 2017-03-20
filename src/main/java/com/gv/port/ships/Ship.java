package com.gv.port.ships;


import com.gv.port.logging.PortLogger;
import com.gv.port.portStructure.docks.Dock;

public class Ship {

    protected final int shipId;

    protected final long accessTime;

    protected int downloadSupplyCount;

    protected int unloadSupplyCount;

    protected int priority;

    protected final static int MAX_WAITING_TIME = 1000;

    protected final static long MILLIS_IN_SECONDS = 1000;

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

    public String getPriorityText(){
        switch (this.priority){
            case 1: return "LOW";
            case 2: return "MEDIUM";
            case 3: return "HIGH";
            default: return "NONE";
        }
    }

    protected void setShipIdToPortLogger(Dock dock) {
        switch (dock.getDockId()){
            case 1: PortLogger.setShipIdInFirstDock(new Integer(this.shipId).toString()); break;
            case 2: PortLogger.setShipIdInSecondDock(new Integer(this.shipId).toString()); break;
            case 3: PortLogger.setShipIdInThirdDock(new Integer(this.shipId).toString()); break;
            default: break;
        }
    }

    protected void resetShipIdFromPortLogger(Dock dock) {
        switch (dock.getDockId()){
            case 1: PortLogger.setShipIdInFirstDock("Empty"); break;
            case 2: PortLogger.setShipIdInSecondDock("Empty"); break;
            case 3: PortLogger.setShipIdInThirdDock("Empty"); break;
            default: break;
        }
    }
}
