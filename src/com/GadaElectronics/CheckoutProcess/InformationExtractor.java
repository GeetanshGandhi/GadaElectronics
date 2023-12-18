package com.GadaElectronics.CheckoutProcess;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class InformationExtractor extends InitiateCheckout{
    @Override
    ResultSet getProductInformation(LinkedHashMap<String, String> prdDet) {
        ResultSet res = null;
        try{
            DatabaseConnection connector = new DatabaseConnection();
            Connection con = connector.GetDatabaseConnection();
            Statement line = con.createStatement();
            StringBuilder query = new StringBuilder("select * from inventory where product_code = \"");
            for(Map.Entry<String,String> iter:prdDet.entrySet()){
                query.append(iter.getKey()).append("\" or product_code = \"");
            }
            int len = query.length();
            String sub = query.substring(0,len-20);
            res = line.executeQuery(sub);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
}