package com.GadaElectronics.UserSignUp;

import java.sql.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class InfoValidator {
    private static final String url = "jdbc:mysql://localhost:3306/gadaelectronics";
    private static final String user = "root";
    private static final String password = "mysql";

    public InfoValidator(String Name, String phoneNumber, String email, String password) {
        SignUp user = new SignUp(Name, phoneNumber, email, password);
    }

    public static boolean isPasswordValid(String password) {
        if (password == null || password.length() < 8)
            return false;
        else {
            String uppercaseRegex = ".*[A-Z].*";
            String lowercaseRegex = ".*[a-z].*";
            String digitRegex = ".*\\d.*";
            String specialCharRegex = ".*[@#$%^&!/].*";

            boolean hasUppercase = Pattern.matches(uppercaseRegex, password);
            boolean hasLowercase = Pattern.matches(lowercaseRegex, password);
            boolean hasDigit = Pattern.matches(digitRegex, password);
            boolean hasSpecialChar = Pattern.matches(specialCharRegex, password);

            return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
        }
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        String pattern = "\\d{10}";

        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean uniqueEmail(String email) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);

            String sql = "SELECT COUNT(*) FROM user WHERE email_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
            resultSet.close();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}