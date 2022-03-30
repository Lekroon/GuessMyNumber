package com.guessgame.guessmynumber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class StartWindowController {

    @FXML
    private Label leftErrorLabel;
    @FXML
    private Label rightErrorLabel;
    @FXML
    private TextField leftLabel;
    @FXML
    private TextField rightLabel;


    @FXML
    void onStartingBtnClick(ActionEvent event) throws IOException {
        boolean openScene = true;
        boolean canParse = true;

        String startingNumber = leftLabel.getText();
        String endingNumber = rightLabel.getText();

        // Obsługa błędów
        if (startingNumber.isEmpty()) {
            openScene = false;
            canParse = false;
            leftErrorLabel.setText("Enter the number!");
        }
        if (endingNumber.isEmpty()) {
            openScene = false;
            canParse = false;
            rightErrorLabel.setText("Enter the number!");
        }
        if (!endingNumber.isEmpty())
            rightErrorLabel.setText("");

        if (!startingNumber.isEmpty())
            leftErrorLabel.setText("");
        else if (!endingNumber.isEmpty())
            rightErrorLabel.setText("");

        if (canParse) {
            int parsedStartingNumber = Integer.parseInt(startingNumber);
            int parsedEndingNumber = Integer.parseInt(endingNumber);
            if (parsedStartingNumber > parsedEndingNumber) {
                openScene = false;
                leftErrorLabel.setText("This number must be smaller!");
            }
        }

        // Tworzenie nowej sceny
        if (openScene) {
            FXMLLoader fxmlLoader = new FXMLLoader(MainGameWindow.class.getResource("game-window.fxml"));
            Parent root = fxmlLoader.load();

            MainGameController mainGameController = fxmlLoader.getController();
            mainGameController.setNumbers(startingNumber, endingNumber);
            mainGameController.rollingNumber();
            mainGameController.setYourScoreLabel();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
