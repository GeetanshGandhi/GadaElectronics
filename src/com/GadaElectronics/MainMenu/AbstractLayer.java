package com.GadaElectronics.MainMenu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

abstract class DatabaseConnectivity {
    static final String host = "jdbc:mysql://localhost:3306/GadaElectronics";
    static final String mysqlUsername = "root";
    static final String mysqlPassword = "mysql";
    abstract Connection GetDatabaseConnection();
}
abstract class FetchDatabase{
    abstract ArrayList<String> FetchCategories();
    abstract ArrayList<String> FetchBrands(String Category);
    abstract ArrayList<String> FetchModels(String Category, String Brand);
    abstract ResultSet FetchProductDetails(String Category, String Brand, String Model);
}
interface MainMenuUI{
    void ShowMenu(ArrayList<String> userDet);
    void Cart(HashMap<String,String> cartItems);
}