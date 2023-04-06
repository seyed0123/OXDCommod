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
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import static com.example.digikala.Main.store ;

public class SignPanel implements Initializable {
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
    @FXML
    private TextField CAPTCHABar;
    @FXML
    private ImageView CAPTCHAImage;
    @FXML
    private Button CAPTCHAButton;
    private String CAPTCHA;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCAPTCHA();
    }
    public void signIn(ActionEvent e) throws IOException, InterruptedException {
        String username = usernameBar.getText();
        String password = passwordBar.getText();
        if(!Objects.equals(CAPTCHABar.getText(), CAPTCHA) && !Objects.equals(CAPTCHA, ""))
        {
            result.setText("sorry you are a robot.");
            result.setTextFill(Color.BROWN);
            return;
        }
        Pair <String , UUID> result = store.login(username,password);
        if(result!=null)
        {
            if(Objects.equals(result.getKey(), "banUser") || Objects.equals(result.getKey(), "banSeller"))
            {
                this.result.setText("you have been banned by OXDEye");
            }else if(Objects.equals(result.getKey(), "verifySeller"))
                this.result.setText("you haven't been verified yet.");
            else if(Objects.equals(result.getKey(), "user"))
            {
                User user = store.findUser(result.getValue());
                SearchTab.setUser(user);
                MainMenu.setUser(user);
                SeeProduct.setUser(user);
                this.result.setTextFill(Color.GREEN);
                this.result.setText("hello "+user.getUsername()+".you have been logged in as a USER.");
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
                Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                currentStage.close();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                Scene scene = new Scene(root);
                scene.getProperties().put("name","MainMenu");
                stage.getIcons().add(new Image(Main.logoAddress));
                stage.setTitle("OXDCommod!!");
                stage.setScene(scene);
                stage.show();
            }else if(Objects.equals(result.getKey(),"seller"))
            {
                SellerPanel.setStatus(store.findSeller(result.getValue()));
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("SellerPanel.fxml"));
                Scene scene = new Scene(root);
                scene.getProperties().put("name","SellerPanel");
                stage.getIcons().add(new Image(Main.logoAddress));
                stage.setTitle("OXDCommod!!");
                stage.setScene(scene);
                stage.show();
                stage.setOnHiding(event -> {
                    event.consume();
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
                });
            }else
            {
                AdminPanel.setStatus(store.findAdmin(result.getValue()));
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
                Scene scene = new Scene(root);
                scene.getProperties().put("name","AdminPanel");
                stage.getIcons().add(new Image(Main.logoAddress));
                stage.setTitle("OXDCommod!!");
                stage.setScene(scene);
                stage.show();
                stage.setOnHiding(event -> {
                    event.consume();
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
                });
            }
        }else
        {
            this.result.setText("OOPS!! Your username or password isn't correct.");
            this.result.setTextFill(Color.RED);
        }
    }
    private void loadCAPTCHA()
    {
        Random random = new Random();
        int randomInt = random.nextInt(store.CAPTCHA.length);
        try
        {
            Image image = new Image("G:\\code\\java\\OXDCommod\\CAPTCHA\\"+store.CAPTCHA[randomInt]);
            CAPTCHA=store.CAPTCHA[randomInt].substring(0,5);
            CAPTCHAImage.setImage(image);
            CAPTCHAImage.setVisible(true);
            CAPTCHABar.setVisible(true);
            CAPTCHAButton.setVisible(true);
        }catch (Exception e) {
            CAPTCHA="";
            CAPTCHAImage.setVisible(false);
            CAPTCHABar.setVisible(false);
            CAPTCHAButton.setVisible(false);
        }
    }
    public void refresh(ActionEvent e)
    {
        loadCAPTCHA();
    }
    public void signUp(ActionEvent e) throws IOException {
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
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
