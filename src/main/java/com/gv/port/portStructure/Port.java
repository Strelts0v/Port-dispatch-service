package com.gv.port.portStructure;

import com.gv.port.portStructure.docks.Dock;
import com.gv.port.portStructure.docks.exceptions.DockException;
import com.gv.port.portStructure.storages.Storage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Port{

    private final static int DOCK_COUNT = 3;

    private final static int INITIAL_STORAGE_SUPPLY_COUNT = 1000;

    private final Semaphore semaphore = new Semaphore(DOCK_COUNT, true);

    private Storage storage = new Storage(INITIAL_STORAGE_SUPPLY_COUNT);

    private final Queue<Dock> docks = new LinkedList();

    private static Port INSTANCE = new Port();

    private Port(){
        this.docks.add(new Dock(this.storage));
        this.docks.add(new Dock(this.storage));
        this.docks.add(new Dock(this.storage));
    }

    public static Port getInstance(){
        return INSTANCE;
    }

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

    public void returnDock(Dock dock){
        docks.add(dock);
        semaphore.release();
    }

    public int getDockCount(){
        return DOCK_COUNT;
    }

    public Storage getStorage(){
        return storage;
    }
}
