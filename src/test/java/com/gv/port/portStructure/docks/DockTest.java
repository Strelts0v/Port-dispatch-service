package com.gv.port.portStructure.docks;

import com.gv.port.portStructure.storages.Storage;
import org.junit.Assert;
import org.junit.Test;

public class DockTest {
    @Test
    public void Test1() throws Exception {
        Storage storage = new Storage(70);
        Dock dock = new Dock(storage);
        Assert.assertTrue(dock.downloadSupplies(47));
        Assert.assertTrue(dock.unloadSupplies(47));
        Assert.assertEquals(70, storage.getSupplyCount());
    }

    @Test
    public void Test2() throws Exception {
        Storage storage = new Storage(0);
        Dock dock = new Dock(storage);
        Assert.assertFalse(dock.downloadSupplies(47));
        Assert.assertTrue(dock.unloadSupplies(47));
        Assert.assertEquals(47, storage.getSupplyCount());
    }

    @Test
    public void Test3() throws Exception {
        Storage storage = new Storage(Integer.MAX_VALUE);
        Dock dock = new Dock(storage);
        Assert.assertFalse(dock.unloadSupplies(47));
        Assert.assertTrue(dock.downloadSupplies(47));
        Assert.assertEquals(Integer.MAX_VALUE - 47, storage.getSupplyCount());
    }
}