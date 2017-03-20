package com.gv.port.ships.dao;

import com.gv.port.ships.Ship;
import java.util.List;

public interface ShipDao {

    List<Ship> getShipsQueue();

    void deleteShipFromQueue(int shipId);

    int addShipToQueue(long accessTime, int downloadSupplyCount, int unloadSupplyCount, int priority);
}
