package com.example.digikala;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import static com.example.digikala.Main.store;

public class MainMenu implements Initializable{
    private static User user;
    @FXML
    private TextField searchBar;
    @FXML
    private Button signButton;
    @FXML
    private ListView<String> offers;
    @FXML
    private ImageView person;
    @FXML
    private Label userLogIn;
    @FXML
    private Label storeInfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storeInfo.setText("!!OXDCommodity!!  Owner:"+store.getOwner()+" supportNumber:"+store.getSupportNumber()+" webAddress:"+store.getWebAddress()+" location: name: Souprosse region: Aquitaine country: France");
        if(user==null)
        {
            person.setVisible(false);
            person.setDisable(true);
        }else {
            userLogIn.setText(user.getUsername());
            signButton.setDisable(true);
            signButton.setVisible(false);
        }
        ArrayList<UUID> offer =store.offers();
        ArrayList<String>  list = new ArrayList<>();
        for(UUID product : offer)
        {
            Product pro = store.findProduct(product);
            list.add(pro.toString());
        }
        offers.getItems().addAll(list);
        offers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                UUID product = offer.get(offers.getSelectionModel().getSelectedIndex());
                SeeProduct.setStatus(store.findProduct(product),null);
                Stage seeProductStage= new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("SeeProduct.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                seeProductStage.setTitle("OXDCommod!!");
                Scene scene  =new Scene(root);
                scene.getProperties().put("name","SeeProduct");
                seeProductStage.getIcons().add(new Image(Main.logoAddress));
                seeProductStage.setScene(scene);
                seeProductStage.show();
            }
        });
        person.setPickOnBounds(true);
        person.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                UserPanel.setStatus(user);
                Stage UserPanel= new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                UserPanel.setTitle("OXDCommod!!");
                Scene scene  =new Scene(root);
                scene.getProperties().put("name","UserPanel");
                UserPanel.getIcons().add(new Image(Main.logoAddress));
                UserPanel.setScene(scene);
                UserPanel.show();
            }
        });
    }

    public static void setUser(User user) {
        MainMenu.user = user;
    }

    public static void setStatus( User inUser) {
        if(inUser!=null)
            MainMenu.user=inUser;
    }
    public void searchButton(ActionEvent e) throws IOException {
        String wasSearch = searchBar.getText();
        SearchTab.setStatus(user,wasSearch);
        Stage SearchTab= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SearchTab.fxml"));
        SearchTab.setTitle("OXDCommod!!");
        Scene scene  =new Scene(root);
        scene.getProperties().put("name","SearchTab");
        SearchTab.getIcons().add(new Image(Main.logoAddress));
        SearchTab.setScene(scene);
        SearchTab.show();
    }
    public void singButton(ActionEvent e) throws IOException {
        Stage SignPanel= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SignPanel.fxml"));
        SignPanel.setTitle("OXDCommod!!");
        Scene scene  =new Scene(root);
        scene.getProperties().put("name","SignPanel");
        SignPanel.getIcons().add(new Image(Main.logoAddress));
        SignPanel.setScene(scene);
        SignPanel.show();
    }
    public void close(ActionEvent e) throws IOException {
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage.close();
        SaveAdmin temp = new SaveAdmin(Admin.getAllNotification(),Admin.getOldNotification(),Admin.isFirstTime(),Admin.getSellerConfirm(),Admin.getOrders(),Admin.getWalletRequests(),Admin.getSellerRequests(),Admin.getSubscriptions(),Admin.getRefunds());
        Main.save1(temp,Main.adminFileName);
        Main.save1(store,Main.storeFileName);
    }
}
