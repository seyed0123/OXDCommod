module com.example.digikala {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires org.json;
    requires javafx.media;


    opens com.example.digikala to javafx.fxml;
    exports com.example.digikala;
}