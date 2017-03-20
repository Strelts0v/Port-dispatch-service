package com.gv.port.ships.threads;

import com.gv.port.logging.PortLogger;
import com.gv.port.portStructure.Port;
import com.gv.port.portStructure.docks.Dock;
import com.gv.port.portStructure.docks.exceptions.DockException;
import com.gv.port.ships.Ship;

import java.util.Date;

public class ShipThreadUnloaderDownloader extends Ship implements Runnable{

    public ShipThreadUnloaderDownloader(int shipId, long accessTime, int downloadSupplyCount,
                                        int unloadSupplyCount, int priority){
        super(shipId, accessTime, downloadSupplyCount, unloadSupplyCount, priority);
    }

    public void run() {
        Dock dock = null;
        try {
            dock = Port.getInstance().getDock(MAX_WAITING_TIME);
            setShipIdToPortLogger(dock);
            Thread.sleep(accessTime * MILLIS_IN_SECONDS);
            if(dock.unloadSupplies(this.unloadSupplyCount)){
                PortLogger.addLogInfo(new Date().toString() + ". Ship #" + this.shipId +
                        " has been successfully unloaded " + this.unloadSupplyCount + " to port storage.");
            } else {
                PortLogger.addLogInfo("Error! " + new Date().toString() + ". Ship #" + this.shipId +
                        " hasn't been unloaded " + this.unloadSupplyCount + " to port storage.");
            }
            if(dock.downloadSupplies(this.downloadSupplyCount)) {
                PortLogger.addLogInfo(new Date().toString() + ". Ship #" + this.shipId +
                        " has been successfully downloaded " + this.downloadSupplyCount + " to port storage.");
            } else {
                PortLogger.addLogInfo("Error! " + new Date().toString() + ". Ship #" + this.shipId +
                        " hasn't been downloaded " + this.downloadSupplyCount + " to port storage.");
            }
        } catch (DockException | InterruptedException e){
            PortLogger.getLogger().error(e);
        } finally {
            resetShipIdFromPortLogger(dock);
            Port.getInstance().returnDock(dock);
        }
    }
}
