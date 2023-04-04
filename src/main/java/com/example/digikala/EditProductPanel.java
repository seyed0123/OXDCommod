package com.example.digikala;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import static com.example.digikala.Main.store;

public class EditProductPanel implements Initializable {
    private static SubCategory product;
    private static boolean isProductWaiting;
    @FXML
    private TextField name;
    @FXML
    private TextField brand;
    @FXML
    private TextField price;
    @FXML
    private TextField amount;
    @FXML
    private TextField category;
    @FXML
    private TextField subCategory;
    @FXML
    private TextArea description;
    @FXML
    private TextField discountBar;
    @FXML
    private TextField imagePath;
    public static void setStatus(Product product,boolean isProductWaiting)
    {
        EditProductPanel.product= (SubCategory) product;
        EditProductPanel.isProductWaiting = isProductWaiting;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(product.getName());
        brand.setText(product.getBrand());
        price.setText((int)product.getPrice()+"");
        amount.setText(product.getAmount()+"");
        category.setText(product.getTitle());
        subCategory.setText(product.getSubTitle());
        description.setText(product.getDescription());
        discountBar.setText(product.getDiscount()+"");
        imagePath.setText(product.getImageAddress());
    }
    public void change(ActionEvent e)
    {
        if(!amount.getText().matches("\\d+") || Objects.equals(amount.getText(), ""))
        {
            amount.setText("enter a valid integer");return;
        }
        if(!price.getText().matches("\\d+") || Objects.equals(price.getText(), ""))
        {
            price.setText("enter a valid integer");return;
        }
        if(!discountBar.getText().matches("\\d+") || Objects.equals(discountBar.getText(), "") || Integer.parseInt(discountBar.getText())>100)
        {
            discountBar.setText("enter a valid integer");return;
        }
        if(Objects.equals(name.getText(), "")) {
            name.setText("this field can't be empty.");
            return;
        }
        if(Objects.equals(brand.getText(), "")) {
            brand.setText("this field can't be empty.");
            return;
        }
        if(Objects.equals(category.getText(), "")) {
            category.setText("this field can't be empty.");
            return;
        }
        if(Objects.equals(subCategory.getText(), "")) {
            subCategory.setText("this field can't be empty.");
            return;
        }
        product.setName(name.getText());
        product.setBrand(brand.getText());
        product.setExactAmount(Integer.parseInt(amount.getText()));
        product.setPrice(Integer.parseInt(price.getText()));
        product.setDescription(description.getText());
        product.setTitle(category.getText());
        product.setSubTitle(subCategory.getText());
        product.setDiscount(Integer.parseInt(discountBar.getText()));
        product.setImageAddress(imagePath.getText());
        if(isProductWaiting)
            store.sendSellerOrder(product.getSellerID(),product);
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage.close();
    }
    public void remove (ActionEvent e)
    {
        if(!isProductWaiting)
            store.removeProduct(product.getUuid());
        else
            store.findSeller(product.getSellerID()).removeWaitProduct(product.getUuid());
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage.close();
    }
    public void back(ActionEvent e)
    {
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
