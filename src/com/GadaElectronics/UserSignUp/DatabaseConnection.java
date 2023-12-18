package com.GadaElectronics.UserSignUp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static Connection con;
    public static Connection create() throws RuntimeException {
        final String driver_class="com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver_class);
            String user="root";
            String password="mysql";
            String url="jdbc:mysql://localhost:3306/gadaelectronics";

            con=DriverManager.getConnection(url,user,password);
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
