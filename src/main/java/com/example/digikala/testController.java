package com.example.digikala;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class testController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene1(ActionEvent e) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
         stage = (Stage)((Node)e.getSource()).getScene().getWindow();
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
    }
    public void switchToScene2(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void up(ActionEvent e) {
        System.out.println("up");
    }
}
