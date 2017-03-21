package com.gv.port.ships.dao;

import com.gv.port.ships.Ship;
import java.util.List;

/**
 * provides methods for accessing remote data storage, retrieve and modify data
 */
public interface ShipDao {

    /**
     * gets all ships from current queue in remote data storage.
     * @return list of Ship objects
     */
    List<Ship> getShipsQueue();

    /**
     * deletes ships by id from current queue, stored in remote data storage
     * @param shipId - unique id of ship
     */
    void deleteShipFromQueue(int shipId);

    /**
     * adds new ship to queue in remote data storage
     * @param accessTime - max access time for using dock
     * @param downloadSupplyCount - count of supplies for downloading
     * @param unloadSupplyCount - count of supplies for unloading
     * @param priority - priority of ship in queue
     * @return created id for new ship
     */
    int addShipToQueue(long accessTime, int downloadSupplyCount, int unloadSupplyCount, int priority);
}
