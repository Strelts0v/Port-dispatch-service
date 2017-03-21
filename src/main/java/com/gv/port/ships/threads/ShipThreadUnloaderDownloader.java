package com.gv.port.ships.threads;

import com.gv.port.logging.PortLogger;
import com.gv.port.portStructure.Port;
import com.gv.port.portStructure.docks.Dock;
import com.gv.port.portStructure.docks.exceptions.DockException;
import com.gv.port.ships.Ship;
import java.util.Date;


/**
 * specifies actions for ship that going to unload and download supplies to storage of port
 */
public class ShipThreadUnloaderDownloader extends Ship implements Runnable{

    /**
     * constructor for initializing object of ship thread that will unload
     * and download supplies to storage of port
     * @param shipId - uniqie identifier of ship
     * @param accessTime - time that has ship to use dock in port
     * @param downloadSupplyCount - count of supplies for downloading
     * @param unloadSupplyCount - count of supplies for unloading
     * @param priority - id of priority of ship
     */
    public ShipThreadUnloaderDownloader(int shipId, long accessTime, int downloadSupplyCount,
                                        int unloadSupplyCount, int priority){
        super(shipId, accessTime, downloadSupplyCount, unloadSupplyCount, priority);
    }

    /**
     * actions for ship that going to unload
     * and download supplies to storage of port
     */
    public void run() {
        Dock dock = null;
        try {
            dock = Port.getInstance().getDock(MAX_WAITING_TIME);
            setShipIdToPortLogger(dock);
            PortLogger.addLogInfo(new Date().toString() + ". Ship #" + this.shipId + " " +
                    "has been taken dock #" + dock.getDockId());
            Thread.sleep(accessTime * MILLIS_IN_SECONDS);
            if(dock.unloadSupplies(this.unloadSupplyCount)){
                PortLogger.addLogInfo(new Date().toString() + ". Ship #" + this.shipId +
                        " has been successfully unloaded " + this.unloadSupplyCount);
            } else {
                PortLogger.addLogInfo("Error! " + new Date().toString() + ". Ship #" + this.shipId +
                        " hasn't been unloaded " + this.unloadSupplyCount);
            }
            if(dock.downloadSupplies(this.downloadSupplyCount)) {
                PortLogger.addLogInfo(new Date().toString() + ". Ship #" + this.shipId +
                        " successfully downloaded " + this.downloadSupplyCount);
            } else {
                PortLogger.addLogInfo("Error! " + new Date().toString() + ". Ship #" + this.shipId +
                        " hasn't been downloaded " + this.downloadSupplyCount);
            }
        } catch (DockException | InterruptedException e){
            PortLogger.getLogger().error(e);
        } finally {
            resetShipIdFromPortLogger(dock);
            Port.getInstance().returnDock(dock);
            PortLogger.addLogInfo(new Date().toString() + ". Ship #" + this.shipId +
                    " has been returned dock #" + dock.getDockId());
        }
    }
}
