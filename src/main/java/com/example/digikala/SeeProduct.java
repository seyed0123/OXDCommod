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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import static com.example.digikala.Main.store;

public class SeeProduct implements Initializable{
    private static User user;
    private static Product product;
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
    @FXML
    private Button favorite;
    @FXML
    private Label favoriteLabel;
    @FXML
    private Label rateLabel;
    @FXML
    private TextField rateBar;
    @FXML
    private ImageView productImage;
    @FXML
    private ListView<String> commentList;
    @FXML
    private TextField commentBar;
    @FXML
    private Button removeCommentButton;
    @FXML
    private Button removeRateButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(product.getAmount()<1)
        {
            addToCart.setText("unavailable");
            addToCart.setTextFill(Color.RED);
            addToCart.setDisable(true);
        }
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
            removeRateButton.setVisible(false);
            removeCommentButton.setVisible(false);
            commentBar.setVisible(false);
            favorite.setVisible(false);
            person.setVisible(false);
            person.setDisable(true);
            addToCart.setDisable(true);
        }else {
            if(user.getWait())
            {
                addToCart.setDisable(true);
                addToCart.setText("waiting for admin to approval");
            }
            if(product.didRate(user.getUuid()))
            {
                removeRateButton.setDisable(false);
                rateLabel.setText("The score given by you: "+product.getUserRate(user.getUuid()));
            }
            user.addLastSeen(product.getUuid());
            if(user.favoriteExist(product.getUuid()))
            {
                favoriteLabel.setText("added to favorite.");
                favorite.setText("remove from favorite");
            }
            if(user.cartExist(product.getUuid())>0)
            {
                addToCart.setText("added.");
                addToCart.setTextFill(Color.GREEN);
                addToCart.setDisable(true);
            }
            if(product.didComment(user.getUuid()))
            {
                removeCommentButton.setDisable(false);
                commentBar.setText(product.getComment(user.getUuid()));
            }
            rateBar.setVisible(true);
            userLogIn.setText(user.getUsername());
            signButton.setDisable(true);
            signButton.setVisible(false);
        }
        name.setText("name: "+product.getName());
        brand.setText("brand: "+product.getBrand());
        rate.setText("RATE: "+Double.toString(product.getRate()));
        comment.setText("Description: "+product.getDescription());
        another.setText("more info: "+product.toString()+ "\n\n seller level ="+Main.level[store.findSeller(product.getSellerID()).getSellerLevel()]);
        totalPrice.setText(Double.toString(product.getFinalPrice()));
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
        commentList.getItems().addAll(product.getComment());
        commentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("check comment");
                alert.setHeaderText(commentList.getSelectionModel().getSelectedItem()+"\n");
                alert.show();
            }
        });
        try
        {
            Image image = new Image(product.getImageAddress());
            productImage.setImage(image);
        }catch (Exception e)
        {
        }
    }
    public static void setUser(User user) {
        SeeProduct.user = user;
    }
    public static void setStatus(Product product, User inUser)
    {
        if(inUser!=null)
            SeeProduct.user=inUser;
        SeeProduct.product=product;

    }
    public void signButton(ActionEvent e) throws IOException {
        Stage SignPanel= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SignPanel.fxml"));
        SignPanel.setTitle("OXDCommod!!");
        Scene scene  =new Scene(root);
        scene.getProperties().put("name","SignPanel");
        SignPanel.getIcons().add(new Image(Main.logoAddress));
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
    public void addFavorite(ActionEvent e)
    {
        if(Objects.equals(favorite.getText(), "add to favorite")) {
            user.addFavorite(product.getUuid());
            favoriteLabel.setText("added to favorite.");
            favorite.setText("remove from favorite");
        }else
        {
            user.removeFavorite(product.getUuid());
            favorite.setText("add to favorite");
            favoriteLabel.setText("");
        }
    }
    public void rate(ActionEvent e)
    {
        if(!rateBar.getText().matches("\\d+")) {
            rateLabel.setText("only enter integers.");
        }else if(Integer.parseInt(rateBar.getText())>10)
        {
            rateLabel.setText("enter number in range (0,10).");
        }else {
            int rate = Integer.parseInt(rateBar.getText());
            if(product.didRate(user.getUuid()))
            {
                product.changeRate(rate,user.getUuid());
            }else
            {
                product.setRate(rate,user.getUuid());
            }
            rateLabel.setText("The score given by you: "+rate);
            removeRateButton.setDisable(false);
            removeRateButton.setText("remove rate");
        }
        rateBar.setText("");
    }
    public void comment(ActionEvent e)
    {
        product.setComment(commentBar.getText(),user.getUuid());
        commentBar.setText("your comment added.");
        commentBar.setDisable(true);
        removeCommentButton.setDisable(false);
        removeCommentButton.setText("remove comment");
    }
    public void removeRate(ActionEvent e)
    {
        product.removeRate(user.getUuid());
        rateBar.setText("");
        rateLabel.setText("");
        removeRateButton.setText("removed");
        removeRateButton.setDisable(true);
    }
    public void removeComment(ActionEvent e)
    {
        product.removeComment(user.getUuid());
        commentBar.setText("");
        removeCommentButton.setText("removed");
        removeCommentButton.setDisable(true);
    }
    public void back(ActionEvent e)
    {
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
