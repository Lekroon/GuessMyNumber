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
import java.util.Objects;
import java.util.Random;

public class MainGameController {

    @FXML
    private Label attemptLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField yourNumber;
    @FXML
    private Label yourScoreLabel;
    @FXML
    private Label rangeLabel;

    private int startNumber;
    private int endNumber;
    private int randomNumber;
    private int attempt = 0;
    private int score = 0;


    @FXML
    void onGuessBtnClick(ActionEvent event) throws IOException {
        boolean canParse = true;
        int guessedNumber;

        attempt++;
        String guessedNumberStr = yourNumber.getText();

        if (guessedNumberStr.isEmpty()) {
            canParse = false;
            errorLabel.setText("Enter the number!");
        }

        if (canParse) {
            guessedNumber = Integer.parseInt(guessedNumberStr);
            if (guessedNumber < startNumber || guessedNumber > endNumber) {
                errorLabel.setText("Number out of range!");
                attemptLabel.setText(String.valueOf(attempt));
                yourNumber.setText("");
            }
            else if (guessedNumber < randomNumber) {
                errorLabel.setText("My number is bigger than this!");
                attemptLabel.setText(String.valueOf(attempt));
                yourNumber.setText("");
            }
            else if (guessedNumber > randomNumber) {
                errorLabel.setText("My number is smaller than this!");
                attemptLabel.setText(String.valueOf(attempt));
                yourNumber.setText("");
            }
            if (guessedNumber == randomNumber) {
                score++;

                FXMLLoader fxmlLoader = new FXMLLoader(MainGameWindow.class.getResource("winning-window.fxml"));
                Parent root = fxmlLoader.load();

                WinningWindowController winningGameController = fxmlLoader.getController();
                winningGameController.setAttempt(attempt);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    @FXML
    void onBackBtnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start-window.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onRerollBtnClick() {
        rollingNumber();
    }

    public void setNumbers(String startingNumber, String endingNumber) {
        startNumber = Integer.parseInt(startingNumber);
        endNumber = Integer.parseInt(endingNumber);
        rangeLabel.setText("Range: <" + startingNumber + ", " + endingNumber + ">");
    }

    public void setYourScoreLabel() {
        yourScoreLabel.setText(String.valueOf(score));
    }

    public void rollingNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(endNumber - startNumber +1) + startNumber;
    }

}
