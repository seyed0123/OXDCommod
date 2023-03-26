package com.example.digikala;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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

public class MainMenu implements Initializable{
    private static Store store;
    private static User user;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button searchButton;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            list.add(pro.getName()+" with brand "+pro.getBrand()+"    RATE : "+pro.getRate()+"    Discount:  "+pro.getDiscount()+"  finalPrice :"+pro.getFinalPrice());
        }
        offers.getItems().addAll(list);
        offers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                UUID product = offer.get(offers.getSelectionModel().getSelectedIndex());
                SeeProduct.setStatus(store,store.findProduct(product),null);
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
                seeProductStage.getIcons().add(new Image("G:\\code\\java\\OXDCommod\\src\\main\\resources\\com\\example\\digikala\\OXDCommod.png"));
                seeProductStage.setScene(scene);
                seeProductStage.show();
            }
        });
    }

    public static void setUser(User user) {
        MainMenu.user = user;
    }

    public static void setStatus(Store store, User inUser) {
        MainMenu.store = store;
        if(inUser!=null)
            MainMenu.user=inUser;
    }
    public void searchButton(ActionEvent e) throws IOException {
        String wasSearch = searchBar.getText();
        SearchTab.setStatus(store,null,wasSearch);
        Stage SearchTab= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SearchTab.fxml"));
        SearchTab.setTitle("OXDCommod!!");
        Scene scene  =new Scene(root);
        scene.getProperties().put("name","SearchTab");
        SearchTab.getIcons().add(new Image("G:\\code\\java\\OXDCommod\\src\\main\\resources\\com\\example\\digikala\\OXDCommod.png"));
        SearchTab.setScene(scene);
        SearchTab.show();
    }
    public void singButton(ActionEvent e) throws IOException {
        SignPanel.setStatus(store);
        Stage SignPanel= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SignPanel.fxml"));
        SignPanel.setTitle("OXDCommod!!");
        Scene scene  =new Scene(root);
        scene.getProperties().put("name","SignPanel");
        SignPanel.getIcons().add(new Image("G:\\code\\java\\OXDCommod\\src\\main\\resources\\com\\example\\digikala\\OXDCommod.png"));
        SignPanel.setScene(scene);
        SignPanel.show();
    }
    public void personPanel(ActionEvent e)
    {

    }
}
