package com.gv.port.logging;

import org.apache.log4j.Logger;
import java.util.concurrent.LinkedBlockingQueue;

public class PortLogger {

    private static final Logger log = Logger.getLogger(PortLogger.class);

    private static LinkedBlockingQueue<String> lastLogInfo = new LinkedBlockingQueue<String>();

    public static Logger getLogger(){
        return log;
    }

    public static void addLogInfo(String message){
        try {
            lastLogInfo.put(message);
        } catch (InterruptedException e){
            log.error(e);
        }
    }

    public static String[] getLastLogInfo(){
        int logInfoSize = lastLogInfo.size();
        String[] logInfo = new String[logInfoSize];
        for(int i = 0; i < logInfoSize; i++){
            logInfo[i] = lastLogInfo.poll();
            log.info(logInfo[i]);
        }
        return logInfo;
    }
}
