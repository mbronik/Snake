package org.example;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EnterNickController {
    @FXML
    Button save;
    @FXML
    TextField textField;

    @FXML
    private void onSaveButtonAction() throws IOException {
        App.setNick(textField.getText());
        App.openMainMenuWindow();
    }
}
