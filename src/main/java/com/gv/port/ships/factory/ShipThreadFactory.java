package com.gv.port.ships.factory;

import com.gv.port.ships.threads.ShipThreadDownloader;
import com.gv.port.ships.threads.ShipThreadUnloader;
import com.gv.port.ships.threads.ShipThreadUnloaderDownloader;

public class ShipThreadFactory {

    public static ShipThreadUnloader getShipUnloader(int shipId, long accessTime, int unloadSupplyCount, int priority){
        return new ShipThreadUnloader(shipId, accessTime, 0, unloadSupplyCount, priority);
    }

    public static ShipThreadDownloader getShipDownloader(int shipId, long accessTime, int downloadSupplyCount, int priority){
        return new ShipThreadDownloader(shipId, accessTime, downloadSupplyCount, 0, priority);
    }

    public static ShipThreadUnloaderDownloader getShipUnloaderDownloader(int shipId, long accessTime,
                                                                         int downloadSupplyCount, int unloadSupplyCount, int priority){
        return new ShipThreadUnloaderDownloader(shipId, accessTime, downloadSupplyCount, unloadSupplyCount, priority);
    }
}
