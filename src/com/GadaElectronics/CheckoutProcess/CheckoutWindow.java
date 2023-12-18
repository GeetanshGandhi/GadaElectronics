package com.GadaElectronics.CheckoutProcess;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CheckoutWindow implements CheckoutUI{
    final Font subheadFont = new Font("Consolas",Font.BOLD,25);
    final Font entryFont = new Font("Arial",Font.PLAIN, 20);
    final Font costMsgFont= new Font("Consolas", Font.PLAIN,20);
    final Color subheadColor = new Color(0,155,255);
    final Color entryColor = new Color(0,0,100);
    @Override
    public void BillWindow(LinkedHashMap<String, String> cart, ArrayList<String> userDet) {
        //getting procudt data from sql server
        TransactionAdder callObject = new TransactionAdder();
        ResultSet prodDet = callObject.getProductInformation(cart);
        ResultSet auxSet = callObject.getProductInformation(cart);
        //UI starts here
        JFrame bill = new JFrame("Home");
        int height = 730, width = 1300;
        try{
            Image im = ImageIO.read(new File("background.jpg"));
            Image newImage = im.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            bill.setContentPane(new JLabel(new ImageIcon(newImage)));        }
        catch(IOException e){
            e.printStackTrace();
        }
        Border blackline = BorderFactory.createLineBorder(Color.black);

        //LABELS
        //1) main heading
        JLabel head = new JLabel("Checkout");
        head.setBounds(width/2 - 100,10,200,35);
        head.setForeground(Color.RED);
        head.setHorizontalAlignment(JLabel.CENTER);
        head.setFont(new Font("Centaur", Font.BOLD, 33));
        bill.add(head);
        //2)signed in as label
        JLabel signedin = new JLabel("<html>Signed in as: <u>"+userDet.get(1)+"</u></html>");
        signedin.setBounds(950,40,300,20);
        signedin.setFont(new Font("Monospaced", Font.ITALIC, 17));
        signedin.setForeground(Color.BLACK);
        bill.add(signedin);
        //3)date tag
        String currDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        JLabel date = new JLabel("Date: "+currDate);
        date.setBounds(1010,65,180,20);
        date.setFont(new Font("Monospaced", Font.ITALIC, 17));
        date.setForeground(Color.BLACK);
        bill.add(date);
        //4) tax invoice
        JLabel subhead = new JLabel("TAX INVOICE");
        subhead.setBounds(width/2-150,60,300,34);
        subhead.setForeground(new Color(0,0,150));
        subhead.setFont(new Font("Algerian",Font.PLAIN,30));
        subhead.setHorizontalAlignment(JLabel.CENTER);
        bill.add(subhead);
        //5) serial number
        JLabel sno = new JLabel("S.No.");
        sno.setBounds(70,130,100,27);
        sno.setFont(subheadFont);
        sno.setForeground(subheadColor);
        sno.setHorizontalAlignment(JLabel.CENTER);
        bill.add(sno);
        //6) particulars
        JLabel particulars = new JLabel("Particulars");
        particulars.setBounds(175,130,700,27);
        particulars.setFont(subheadFont);
        particulars.setForeground(subheadColor);
        particulars.setHorizontalAlignment(JLabel.CENTER);
        bill.add(particulars);
        //7) Price
        JLabel price = new JLabel("Price");
        price.setBounds(925,130,250,27);
        price.setFont(subheadFont);
        price.setHorizontalAlignment(JLabel.CENTER);
        price.setForeground(subheadColor);
        bill.add(price);

        //8)looping entries
        double totalPrice = 0.0;
        try{
            int counter = 1, y_coordinate = 160;
            while(prodDet.next()) {
                int currprice = prodDet.getInt("Product_Price");
                String currname = prodDet.getString("product_name");
                String currcode = prodDet.getString("product_code");
                //8.1) serial number
                JLabel snoentry = new JLabel(counter + ".");
                snoentry.setBounds(70, y_coordinate, 50, 23);
                snoentry.setForeground(entryColor);
                snoentry.setFont(entryFont);
                snoentry.setHorizontalAlignment(JLabel.CENTER);
                bill.add(snoentry);
                //8.2) particular
                JLabel prdName = new JLabel(currcode+" : "+currname);
                prdName.setBounds(225, y_coordinate, 650, 23);
                prdName.setForeground(entryColor);
                prdName.setFont(entryFont);
                bill.add(prdName);
                //8.3) price
                JLabel priceentry = new JLabel(currprice+".0/-");
                priceentry.setBounds(950,y_coordinate,170,23);
                priceentry.setForeground(entryColor);
                priceentry.setFont(new Font("Consolas", Font.ITALIC,20));
                priceentry.setHorizontalAlignment(JLabel.RIGHT);
                bill.add(priceentry);

                totalPrice += currprice;
                //System.out.println(currprice+" "+totalPrice+" "+iter.getValue());
                counter++;
                y_coordinate+=40;
            }
        }catch (SQLException e) {
            System.out.println("sql error");
            e.printStackTrace();
        }

        //printing total costs and taxes
        JLabel ttl = new JLabel("Total Cost: ");
        ttl.setBounds(775,400,250,22);
        ttl.setFont(costMsgFont);
        ttl.setHorizontalAlignment(JLabel.RIGHT);
        bill.add(ttl);
        //tax labels
        JLabel cgst = new JLabel("CGST@9%: ");
        cgst.setBounds(775,430,250,22);
        cgst.setFont(costMsgFont);
        cgst.setHorizontalAlignment(JLabel.RIGHT);
        bill.add(cgst);
        JLabel sgst = new JLabel("SGST@9%: ");
        sgst.setBounds(775,460,250,22);
        sgst.setFont(costMsgFont);
        sgst.setHorizontalAlignment(JLabel.RIGHT);
        bill.add(sgst);
        //total payable
        JLabel payable = new JLabel("Payable Amount: ");
        payable.setBounds(775,490,250,22);
        payable.setHorizontalAlignment(JLabel.RIGHT);
        payable.setFont(costMsgFont);
        bill.add(payable);

        //final amounts
        double tax = 0.09*totalPrice;
        double payableamount = totalPrice+(2*tax);
        JLabel wotax = new JLabel(totalPrice+"/-");
        wotax.setBounds(1025,400,170,22);
        wotax.setFont(costMsgFont);
        bill.add(wotax);
        JLabel cgstAmt = new JLabel(tax+"/-");
        cgstAmt.setBounds(1025,430,170,22);
        cgstAmt.setFont(costMsgFont);
        bill.add(cgstAmt);
        JLabel sgstAmt = new JLabel(tax+"/-");
        sgstAmt.setBounds(1025,460,170,22);
        sgstAmt.setFont(costMsgFont);
        bill.add(sgstAmt);
        JLabel payableAmt = new JLabel(payableamount+"/-");
        payableAmt.setBounds(1025,490,170,22);
        payableAmt.setFont(costMsgFont);
        bill.add(payableAmt);

        //payment method
        JLabel pmMsg = new JLabel("Payment Method: ");
        pmMsg.setBounds(40,360,250,25);
        pmMsg.setFont(new Font("monospaced", Font.BOLD,20));
        pmMsg.setForeground(Color.RED);
        bill.add(pmMsg);
        String[] paymentMethods = {"Cash on Delivery","UPI","Net Banking","Credit Card","Debit Card"};
        JComboBox<String> methods = new JComboBox<>(paymentMethods);
        methods.setBounds(340,360,220,30);
        methods.setFont(new Font("Arial",Font.PLAIN,20));
        methods.setForeground(Color.BLACK);
        methods.setSelectedItem(paymentMethods[0]);
        methods.setFocusable(false);
        bill.add(methods);
        //address prompt
        JLabel adMsg = new JLabel("Address: ");
        adMsg.setBounds(40, 420, 150,25);
        adMsg.setFont(new Font("monospaced", Font.BOLD, 20));
        adMsg.setForeground(Color.RED);
        bill.add(adMsg);
        //address guideline
        String guideText = "Make sure you enter your complete address in proper format for hassle free delivery!";
        guideText+="<br><b>Format</b>:<br>house no., street name, city - pincode, state, landMark";
        guideText+="<br><b>It should not exceed 100 characters!</b>";
        JLabel guide = new JLabel("<html>"+guideText+"</html>");
        guide.setBounds(40,455,150,160);
        guide.setFont(new Font("Arial", Font.ITALIC, 12));
        bill.add(guide);
        JTextArea addInp = new JTextArea(40,4);
        addInp.setBounds(200, 420, 400, 150);
        addInp.setFont(new Font("Arial",Font.BOLD,15));
        addInp.setLineWrap(true);
        bill.add(addInp);
        //error in address - catches only empty address and lengthy
        JLabel addressError = new JLabel("Address field empty or too long!");
        addressError.setBounds(200,580, 400, 20);
        addressError.setFont(new Font("Consolas",Font.PLAIN,17));
        addressError.setForeground(Color.RED);
        addressError.setVisible(false);
        bill.add(addressError);
        //confirmation button
        JButton confirmButton = new JButton("Confirm Purchase");
        confirmButton.setBounds(width/2 - 110, 640, 220, 35);
        confirmButton.setFont(new Font("Arial", Font.ITALIC,20));
        confirmButton.setBackground(Color.yellow);
        confirmButton.setFocusPainted(false);
        bill.add(confirmButton);
        //discard button
        JButton discard = new JButton("Discard");
        discard.setBounds(40,640,150,35);
        discard.setForeground(Color.WHITE);
        discard.setBackground(new Color(0,0,155));
        discard.setFocusPainted(false);
        discard.setFont(new Font("sans serif",Font.PLAIN,20));
        bill.add(discard);
        //close button
        JButton closeButton = new JButton("Close");
        closeButton.setBounds(1100,640,150,35);
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(new Color(0,0,155));
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Consolas",Font.PLAIN,20));
        bill.add(closeButton);

        //message of successful purchase
        String confirmText = "Purchase Completed! Your order will be delivered to you within 5-7 days.";
        confirmText+="<br>You can now close the window";
        JLabel purchaseDone = new JLabel("<html>"+confirmText+"</html>");
        purchaseDone.setForeground(new Color(0,150,0));
        purchaseDone.setBounds(670, 540, 400, 80);
        purchaseDone.setFont(new Font("Arial", Font.BOLD, 20));
        purchaseDone.setVisible(false);
        bill.add(purchaseDone);

        bill.setPreferredSize(new Dimension(width, height));
        bill.setResizable(false);
        bill.getContentPane().setBackground(Color.CYAN);
        bill.setLayout(null);
        bill.pack();
        bill.setVisible(true);

        //ACTION LISTERNERS
        discard.addActionListener(e -> bill.dispose());
        closeButton.addActionListener(e -> bill.dispose());
        //complete transaction process
        confirmButton.addActionListener(e -> {
            String addressInp = addInp.getText();
            if(Objects.equals(addressInp, "")){
                addressError.setVisible(true);
                return;
            }
            ArrayList<ArrayList<String>> dataToAdd= new ArrayList<>();
            try{
                while(auxSet.next()){
                    ArrayList<String> item = new ArrayList<>();
                    item.add(userDet.get(0));
                    item.add(auxSet.getString("product_code"));
                    try{
                        item.add(methods.getSelectedItem().toString());
                    }catch (NullPointerException nullEx){
                        nullEx.printStackTrace();
                    }
                    item.add(addressInp);
                    dataToAdd.add(item);
                }
            }catch (SQLException sqlE){
                sqlE.printStackTrace();
            }
            TransactionAdder addData = new TransactionAdder();
            boolean successfulAddition = addData.AddTransactionOnConfirmation(dataToAdd);
            addressError.setVisible(!successfulAddition);
            purchaseDone.setVisible(true);
            confirmButton.setEnabled(false);
        });
    }
}