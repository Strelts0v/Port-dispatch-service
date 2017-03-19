package com.gv.port.database;

import org.junit.Assert;
import org.junit.Test;

public class DatabaseConfigurationManagerTest {
    @Test
    public void getPropertyTest() throws Exception {
        Assert.assertEquals("port", DatabaseConfigurationManager.getProperty("database.name"));
        Assert.assertEquals("root", DatabaseConfigurationManager.getProperty("database.user"));
        Assert.assertEquals("", DatabaseConfigurationManager.getProperty("database.password"));
        Assert.assertEquals("jdbc:mysql://localhost:3306/", DatabaseConfigurationManager.getProperty("database.url"));
    }

}