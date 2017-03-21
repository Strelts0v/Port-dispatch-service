package com.gv.port.logging;

import org.apache.log4j.Logger;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * logger class that realized Singleton pattern. Uses log4j library for logging and holds last
 * log info from ships threads
 */
public class PortLogger {

    /** Object from log4j library that used for logging in separate file */
    private static final Logger log = Logger.getLogger(PortLogger.class);

    /** Object of blocking queue that hold last log info */
    private static LinkedBlockingQueue<String> lastLogInfo = new LinkedBlockingQueue<String>();

    /** returns Logger object */
    public static Logger getLogger(){
        return log;
    }

    /** variable for storing if of ship that at this moment holds dock #1 */
    public static String shipIdInFirstDock;

    /** variable for storing if of ship that at this moment holds dock #2 */
    public static String shipIdInSecondDock;

    /** variable for storing if of ship that at this moment holds dock #3 */
    public static String shipIdInThirdDock;

    /**
     * saves log message to blocking queue
     * @param message - log message from ship thread
     */
    public static void addLogInfo(String message){
        try {
            lastLogInfo.put(message);
        } catch (InterruptedException e){
            log.error(e);
        }
    }

    /**
     * returns last log messages from ship threads
     * @return array of messages
     */
    public static String[] getLastLogInfo(){
        int logInfoSize = lastLogInfo.size();
        String[] logInfo = new String[logInfoSize];
        for(int i = 0; i < logInfoSize; i++){
            logInfo[i] = lastLogInfo.poll();
            log.info(logInfo[i]);
        }
        return logInfo;
    }

    /**
     * @return variable for storing if of ship that at this moment holds dock #1
     */
    public static String getShipIdInFirstDock() {
        return shipIdInFirstDock;
    }

    /**
     * sets variable for storing if of ship that at this moment holds dock #1
     * @param shipIdInFirstDock current ship id that at this moment holds dock #1
     */
    public static void setShipIdInFirstDock(String shipIdInFirstDock) {
        PortLogger.shipIdInFirstDock = shipIdInFirstDock;
    }

    /**
     * @return variable for storing if of ship that at this moment holds dock #2
     */
    public static String getShipIdInSecondDock() {
        return shipIdInSecondDock;
    }

    /**
     * sets variable for storing if of ship that at this moment holds dock #2
     * @param shipIdInSecondDock current ship id that at this moment holds dock #2
     */
    public static void setShipIdInSecondDock(String shipIdInSecondDock) {
        PortLogger.shipIdInSecondDock = shipIdInSecondDock;
    }

    /**
     * @return variable for storing if of ship that at this moment holds dock #3
     */
    public static String getShipIdInThirdDock() {
        return shipIdInThirdDock;
    }

    /**
     * sets variable for storing if of ship that at this moment holds dock #3
     * @param shipIdInThirdDock current ship id that at this moment holds dock #3
     */
    public static void setShipIdInThirdDock(String shipIdInThirdDock) {
        PortLogger.shipIdInThirdDock = shipIdInThirdDock;
    }
}
