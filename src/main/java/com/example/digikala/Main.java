package com.example.digikala;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.*;

public class Main extends Application {
    public static Store store;
    public static final String storeFileName = "saveStr.txt";
    public static final String adminFileName = "saveAdm.txt";
    public static final String logoAddress = "G:\\code\\java\\OXDCommod\\OXDCommod.png";
    public static final String[] level = {"no rank","POLLO Seller","noobBag seller","noob seller","NORO seller","PROB seller","proBag seller","pro seller","cheater seller"};
    @Override
    public void start(Stage stage) throws Exception
    {
        File file = new File("OXD.mp4");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        Text label = new Text();
        label.setText("OXD employees is cleaning the entrance hall.\n" +
                "                     just a moment.");
        label.setLayoutX(100);
        label.setLayoutY(60);
        label.setFill(Color.DARKORANGE);
        label.setOpacity(0.7);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 35));
        AnchorPane stackPane =  new AnchorPane();
        stackPane.getChildren().add(mediaView);
        stackPane.getChildren().add(label);
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
                try {
                    save1(store,storeFileName);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }
    public static void save1(Object obj , String fileName) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(obj);
            outputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object load1(String fileName) throws IOException, ClassNotFoundException {
        Object obj=null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            obj = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static void Test()
    {
        store = new Store("www.OXDCommod.com",33655523258L,"OXD-Seyed",43.78,-0.7);
        Admin.setStatics();
        store.addUser("u3","123",1234,"washington","@");
        store.addUser("u2","123",123,"jiroft","@");
        store.addUser("u1","123",123,"shahr babak","@");
        store.addSeller("sel1","123","@","inc");
        store.addSeller("sel2","123","@","inc");
        store.addAdmin("ad1","123","@");
        store.addAdmin("ad2","123","@");
        Admin admin = store.findAdmin(store.login("ad1","123").getValue());
        admin.sellerConfirmation(true,null);
        admin.sellerConfirmation(true,null);
        Seller seller = store.findSeller(store.login("sel1","123").getValue());
        Seller seller1 = store.findSeller(store.login("sel2","123").getValue());
        SubCategory cat = new SubCategory("Tom",100,2,"very bad","   ",seller.getUuid(),"Pet","Cat");
        SubCategory dog = new SubCategory("Mike",100,5,"trained","   ",seller.getUuid(),"Pet","dog");
        SubCategory safeBox = new SubCategory("SereneLife Safe Box",500,7,"safe ","X%$",seller1.getUuid(),"tool","safeBox");
        SubCategory soup = new SubCategory("special soup",100,50,"nice","soupSaz",seller1.getUuid(),"food","soup");
        SubCategory soup2 = new SubCategory("special soup",100,50,"nice","soupSaz",seller.getUuid(),"food","soup");
        dog.setImageAddress("G:\\code\\java\\OXDCommod\\src\\main\\resources\\com\\example\\digikala\\Mike.jpg");
        soup.setImageAddress("G:\\code\\java\\OXDCommod\\src\\main\\resources\\com\\example\\digikala\\special soup.jpg");
        soup2.setImageAddress("G:\\code\\java\\OXDCommod\\src\\main\\resources\\com\\example\\digikala\\special soup.jpg");
        store.sendSellerOrder(seller1.getUuid(),safeBox);
        store.sendSellerOrder(seller.getUuid(),cat);
        store.sendSellerOrder(seller.getUuid(),dog);
        store.sendSellerOrder(seller1.getUuid(),soup);
        store.sendSellerOrder(seller.getUuid(),soup2);
        admin.checkSellerReq(true,new Pair<>(seller.getUuid(),dog.getUuid()),null);
        admin.checkSellerReq(true,new Pair<>(seller.getUuid(),cat.getUuid()),null);
        admin.checkSellerReq(true,new Pair<>(seller1.getUuid(),safeBox.getUuid()),null);
        admin.checkSellerReq(true,new Pair<>(seller.getUuid(),soup2.getUuid()),null);
        admin.checkSellerReq(true,new Pair<>(seller1.getUuid(),soup.getUuid()),null);
        seller.makeDiscount(10,dog.getUuid());
        seller.makeDiscount(50,cat.getUuid());
        seller1.makeDiscount(20,soup.getUuid());
        store.ban(seller1.getUuid());
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //OXDCommodity;
        //shop location : "name": "Souprosse",
        //                "region": "Aquitaine",
        //                "country": "France",
        store = (Store) load1(storeFileName);
        SaveAdmin temp = (SaveAdmin) load1(adminFileName);
        Admin.loadAdmin(temp.getNotification(),temp.getOldNotification(),temp.isFirstTime(),temp.getSellerConfirm(),temp.getOrders(),temp.getWalletRequests(),temp.getSellerRequests(),temp.getSubscriptions(),temp.getRefunds());
        //Test();
        MainMenu.setStatus(null);
        launch(args);
    }
}
