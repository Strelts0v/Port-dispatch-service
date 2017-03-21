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

/**
 * represents interface and unites logic of whole ship module for other modules of app
 */
public class ShipManager {

    /** initial capacity of priority queue */
    private static final int QUEUE_INITIAL_CAPACITY = 47;

    /** priority queue for storing actual ships registered in queue of database */
    private PriorityQueue<Ship> shipsQueue = new PriorityQueue<>(QUEUE_INITIAL_CAPACITY, new Comparator<Ship>() {
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

    /** single object of class */
    private static ShipManager INSTANCE = new ShipManager();

    /** private basic constructor of class */
    private ShipManager(){}

    /** returns single instance of class */
    public static ShipManager getInstance(){
        return INSTANCE;
    }

    /** adds new ship to queue of ships and to remote database */
    public void addNewShip(long accessTime, int downloadSupplyCount, int unloadSupplyCount, int priority){
        int shipId = ShipDaoSingleton.getInstance().
                addShipToQueue(accessTime, downloadSupplyCount, unloadSupplyCount, priority);
        if(shipId != 0){
            shipsQueue.offer(new Ship(shipId, accessTime, downloadSupplyCount, unloadSupplyCount, priority));
        }
    }

    /**
     * deletes ship by ship id in local queue and remote database queue
     * @param shipId
     */
    public void deleteShip(int shipId){
        ShipDaoSingleton.getInstance().deleteShipFromQueue(shipId);
        for (Ship ship : shipsQueue){
            if(ship.shipId == shipId){
                shipsQueue.remove(ship);
            }
        }
    }

    /**
     * transforms ships objects into threads and adds them to @see PortAccessService
     */
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

    /**
     * initializes local queue of ships from remote database queue
     */
    public void initializeShipsQueue(){
        List<Ship> actualShips = ShipDaoSingleton.getInstance().getShipsQueue();
        for(Ship ship : actualShips){
            this.shipsQueue.offer(ship);
        }
    }

    /** returns queue of ships */
    public PriorityQueue<Ship> getShipsQueue() {
        return shipsQueue;
    }
}
