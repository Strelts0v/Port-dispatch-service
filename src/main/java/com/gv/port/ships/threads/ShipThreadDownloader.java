package com.gv.port.ships.threads;

import com.gv.port.logging.PortLogger;
import com.gv.port.portStructure.Port;
import com.gv.port.portStructure.docks.Dock;
import com.gv.port.portStructure.docks.exceptions.DockException;
import com.gv.port.ships.Ship;
import com.gv.port.start.Main;

import java.util.Date;

public class ShipThreadDownloader extends Ship implements Runnable{

    public ShipThreadDownloader(int shipId, long accessTime, int downloadSupplyCount,
                                int unloadSupplyCount, int priority){
        super(shipId, accessTime, downloadSupplyCount, unloadSupplyCount, priority);
    }

    public void run() {
        Dock dock = null;
        try {
            dock = Port.getInstance().getDock(MAX_WAITING_TIME);
            setShipIdToPortLogger(dock);
            PortLogger.addLogInfo(new Date().toString() + ". Ship #" + this.shipId + " has been taken dock #" + dock.getDockId());
            Thread.sleep(accessTime * MILLIS_IN_SECONDS);
            if(dock.downloadSupplies(this.downloadSupplyCount)) {
                PortLogger.addLogInfo(new Date().toString() + ". Ship #" + this.shipId +
                        " has been successfully downloaded " + this.downloadSupplyCount);
            } else {
                PortLogger.addLogInfo("Error! " + new Date().toString() + ". Ship #" + this.shipId +
                        " hasn't been downloaded " + this.downloadSupplyCount);
            }
        } catch (DockException | InterruptedException e){
            PortLogger.getLogger().error(e);
        } finally {
            resetShipIdFromPortLogger(dock);
            Port.getInstance().returnDock(dock);
            PortLogger.addLogInfo(new Date().toString() + ". Ship #" + this.shipId + " has been returned dock #" + dock.getDockId());
        }
    }
}
