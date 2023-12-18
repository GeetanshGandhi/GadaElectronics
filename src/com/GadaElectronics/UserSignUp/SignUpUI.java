package com.GadaElectronics.UserSignUp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.GadaElectronics.UserSignUp.InfoValidator.*;
import static com.GadaElectronics.UserSignUp.InsertIntoDB.*;


public class SignUpUI extends Component {
    String user_Name = "";
    String user_PhoneNumber = "";
    String user_Email = "";
    String user_Password = "";


    public void SignUpUIWindow() {

        int height = 640;
        int width = 960;

        //creating frame
        JFrame signUpPage = new JFrame();
        //signUpPage.setSize(width,height);
        try {
            Image im = ImageIO.read(new File("background.jpg"));
            Image newImage = im.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            signUpPage.setContentPane(new JLabel(new ImageIcon(newImage)));

        } catch (IOException e) {
            e.printStackTrace();
        }

        //creating labels: 1) title: GADA ELECTRONICS
        JLabel title = new JLabel("GADA ELECTRONICS");
        title.setBounds(width/2 -500,50,1000,50);
        title.setFont(new Font("Algerian", Font.PLAIN, 50));

        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.RED);
        signUpPage.add(title);

        //2) subtitle: Sign Up
        JLabel signupReq = new JLabel("Sign Up");
        signupReq.setBounds(width/2 - 100, 100, 200, 40);
        signupReq.setHorizontalAlignment(JLabel.CENTER);
        signupReq.setFont(new Font("Arial",Font.BOLD, 30));
        signupReq.setForeground(new Color(0,0,100));
        signUpPage.add(signupReq);

        //3) name request
        JLabel nameReq = new JLabel("Name:");
        nameReq.setBounds(width/2 - 150, 150, 300,23);
        nameReq.setForeground(new Color(0,0,150));
        nameReq.setFont(new Font("Consolas", Font.ITALIC, 20));
        nameReq.setHorizontalAlignment(JLabel.CENTER);
        signUpPage.add(nameReq);

        //4) phone number request
        JLabel phnnoReq = new JLabel("Phone Number:");
        phnnoReq.setBounds(width/2 - 150, 225, 300,23);
        phnnoReq.setForeground(new Color(0,0,150));
        phnnoReq.setFont(new Font("Consolas", Font.ITALIC, 20));
        phnnoReq.setHorizontalAlignment(JLabel.CENTER);
        signUpPage.add(phnnoReq);

        //5) email request
        JLabel emailReq = new JLabel("E-mail:");
        emailReq.setBounds(width/2 - 150, 300, 300,23);
        emailReq.setForeground(new Color(0,0,150));
        emailReq.setFont(new Font("Consolas", Font.ITALIC, 20));
        emailReq.setHorizontalAlignment(JLabel.CENTER);
        signUpPage.add(emailReq);

        //6) password request
        JLabel pswdReq = new JLabel("Password:");
        pswdReq.setBounds(width/2 - 150, 375, 300,23);
        pswdReq.setForeground(new Color(0,0,150));
        pswdReq.setFont(new Font("Consolas", Font.ITALIC, 20));
        pswdReq.setHorizontalAlignment(JLabel.CENTER);
        signUpPage.add(pswdReq);

        //TEXT FIELDS:

        // 1) name
        JTextField name = new JTextField();
        name.setBounds(width/2 - 150, 175,300,25);
        name.setFont(new Font("Monospaced", Font.BOLD, 18));
        signUpPage.add(name);

        // 2) phone number
        JTextField phnno = new JTextField();
        phnno.setBounds(width/2 - 150, 250,300,25);
        phnno.setFont(new Font("Monospaced", Font.BOLD, 18));
        signUpPage.add(phnno);

        // 3) email
        JTextField email = new JTextField();
        email.setBounds(width/2 - 150, 325,300,25);
        email.setFont(new Font("Monospaced", Font.BOLD, 18));
        signUpPage.add(email);

        // 4) password
        JPasswordField password = new JPasswordField();
        password.setBounds(width/2 - 150, 400,300,25);
        password.setFont(new Font("Monospaced", Font.BOLD, 18));
        signUpPage.add(password);

        //show passwrod checkbox
        JCheckBox showPass = new JCheckBox("Show Password");
        showPass.setBounds(width/2 - 100, 450,200,22);
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
        signUpPage.add(showPass);
        //Errors
        //1)Enter all fields
        JLabel error1=new JLabel("Please enter all fields.");
        error1.setBounds(width/2-100, 500, 200, 40);
        error1.setHorizontalAlignment(JLabel.CENTER);
        error1.setFont(new Font("Arial",Font.BOLD, 15));
        error1.setForeground(Color.RED);
        signUpPage.add(error1);
        error1.setVisible(false);

        //2)Valid password
        JLabel error2=new JLabel("Password must contain at least one uppercase character, lowercase character, number and special character and must exceed eight characters.");
        error2.setBounds(width/2-100, 500, 600, 40);
        error2.setHorizontalAlignment(JLabel.CENTER);
        error2.setFont(new Font("Arial",Font.BOLD, 15));
        error2.setForeground(Color.RED);
        signUpPage.add(error2);
        error2.setVisible(false);

        //3)Valid phone number
        JLabel error3=new JLabel("Please enter a valid 10 digit phone number.");
        error3.setBounds(width/2-200, 500, 400, 40);
        error3.setHorizontalAlignment(JLabel.CENTER);
        error3.setFont(new Font("Arial",Font.BOLD, 15));
        error3.setForeground(Color.RED);
        signUpPage.add(error3);
        error3.setVisible(false);

        //4) unique email
        JLabel error4=new JLabel("Please enter a unique email.");
        error4.setBounds(width/2-200, 500, 400, 40);
        error4.setHorizontalAlignment(JLabel.CENTER);
        error4.setFont(new Font("Arial",Font.BOLD, 15));
        error4.setForeground(Color.RED);
        signUpPage.add(error4);
        error4.setVisible(false);

        //EXTRAS
        //1)success message
        JLabel successmsg=new JLabel("<html>Sign up successful. Please log in to continue.<br>You can now close the window</html>");
        successmsg.setBounds(width/2-250, 480, 500, 40);
        successmsg.setHorizontalAlignment(JLabel.CENTER);
        successmsg.setFont(new Font("Arial",Font.BOLD, 15));
        successmsg.setForeground(new Color(0,150,0));
        signUpPage.add(successmsg);
        successmsg.setVisible(false);

        //2)failure message
        JLabel failmsg=new JLabel("Something went wrong. Please try again.");
        failmsg.setBounds(width/2-250, 500, 500, 40);
        failmsg.setHorizontalAlignment(JLabel.CENTER);
        failmsg.setFont(new Font("Arial",Font.BOLD, 15));
        failmsg.setForeground(new Color(200,0,0));
        signUpPage.add(failmsg);
        failmsg.setVisible(false);

        //BUTTONS: 1) exit button
        JButton exitButton = new JButton("Close");
        exitButton.setBounds(width/2 -200, 550, 150, 40);
        exitButton.setFont(new Font("Consolas", Font.BOLD, 22));
        exitButton.setBackground(new Color(0,0,150));
        exitButton.setForeground(Color.white);
        exitButton.setFocusPainted(false);
        exitButton.setVerticalTextPosition(JLabel.CENTER);
        exitButton.addActionListener(e -> {
            signUpPage.dispose();//to close the window
        });
        signUpPage.add(exitButton);
        //2) signup button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(width/2 +50, 550, 150, 40);
        signUpButton.setFont(new Font("Arial", Font.ITALIC, 20));
        signUpButton.setBackground(new Color(255,255,0));
        signUpButton.setForeground(Color.black);
        signUpButton.setFocusPainted(false);
        signUpButton.setVerticalTextPosition(JLabel.CENTER);
        signUpButton.addActionListener(e -> {
            user_Name = name.getText();
            user_Email = email.getText();
            user_Password = password.getText();
            user_PhoneNumber = phnno.getText();
            if (Objects.equals(user_Name, "") || Objects.equals(user_PhoneNumber, "") || Objects.equals(user_Email, "") || Objects.equals(user_Password, ""))
            {
                error1.setVisible(true);
                error2.setVisible(false);
                error3.setVisible(false);
                error4.setVisible(false);
                return;
            }
            else if (!isPhoneNumberValid(user_PhoneNumber)){
                error1.setVisible(false);
                error2.setVisible(false);
                error3.setVisible(true);
                error4.setVisible(false);
                return;
            }
            else if (!isPasswordValid(user_Password)){
                error1.setVisible(false);
                error2.setVisible(true);
                error3.setVisible(false);
                error4.setVisible(false);
                return;
            }
            else if (uniqueEmail(user_Email)){
                error1.setVisible(false);
                error2.setVisible(false);
                error3.setVisible(false);
                error4.setVisible(true);
                return;
            }
            try {
                SignUp user = new SignUp(user_Name, user_PhoneNumber, user_Email, user_Password);
                boolean flagout = insertUsertoDB(user);
                error1.setVisible(false);
                error2.setVisible(false);
                error3.setVisible(false);
                error4.setVisible(false);
                if (flagout) {
                    successmsg.setVisible(true);
                    signUpButton.setEnabled(false);
                }
                else{
                    failmsg.setVisible(true);
                }

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        signUpPage.add(signUpButton);

        signUpPage.setPreferredSize(new Dimension(width, height));
        signUpPage.setResizable(false);
        signUpPage.pack();
        signUpPage.setVisible(true);

    }
}