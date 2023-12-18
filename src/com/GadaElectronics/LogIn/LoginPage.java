package com.GadaElectronics.LogIn;

import com.GadaElectronics.MainMenu.MainMenu;
import com.GadaElectronics.UserSignUp.SignUpUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LoginPage implements LoginPageUI{
    @Override
    public void LogIn() {
        //getting screen size of window and determining width and height
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        //creating frame
        JFrame loginPage = new JFrame();
        try{
            Image im = ImageIO.read(new File("background.jpg"));
            Image newImage = im.getScaledInstance(screenWidth, screenHeight, Image.SCALE_DEFAULT);
            loginPage.setContentPane(new JLabel(new ImageIcon(newImage)));
        }catch(IOException e){
            e.printStackTrace();
        }

        //creating labels: 1) welcome label
        JLabel title = new JLabel("GADA ELECTRONICS");
        title.setBounds(screenWidth/2 -500,50,1000,60);
        title.setFont(new Font("Algerian", Font.PLAIN, 60));

        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.RED);
        loginPage.add(title);
        //2) login to continue label
        JLabel loginReq = new JLabel("Log In");
        loginReq.setBounds(screenWidth/2 - 75, 125, 150, 50);
        loginReq.setFont(new Font("Arial",Font.BOLD, 40));
        loginReq.setForeground(new Color(0,0,100));
        loginPage.add(loginReq);
        //3) email request
        JLabel emailReq = new JLabel("E-mail:");
        emailReq.setBounds(screenWidth/2 - 150, 225, 300,30);
        emailReq.setForeground(new Color(0,0,150));
        emailReq.setFont(new Font("Consolas", Font.ITALIC, 28));
        emailReq.setHorizontalAlignment(JLabel.CENTER);
        loginPage.add(emailReq);
        //4)PASSWORD REQ
        JLabel passReq = new JLabel("Password:");
        passReq.setBounds(screenWidth/2 - 150, 350, 300,30);
        passReq.setForeground(new Color(0,0,150));
        passReq.setFont(new Font("Consolas", Font.ITALIC, 28));
        passReq.setHorizontalAlignment(JLabel.CENTER);
        loginPage.add(passReq);
        //5)new to our venture label
        JLabel signupMsg = new JLabel("New to our venture?");
        signupMsg.setBounds(screenWidth/2 -185, 603, 230,20);
        signupMsg.setForeground(new Color(0,0,0));
        signupMsg.setFont(new Font("Consolas", Font.PLAIN, 20));
        signupMsg.setHorizontalAlignment(JLabel.RIGHT);
//        signupMsg.setBorder(blackline);
        loginPage.add(signupMsg);
        //6) sign up button as a label
        JLabel signupButton = new JLabel("<html><u>Sign up here</u></html>");
        signupButton.setBounds(screenWidth/2 +50, 600,140,20);
        signupButton.setForeground(new Color(0,0,255));
        signupButton.setFont(new Font("Consolas", Font.PLAIN, 20));
        signupButton.setHorizontalAlignment(JLabel.LEFT);
        signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SignUpUI signup = new SignUpUI();
                signup.SignUpUIWindow();
            }
        });
        loginPage.add(signupButton);

        //TEXT FIELDS: 1) email
        JTextField email = new JTextField();
        email.setBounds(screenWidth/2 - 150, 275,300,30);
        email.setFont(new Font("Monospaced", Font.BOLD, 17));
        loginPage.add(email);
        //2) password field
        JPasswordField password = new JPasswordField();
        password.setBounds(screenWidth/2 - 150, 400, 300,30);
        password.setFont(new Font("Monospaced",Font.BOLD, 17));
        loginPage.add(password);
        //check box for show password
        JCheckBox showPass = new JCheckBox("Show Password");
        showPass.setBounds(screenWidth/2 - 100, 450,200,22);
        showPass.setHorizontalAlignment(JLabel.CENTER);
        showPass.setFont(new Font("Serif", Font.ITALIC, 20));
        showPass.setOpaque(false);//to remove bg from chckbox text
        showPass.addActionListener(e -> {
            if(showPass.isSelected()){
                password.setEchoChar((char)0);
            }else {
                password.setEchoChar('*');
            }
        });
        loginPage.add(showPass);

        //ERROR MESSAGE:
        JLabel error = new JLabel("INVALID E-MAIL ID OR PASSWORD!");
        error.setFont(new Font("Arial", Font.BOLD, 20));
        error.setForeground(Color.RED);
        error.setBounds(screenWidth/2 - 175,320,350,22);
        error.setVisible(false);
        loginPage.add(error);

        //BUTTONS: 1) close button:
        JButton closeButton = new JButton("Close");
        closeButton.setBounds(screenWidth/2 -50, 700, 150, 40);
        closeButton.setFont(new Font("Consolas", Font.BOLD, 30));
        closeButton.setBackground(new Color(0,0,150));
        closeButton.setForeground(Color.white);
        closeButton.setFocusPainted(false);
        closeButton.setVerticalTextPosition(JLabel.CENTER);
        closeButton.addActionListener(e -> {
            loginPage.dispose();//to close the window
        });
        loginPage.add(closeButton);
        //2) login button
        JButton loginButton = new JButton("Log in");
        loginButton.setBounds(screenWidth/2 -150, 500, 300, 40);
        loginButton.setFont(new Font("Arial", Font.ITALIC, 20));
        loginButton.setBackground(new Color(255,255,0));
        loginButton.setForeground(Color.black);
        loginButton.setFocusPainted(false);
        loginButton.setVerticalTextPosition(JLabel.CENTER);
        loginButton.addActionListener(e -> {
            LoginValid validate = new LoginValid();
            ArrayList<String> userDet = validate.ValidateLogin(email.getText(),password.getText());
            if (userDet.isEmpty()){
                error.setVisible(true);
            }
            else{
                error.setVisible(false);
                email.setText("");
                password.setText("");
                MainMenu menuWindow = new MainMenu();
                menuWindow.ShowMenu(userDet);
            }
        });
        loginPage.add(loginButton);

        loginPage.setExtendedState(JFrame.MAXIMIZED_BOTH);
        loginPage.setUndecorated(true);
        loginPage.getContentPane().setBackground(Color.BLACK);
        loginPage.setLayout(null);
        loginPage.pack();
        loginPage.setVisible(true);
    }
}