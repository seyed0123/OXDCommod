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
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;

public class AdminPanel implements Initializable {
    private static Store store;
    private static Admin admin;
    @FXML
    private TextField usernameBar;
    @FXML
    private PasswordField passwordBar;
    @FXML
    private PasswordField conPasswordBar;
    @FXML
    private TextField emailBar;
    @FXML
    private Button change;
    @FXML
    private Label conPassLabel;
    @FXML
    private PasswordField oldPasswordBar;
    @FXML
    private Label notifLabel;
    @FXML
    private Button nextNotifButton;
    @FXML
    private ListView<String> notifList;
    @FXML
    private Label SellerOrderLabel;
    @FXML
    private Label SellerOrderStatusLabel;
    @FXML
    private Label UserOrderLabel;
    @FXML
    private Label UserOrderStatusLabel;
    @FXML
    private Label WalletOrderLabel;
    @FXML
    private Label WalletOrderStatusLabel;
    @FXML
    private Label SubsOrderLabel;
    @FXML
    private Label SubsOrderStatusLabel;
    @FXML
    private ListView<String> userList;
    @FXML
    private ListView<String> productList;
    @FXML
    private ListView<String> sellerList;
    @FXML
    private ListView<String> orderList;
    @FXML
    private Label profit;
    @FXML
    private Button banButton;
    @FXML
    private Button permitButton;
    @FXML
    private Button removeProductButton;
    @FXML
    private TextField banBar;
    @FXML
    private TextField permitBar;
    @FXML
    private TextField removeProductBar;
    @FXML
    private TextField checkBar;
    @FXML
    private Button signUp;
    @FXML
    private Button checkButton;
    @FXML
    private ListView<String> logList;
    @FXML
    private TextField usernameBarUser;
    @FXML
    private PasswordField passwordBarUser;
    @FXML
    private PasswordField conPasswordBarUser;
    @FXML
    private TextField emailBarUser;
    @FXML
    private Label usernameLabelUser;
    @FXML
    private Label conPassLabelUser;
    @FXML
    private TextField reasonWalletBar;
    @FXML
    private TextField reasonSellerBar;
    @FXML
    private TextField reasonUserBar;
    @FXML
    private TextField reasonSubsBar;
    @FXML
    private Label SellerVerifyLabel;
    @FXML
    private Label SellerVerifyStatusLabel;
    @FXML
    private ListView<String> banList;
    public static void setStatus(Store store, Admin admin) {
        AdminPanel.store = store;
        AdminPanel.admin = admin;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameBar.setDisable(true);
        usernameBar.setText(admin.getUsername());
        emailBar.setText(admin.getEmail());
        notifList.getItems().addAll(Admin.getOldNotification());
        notifLabel.setText(Admin.getNotification());
        UserOrderLabel.setText(admin.orderRequest());
        WalletOrderLabel.setText(admin.walletRequest());
        SellerOrderLabel.setText(admin.sellerRequest());
        SubsOrderLabel.setText(admin.subscriptionRequest());
        profit.setText(store.getProfit() + "$ earned until now");
        userList.getItems().addAll(store.users());
        sellerList.getItems().addAll(store.sellers());
        orderList.getItems().addAll(store.orders());
        productList.getItems().addAll(store.products());
        banList.getItems().addAll(store.bans());
        logList.getItems().addAll(store.log());
    }

    public void change(ActionEvent e) {
        if (Objects.equals(change.getText(), "change")) {
            passwordBar.setVisible(true);
            conPasswordBar.setVisible(true);
            oldPasswordBar.setVisible(true);
            emailBar.setEditable(true);
            change.setText("submit");
        } else {
            if (!Objects.equals(passwordBar.getText(), conPasswordBar.getText())) {
                conPassLabel.setText("confirmed password doesn't match");
            } else if (admin.setPassword(passwordBar.getText(), oldPasswordBar.getText())) {
                conPassLabel.setText("old password doesn't match");
            } else {
                admin.setEmail(emailBar.getText());
                passwordBar.setVisible(false);
                conPasswordBar.setVisible(false);
                oldPasswordBar.setVisible(false);
                emailBar.setEditable(false);
                change.setText("change");
                conPassLabel.setText("");
            }
        }
    }

    public void nextNotif(ActionEvent e) {
        if (Objects.equals(notifLabel.getText(), "")) {
            nextNotifButton.setText("no more notification.");
            nextNotifButton.setDisable(true);
        } else {
            notifList.getItems().add(0, notifLabel.getText());
            notifLabel.setText(Admin.getNotification());
        }
    }
    private boolean nextSellerOrder()
    {
        if(Objects.equals(SellerOrderLabel.getText(),""))
        {
            SellerOrderStatusLabel.setText("no more orders");
            return false;
        }
        return true;
    }
    public void acceptSellerOrder(ActionEvent e) {
        if(!nextSellerOrder())
            return;
        admin.checkSellerReq(true, admin.sellerRequestUUID(),null);
        SellerOrderLabel.setText(admin.sellerRequest());
        SellerOrderStatusLabel.setText("accepted");
    }

    public void refuseSellerOrder(ActionEvent e) {
        if(!nextSellerOrder())
            return;
        if(Objects.equals(reasonSellerBar.getText(), ""))
        {
            SellerOrderStatusLabel.setText("reason is necessary.");
            return;
        }
        admin.checkSellerReq(false, admin.sellerRequestUUID(),reasonSellerBar.getText());
        SellerOrderLabel.setText(admin.sellerRequest());
        SellerOrderStatusLabel.setText("refused");
    }
    private boolean nextOrder()
    {
        if(Objects.equals(UserOrderLabel.getText(),""))
        {
            UserOrderStatusLabel.setText("no more orders");
            return false;
        }
        return true;
    }
    public void acceptOrder(ActionEvent e) {
        if(!nextOrder())
            return;
        String problems= store.findUser(store.findOrder(admin.orderRequestUUID()).getUser()).isOrderOK();
        if(!Objects.equals(problems, ""))
        {
            UserOrderStatusLabel.setText(problems);
            return;
        }else if(store.findUser(store.findOrder(admin.orderRequestUUID()).getUser()).getWallet()<store.findOrder(admin.orderRequestUUID()).getTotalPrice()*1.1)
        {
            UserOrderStatusLabel.setText("this user doesn't have enough money to payment this order");
            return;
        }
        admin.checkOrderUser(true, admin.orderRequestUUID(),null);
        UserOrderLabel.setText(admin.orderRequest());
        UserOrderStatusLabel.setText("accepted");
    }

    public void refuseOrder(ActionEvent e) {
        if(!nextOrder())
            return;
        if(reasonUserBar.getText()=="")
        {
            UserOrderStatusLabel.setText("reason is necessary.");
            return;
        }
        admin.checkOrderUser(false, admin.orderRequestUUID(),reasonUserBar.getText());
        UserOrderLabel.setText(admin.orderRequest());
        UserOrderStatusLabel.setText("refused");
    }
    private boolean nextWalletOrder()
    {
        if(Objects.equals(WalletOrderLabel.getText(),""))
        {
            WalletOrderStatusLabel.setText("no more orders");
            return false;
        }
        return true;
    }
    public void acceptWalletOrder(ActionEvent e) {
        if(!nextWalletOrder())
            return;
        admin.checkWalletReq(true, admin.walletRequestUUID(),null);
        WalletOrderLabel.setText(admin.walletRequest());
        WalletOrderStatusLabel.setText("accepted");
    }

    public void refuseWalletOrder(ActionEvent e) {
        if(!nextWalletOrder())
            return;
        if(reasonWalletBar.getText()=="")
        {
            WalletOrderStatusLabel.setText("reason is necessary.");
            return;
        }
        admin.checkWalletReq(false, admin.walletRequestUUID(),reasonWalletBar.getText());
        WalletOrderLabel.setText(admin.walletRequest());
        WalletOrderStatusLabel.setText("refused");
    }
    private boolean nextSubsOrder()
    {
        if(Objects.equals(SubsOrderLabel.getText(),""))
        {
            SubsOrderStatusLabel.setText("no more orders");
            return false;
        }
        return true;
    }
    public void acceptSubsOrder(ActionEvent e) {
        if(!nextSubsOrder())
            return;
        admin.checkSubscriptionReq(true, admin.subscriptionRequestUUID(),null);
        SubsOrderLabel.setText(admin.subscriptionRequest());
        SubsOrderStatusLabel.setText("accepted");
    }

    public void refuseSubsOrder(ActionEvent e) {
        if(!nextSubsOrder())
            return;
        if(Objects.equals(reasonSubsBar.getText(), ""))
        {
            SubsOrderStatusLabel.setText("reason is necessary.");
            return;
        }
        admin.checkSubscriptionReq(false, admin.subscriptionRequestUUID(),reasonSubsBar.getText());
        SubsOrderLabel.setText(admin.subscriptionRequest());
        SubsOrderStatusLabel.setText("refused");
    }

    private boolean nextSellerConfirm()
    {
        if(Objects.equals(SellerVerifyLabel.getText(),""))
        {
            SellerVerifyStatusLabel.setText("no more orders");
            return false;
        }
        return true;
    }
    public void acceptSellerConfirm(ActionEvent e) {
        if(!nextSellerConfirm())
            return;
        admin.sellerConfirmation(true, admin.sellerConfirmRequestUUID());
        SellerVerifyLabel.setText(admin.sellerConfirmRequest());
        SellerVerifyStatusLabel.setText("accepted");
    }

    public void refuseSellerConfirm(ActionEvent e) {
        if(!nextSellerConfirm())
            return;
        if(Objects.equals(reasonSubsBar.getText(), ""))
        {
            SubsOrderStatusLabel.setText("reason is necessary.");
            return;
        }
        admin.sellerConfirmation(false, admin.sellerConfirmRequestUUID());
        SellerVerifyLabel.setText(admin.sellerConfirmRequest());
        SellerVerifyStatusLabel.setText("refused");
    }
    public void ban(ActionEvent e) {
        UUID uuid = null;
        try {
            uuid = UUID.fromString(banBar.getText());
        } catch (IllegalArgumentException p) {
            banBar.setText("invalid UUID string");
            return;
        }
        if (!store.isUserExist(uuid) || !store.isSellerExist(uuid)) {
            banBar.setText("this person doesn't exist");
        } else {
            store.ban(uuid);
            banButton.setText("done!");
        }
    }

    public void permit(ActionEvent e) {
        UUID uuid = null;
        try {
            uuid = UUID.fromString(permitBar.getText());
        } catch (IllegalArgumentException p) {
            permitBar.setText("invalid UUID string");
            return;
        }
        if (!store.isUserExist(uuid) || !store.isSellerExist(uuid)) {
            permitBar.setText("this person doesn't exist");
        } else {
            store.permit(uuid);
            permitButton.setText("done!");
        }
    }

    public void removeProduct(ActionEvent e) {
        UUID uuid = null;
        try {
            uuid = UUID.fromString(removeProductBar.getText());
        } catch (IllegalArgumentException p) {
            removeProductBar.setText("invalid UUID string");
            return;
        }
        if (!store.isUserExist(uuid) || !store.isSellerExist(uuid)) {
            removeProductBar.setText("this person doesn't exist");
        } else {
            store.removeProduct(uuid);
            removeProductButton.setText("done!");
        }
    }

    public void check(ActionEvent e) {
        String content = "";
        UUID uuid = null;
        try {
            uuid = UUID.fromString(checkBar.getText());
        } catch (IllegalArgumentException p) {
            checkBar.setText("invalid UUID string");
            return;
        }
        if (!store.isUserExist(uuid)) {
            checkBar.setText("this person doesn't exist");
            return;
        } else {
            content = admin.checkUser(uuid);
        }
        if (!store.isSellerExist(uuid)) {
            checkBar.setText("this person doesn't exist");
            return;
        } else {
            content = admin.checkSeller(uuid);
        }
        if (!store.isProductExist(uuid)) {
            checkBar.setText("this person doesn't exist");
        } else {
            content = admin.checkProduct(uuid);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("check person");
        alert.setContentText(content);
        alert.show();
    }

    public void addAdmin(ActionEvent e)
    {
        String username =usernameBarUser.getText();
        if(store.IsUsernameExist(username))
        {
            usernameLabelUser.setText("this username already in user.");
            return;
        }
        if(!Objects.equals(passwordBarUser.getText(), conPasswordBarUser.getText()))
        {
            conPassLabelUser.setText("confirmed password doesn't match");
            return;
        }
        store.addAdmin(username,passwordBarUser.getText(),emailBarUser.getText());
        signUp.setText("admin added.");
    }
    public void logout(ActionEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("you are about to logout!!");
        alert.setContentText("Do you sure to logout from your account?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            currentStage.close();
        }
    }
}