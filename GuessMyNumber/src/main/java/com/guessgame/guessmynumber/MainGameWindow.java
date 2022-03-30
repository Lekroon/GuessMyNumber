package com.guessgame.guessmynumber;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainGameWindow extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainGameWindow.class.getResource("start-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 413, 257);
        stage.setTitle("Guess my number");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}