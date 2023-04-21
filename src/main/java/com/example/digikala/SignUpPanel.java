package com.example.digikala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static com.example.digikala.Main.store;
import java.util.Currency;
import java.util.Objects;

public class SignUpPanel {
    @FXML
    private TextField usernameBarUser;
    @FXML
    private PasswordField passwordBarUser;
    @FXML
    private PasswordField conPasswordBarUser;
    @FXML
    private TextField emailBarUser;
    @FXML
    private TextField addressBarUser;
    @FXML
    private TextField numberBarUser;
    @FXML
    private Button signUpUser;
    @FXML
    private Label usernameLabelUser;
    @FXML
    private Label conPassLabelUser;
    @FXML
    private TextField usernameBarSeller;
    @FXML
    private PasswordField passwordBarSeller;
    @FXML
    private PasswordField conPasswordBarSeller;
    @FXML
    private TextField emailBarSeller;
    @FXML
    private TextField companyBarSeller;
    @FXML
    private Button signUpSeller;
    @FXML
    private Label usernameLabelSeller;
    @FXML
    private Label conPassLabelSeller;
    @FXML
    private Label numberLabelUser;

    public void checkStatusUser(ActionEvent e)
    {
        checkNumber();
        checkPasswordUser();
        checkUsernameUser();
        if(Objects.equals(usernameLabelUser.getText(), "this username is available"))
            if(Objects.equals(conPassLabelUser.getText(), "OK!!") && !Objects.equals(passwordBarUser.getText(), ""))
                if(!Objects.equals(emailBarUser.getText(), ""))
                    if(!Objects.equals(addressBarUser.getText(), ""))
                        if(!Objects.equals(numberBarUser.getText(), "") && numberBarUser.getText().matches("\\d+"))
                            signUpUser.setDisable(false);
    }
    public void checkStatusSeller(ActionEvent e)
    {
        checkPasswordSeller();
        checkUsernameSeller();
        if(Objects.equals(usernameLabelSeller.getText(), "this username is available"))
            if(Objects.equals(conPassLabelSeller.getText(), "OK!!") && !Objects.equals(passwordBarSeller.getText(), ""))
                if(!Objects.equals(emailBarSeller.getText(), ""))
                    if(!Objects.equals(companyBarSeller.getText(), ""))
                        signUpSeller.setDisable(false);
    }
    private void checkUsernameUser()
    {
        if(store.IsUsernameExist(usernameBarUser.getText()) || Objects.equals(usernameBarUser.getText(), ""))
        {
            usernameLabelUser.setText("this username already in use.");
        }else
            usernameLabelUser.setText("this username is available");
    }
    private void checkUsernameSeller()
    {
        if(store.IsUsernameExist(usernameBarSeller.getText())|| Objects.equals(usernameBarSeller.getText(), ""))
        {
            usernameLabelSeller.setText("this username is already in use.");
        }else
            usernameLabelSeller.setText("this username is available");
    }
    private void checkPasswordUser()
    {
        if(!Objects.equals(passwordBarUser.getText(), conPasswordBarUser.getText()))
        {
            conPassLabelUser.setText("confirmed password doesn't match");
        }else
        {
            conPassLabelUser.setText("OK!!");
        }
    }
    private void checkPasswordSeller()
    {
        if(!Objects.equals(passwordBarSeller.getText(), conPasswordBarSeller.getText()))
        {
            conPassLabelSeller.setText("confirmed password doesn't match");
        }else
            conPassLabelSeller.setText("OK!!");
    }
    private void checkNumber()
    {
        if(!numberBarUser.getText().matches("\\d+"))
            numberLabelUser.setText("phoneNumber must only contains numbers");
        else
            numberLabelUser.setText("OK!!");
    }
    public void singUpUser(ActionEvent e) throws InterruptedException {
        String username = usernameBarUser.getText();
        String password = passwordBarUser.getText();
        String address = addressBarUser.getText();
        String email = emailBarUser.getText();
        int number = Integer.parseInt(numberBarUser.getText());
        store.addUser(username,password,number,address,email);
        signUpUser.setText("done!");
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage.close();
    }
    public void signUPSeller(ActionEvent e) throws InterruptedException {
        String username = usernameBarSeller.getText();
        String password = passwordBarSeller.getText();
        String company = companyBarSeller.getText();
        String email = emailBarSeller.getText();
        store.addSeller(username,password,email,company);
        signUpUser.setText("done!");
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage.close();
    }
}