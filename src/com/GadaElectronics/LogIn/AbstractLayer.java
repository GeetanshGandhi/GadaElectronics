package com.GadaElectronics.LogIn;

import java.sql.Connection;
import java.util.ArrayList;

abstract class DatabaseConnectivity {
    static final String host = "jdbc:mysql://localhost:3306/GadaElectronics";
    static final String mysqlUsername = "root";
    static final String mysqlPassword = "mysql";

    abstract Connection GetDatabaseConnection();
}
abstract class LogInProcess{
    abstract ArrayList<String> ValidateLogin(String user, String password);
}

interface LoginPageUI{
    void LogIn();
}