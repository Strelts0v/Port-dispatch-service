package com.gv.port.ships.factory;

import com.gv.port.ships.threads.ShipThreadDownloader;
import com.gv.port.ships.threads.ShipThreadUnloader;
import com.gv.port.ships.threads.ShipThreadUnloaderDownloader;

/**
 *  defines and returns ship threads objects according download supply count
 *  and unload supply count
 */
public class ShipThreadFactory {

    /**
     * returns object of ship thread that will unload supplies to storage of port
     * @param shipId - uniqie identifier of ship
     * @param accessTime - time that has ship to use dock in port
     * @param unloadSupplyCount - count of supplies for unloading
     * @param priority - id of priority of ship
     * @return object of ship thread unloader
     */
    public static ShipThreadUnloader getShipUnloader(int shipId, long accessTime, int unloadSupplyCount, int priority){
        return new ShipThreadUnloader(shipId, accessTime, 0, unloadSupplyCount, priority);
    }

    /**
     * returns object of ship thread that will download supplies to storage of port
     * @param shipId - uniqie identifier of ship
     * @param accessTime - time that has ship to use dock in port
     * @param downloadSupplyCount - count of supplies for downloading
     * @param priority - id of priority of ship
     * @return object of ship thread downloader
     */
    public static ShipThreadDownloader getShipDownloader(int shipId, long accessTime, int downloadSupplyCount, int priority){
        return new ShipThreadDownloader(shipId, accessTime, downloadSupplyCount, 0, priority);
    }

    /**
     * returns object of ship thread that will unload and download supplies to storage of port
     * @param shipId - uniqie identifier of ship
     * @param accessTime - time that has ship to use dock in port
     * @param downloadSupplyCount - count of supplies for downloading
     * @param unloadSupplyCount - count of supplies for unloading
     * @param priority - id of priority of ship
     * @return object of ship thread downloader & unloader
     */
    public static ShipThreadUnloaderDownloader getShipUnloaderDownloader(int shipId, long accessTime,
                                                                         int downloadSupplyCount, int unloadSupplyCount, int priority){
        return new ShipThreadUnloaderDownloader(shipId, accessTime, downloadSupplyCount, unloadSupplyCount, priority);
    }
}
