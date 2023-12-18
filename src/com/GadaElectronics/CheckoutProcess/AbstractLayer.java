package com.GadaElectronics.CheckoutProcess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;

abstract class DatabaseConnectivity {
    static final String host = "jdbc:mysql://localhost:3306/GadaElectronics";
    static final String mysqlUsername = "root";
    static final String mysqlPassword = "mysql";
    abstract Connection GetDatabaseConnection();
}

abstract class InitiateCheckout{
    abstract boolean AddTransactionOnConfirmation(ArrayList<ArrayList<String>> data);
    abstract ResultSet getProductInformation(LinkedHashMap<String, String> prdDet);
}
interface CheckoutUI{
    void BillWindow(LinkedHashMap<String,String> cart, ArrayList<String> userDet);
}