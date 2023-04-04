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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import static com.example.digikala.Main.store;

public class SearchTab implements Initializable {
    private static User user;
    private static String wasSearched;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button back;
    @FXML
    private Button signButton;
    @FXML
    private ListView<String> searched;
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
        ArrayList<UUID> offer =store.findProductByInfo(wasSearched);
        ArrayList<String>  list = new ArrayList<>();
        for(UUID product : offer)
        {
            Product pro = store.findProduct(product);
            list.add(pro.toString());
        }
        searched.getItems().addAll(list);
        searched.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                UUID product = offer.get(searched.getSelectionModel().getSelectedIndex());
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
                //scene.getProperties().put("name","UserPanel");
                UserPanel.getIcons().add(new Image(Main.logoAddress));
                UserPanel.setScene(scene);
                UserPanel.show();
            }
        });
    }

    public static void setUser(User user) {
        SearchTab.user = user;
    }

    public static void setStatus(User inUser, String searched) {
        if(inUser!=null)
            SearchTab.user=inUser;
        SearchTab.wasSearched=searched;
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
    public void back(ActionEvent e)
    {
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
