package com.gv.port.portStructure.storages;

public class Storage {

    private int supplyCount;

    private final static int MAX_SUPPLY_COUNT = Integer.MAX_VALUE;

    public Storage(int supplyCount) {
        this.supplyCount = supplyCount;
    }

    public int getSupplyCount() {
        return supplyCount;
    }

    public void setSupplyCount(int supplyCount) {
        this.supplyCount = supplyCount;
    }

    public synchronized boolean download(int supplies){
        if(checkRange(supplyCount - supplies)) {
            setSupplyCount(supplyCount - supplies);
            return true;
        }
        return false;
    }

    public synchronized boolean unload(int supplies){
        if(checkRange(supplyCount + supplies)) {
            setSupplyCount(supplyCount + supplies);
            return true;
        }
        return false;
    }

    private boolean checkRange(int resultSupplyCount){
        if(resultSupplyCount >= 0 && resultSupplyCount <= MAX_SUPPLY_COUNT){
            return true;
        }
        return false;
    }
}
