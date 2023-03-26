package com.example.digikala;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class SignPanel {
    private static Store store;
    @FXML
    private Button signIn;
    @FXML
    private Button forgetPass;
    @FXML
    private Button signUp;
    @FXML
    private TextField usernameBar;
    @FXML
    private PasswordField passwordBar;
    @FXML
    private Label result;
    public static void setStatus(Store store)
    {
        SignPanel.store=store;
    }
    public void signIn(ActionEvent e) throws IOException, InterruptedException {
        String username = usernameBar.getText();
        String password = passwordBar.getText();
        Pair <String , UUID> result = store.login(username,password);
        if(result!=null)
        {
            if(Objects.equals(result.getKey(), "user"))
            {
                User user = store.findUser(result.getValue());
                SearchTab.setUser(user);
                MainMenu.setUser(user);
                SeeProduct.setUser(user);
                this.result.setText("hello again.you have been logged in as a USER named "+user.getUsername());
                ObservableList<Window> openWindow=Window.getWindows();
                for(Window window :openWindow)
                {
                    if(window instanceof Stage)
                    {
                        if (window.getScene().getProperties().get("name").equals("MainMenu"))
                        {
                            ((Stage) window).close();
                        }
                    }
                }
                this.result.setTextFill(Color.GREEN);
                Thread.sleep(2000);
                Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                currentStage.close();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                Scene scene = new Scene(root);
                scene.getProperties().put("name","MainMenu");
                stage.getIcons().add(new Image("G:\\code\\java\\OXDCommod\\src\\main\\resources\\com\\example\\digikala\\OXDCommod.png"));
                stage.setTitle("OXDCommod!!");
                stage.setScene(scene);
                stage.show();
            }
        }else
        {
            this.result.setText("OOPS!! Your username or password isn't correct.");
            this.result.setTextFill(Color.RED);
        }
    }
    public void signUp(ActionEvent e) throws IOException {
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        SignUpPanel.setStore(store);
        Parent root = FXMLLoader.load(getClass().getResource("SignUpPanel.fxml"));
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.show();
    }
    public void forgetPass(ActionEvent e)
    {
        result.setText("You did something wrong.  ψ(｀∇´)ψ  ");
        result.setTextFill(Color.PURPLE);
    }
}
