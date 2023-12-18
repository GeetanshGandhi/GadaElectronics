package com.GadaElectronics.MainMenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection extends DatabaseConnectivity {
    public Connection GetDatabaseConnection(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(host,mysqlUsername,mysqlPassword);
        }
        catch (SQLException e) {
            System.out.println("Could not connect to mysql server");
        }
        return con;
    }
}