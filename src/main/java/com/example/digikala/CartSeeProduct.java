package com.example.digikala;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CartSeeProduct implements Initializable {
    private static Store store;
    private static User user;
    private static Product product;
    @FXML
    private Label info;
    @FXML
    private Button remove;
    @FXML
    private Spinner<Integer> counter;
    @FXML
    private int currentAmount;
    public static void setStatus(Store store, User user , Product product)
    {
        CartSeeProduct.store = store;
        CartSeeProduct.user=user;
        CartSeeProduct.product=product;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        info.setText(product.toString());
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,product.getAmount());
        valueFactory.setValue(user.cartExist(product.getUuid()));
        counter.setValueFactory(valueFactory);
        counter.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                if(currentAmount<counter.getValue())
                    user.addCart(product.getUuid());
                else
                    user.reduceCart(product.getUuid());
                currentAmount=counter.getValue();
            }
        });
    }
    public void removeCart(ActionEvent e)
    {
        user.removeCart(product.getUuid());
        remove.setText("removed");
        back(e);
    }
    public void back(ActionEvent e)
    {
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
