package com.example.digikala;

import static java.util.Arrays.asList;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.*;
import java.util.Objects;

public class Main extends Application {
    public static Store store;
    public static final String fileName="save.txt";
    public static final String logoAddress = "G:\\code\\java\\OXDCommod\\src\\main\\resources\\com\\example\\digikala\\OXDCommod.png";
    public static final String[] level = {"POLLO Seller","noobSab seller","noob seller","NORO seller","PROB seller","pro seller","proSag seller","cheater seller"};
    @Override
    public void start(Stage stage) throws Exception
    {
        File file = new File("G:\\code\\java\\OXDCommod\\src\\main\\resources\\com\\example\\digikala\\OXD.mp4");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        Label label = new Label();
        label.setText("OXD is opening");
        label.setLayoutX(512);
        label.setLayoutY(50);
        label.setTextFill(Color.GAINSBORO);
        label.setFont(new Font(30));
        StackPane stackPane =  new StackPane();
        stackPane.getChildren().addAll(mediaView,label);
        Scene scene2 = new Scene(stackPane,1024,640);
        stage.setScene(scene2);
        stage.show();
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(stage::close);
        stage.setOnHiding(event ->{
            mediaPlayer.pause();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            scene.getProperties().put("name","MainMenu");
            Stage stage1 = new Stage();
            stage1.getIcons().add(new Image(Main.logoAddress));
            stage1.setTitle("OXDCommod!!");
            stage1.setScene(scene);
            stage1.show();
            stage1.setOnHiding(even ->{
                event.consume();
                try {
                    save(store,fileName);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }
    public static void save(Store store , String fileName) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(store);
        }
    }
    public static Store load(String fileName) throws IOException, ClassNotFoundException{
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName)))
        {
            return (Store) inputStream.readObject();
        }
    }
    public static void Test(Store store)
    {
        store = new Store("www.OXDCommod.com",123123,"OXD-Seyed",-54.28,-36.51);
        store.setStore(store);
        MainMenu.setStatus(store,null);
        store.addUser("seyed","123",1234,"paris","qwe@OXD.net");
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
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //OXDCommodity;
        //store = load(fileName);
        Test(store);
        //save(store,fileName);
        launch(args);
    }
}
