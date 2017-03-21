package com.gv.port.portAccess;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * represents pool of threads used to achieve better performance of app
 */
public class PortAccessService{

    /** Count of threads in pool */
    private static final int POOL_SIZE = 3;

    /** Object that represents fixed size of pool and service to execute threads */
    private final ExecutorService executorService =
            Executors.newFixedThreadPool(POOL_SIZE);

    /** Single object of class */
    private static PortAccessService INSTANCE = new PortAccessService();

    /**
     * private constructor according Singleton design pattern
     */
    private PortAccessService(){}

    /**
     * @return single object of class
     */
    public static PortAccessService getInstance(){
        return INSTANCE;
    }

    /**
     * adds all threads that want to be executed in list, and hold them while there is no
     * free thread from pool
     * @param ship remote thread that want to be executed
     */
    public void getAccessToPort(Runnable ship){
        executorService.execute(ship);
    }
}
