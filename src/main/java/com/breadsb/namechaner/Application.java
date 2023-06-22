package com.breadsb.namechaner;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        stage.setTitle("File name changer");
        stage.setScene(scene);
        stage.show();
        ((Label) scene.lookup("#copyrights")).setText("Copyright © BreadSolutions 2023");
    }

    public static void main(String[] args) {
        launch();
    }
}