package com.gv.port.portStructure;

import com.gv.port.portStructure.docks.Dock;
import org.junit.Assert;
import org.junit.Test;

public class PortTest {
    @Test
    public void Test1() throws Exception {
        Port port = Port.getInstance();
        Assert.assertNotNull(port);
        Assert.assertEquals(3, port.getDockCount());
    }

    @Test
    public void Test2() throws Exception {
        Port port = Port.getInstance();
        Dock dock1 = port.getDock(1);
        Dock dock2 = port.getDock(1);
        Dock dock3 = port.getDock(1);

        Assert.assertNotNull(dock1);
        Assert.assertNotNull(dock2);
        Assert.assertNotNull(dock3);

        port.returnDock(dock1);
        dock1 = port.getDock(1);
        Assert.assertNotNull(dock1);

        port.returnDock(dock1);
        port.returnDock(dock2);
        port.returnDock(dock3);
    }

    @Test
    public void Test3() throws Exception {
        Port port = Port.getInstance();
        Dock dock1 = port.getDock(1);
        Assert.assertTrue(dock1.downloadSupplies(1000));
        Assert.assertTrue(dock1.unloadSupplies(1000));
    }
}