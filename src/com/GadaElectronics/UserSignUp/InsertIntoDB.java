package com.GadaElectronics.UserSignUp;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertIntoDB
{
    public static boolean insertUsertoDB(SignUp user)
    {
        boolean flag=false;
        //jdbc code
        try
        {
            Connection con = DatabaseConnection.create();
            String q= "insert into user(name, phone_number, email_id, password) values(?,?,?,?)";
            PreparedStatement pstmt=con.prepareStatement(q);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getUserPhoneNumber());
            pstmt.setString(3, user.getUserEmail());
            pstmt.setString(4, user.getUserPassword());
            pstmt.executeUpdate();
            flag=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }
}
