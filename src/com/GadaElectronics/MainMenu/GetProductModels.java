package com.GadaElectronics.MainMenu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

abstract class GetProductModels extends GetProductBrands{
    ArrayList<String> FetchModels(String category, String brand){
        ArrayList<String> models = new ArrayList<>();
        try{
            DatabaseConnection db = new DatabaseConnection();
            Connection con = db.GetDatabaseConnection();
            Statement line = con.createStatement();
            String fetchQuery = "select DISTINCT Product_Name from inventory";
            fetchQuery+= " where Product_Category = \""+category;
            fetchQuery+= "\" AND Product_Company = \""+brand+"\"";
            ResultSet res = line.executeQuery(fetchQuery);
            while(res.next()){
                models.add(res.getString("Product_Name"));
            }
        }
        catch(SQLException e){
            System.out.println("Could not Fetch required result!");
            e.printStackTrace();
        }
        return models;
    }
}