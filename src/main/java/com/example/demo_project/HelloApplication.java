package com.example.demo_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import com.example.demo_project.HelloController;

public class HelloApplication extends Application {

    int i = 10;
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        ; // layoutX="571.0" layoutY="439.0"

        Scene scene = new Scene(fxmlLoader.load());

        scene.getWindow();
        stage.setTitle("Temperature Calculator 2006 (its that old!)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}