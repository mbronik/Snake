package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainMenuController {

    @FXML
    private void switchToPlay() throws IOException {
        //App.setRoot("game");
        App.startGame();
    }
    @FXML
    private void switchToRanking() throws IOException {
        App.printMainRanking();
        scoreBoard = new Label();
        scoreBoard.setText(App.getMainRanking().toString());
        App.setRoot("ranking");
    }
    @FXML
    private void switchToSetting() throws IOException {
        App.setRoot("settings");
    }
    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("mainMenu");
    }
    @FXML
    private void exitApp() throws IOException {
        System.exit(0);
    }

    @FXML
    private Label lblScore;

    @FXML
    public void setScore(int score) throws IOException {
        lblScore.setText(String.valueOf(score));
    }


    @FXML
    private Label scoreBoard;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("mainMenu");
    }


    @FXML
    private void saveSettings(){

    }

}
