package com.example.digikala;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.*;
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
    private TextField category;
    @FXML
    private TextField subCategory;
    @FXML
    private TextArea description;
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
    @FXML
    private Label levelLabel;
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
        walletLabel.setText(seller.getWallet()+"$ in your wallet");
        levelLabel.setText(Main.level[seller.getSellerLevel()]);
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
        newProductTree.setRoot(rootItem);
        newProductTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!= null) {
            }
        });
        ArrayList<String> products = new ArrayList<>();
        ArrayList<UUID> productUUID = new ArrayList<>(seller.getProduct());
        for(UUID temp :productUUID)
        {
            Product product = store.findProduct(temp);
            products.add(product.TOString()+product.toString());
        }
        ownProductList.getItems().addAll(products);
        ownProductList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                UUID product = productUUID.get(ownProductList.getSelectionModel().getSelectedIndex());
                EditProductPanel.setStatus(store,store.findProduct(product),false);
                Stage EditProductPanel= new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("EditProductPanel.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                EditProductPanel.setTitle("OXDCommod!!");
                Scene scene  =new Scene(root);
                scene.getProperties().put("name","SeeProduct");
                EditProductPanel.getIcons().add(new Image(Main.logoAddress));
                EditProductPanel.setScene(scene);
                EditProductPanel.show();
            }
        });
        ArrayList<String> waitProducts = new ArrayList<>();
        ArrayList<Product> waitProductsUUID = new ArrayList<>(seller.getWaitForConfirmComplete().values());
        for(Product product :waitProductsUUID)
        {
            waitProducts.add(product.TOString()+product.toString());
        }
        waitForConfirmProductList.getItems().addAll(waitProducts);
        waitForConfirmProductList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                EditProductPanel.setStatus(store,waitProductsUUID.get(waitForConfirmProductList.getSelectionModel().getSelectedIndex()),true);
                Stage EditProductPanel= new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("EditProductPanel.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                EditProductPanel.setTitle("OXDCommod!!");
                Scene scene  =new Scene(root);
                scene.getProperties().put("name","SeeProduct");
                EditProductPanel.getIcons().add(new Image(Main.logoAddress));
                EditProductPanel.setScene(scene);
                EditProductPanel.show();
            }
        });
    }
    public void add(ActionEvent e) throws IOException {
        if(!amount.getText().matches("\\d+") || Objects.equals(amount.getText(), ""))
        {
            amount.setText("enter a valid integer");return;
        }
        if(!price.getText().matches("\\d+") || Objects.equals(price.getText(), ""))
        {
            price.setText("enter a valid integer");return;
        }
        if(Objects.equals(name.getText(), "")) {
            name.setText("this field can't be empty.");
            return;
        }
        if(Objects.equals(brand.getText(), "")) {
            brand.setText("this field can't be empty.");
            return;
        }
        if(Objects.equals(category.getText(), "")) {
            category.setText("this field can't be empty.");
            return;
        }
        if(Objects.equals(subCategory.getText(), "")) {
            subCategory.setText("this field can't be empty.");
            return;
        }
        SubCategory temp = new SubCategory(name.getText(),Integer.parseInt(price.getText()),Integer.parseInt(amount.getText()),description.getText(),brand.getText(),seller.getUuid(),category.getText(),subCategory.getText());
        store.sendSellerOrder(seller.getUuid(),temp);
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
            Stage stageM = new Stage();
            Parent rootM = null;
            try {
                rootM = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Scene sceneM = new Scene(rootM);
            sceneM.getProperties().put("name","MainMenu");
            stageM.getIcons().add(new Image(Main.logoAddress));
            stageM.setTitle("OXDCommod!!");
            stageM.setScene(sceneM);
            stageM.show();
        }
    }
}
