package com.gv.port.database;

import org.junit.Assert;
import org.junit.Test;
import java.sql.Connection;

public class DatabaseConnectionManagerTest {
    @Test
    public void getDatabaseConnectionTest() throws Exception {
        Connection connection = DatabaseConnectionManager.getDatabaseConnection();
        Assert.assertNotNull(connection);
    }

}