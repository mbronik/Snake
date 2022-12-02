package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class NickNameScreen extends Application {
    Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Enter a nickname");
        VBox vb = new VBox();
        Label title = new Label("Enter a nickname");
        TextField textField = new TextField();
        Button save = new Button("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("test");
            }
        });
        vb.getChildren().addAll(title, textField, save);
        scene = new Scene(vb, 200, 100);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
