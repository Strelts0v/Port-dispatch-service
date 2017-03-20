package com.gv.port.logging;

import org.apache.log4j.Logger;
import java.util.concurrent.LinkedBlockingQueue;

public class PortLogger {

    private static final Logger log = Logger.getLogger(PortLogger.class);

    private static LinkedBlockingQueue<String> lastLogInfo = new LinkedBlockingQueue<String>();

    public static Logger getLogger(){
        return log;
    }

    public static String shipIdInFirstDock;

    public static String shipIdInSecondDock;

    public static String shipIdInThirdDock;

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

    public static String getShipIdInFirstDock() {
        return shipIdInFirstDock;
    }

    public static void setShipIdInFirstDock(String shipIdInFirstDock) {
        PortLogger.shipIdInFirstDock = shipIdInFirstDock;
    }

    public static String getShipIdInSecondDock() {
        return shipIdInSecondDock;
    }

    public static void setShipIdInSecondDock(String shipIdInSecondDock) {
        PortLogger.shipIdInSecondDock = shipIdInSecondDock;
    }

    public static String getShipIdInThirdDock() {
        return shipIdInThirdDock;
    }

    public static void setShipIdInThirdDock(String shipIdInThirdDock) {
        PortLogger.shipIdInThirdDock = shipIdInThirdDock;
    }
}
