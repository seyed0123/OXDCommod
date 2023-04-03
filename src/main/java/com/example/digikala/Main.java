package com.example.digikala;

import static java.util.Arrays.asList;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Objects;

public class Main extends Application {
    public static Store store;
    public static final String logoAddress = "G:\\code\\java\\OXDCommod\\src\\main\\resources\\com\\example\\digikala\\OXDCommod.png";
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getProperties().put("name","MainMenu");
        stage.getIcons().add(new Image(Main.logoAddress));
        stage.setTitle("OXDCommod!!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args)
    {
        //OXDCommodity;
        store = new Store("www.OXDCommod.com",123123,"OXD-Seyed");
        store.setStore(store);
        MainMenu.setStatus(store,null);
        store.addUser("seyed","123",1234,"iran,soup","qwe@OXD.net");
        store.addSeller("soupy","123","@","p");
        store.addAdmin("taha","123","taha@123.com");
        Admin admin = store.findAdmin(store.login("taha","123").getValue());
        admin.sellerConfirmation(true,null);
        Seller seller = store.findSeller(store.login("soupy","123").getValue());
        SubCategory cat = new SubCategory("cot",10000,2,"very bad","kitty",seller.getUuid(),"Pet","Cat");
        SubCategory dog = new SubCategory("mike",10000,5,"trained","nothing",seller.getUuid(),"Pet","dog");
        SubCategory safeBox = new SubCategory("gatham",5000000,7,"safe ","X%$",seller.getUuid(),"tool","safeBox");
        store.sendSellerOrder(seller.getUuid(),safeBox);
        store.sendSellerOrder(seller.getUuid(),cat);
        store.sendSellerOrder(seller.getUuid(),dog);
        admin.checkSellerReq(true,new Pair<>(seller.getUuid(),dog.getUuid()),null);
        admin.checkSellerReq(true,new Pair<>(seller.getUuid(),cat.getUuid()),null);
        admin.checkSellerReq(true,new Pair<>(seller.getUuid(),safeBox.getUuid()),null);
        seller.makeDiscount(10,dog.getUuid());
        seller.makeDiscount(50,cat.getUuid());
        launch(args);
    }
}
