package com.GadaElectronics.MainMenu;

import java.awt.Desktop;
import java.io.File;

public class ShowPurchaseHistory {
    void purchaseHistory(String email_id){
        String fileLocation = "E:\\geetansh\\java projects\\Gada Electronics\\PurchaseHistoryTextfiles\\";
        fileLocation += email_id+".txt";
        try {
            File textfile = new File(fileLocation);
            Desktop dt = Desktop.getDesktop();
            if (textfile.exists()) {
                dt.open(textfile);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}