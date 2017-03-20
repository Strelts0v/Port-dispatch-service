package com.gv.port.ships.dao;

import com.gv.port.ships.Ship;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ShipDaoSingletonTest {
    @Test
    public void getShipsQueueTest() throws Exception {
        int shipId1 = ShipDaoSingleton.getInstance().addShipToQueue((long)47, 10, 0, 2);
        int shipId2 = ShipDaoSingleton.getInstance().addShipToQueue((long)44, 12, 0, 1);

        List<Ship> ships = ShipDaoSingleton.getInstance().getShipsQueue();
        Assert.assertFalse(ships.isEmpty());
        Assert.assertEquals((long)47, ships.get(0).getAccessTime());
        Assert.assertEquals(10, ships.get(0).getDownloadSupplyCount());
        Assert.assertEquals(0, ships.get(0).getUnloadSupplyCount());
        Assert.assertEquals(2, ships.get(0).getPriority());

        ShipDaoSingleton.getInstance().deleteShipFromQueue(shipId1);
        ShipDaoSingleton.getInstance().deleteShipFromQueue(shipId2);
    }

    @Test
    public void deleteShipFromQueue() throws Exception {

    }

    @Test
    public void addShipToQueue() throws Exception {

    }

}