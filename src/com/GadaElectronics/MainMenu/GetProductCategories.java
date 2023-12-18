package com.GadaElectronics.MainMenu;

import java.sql.*;
import java.util.ArrayList;

abstract public class GetProductCategories extends FetchDatabase{
    public ArrayList<String> FetchCategories(){
        ArrayList<String> categories = new ArrayList<>();
        try{
            DatabaseConnection db = new DatabaseConnection();
            Connection con = db.GetDatabaseConnection();
            Statement line = con.createStatement();
            String fetchQuery = "select DISTINCT Product_Category from inventory";
            ResultSet res = line.executeQuery(fetchQuery);
            while(res.next()){
                categories.add(res.getString("Product_Category"));
            }
        }
        catch(SQLException e){
            System.out.println("Could not Fetch required result!");
        }
        return categories;
    }
}