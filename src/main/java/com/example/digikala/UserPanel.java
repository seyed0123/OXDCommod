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
import javafx.util.Pair;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import static com.example.digikala.Main.store;

public class UserPanel implements Initializable {
    private static User user;
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
    private TextField numberBar;
    @FXML
    private Button change;
    @FXML
    private Label conPassLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private PasswordField oldPasswordBar;
    @FXML
    private Button walletButton;
    @FXML
    private Label walletLabel;
    @FXML
    private TextField walletBar;
    @FXML
    private Label waitForAccept;
    @FXML
    private Label notifLabel;
    @FXML
    private Button nextNotifButton;
    @FXML
    private ListView<String> notifList;
    @FXML
    private ListView<String> ordersList;
    @FXML
    private ListView<String> favoriteList;
    @FXML
    private ListView<String> cartList;
    @FXML
    private ListView<String> recommendList;
    @FXML
    private Tab cartTab;
    @FXML
    private Button sendOrder;
    @FXML
    private Button sendSubs;
    @FXML
    private Label totalPriceCart;
    @FXML
    private Label shippingCost;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if(user.getWait()) {
            waitForAccept.setText("waiting for admin approval for cart");
            cartTab.setDisable(true);
            sendOrder.setDisable(true);
        }
        usernameBar.setDisable(true);
        usernameBar.setText(user.getUsername());
        emailBar.setText(user.getEmail());
        addressBar.setText(user.getAddress());
        numberBar.setText(user.getPhoneNumber()+"");
        walletLabel.setText(user.getWallet()+"$ in your wallet");
        notifList.getItems().addAll(user.getOldNotification());
        notifLabel.setText(user.getNotification());
        totalPriceCart.setText("total price:    "+user.getTotalPriceOfCart());
        if(user.getSubscription()) {
            shippingCost.setText("Shipping is free for you because of your subscription");
            sendSubs.setText("you have subscription.");
            sendSubs.setDisable(true);
        }
        else if(user.isHasLocation())
            shippingCost.setText("you location isn't valid:default shipping cost : "+user.getShippingCost());
        else
            shippingCost.setText("Shipping cost :    "+ user.getShippingCost());
        if(Admin.isUserSendSubs(user.getUuid()))
        {
            sendSubs.setText("waiting for admin to accept");
            sendSubs.setDisable(true);
        }
        ArrayList<String> order = new ArrayList<>();
        ArrayList<UUID> orderUUID = new ArrayList<>(user.getOrders());
        for(UUID temp :orderUUID)
        {
            order.add(store.findOrder(temp).toString());
        }
        ordersList.getItems().addAll(order);
        ordersList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Refund");
                alert.setHeaderText("you are about to refund this order!!");
                alert.setContentText("Do you sure to refund this order from your account?");
                if(alert.showAndWait().get() == ButtonType.OK)
                    store.addRefundReq(new Pair<>(user.getUuid(),orderUUID.get(ordersList.getSelectionModel().getSelectedIndex())));
            }
        });
        ArrayList<String> favorite = new ArrayList<>();
        for(UUID temp :user.getFavorite())
        {
            Product product = store.findProduct(temp);
            favorite.add("name: "+product.getName()+" brand: "+product.getBrand()+" price: "+product.getPrice()+"  "+product+" * "+user.getCart().get(temp));
        }
        favoriteList.getItems().addAll(favorite);
        ArrayList<String> cart = new ArrayList<>();
        ArrayList<UUID> cartUser = new ArrayList<>(user.getCart().keySet());
        for(UUID temp : cartUser)
        {
            Product product = store.findProduct(temp);
            cart.add("name: "+product.getName()+" brand: "+product.getBrand()+" price: "+product.getPrice()+"  "+product+" * "+user.getCart().get(temp));
        }
        cartList.getItems().addAll(cart);
        cartList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                UUID product = cartUser.get(cartList.getSelectionModel().getSelectedIndex());
                CartSeeProduct.setStatus(user,store.findProduct(product));
                Stage seeCartStage= new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("CartSeeProduct.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                seeCartStage.setTitle("OXDCommod!!");
                Scene scene  =new Scene(root);
                scene.getProperties().put("name","SeeCart");
                seeCartStage.getIcons().add(new Image(Main.logoAddress));
                seeCartStage.setScene(scene);
                seeCartStage.show();
                seeCartStage.setOnHiding(event -> {
                    event.consume();
                    ObservableList<Window> openWindow=Window.getWindows();
                    for(Window window :openWindow)
                    {
                        if(window instanceof Stage)
                        {
                            if (window.getScene().getProperties().get("name").equals("UserPanel"))
                            {
                                ((Stage) window).close();
                            }
                        }
                    }
                    Stage UserPanel= new Stage();
                    Parent root2 = null;
                    try {
                        root2 = FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    UserPanel.setTitle("OXDCommod!!");
                    Scene scene2  =new Scene(root2);
                    scene2.getProperties().put("name","UserPanel");
                    UserPanel.getIcons().add(new Image(Main.logoAddress));
                    UserPanel.setScene(scene2);
                    UserPanel.show();
                });
            }
        });
        ArrayList<String> recom = new ArrayList<>();
        ArrayList<UUID> userRecom = new ArrayList<>(user.getLastSeen());
        for(UUID temp : userRecom)
        {
            Product pro = store.findProduct(temp);
            recom.add(pro.getName()+" with brand "+pro.getBrand()+"    RATE : "+pro.getRate()+"    Discount:  "+pro.getDiscount()+"  finalPrice :"+pro.getFinalPrice());
        }
        recommendList.getItems().addAll(recom);
        recommendList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                UUID product = userRecom.get(recommendList.getSelectionModel().getSelectedIndex());
                SeeProduct.setStatus(store.findProduct(product), null);
                Stage seeProductStage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("SeeProduct.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                seeProductStage.setTitle("OXDCommod!!");
                Scene scene = new Scene(root);
                scene.getProperties().put("name", "SeeProduct");
                seeProductStage.getIcons().add(new Image(Main.logoAddress));
                seeProductStage.setScene(scene);
                seeProductStage.show();
            }
        });
    }
    public void sendOrder(ActionEvent e)
    {
        if(user.getWallet()<user.getTotalPriceOfCart()) {
            sendOrder.setText("need money");
            return;
        }
        store.sendOrder(user.getUuid());
        waitForAccept.setText("waiting for admin approval for cart");
        cartTab.setDisable(true);
        sendOrder.setDisable(true);
    }
    public static void setStatus(User user) {
        UserPanel.user = user;
    }
    public void change(ActionEvent e)
    {
        if(Objects.equals(change.getText(), "change"))
        {
            passwordBar.setVisible(true);
            conPasswordBar.setVisible(true);
            oldPasswordBar.setVisible(true);
            numberBar.setEditable(true);
            emailBar.setEditable(true);
            addressBar.setEditable(true);
            change.setText("submit");
        }else
        {
            if(!Objects.equals(passwordBar.getText(), conPasswordBar.getText()))
            {
                conPassLabel.setText("confirmed password doesn't match");
            }else if(user.setPassword(passwordBar.getText(), oldPasswordBar.getText()))
            {
                conPassLabel.setText("old password doesn't match");
            }else if(!numberBar.getText().matches("\\d+"))
                numberLabel.setText("phoneNumber must only contains numbers");
            else
            {
                user.setEmail(emailBar.getText());
                user.setAddress(addressBar.getText());
                user.setPhoneNumber(Integer.parseInt(numberBar.getText()));
                passwordBar.setVisible(false);
                conPasswordBar.setVisible(false);
                oldPasswordBar.setVisible(false);
                numberBar.setEditable(false);
                emailBar.setEditable(false);
                addressBar.setEditable(false);
                change.setText("change");
                conPassLabel.setText("");
                numberLabel.setText("");
            }
        }
    }
    public void addWallet(ActionEvent e) throws InterruptedException {
        if(!walletBar.getText().matches("\\d+"))
            walletBar.setText("just enter integers pls.");
        else {
            store.addWallet(new Pair<>(user.getUuid(),Integer.parseInt(walletBar.getText())));
            walletBar.setText("request sent.");
        }
    }
    public void nextNotif(ActionEvent e)
    {
        if(Objects.equals(notifLabel.getText(), ""))
        {
            nextNotifButton.setText("no more notification.");
            nextNotifButton.setDisable(true);
        }else {
            notifList.getItems().add(notifLabel.getText());
            notifLabel.setText(user.getNotification());
        }
    }
    public void addSubs(ActionEvent e)
    {
        store.addSubscriptions(user.getUuid());
        sendSubs.setText("waiting for admin to accept");
        sendSubs.setDisable(true);
    }
    public void back(ActionEvent e)
    {
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage.close();
    }
    public void logout(ActionEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("you are about to logout!!");
        alert.setContentText("Do you sure to logout from your account?");
        if(alert.showAndWait().get() == ButtonType.OK) {
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
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene scene = new Scene(root);
            scene.getProperties().put("name", "MainMenu");
            stage.getIcons().add(new Image(Main.logoAddress));
            stage.setTitle("OXDCommod!!");
            stage.setScene(scene);
            stage.show();
        }
    }
}
