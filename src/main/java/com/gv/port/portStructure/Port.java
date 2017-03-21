package com.gv.port.portStructure;

import com.gv.port.portStructure.docks.Dock;
import com.gv.port.portStructure.docks.exceptions.DockException;
import com.gv.port.portStructure.storages.Storage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * controls access to docks for ships threads using semaphore, realizes
 * Singleton design pattern
 */
public class Port{

    /** count of docks in port */
    private final static int DOCK_COUNT = 3;

    /** initial supply count in port storage */
    private final static int INITIAL_STORAGE_SUPPLY_COUNT = 1000;

    /** object for controlling access for docks in port */
    private final Semaphore semaphore = new Semaphore(DOCK_COUNT, true);

    /** object for storing supplies of port */
    private Storage storage = new Storage(INITIAL_STORAGE_SUPPLY_COUNT);

    /** queue of docks */
    private final Queue<Dock> docks = new LinkedList();

    private static Port INSTANCE = new Port();

    /** initialized port object with docks */
    private Port(){
        this.docks.add(new Dock(this.storage));
        this.docks.add(new Dock(this.storage));
        this.docks.add(new Dock(this.storage));
    }

    /** return single object of port */
    public static Port getInstance(){
        return INSTANCE;
    }

    /**
     * waits for free dock and returns it to the calling place
     * @param maxWaitTimeInSeconds - max time for waiting dock object
     * @return  dock object of port
     * @throws DockException - object for docks errors
     */
    public Dock getDock(long maxWaitTimeInSeconds) throws DockException {
        try{
            if(semaphore.tryAcquire(maxWaitTimeInSeconds, TimeUnit.SECONDS)){
                Dock dock = docks.poll();
                return dock;
            }
        } catch (InterruptedException e){
            throw new DockException(e);
        }
        throw new DockException("Error! Waiting time is exceeded...");
    }

    /**
     * rids dock object for others
     * @param dock - object of dock port
     */
    public void returnDock(Dock dock){
        docks.add(dock);
        semaphore.release();
    }

    /**
     * @return max count of docks in port
     */
    public int getDockCount(){
        return DOCK_COUNT;
    }

    /**
     * @return returns storage object
     */
    public Storage getStorage(){
        return storage;
    }
}
