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
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getProperties().put("name","MainMenu");
        stage.getIcons().add(new Image("G:\\code\\java\\OXDCommod\\src\\main\\resources\\com\\example\\digikala\\OXDCommod.png"));
        //String css= Objects.requireNonNull(this.getClass().getResource("test.css")).toExternalForm();
        //scene.getStylesheets().add(css);
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
        store.addUser("seyed","123",1234," ","@");
        store.addSeller("soupy","123","@","p");
        store.addAdmin("taha","123","taha@123.com");
        Seller seller = store.findSeller(store.login("soupy","123").getValue());
        Cat cat = new Cat("cot",10000,2,"very bad","kitty",seller.getUuid(),"false","red",2,"soup","2");
        Dog dog = new Dog("mike",10000,5,"trained","nothig",seller.getUuid(),"","black und yellow",2,"german","moz");
        SafeBox safeBox = new SafeBox("gatham",5000000,7,"safe ","X%$",seller.getUuid(),"windy",0,"bank",true,140,12,"scroll",20);
        store.sendSellerOrder(seller.getUuid(),safeBox);
        store.sendSellerOrder(seller.getUuid(),cat);
        store.sendSellerOrder(seller.getUuid(),dog);
        Admin admin = store.findAdmin(store.login("taha","123").getValue());
        admin.checkSellerReq(false,new Pair<>(seller.getUuid(),safeBox.getUuid()));
        admin.checkSellerReq(true,new Pair<>(seller.getUuid(),dog.getUuid()));
        admin.checkSellerReq(true,new Pair<>(seller.getUuid(),cat.getUuid()));
        admin.checkSellerReq(true,new Pair<>(seller.getUuid(),safeBox.getUuid()));
        seller.makeDiscount(10,dog.getUuid());
        seller.makeDiscount(50,cat.getUuid());
        launch(args);
    }
}
