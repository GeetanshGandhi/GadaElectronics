package com.GadaElectronics.LogIn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class LoginValid extends LogInProcess {
    ArrayList<String> ValidateLogin(String email, String password){
        ArrayList<String> userDet = new ArrayList<>();
        try{
            DatabaseConnection db = new DatabaseConnection();
            Connection con = db.GetDatabaseConnection();
            Statement line = con.createStatement();
            String fetchQuery = "select Email_ID, Password, Name from user";
            ResultSet res = line.executeQuery(fetchQuery);
            while(res.next()){
                String email_db = res.getString("Email_ID");
                String password_db = res.getString("Password");
                if(Objects.equals(email_db,email) && Objects.equals(password_db,password)){
                    userDet.add(email_db);
                    userDet.add(res.getString("Name"));
                    return userDet;
                }
            }
        }
        catch(SQLException e){
            System.out.println("Could not fetch required item");
        }
        return userDet;
    }
}