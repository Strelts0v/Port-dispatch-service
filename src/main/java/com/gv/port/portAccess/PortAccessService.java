package com.gv.port.portAccess;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PortAccessService{

    private static final int POOL_SIZE = 4;

    private final ExecutorService executorService =
            Executors.newFixedThreadPool(POOL_SIZE);

    private static PortAccessService INSTANCE = new PortAccessService();

    private PortAccessService(){}

    public static PortAccessService getInstance(){
        return INSTANCE;
    }

    public void getAccessToPort(Runnable ship){
        executorService.execute(ship);
    }
}
