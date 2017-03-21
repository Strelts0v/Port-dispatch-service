package com.gv.port.ships;

import com.gv.port.logging.PortLogger;
import com.gv.port.portStructure.Port;
import org.junit.Assert;
import org.junit.Test;
import java.util.PriorityQueue;

public class ShipManagerTest {

    @Test
    public void addNewShipsTest() throws Exception {
        ShipManager.getInstance().addNewShip(4, 31, 0, 1);
        ShipManager.getInstance().addNewShip(4, 33, 33, 3);
        ShipManager.getInstance().addNewShip(4, 0, 22, 2);

        PriorityQueue<Ship> ships = ShipManager.getInstance().getShipsQueue();
        Assert.assertEquals(33 , ships.poll().getDownloadSupplyCount());
        Assert.assertEquals(22 , ships.poll().getUnloadSupplyCount());
        Assert.assertEquals(31 , ships.poll().getDownloadSupplyCount());
    }

    @Test
    public void lunchShipsInQueueTest() throws Exception {
        ShipManager.getInstance().addNewShip(1, 31, 0, 1);
        ShipManager.getInstance().addNewShip(1, 33, 33, 3);
        ShipManager.getInstance().addNewShip(1, 0, 22, 2);

        ShipManager.getInstance().lunchShipsInQueue();
        Thread.sleep(2000);
        String[] logInfo = PortLogger.getLastLogInfo();
        for(String s: logInfo){
            System.out.println(s);
        }
        Assert.assertEquals(991, Port.getInstance().getStorage().getSupplyCount());
    }
}