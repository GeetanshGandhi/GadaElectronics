package com.GadaElectronics.MainMenu;

import java.sql.*;
import java.util.ArrayList;

abstract public class GetProductBrands extends GetProductCategories {
    ArrayList<String> FetchBrands(String category){
        ArrayList<String> brands = new ArrayList<>();
        try{
            DatabaseConnection db = new DatabaseConnection();
            Connection con = db.GetDatabaseConnection();
            Statement line = con.createStatement();
            String fetchQuery = "select DISTINCT Product_Company from inventory where Product_Category = \"%s\"";
            fetchQuery = String.format(fetchQuery,category);

            ResultSet res = line.executeQuery(fetchQuery);
            while(res.next()){
                brands.add(res.getString("Product_Company"));
            }
        }
        catch(SQLException e){
            System.out.println("Could not Fetch required result!");
            e.printStackTrace();
        }
        return brands;
    }
}