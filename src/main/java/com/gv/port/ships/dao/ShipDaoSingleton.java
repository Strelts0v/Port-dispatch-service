package com.gv.port.ships.dao;

import com.gv.port.database.DatabaseConnectionManager;
import com.gv.port.logging.PortLogger;
import com.gv.port.ships.Ship;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * provides methods for accessing remote database, retrieve and modify data
 * realizes Singleton pattern
 */
public class ShipDaoSingleton implements ShipDao{

    /** single object of class according Singleton pattern */
    private final static ShipDao INSTANCE = new ShipDaoSingleton();

    /** name of column with ship id that stored in remote database */
    private final static String SHIP_ID_COLUMN_NAME = "ship_id";

    /** name of column with priority id that stored in remote database */
    private final static String SHIP_PRIORITY_ID_COLUMN_NAME = "priority_id";

    /** name of column with supply id that stored in remote database */
    private final static String SHIP_SUPPLY_ID_COLUMN_NAME = "supply_id";

    /** name of column with ship access time that stored in remote database */
    private final static String SHIP_ACCESS_TIME_COLUMN_NAME = "access_time";

    /** name of column with supply download count that stored in remote database */
    private final static String DOWNLOAD_SUPPLY_COUNT_COLUMN_NAME = "download_count";

    /** name of column with supply unload count that stored in remote database */
    private final static String UNLOAD_SUPPLY_COUNT_COLUMN_NAME = "unload_count";

    /** initial value for violations */
    private final static int INITIAL_VIOLATIONS_COUNT = 0;

    /** private basic constructor of class */
    private ShipDaoSingleton(){}

    /** returns single instance of class */
    public static ShipDao getInstance(){
        return INSTANCE;
    }


    /**
     * gets all ships from current queue in remote data storage.
     * @return list of Ship objects
     */
    public List<Ship> getShipsQueue() {
        List<Ship> actualShips = null;
        try {
            Connection connection = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement stmt = connection.prepareStatement("select * from ships_queue");
            ResultSet rS = stmt.executeQuery();
            rS.last();
            int shipsCount = rS.getRow();
            actualShips = new ArrayList<Ship>(shipsCount);
            rS.beforeFirst();
            while(rS.next()){
                int shipId = rS.getInt(SHIP_ID_COLUMN_NAME);
                int priority = rS.getInt(SHIP_PRIORITY_ID_COLUMN_NAME);
                long accessTime = (long)rS.getInt(SHIP_ACCESS_TIME_COLUMN_NAME);
                int supplyId = rS.getInt(SHIP_SUPPLY_ID_COLUMN_NAME);
                int[] supplies = getSuppliesCount(supplyId);
                actualShips.add(new Ship(shipId, accessTime, supplies[0], supplies[1], priority));
            }
        } catch (SQLException e){
            PortLogger.getLogger().error(e);
        } finally {
            return actualShips;
        }
    }

    /**
     * deletes ships by id from current queue, stored in remote database
     * @param shipId - unique id of ship
     */
    public void deleteShipFromQueue(int shipId) {
        try{
            Connection connection = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM ships_queue where ship_id = ?");
            stmt.setInt(1, shipId);
            stmt.executeUpdate();
        } catch (SQLException e){
            PortLogger.getLogger().error(e);
        }
    }

    /**
     * adds new ship to queue in remote database
     * @param accessTime - max access time for using dock
     * @param downloadSupplyCount - count of supplies for downloading
     * @param unloadSupplyCount - count of supplies for unloading
     * @param priority - priority of ship in queue
     * @return created id for new ship
     */
    public int addShipToQueue(long accessTime, int downloadSupplyCount, int unloadSupplyCount, int priority) {
        int shipId = 0;
        try{
            Connection connection = DatabaseConnectionManager.getDatabaseConnection();
            shipId = registerShip(accessTime);
            int supplyId = registerShipSupplies(shipId, downloadSupplyCount, unloadSupplyCount);
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO ships_queue(ship_id," +
                    " supply_id," +
                    " priority_id, access_time) values (?,?,?,?)");
            stmt.setInt(1, shipId);
            stmt.setInt(2, supplyId);
            stmt.setInt(3, priority);
            stmt.setInt(4, (int)accessTime);
            stmt.executeUpdate();
        } catch(SQLException e){
            PortLogger.getLogger().error(e);
        } finally {
            return shipId;
        }
    }

    /**
     * gets download and unload supply count according supply id
     * @param supplyId - unique identifier of supplies
     * @return array of 2 numbers, first - download supply count,
     * second - unload supply count
     */
    private int[] getSuppliesCount(int supplyId) {
        int[] supplies = new int[2];
        try {
            Connection connection = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement stmt = connection.prepareStatement("select download_count," +
                    " unload_count from supplies where supply_id = ?");
            stmt.setInt(1, supplyId);
            ResultSet rS = stmt.executeQuery();
            rS.next();
            supplies[0] = rS.getInt(DOWNLOAD_SUPPLY_COUNT_COLUMN_NAME);
            supplies[1] = rS.getInt(UNLOAD_SUPPLY_COUNT_COLUMN_NAME);
        } catch (SQLException e){
            PortLogger.getLogger().error(e);
        } finally {
            return supplies;
        }
    }

    /**
     * registers ship in table with all ships in remote database
     * @param accessTime - last access time of that ship
     * @return return id of registered ship
     */
    private int registerShip(long accessTime) {
        int shipId = 0;
        try{
            Connection connection = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO ships_history(all_access_time," +
                    " violations_count) values (?, ?)");
            stmt.setInt(1, (int)accessTime);
            stmt.setInt(2, INITIAL_VIOLATIONS_COUNT);
            stmt.executeUpdate();
            stmt = connection.prepareStatement("SELECT max(ship_id) as ship_id from ships_history");
            ResultSet rS = stmt.executeQuery();
            rS.next();
            shipId = rS.getInt(SHIP_ID_COLUMN_NAME);
        } catch(SQLException e){
            PortLogger.getLogger().error(e);
        } finally {
            return shipId;
        }
    }

    /**
     * registers supplies of ship in remote database
     * @param shipId - uniqie identifier of ship
     * @param downloadSupplyCount - count of supplies for downloading
     * @param unloadSupplyCount - count of supplies for unloading
     * @return - supply id stored in remote database
     */
    private int registerShipSupplies(int shipId, int downloadSupplyCount, int unloadSupplyCount) {
        int supply_id = 0;
        try{
            Connection connection = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO supplies(ship_id, download_count," +
                    " unload_count) values (?,?,?)");
            stmt.setInt(1, shipId);
            stmt.setInt(2, downloadSupplyCount);
            stmt.setInt(3, unloadSupplyCount);
            stmt.executeUpdate();
            stmt = connection.prepareStatement("SELECT max(supply_id) as supply_id from supplies");
            ResultSet rS = stmt.executeQuery();
            rS.next();
            supply_id = rS.getInt(SHIP_SUPPLY_ID_COLUMN_NAME);
        } catch(SQLException e){
            PortLogger.getLogger().error(e);
        } finally {
            return supply_id;
        }
    }
}
