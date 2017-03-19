package com.gv.port.logging;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class PortLoggerTest {

    @Test
    public void portLoggerTest1() throws Exception {
        final List<String> threadsMessages = new ArrayList<String>(6);
        threadsMessages.add("Thread 1. Log info 1");
        threadsMessages.add("Thread 1. Log info 2");
        threadsMessages.add("Thread 1. Log info 3");
        threadsMessages.add("Thread 2. Log info 1");
        threadsMessages.add("Thread 2. Log info 2");
        threadsMessages.add("Thread 2. Log info 3");

        new Thread(){
            @Override
            public void run() {
                PortLogger.addLogInfo("Thread 1. Log info 1");
                PortLogger.addLogInfo("Thread 1. Log info 2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    System.err.println(e);
                }
                PortLogger.addLogInfo("Thread 1. Log info 3");
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                PortLogger.addLogInfo("Thread 2. Log info 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    System.err.println(e);
                }
                PortLogger.addLogInfo("Thread 2. Log info 2");
                PortLogger.addLogInfo("Thread 2. Log info 3");
            }
        }.start();

        for(int i = 0; i < 2; i++) {
            try {
                Thread.sleep(1000);
                String[] logInfo = PortLogger.getLastLogInfo();
                for (int j = 0; j < logInfo.length; j++) {
                    Assert.assertTrue(threadsMessages.contains(logInfo[j]));
                    System.out.println("Check number " + i + " : " + logInfo[j]);
                }
            } catch (InterruptedException e){
                System.err.println(e);
            }
        }
    }
}