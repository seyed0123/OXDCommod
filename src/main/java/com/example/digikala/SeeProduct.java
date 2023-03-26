package com.example.digikala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SeeProduct implements Initializable{
    private static User user;
    private static Product product;
    private static Store store;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button addToCart;
    @FXML
    private Button signButton;
    @FXML
    private Label dis;
    @FXML
    private Label name;
    @FXML
    private Label brand;
    @FXML
    private Label rate;
    @FXML
    private Label comment;
    @FXML
    private Label another;
    @FXML
    private Label totalPrice;
    @FXML
    private ImageView person;
    @FXML
    private Label userLogIn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(product.getDiscount()==0)
        {
            dis.setVisible(false);
            dis.setDisable(true);
        }else
        {
            dis.setText("<--- discount"+product.getDiscount() + "% with " + product.getPrice());
        }
        if(user==null)
        {
            person.setVisible(false);
            person.setDisable(true);
            addToCart.setDisable(true);
        }else {
            userLogIn.setText(user.getUsername());
            signButton.setDisable(true);
            signButton.setVisible(false);
        }
        name.setText("name:"+product.getName());
        brand.setText("brand:"+product.getBrand());
        rate.setText("RATE:"+Double.toString(product.getRate()));
        comment.setText("comment:"+product.getComment());
        another.setText("more info:"+product.toString());
        totalPrice.setText(Double.toString(product.getFinalPrice()));
    }
    public static void setUser(User user) {
        SeeProduct.user = user;
    }
    public static void setStatus(Store store, Product product, User inUser)
    {
        SeeProduct.store = store;
        if(inUser!=null)
            SeeProduct.user=inUser;
        SeeProduct.product=product;

    }
    public void personPanel(ActionEvent e)
    {

    }
    public void signButton(ActionEvent e) throws IOException {
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
    public void addToCart(ActionEvent e)
    {
        user.addCart(product.getUuid());
        addToCart.setText("added.");
        addToCart.setTextFill(Color.GREEN);
        addToCart.setDisable(true);
    }
    public void back(ActionEvent e)
    {
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
