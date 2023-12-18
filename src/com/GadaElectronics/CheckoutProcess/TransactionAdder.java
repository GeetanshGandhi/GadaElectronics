package com.GadaElectronics.CheckoutProcess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("ALL")
public class TransactionAdder extends InformationExtractor{
    /*each arrayList element is arraylist as follows
    email_id,product_code,payment method, address
     */
    @Override
    boolean AddTransactionOnConfirmation(ArrayList<ArrayList<String>> data) {
        boolean flag = false;
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currDate = new Date();
            String strDate = formatter.format(currDate);
            String date = strDate.substring(0,10);
            String time = strDate.substring(11,19);
            Connection con = (new DatabaseConnection()).GetDatabaseConnection();
            Statement line = con.createStatement();
            for(ArrayList<String> iterate: data){
                String query = "insert into transactions(email_id,product_code, date_checkout, time_checkout, payment_method, address) values(?,?,?,?,?,?)";
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1,iterate.get(0));
                st.setString(2,iterate.get(1));
                st.setString(3,date);
                st.setString(4,time);
                st.setString(5,iterate.get(2));
                st.setString(6,iterate.get(3));
                st.executeUpdate();
                String qtyquery = "update inventory set product_quantity = product_quantity-1 where product_code = ?";
                PreparedStatement st2 = con.prepareStatement(qtyquery);
                st2.setString(1, iterate.get(1));
                st2.executeUpdate();
            }
            con.close();
        } catch(SQLException e){
            return flag;
        }
        flag = true;
        return flag;
    }
}