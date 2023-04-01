package com.example.digikala;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.Semaphore;

public class SellerPanel implements Initializable {
    private static Store store;
    private static Seller seller;
    @FXML
    private TextField usernameBar;
    @FXML
    private PasswordField passwordBar;
    @FXML
    private PasswordField conPasswordBar;
    @FXML
    private TextField emailBar;
    @FXML
    private TextField addressBar;
    @FXML
    private Button change;
    @FXML
    private Label conPassLabel;
    @FXML
    private PasswordField oldPasswordBar;
    @FXML
    private Label walletLabel;
    @FXML
    private Button walletButton;
    @FXML
    private Label notifLabel;
    @FXML
    private Button nextNotifButton;
    @FXML
    private ListView<String> notifList;
    @FXML
    private TreeView<String> newProductTree;
    @FXML
    private TextField name;
    @FXML
    private TextField brand;
    @FXML
    private TextField price;
    @FXML
    private TextField amount;
    @FXML
    private TextField string1;
    @FXML
    private TextField string2;
    @FXML
    private TextField string3;
    @FXML
    private TextField string4;
    @FXML
    private TextField string5;
    @FXML
    private TextField int1;
    @FXML
    private TextField int2;
    @FXML
    private TextField int3;
    @FXML
    private TextField int4;
    @FXML
    private TextArea comment;
    @FXML
    private CheckBox bool1;
    @FXML
    private CheckBox bool2;
    @FXML
    private CheckBox bool3;
    @FXML
    private CheckBox bool4;
    @FXML
    private Button addButton;
    @FXML
    private TextField string6;
    @FXML
    private ListView<String> ownProductList;
    @FXML
    private ListView<String> waitForConfirmProductList;
    @FXML
    private TextField DiscountAmountBar;
    @FXML
    private Button makeDiscountButton;
    @FXML
    private TextField cancelDiscountUUID;
    @FXML
    private Button cancelDiscountButton;
    @FXML
    private TextField DiscountUUIDBar;
    public static void setStatus(Store store , Seller seller)
    {
        SellerPanel.store=store;
        SellerPanel.seller= seller;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameBar.setText(seller.getUsername());
        emailBar.setText(seller.getEmail());
        addressBar.setText(seller.getCompanyName());
        notifList.getItems().addAll(seller.getOldNotification());
        notifLabel.setText(seller.getNotification());
        TreeItem<String> rootItem = new TreeItem<>("Products");

        TreeItem<String> cat1= new TreeItem<>("Book");
        TreeItem<String> cat2= new TreeItem<>("Clothing");
        TreeItem<String> cat3= new TreeItem<>("Eatable");
        TreeItem<String> cat4= new TreeItem<>("Electronics");
        TreeItem<String> cat5= new TreeItem<>("Household");
        TreeItem<String> cat6= new TreeItem<>("Pet");
        TreeItem<String> cat7= new TreeItem<>("SportEquip");
        TreeItem<String> cat8= new TreeItem<>("Tool");
        TreeItem<String> cat9= new TreeItem<>("Vehicle");
        TreeItem<String> cat10= new TreeItem<>("Cosmetic");

        TreeItem<String> subCat1 = new TreeItem<>("FictionBook");
        TreeItem<String> subCat2 = new TreeItem<>("NonFictionBook");
        TreeItem<String> subCat3 = new TreeItem<>("TextBook");

        TreeItem<String> subCat4 = new TreeItem<>("Pant");
        TreeItem<String> subCat5 = new TreeItem<>("Shirt");
        TreeItem<String> subCat6 = new TreeItem<>("Sunglasses");

        TreeItem<String> subCat7 = new TreeItem<>("Cake");
        TreeItem<String> subCat8 = new TreeItem<>("Meat");
        TreeItem<String> subCat9 = new TreeItem<>("Yogurt");

        TreeItem<String> subCat10 = new TreeItem<>("Camera");
        TreeItem<String> subCat11 = new TreeItem<>("Laptop");
        TreeItem<String> subCat12 = new TreeItem<>("MobilePhone");

        TreeItem<String> subCat13 = new TreeItem<>("Blender");
        TreeItem<String> subCat14 = new TreeItem<>("Carpet");
        TreeItem<String> subCat15 = new TreeItem<>("TV");

        TreeItem<String> subCat16 = new TreeItem<>("Cat");
        TreeItem<String> subCat17 = new TreeItem<>("Dog");
        TreeItem<String> subCat18 = new TreeItem<>("Fish");

        TreeItem<String> subCat19 = new TreeItem<>("Ski");
        TreeItem<String> subCat20 = new TreeItem<>("SoccerBall");
        TreeItem<String> subCat21 = new TreeItem<>("TennisRocket");

        TreeItem<String> subCat22 = new TreeItem<>("Pickor");
        TreeItem<String> subCat23 = new TreeItem<>("SafeBox");
        TreeItem<String> subCat24 = new TreeItem<>("Shovel");

        TreeItem<String> subCat25 = new TreeItem<>("Car");
        TreeItem<String> subCat26 = new TreeItem<>("Motorcycle");
        TreeItem<String> subCat27 = new TreeItem<>("Truck");

        TreeItem<String> subCat28 = new TreeItem<>("FaceMask");
        TreeItem<String> subCat29 = new TreeItem<>("Moisturizer");
        TreeItem<String> subCat30 = new TreeItem<>("fragrance");

        cat1.getChildren().addAll(subCat1,subCat2,subCat3);
        cat2.getChildren().addAll(subCat4,subCat5,subCat6);
        cat3.getChildren().addAll(subCat7,subCat8,subCat9);
        cat4.getChildren().addAll(subCat10,subCat11,subCat12);
        cat5.getChildren().addAll(subCat14,subCat15,subCat13);
        cat6.getChildren().addAll(subCat16,subCat17,subCat18);
        cat7.getChildren().addAll(subCat19,subCat20,subCat21);
        cat8.getChildren().addAll(subCat22,subCat23,subCat24);
        cat9.getChildren().addAll(subCat25,subCat26,subCat27);
        cat10.getChildren().addAll(subCat28,subCat29,subCat30);

        rootItem.getChildren().addAll(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10);
        price.setOnAction(e ->{
            try {
                int intValue = Integer.parseInt(price.getText());
            } catch (NumberFormatException ex) {
                price.setText("Please enter a valid integer");
            }
        });
        amount.setOnAction(e ->{
            try {
                int intValue = Integer.parseInt(amount.getText());
            } catch (NumberFormatException ex) {
                amount.setText("Please enter a valid integer");
            }
        });
        newProductTree.setRoot(rootItem);
        newProductTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!= null) {
                string1.setVisible(false);string2.setVisible(false);string3.setVisible(false);string4.setVisible(false);string5.setVisible(false);string6.setVisible(false);
                int1.setVisible(false); int2.setVisible(false); int3.setVisible(false); int4.setVisible(false);
                bool1.setVisible(false);bool2.setVisible(false);bool3.setVisible(false);
                if (Objects.equals(newValue.getParent().getValue(), "Book")) {
                    string1.setVisible(true);
                    string2.setVisible(true);
                    int1.setVisible(true);
                    int2.setVisible(true);
                    string1.setPromptText("publisher");
                    string2.setPromptText("language");
                    int1.setPromptText("year of publish (int)");
                    int2.setPromptText("number of pages (int) ");
                    if (Objects.equals(newValue.getValue(), "FictionBook")) {
                        string3.setVisible(true);
                        string4.setVisible(true);
                        string5.setVisible(true);
                        string3.setPromptText("genre");
                        string4.setPromptText("authorsCredentials");
                        string5.setPromptText("authors");
                    }
                    if (Objects.equals(newValue.getValue(), "NonFictionBook")) {
                        string3.setVisible(true);
                        string3.setPromptText("topic");
                    }if(Objects.equals(newValue.getValue(), "TextBook"))
                    {
                        string3.setVisible(true);
                        string3.setPromptText("subject");
                        string4.setVisible(true);
                        string4.setPromptText("cover type");
                        string5.setVisible(true);
                        string5.setPromptText("edition");
                    }
                }else if(Objects.equals(newValue.getParent().getValue(), "Clothing"))
                {
                    string1.setPromptText("color");
                    string2.setPromptText("material");
                    string3.setPromptText("size");
                    string4.setPromptText("style");
                    string1.setVisible(true);
                    string2.setVisible(true);
                    string3.setVisible(true);
                    string4.setVisible(true);
                    if(Objects.equals(newValue.getValue(), "Pant"))
                    {
                        string5.setPromptText("fit type");
                        string6.setPromptText("pocket type");
                        string6.setVisible(true);
                        string5.setVisible(true);
                    }else if(Objects.equals(newValue.getValue(), "Shirt"))
                    {
                        string5.setPromptText("pattern");
                        string6.setPromptText("neck line type");
                        int1.setPromptText("sleeve Length (int)");
                        string6.setVisible(true);
                        int1.setVisible(true);
                        string5.setVisible(true);
                    }else if(Objects.equals(newValue.getValue(), "Sunglasses"))
                    {
                        string5.setPromptText("lens type");
                        string6.setPromptText("lens material");
                        int1.setPromptText("Prescription (int)");
                        string5.setVisible(true);
                        string6.setVisible(true);
                        int1.setVisible(true);
                    }
                }else if(Objects.equals(newValue.getParent().getValue(), "Eatable"))
                {
                    string1.setPromptText("raw material");
                    string2.setPromptText("ExpirationDate");
                    string3.setPromptText("ProductionDate");
                    bool1.setText("isManufacturedByFactory");
                    string1.setVisible(true);
                    string2.setVisible(true);
                    string3.setVisible(true);
                    bool1.setVisible(true);
                    if(Objects.equals(newValue.getValue(), "Cake"))
                    {
                        string4.setPromptText("taste");
                        int1.setPromptText("number in the package (int)");
                        bool2.setText("is nut cake");
                        string4.setVisible(true);
                        int1.setVisible(true);
                        bool2.setVisible(true);
                    } else if (Objects.equals(newValue.getValue(), "Meat"))
                    {
                        string4.setPromptText("meat type");
                        bool2.setText("is it frozen");
                        bool3.setText("is it half Backed");
                        string4.setVisible(true);
                        bool2.setVisible(true);
                        bool3.setVisible(true);
                    }else if(Objects.equals(newValue.getValue(), "Yogurt"))
                    {
                        string4.setPromptText("Yogurt type");
                        string5.setPromptText("taste");
                        string6.setPromptText("fat Amount");
                        string4.setVisible(true);
                        string5.setVisible(true);
                        string6.setVisible(true);
                    }
                }else if(Objects.equals(newValue.getParent().getValue(),"Electronics"))
                {
                    string1.setPromptText("battery type");
                    string2.setPromptText("color");
                    string3.setPromptText("model");
                    string1.setVisible(true);
                    string2.setVisible(true);
                    string3.setVisible(true);
                    if(Objects.equals(newValue.getValue(),"Camera"))
                    {
                        string4.setPromptText("lens type");
                        string5.setPromptText("sensor type");
                        string6.setPromptText("zoom range");
                        string4.setVisible(true);
                        string5.setVisible(true);
                        string6.setVisible(true);
                    }else if(Objects.equals(newValue.getValue(),"Laptop"))
                    {
                        string4.setPromptText("operatingSystem");
                        int1.setPromptText("RAM (int)");
                        int2.setPromptText("storage (int)");
                        string4.setVisible(true);
                        int1.setVisible(true);
                        int2.setVisible(true);
                    }else if(Objects.equals(newValue.getValue(),"MobilePhone"))
                    {
                        int1.setPromptText("screen size (int)");
                        int2.setPromptText("battery Life (int)");
                        int3.setPromptText("camera Resolution (int)");
                        int1.setVisible(true);
                        int2.setVisible(true);
                        int3.setVisible(true);
                    }
                }
            }
        });
        ArrayList<String> products = new ArrayList<>();
        for(UUID temp :seller.getProduct())
        {
            Product product = store.findProduct(temp);
            products.add(product.TOString()+product.toString());
        }
        ownProductList.getItems().addAll(products);
        ArrayList<String> waitProducts = new ArrayList<>();
        for(Product product :seller.getWaitForConfirmComplete().values())
        {
            waitProducts.add(product.TOString()+product.toString());
        }
        waitForConfirmProductList.getItems().addAll(waitProducts);
    }
    public void add(ActionEvent e) throws IOException {
        if(!amount.getText().matches("\\d+"))
        {
            amount.setText("enter a valid integer");return;
        }
        if(!price.getText().matches("\\d+"))
        {
            price.setText("enter a valid integer");return;
        }
        if(!int1.getText().matches("\\d+") && !Objects.equals(int1.getText(), ""))
        {
            int1.setText("enter a valid integer");return;
        }
        if(!int2.getText().matches("\\d+")&& !Objects.equals(int2.getText(), ""))
        {
            int2.setText("enter a valid integer");return;
        }
        if(!int3.getText().matches("\\d+")&& !Objects.equals(int3.getText(), ""))
        {
            int3.setText("enter a valid integer");return;
        }
        if(!int4.getText().matches("\\d+")&& !Objects.equals(int4.getText(), ""))
        {
            int4.setText("enter a valid integer");return;
        }
        if(Objects.equals(string5.getPromptText(), "authors"))
        {
            FictionBook temp = new FictionBook(name.getText(), Integer.parseInt(price.getText()), Integer.parseInt(amount.getText()), comment.getText(), brand.getText(), seller.getUuid(), Integer.parseInt(int1.getText()), string1.getText(), Integer.parseInt(int2.getText()), string2.getText(), string3.getText(), string4.getText(), string5.getText());
            store.sendSellerOrder(seller.getUuid(), temp);
        }else if(Objects.equals(string3.getPromptText(), "topic"))
        {
            Nonfiction temp = new Nonfiction(name.getText(), Integer.parseInt(price.getText()), Integer.parseInt(amount.getText()), comment.getText(), brand.getText(), seller.getUuid(), Integer.parseInt(int1.getText()), string1.getText(), Integer.parseInt(int2.getText()), string2.getText(), string3.getText());
            store.sendSellerOrder(seller.getUuid(), temp);
        }else if(Objects.equals(string3.getPromptText(), "subject"))
        {
            Textbooks temp = new Textbooks(name.getText(), Integer.parseInt(price.getText()), Integer.parseInt(amount.getText()), comment.getText(), brand.getText(), seller.getUuid(), Integer.parseInt(int1.getText()), string1.getText(), Integer.parseInt(int2.getText()), string2.getText(),string3.getText(),string4.getText(),string5.getText());
            store.sendSellerOrder(seller.getUuid(),temp);
        }else if(Objects.equals(string6.getPromptText(), "pocket type"))
        {
            Pant temp = new Pant(name.getText(), Integer.parseInt(price.getText()), Integer.parseInt(amount.getText()), comment.getText(), brand.getText(), seller.getUuid(),string1.getText(),string2.getText(),string3.getText(),string4.getText(),string5.getText(),string6.getText());
            store.sendSellerOrder(seller.getUuid(),temp);
        }else if(Objects.equals(string6.getPromptText(), "neck line type"))
        {
            Shirt temp = new Shirt(name.getText(), Integer.parseInt(price.getText()), Integer.parseInt(amount.getText()), comment.getText(), brand.getText(), seller.getUuid(),string1.getText(),string2.getText(),string3.getText(),string4.getText(),string5.getText(),Integer.parseInt(int1.getText()),string6.getText());
            store.sendSellerOrder(seller.getUuid(),temp);
        }else if(Objects.equals(string6.getPromptText(), "lens material"))
        {
            Sunglasses temp = new Sunglasses(name.getText(), Integer.parseInt(price.getText()), Integer.parseInt(amount.getText()), comment.getText(), brand.getText(), seller.getUuid(),string1.getText(),string2.getText(),string3.getText(),string4.getText(),string5.getText(),Integer.parseInt(int1.getText()),string6.getText());
            store.sendSellerOrder(seller.getUuid(),temp);
        }else if(Objects.equals(bool2.getText(), "is nut cake"))
        {
            Cake temp = new Cake(name.getText(), Integer.parseInt(price.getText()), Integer.parseInt(amount.getText()), comment.getText(), brand.getText(), seller.getUuid(),string1.getText(),string2.getText(),string3.getText(),bool1.isSelected(),string4.getText(),bool2.isSelected(),Integer.parseInt(int1.getText()));
            store.sendSellerOrder(seller.getUuid(),temp);
        }else if(Objects.equals(bool3.getText(), "is it half Backed"))
        {
            Meat temp = new Meat(name.getText(), Integer.parseInt(price.getText()), Integer.parseInt(amount.getText()), comment.getText(), brand.getText(), seller.getUuid(),string1.getText(),string2.getText(),string3.getText(),bool1.isSelected(),string4.getText(),bool2.isSelected(),bool3.isSelected());
            store.sendSellerOrder(seller.getUuid(),temp);
        }else if(Objects.equals(string6.getPromptText(), "fat Amount"))
        {
            Yogurt temp = new Yogurt(name.getText(), Integer.parseInt(price.getText()), Integer.parseInt(amount.getText()), comment.getText(), brand.getText(), seller.getUuid(),string1.getText(),string2.getText(),string3.getText(),bool1.isSelected(),string4.getText(),string5.getText(),string6.getText());
            store.sendSellerOrder(seller.getUuid(),temp);
        }else if(Objects.equals(string6.getPromptText(), "zoom range"))
        {
            Camera temp = new Camera(name.getText(), Integer.parseInt(price.getText()), Integer.parseInt(amount.getText()), comment.getText(), brand.getText(), seller.getUuid(),string1.getText(),string2.getText(),string3.getText(),string4.getText(),string5.getText(),string6.getText());
            store.sendSellerOrder(seller.getUuid(),temp);
        }else if(Objects.equals(int2.getPromptText(), "storage (int)"))
        {
            Laptop temp = new Laptop(name.getText(), Integer.parseInt(price.getText()), Integer.parseInt(amount.getText()), comment.getText(), brand.getText(), seller.getUuid(),string1.getText(),string2.getText(),string3.getText(),Integer.parseInt(int1.getText()),Integer.parseInt(int2.getText()),string4.getText());
            store.sendSellerOrder(seller.getUuid(),temp);
        }else if(Objects.equals(int3.getPromptText(), "camera Resolution (int)"))
        {
            MobilePhone temp = new MobilePhone(name.getText(), Integer.parseInt(price.getText()), Integer.parseInt(amount.getText()), comment.getText(), brand.getText(), seller.getUuid(),string1.getText(),string2.getText(),string3.getText(),Integer.parseInt(int1.getText()),Integer.parseInt(int2.getText()),Integer.parseInt(int3.getText()));
            store.sendSellerOrder(seller.getUuid(),temp);
        }
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage.close();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SellerPanel.fxml"));
        Scene scene = new Scene(root);
        scene.getProperties().put("name","SellerPanel");
        stage.getIcons().add(new Image(Main.logoAddress));
        stage.setTitle("OXDCommod!!");
        stage.setScene(scene);
        stage.show();
    }
    public void change(ActionEvent e)
    {
        if(Objects.equals(change.getText(), "change"))
        {
            passwordBar.setVisible(true);
            conPasswordBar.setVisible(true);
            oldPasswordBar.setVisible(true);
            emailBar.setEditable(true);
            addressBar.setEditable(true);
            change.setText("submit");
            walletLabel.setText(seller.getWallet()+"$ in you wallet");
        }else
        {
            if(!Objects.equals(passwordBar.getText(), conPasswordBar.getText()))
            {
                conPassLabel.setText("confirmed password doesn't match");
            }else if(seller.setPassword(passwordBar.getText(), oldPasswordBar.getText()))
            {
                conPassLabel.setText("old password doesn't match");
            }
            else
            {
                seller.setEmail(emailBar.getText());
                seller.setCompanyName(addressBar.getText());
                passwordBar.setVisible(false);
                conPasswordBar.setVisible(false);
                oldPasswordBar.setVisible(false);
                emailBar.setEditable(false);
                addressBar.setEditable(false);
                change.setText("change");
                conPassLabel.setText("");
            }
        }
    }
    public void makeDiscount(ActionEvent e)
    {
        UUID uuid = null;
        try
        {
            uuid = UUID.fromString(DiscountUUIDBar.getText());
        }catch (IllegalArgumentException p)
        {
            DiscountUUIDBar.setText("invalid UUID string");
            return;
        }
        if(!seller.haveProduct(uuid))
        {
            DiscountUUIDBar.setText("you don't have permission to change this product");
            return;
        }else if(!DiscountAmountBar.getText().matches("\\d+") || Integer.parseInt(DiscountAmountBar.getText())>99)
        {
            DiscountAmountBar.setText("enter a valid integer");
            return;
        }
        seller.makeDiscount(Integer.parseInt(DiscountAmountBar.getText()),uuid);
        makeDiscountButton.setText("done!");
    }
    public void cancelDiscount(ActionEvent e)
    {
        UUID uuid = null;
        try
        {
            uuid = UUID.fromString(cancelDiscountUUID.getText());
        }catch (IllegalArgumentException p)
        {
            cancelDiscountUUID.setText("invalid UUID string");
            return;
        }
        if(!seller.haveProduct(uuid)) {
            cancelDiscountUUID.setText("you don't have permission to change this product");
            return;
        }
        seller.cancelDiscount(uuid);
        cancelDiscountButton.setText("done!");
    }
    public void claim(ActionEvent e)
    {
        walletButton.setText("Please try again later due to technical problems");
    }
    public void nextNotif(ActionEvent e)
    {
        if(Objects.equals(notifLabel.getText(), ""))
        {
            nextNotifButton.setText("no more notification.");
            nextNotifButton.setDisable(true);
        }else {
            notifList.getItems().add(notifLabel.getText());
            notifLabel.setText(seller.getNotification());
        }
    }
    public void logout(ActionEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("you are about to logout!!");
        alert.setContentText("Do you sure to logout from your account?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            SearchTab.setUser(null);
            MainMenu.setUser(null);
            SeeProduct.setUser(null);
            ObservableList<Window> openWindow = Window.getWindows();
            for (Window window : openWindow) {
                if (window instanceof Stage) {
                    if (window.getScene().getProperties().get("name").equals("MainMenu")) {
                        ((Stage) window).close();
                    }
                }
            }
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            currentStage.close();
        }
    }
}
