module com.example.digikala {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.digikala to javafx.fxml;
    exports com.example.digikala;
}