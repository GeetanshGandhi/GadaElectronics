package com.GadaElectronics.MainMenu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetProductDetails extends GetProductModels{

    @Override
    ResultSet FetchProductDetails(String Category, String Brand, String Model) {
        ResultSet res = null;
        try{
            DatabaseConnection db = new DatabaseConnection();
            Connection con = db.GetDatabaseConnection();
            Statement line = con.createStatement();
            String fetchQuery = "select * from inventory where product_category = \"";
            fetchQuery+= Category+ "\" and product_company = \""+Brand+"\"";
            fetchQuery+= " and product_name = \""+Model+"\"";

            res = line.executeQuery(fetchQuery);
        }
        catch(SQLException e){
            System.out.println("Could not Fetch required result!");
            e.printStackTrace();
        }
        return res;
    }
}