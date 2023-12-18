package com.GadaElectronics.MainMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.GadaElectronics.CheckoutProcess.CheckoutWindow;

@SuppressWarnings("ALL")
public class MainMenu implements MainMenuUI{
    ResultSet res = null;
    public LinkedHashMap<String, String> cart = new LinkedHashMap<>();
    final Font headFont = new Font("centaur", Font.BOLD,40);
    final Font optionLabelFont = new Font("Arial",Font.BOLD, 25);
    final Color optionLabelColor = new Color(0,0,155);
    final Font optionCBoxFont = new Font("monospaced", Font.BOLD,22);
    final Color optionCBoxColor = new Color(240,240,240);
    final Font buttonFont = new Font("sans-serif",Font.ITALIC,17);
    final Font anonymousMessageFont = new Font("Consolas", Font.BOLD, 17);
    final Color anonymousButtonColor = new Color(250,230,0);
    @Override
    public void ShowMenu(ArrayList<String> userDet){
        final boolean[] returnFlag = {false};
        //adding user email and name to cart
        JFrame menu = new JFrame("Home");
        int height = 730, width = 1375;
        try{
            Image im = ImageIO.read(new File("background.jpg"));
            Image newImage = im.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            menu.setContentPane(new JLabel(new ImageIcon(newImage)));        }
        catch(IOException e){
            e.printStackTrace();
        }
        Border blackline = BorderFactory.createLineBorder(Color.black);

        //labels: 1) main heading
        JLabel title = new JLabel("What would you like to do today?");
        title.setBounds(width/2 - 500, 20,1000, 44);
        title.setFont(headFont);
        title.setForeground(Color.RED);
        title.setHorizontalAlignment(JLabel.CENTER);
        //title.setBorder(blackline);
        menu.add(title);
        //2) logged in as label:
        JLabel loggedUser = new JLabel("<html>Logged In as: <u>"+userDet.get(1)+"</u></html>");
        loggedUser.setBounds(width/2-200,100, 400, 20);
        loggedUser.setFont(new Font("Monospaced", Font.ITALIC, 17));
        loggedUser.setForeground(Color.BLACK);
        menu.add(loggedUser);
        //3) msg for selecting product category
        JLabel pCategory = new JLabel("Select the Product Category: ");
        pCategory.setBounds(10, 250, 350, 30);
        pCategory.setForeground(optionLabelColor);
        pCategory.setFont(optionLabelFont);
        pCategory.setHorizontalAlignment(JLabel.RIGHT);
        menu.add(pCategory);
        //4) msg for selecting product brand
        JLabel pBrand = new JLabel("Select the Product Brand: ");
        pBrand.setBounds(10, 325, 350, 30);
        pBrand.setForeground(optionLabelColor);
        pBrand.setFont(optionLabelFont);
        pBrand.setHorizontalAlignment(JLabel.RIGHT);
        menu.add(pBrand);
        //5) msg for selecting product model
        JLabel pModel = new JLabel("Select the Product Model: ");
        pModel.setBounds(10, 400, 350, 30);
        pModel.setForeground(optionLabelColor);
        pModel.setFont(optionLabelFont);
        pModel.setHorizontalAlignment(JLabel.RIGHT);
        menu.add(pModel);
        //6) empty label for product title:
        JLabel prodName = new JLabel("-Product name-");
        prodName.setVisible(false);
        prodName.setBounds(900,90,400,23);
        prodName.setHorizontalAlignment(JLabel.CENTER);
        prodName.setForeground(Color.RED);
        prodName.setFont(new Font("Arial", Font.PLAIN, 22));
        menu.add(prodName);
        //7) empty label for product price:
        JLabel prodPrice = new JLabel("-product price-");
        prodPrice.setVisible(false);
        prodPrice.setForeground(new Color(155,0,0));
        prodPrice.setBounds(1025,130,150,23);
        prodPrice.setHorizontalAlignment(JLabel.CENTER);
        prodPrice.setFont(new Font("Consolas", Font.ITALIC,23));
        menu.add(prodPrice);
        //8) empty label for product details:
        JLabel prodDet = new JLabel("");
        prodDet.setVisible(false);
        prodDet.setBounds(800,450,500,96);
        prodDet.setHorizontalAlignment(JLabel.CENTER);
        prodDet.setFont(new Font("Arial",Font.PLAIN,16));
        prodDet.setForeground(new Color(100,100,100));
        menu.add(prodDet);
        //9) selection error message
        JLabel selectError = new JLabel("Invalid Product Selection!");
        selectError.setBounds(300,450,300,20);
        selectError.setFont(anonymousMessageFont);
        selectError.setForeground(Color.red);
        selectError.setVisible(false);
        menu.add(selectError);
        //10)product added to cart
        JLabel prodAddedCart = new JLabel("");
        prodAddedCart.setVisible(false);
        prodAddedCart.setBounds(100,450,700,40);
        prodAddedCart.setFont(anonymousMessageFont);
        prodAddedCart.setForeground(new Color(0,150,0));
        menu.add(prodAddedCart);
        //11) cart size exceeded error
        JLabel cartSizeExceed = new JLabel("You can only purchase upto 5 items at a time!");
        cartSizeExceed.setVisible(false);
        cartSizeExceed.setBounds(250,450,500,20);
        cartSizeExceed.setForeground(Color.RED);
        cartSizeExceed.setFont(anonymousMessageFont);
        menu.add(cartSizeExceed);
        //12)double cart entry error
        JLabel dblCartAdd = new JLabel("");
        dblCartAdd.setVisible(false);
        dblCartAdd.setBounds(100,450,700,40);
        dblCartAdd.setFont(anonymousMessageFont);
        dblCartAdd.setForeground(Color.RED);
        menu.add(dblCartAdd);
        //13) image label
        JLabel imageLabel = new JLabel("");
        imageLabel.setVisible(false);
        imageLabel.setBounds(900,150,400,300);
        menu.add(imageLabel);

        //object of chained abstract classes
        GetProductDetails callObject = new GetProductDetails();
        //Drop down Combination Boxes: 1)product category
        ArrayList<String> categoryList= callObject.FetchCategories();
        JComboBox category = new JComboBox(categoryList.toArray());
        category.setBounds(400,250,200,30);
        category.setFont(optionCBoxFont);
        category.setBackground(optionCBoxColor);
        category.setForeground(optionLabelColor);
        category.setFocusable(false);
        menu.add(category);

        //2) product brand/company
        String[] arr = {};
        JComboBox brand = new JComboBox(arr);
        brand.setBounds(400,325,200,30);
        brand.setFont(optionCBoxFont);
        brand.setBackground(optionCBoxColor);
        brand.setForeground(optionLabelColor);
        brand.setEnabled(false);
        menu.add(brand);
        //3) product models
        JComboBox model = new JComboBox();
        model.setBounds(400,400,400,30);
        model.setEnabled(false);
        model.setFont(new Font("monospaced", Font.BOLD,17));
        model.setBackground(optionCBoxColor);
        model.setForeground(optionLabelColor);
        menu.add(model);

        //buttons: 1) close button
        JButton closeButton = new JButton("Close");
        closeButton.setBounds(60,630,120,40);
        closeButton.setForeground(Color.white);
        closeButton.setFont(new Font("Consolas", Font.BOLD, 20));
        closeButton.setBackground(new Color(0,0,150));
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> {
            menu.dispose();//to close the window
            returnFlag[0] = true;
        });
        menu.add(closeButton);

        //2) search product button
        JButton searchButton = new JButton("Search Product");
        searchButton.setBounds(300,500,300,30);
        searchButton.setFont(new Font("consolas",Font.PLAIN,25));
        searchButton.setForeground(Color.BLACK);
        searchButton.setFocusPainted(false);
        searchButton.setEnabled(false);
        menu.add(searchButton);
        //3) add to cart button
        JButton addToCart = new JButton("Add to Cart");
        addToCart.setBounds(300,630,200,40);
        addToCart.setFont(buttonFont);
        addToCart.setBackground(anonymousButtonColor);
        menu.add(addToCart);
        //4)checkout button
        JButton checkout = new JButton("Checkout→");
        checkout.setBounds(1200,630,150,40);
        checkout.setBackground(anonymousButtonColor);
        checkout.setFont(buttonFont);
        checkout.setVisible(false);
        menu.add(checkout);
        //5) view cart button
        JButton viewCart = new JButton("View Cart");
        viewCart.setBounds(650,630,200,40);
        viewCart.setBackground(anonymousButtonColor);
        viewCart.setFont(buttonFont);
        viewCart.setVisible(true);
        menu.add(viewCart);
        //6) checkout history button
        JButton history = new JButton("View Purchase History");
        history.setBounds(70,100,300,28);
        history.setFont(new Font("Arial",Font.ITALIC,18));
        history.setBackground(anonymousButtonColor);
        menu.add(history);

        menu.setPreferredSize(new Dimension(width, height));
        menu.setResizable(false);
        menu.getContentPane().setBackground(Color.CYAN);
        menu.setLayout(null);
        menu.pack();
        menu.setVisible(true);

        //ACTION LISTENERS
        //action listener to enable brand combination box
        category.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = category.getSelectedItem().toString();
                ArrayList<String> brands = callObject.FetchBrands(selectedCategory);
                brand.removeAllItems();
                model.removeAllItems();
                for(String s: brands){
                    brand.addItem(s);
                }

                searchButton.setEnabled(false);
                model.setEnabled(false);
                brand.setEnabled(true);
            }
        });
        //action listener to enable model combination box
        brand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String selectedCategory = category.getSelectedItem().toString();
                    String selectedBrand = brand.getSelectedItem().toString();
                    ArrayList<String> models = callObject.FetchModels(selectedCategory,selectedBrand);
                    model.removeAllItems();
                    for(String s: models){
                        model.addItem(s);
                    }
                    searchButton.setEnabled(false);
                    model.setEnabled(true);
                } catch (NullPointerException ex){
                    System.out.println("Casual selection error");
                }
            }
        });
        //action listener to enable search button
        model.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButton.setEnabled(true);
            }
        });
        //action listener to display product details.
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectError.setVisible(false);
                try{
                    String selectCat = category.getSelectedItem().toString();
                    String selectBnd = brand.getSelectedItem().toString();
                    String selectMdl = model.getSelectedItem().toString();
                    res = callObject.FetchProductDetails(selectCat,selectBnd,selectMdl);
                    res.next();
                    String prodname = res.getString("product_name");
                    String prodcode = res.getString("product_code");
                    prodName.setText(prodname);
                    prodName.setVisible(true);
                    prodPrice.setText(String.valueOf(res.getInt("product_price"))+"/-");
                    prodPrice.setVisible(true);
                    String in = res.getString("product_information");
                    prodDet.setText("<html><center>"+in+"</center></html>");
                    prodDet.setVisible(true);
                    String imgPath = "E:\\geetansh\\java projects\\Gada Electronics\\Product_Images\\";
                    imgPath += prodcode+".jpg";
                    Image prodimg = ImageIO.read(new File(imgPath));
                    Image finalProdImg = prodimg.getScaledInstance(400, 300, Image.SCALE_DEFAULT);
                    imageLabel.setIcon(new ImageIcon(finalProdImg));
                    imageLabel.setVisible(true);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch(IOException ie){
                    ie.printStackTrace();
                } catch (NullPointerException nullVal){
                    selectError.setVisible(true);
                }
            }
        });
        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cart.size()==5){
                    prodAddedCart.setVisible(false);
                    cartSizeExceed.setVisible(true);
                    return;
                }
                String selectMdl = "", selectCat = "", selectBnd = "";
                try{
                    selectCat = category.getSelectedItem().toString();
                    selectBnd = brand.getSelectedItem().toString();
                    selectMdl = model.getSelectedItem().toString();
                    res = callObject.FetchProductDetails(selectCat,selectBnd,selectMdl);
                    res.next();
                    String prd_code = res.getString("Product_Code");
                    //check if product already in cart
                    for(Map.Entry<String, String> iter: cart.entrySet()){
                        if(Objects.equals(prd_code,iter.getKey())){
                            String errorMsg = "<html>Cannot add <i><u>"+selectMdl+"</u></i> more than once!</html>";
                            dblCartAdd.setText(errorMsg);
                            prodAddedCart.setVisible(false);
                            dblCartAdd.setVisible(true);
                            return;
                        }
                    }
                    dblCartAdd.setVisible(false);
                    cart.put(prd_code,selectMdl);
                    String msg = "<html><u><i>"+res.getString("product_name")+"</i></u> added to cart!<br>";
                    msg+="Note that you can purchase only 1 quantity of added item at a time."+"</html>";
                    prodAddedCart.setText(msg);
                    prodAddedCart.setVisible(true);
                    checkout.setVisible(true);
                    viewCart.setVisible(true);
                }catch (NullPointerException nullVal){
                    selectError.setVisible(true);
                } catch (SQLException sql){
                    sql.printStackTrace();
                }
            }
        });
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutWindow win = new CheckoutWindow();
                win.BillWindow(cart,userDet);
                menu.dispose();
            }
        });
        viewCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cart(cart);
            }
        });
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowPurchaseHistory().purchaseHistory(userDet.get(0));
            }
        });
    }

    public void Cart(HashMap<String,String> cartItems){
        JFrame cart = new JFrame("Home");
        int height = 500, width = 700;
        try{
            Image im = ImageIO.read(new File("background.jpg"));
            Image newImage = im.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            cart.setContentPane(new JLabel(new ImageIcon(newImage)));        }
        catch(IOException e){
            e.printStackTrace();
        }
        Border blackline = BorderFactory.createLineBorder(Color.black);

        JLabel head = new JLabel("Your Cart");
        head.setBounds(width/2 - 90,20,180,42);
        head.setFont(headFont);
        head.setForeground(Color.RED);
        cart.add(head);

        //buttons: 1) ok button
        JButton ok = new JButton("OK");
        ok.setBounds(260,400,80,25);
        ok.setFont(new Font("Consolas", Font.PLAIN, 22));
        ok.setBackground(new Color(0,0,150));
        ok.setForeground(Color.WHITE);
        ok.setFocusPainted(false);
        cart.add(ok);

        int y_coordinate = 100;
        JLabel[] jarr = {new JLabel(""),new JLabel(""), new JLabel(""), new JLabel(""), new JLabel("")};
        JButton[] barr = {new JButton(""),new JButton(""),new JButton(""),new JButton(""),new JButton("")};

        //looping cart elements
        int i = 0;
        for(Map.Entry<String, String> iter: cartItems.entrySet()){
            loopEntry(cart,jarr[i],iter.getValue(),barr[i],y_coordinate,cartItems,iter.getKey());
            y_coordinate+=60;
            i++;
        }

        cart.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        cart.setPreferredSize(new Dimension(width, height));
        cart.setResizable(false);
        cart.getContentPane().setBackground(Color.CYAN);
        cart.setLayout(null);
        cart.pack();
        cart.setVisible(true);

        //ACTION LISTENERS
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart.dispose();
            }
        });
    }
    static void loopEntry(JFrame frame, JLabel label,String text, JButton button, int y_coordinate, HashMap<String, String> cartItems, String prdcode){
        label.setText(text);
        label.setBounds(30,y_coordinate,400,25);
        label.setForeground(new Color(0,100,150));
        label.setFont(new Font("Arial",Font.PLAIN,22));
        frame.add(label);
        button = new JButton("Remove");
        button.setFont(new Font("monospaced", Font.BOLD, 17));
        button.setBounds(500,y_coordinate, 115, 28);
        frame.add(button);
        JButton finalButton = button;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cartItems.remove(prdcode);
                label.setVisible(false);
                finalButton.setVisible(false);
            }
        });
    }
    static String detailString(String in){
        String out = "<html>";
        String[] arr = in.split(",");
        for(String i: arr){
            out+=i+"<br>";
        }
        out+="</html>";
        return out;
    }
}