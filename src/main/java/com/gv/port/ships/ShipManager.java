package com.gv.port.ships;

import com.gv.port.portAccess.PortAccessService;
import com.gv.port.ships.dao.ShipDaoSingleton;
import com.gv.port.ships.factory.ShipThreadFactory;
import com.gv.port.ships.threads.ShipThreadDownloader;
import com.gv.port.ships.threads.ShipThreadUnloader;
import com.gv.port.ships.threads.ShipThreadUnloaderDownloader;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ShipManager {

    private static final int QUEUE_INITIAL_CAPACITY = 47;

    private PriorityQueue<Ship> shipsQueue = new PriorityQueue<Ship>(QUEUE_INITIAL_CAPACITY, new Comparator<Ship>() {
        public int compare(Ship s1, Ship s2) {
            if (s1.getPriority() > s2.getPriority())
            {
                return -1;
            }
            if (s1.getPriority() < s2.getPriority())
            {
                return 1;
            }
            return 0;
        }
    });

    private static ShipManager INSTANCE = new ShipManager();

    private ShipManager(){}

    public static ShipManager getInstance(){
        return INSTANCE;
    }

    public void addNewShip(long accessTime, int downloadSupplyCount, int unloadSupplyCount, int priority){
        int shipId = ShipDaoSingleton.getInstance().
                addShipToQueue(accessTime, downloadSupplyCount, unloadSupplyCount, priority);
        if(shipId != 0){
            shipsQueue.offer(new Ship(shipId, accessTime, downloadSupplyCount, unloadSupplyCount, priority));
        }
    }

    public void deleteShip(int shipId){
        ShipDaoSingleton.getInstance().deleteShipFromQueue(shipId);
        for (Ship ship : shipsQueue){
            if(ship.shipId == shipId){
                shipsQueue.remove(ship);
            }
        }
    }

    public void lunchShipsInQueue(){
        while(!shipsQueue.isEmpty()){
            Ship ship = shipsQueue.poll();
            if(ship.getUnloadSupplyCount() == 0){
                ShipThreadDownloader sTD = ShipThreadFactory.getShipDownloader(ship.shipId,
                        ship.accessTime, ship.downloadSupplyCount, ship.priority);
                PortAccessService.getInstance().getAccessToPort(sTD);
            } else if(ship.getDownloadSupplyCount() == 0){
                ShipThreadUnloader sTU = ShipThreadFactory.getShipUnloader(ship.shipId, ship.accessTime,
                        ship.unloadSupplyCount, ship.priority);
                PortAccessService.getInstance().getAccessToPort(sTU);
            } else {
                ShipThreadUnloaderDownloader sTUD = ShipThreadFactory.getShipUnloaderDownloader(ship.shipId,
                        ship.accessTime, ship.downloadSupplyCount, ship.unloadSupplyCount, ship.priority);
                PortAccessService.getInstance().getAccessToPort(sTUD);
            }
            ShipDaoSingleton.getInstance().deleteShipFromQueue(ship.shipId);
        }
    }

    public void initializeShipsQueue(){
        List<Ship> actualShips = ShipDaoSingleton.getInstance().getShipsQueue();
        for(Ship ship : actualShips){
            this.shipsQueue.offer(ship);
        }
    }

    public PriorityQueue<Ship> getShipsQueue() {
        return shipsQueue;
    }
}
