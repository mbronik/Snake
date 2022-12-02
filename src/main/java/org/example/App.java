package org.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static int gameHigh = 20;
    private static int gameWidth = 32;

    private static int ScreenFactor = 28;

    public static int ScreenHigh = gameHigh * ScreenFactor;
    public static int ScreenWidth = gameWidth * ScreenFactor;

    private static Scene scene;
    private static SnakeGraphic sg;

    private static Ranking mainRanking = new Ranking(gameHigh, gameWidth, 20);

    private static String nick = "Player";

    private static Stage loginStage;

    @Override
    public void start(Stage stage) throws IOException {
        loginStage = stage;
        Scene login = new Scene(loadFXML("enterNick"));
        loginStage.setTitle("Enter nick");
        loginStage.setScene(login);
        loginStage.show();
//        stage.setTitle("Snake");
//        scene = new Scene(loadFXML("mainMenu"), ScreenWidth, ScreenHigh);
//        stage.setResizable(false);
//        stage.setOnCloseRequest( ev -> {
//            System.exit(0);
//        });
//        stage.setScene(scene);
//        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void startGame(){
        sg = new SnakeGraphic(gameWidth, gameHigh, ScreenFactor);
        sg.init(scene);
        sg.start();
    }

    public static void addToRanking(int score){
        mainRanking.addScore( new PlayerScore(nick, score) );
    }

    public static Ranking getMainRanking() {
        return mainRanking;
    }

    static void printMainRanking(){
        System.out.println(mainRanking.toString());
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException {
        mainRanking.load("MainRanking.txt");
        launch();
    }

    public static void setNick(String nick) {
        App.nick = nick;
    }

    public static void openMainMenuWindow() throws IOException {
        loginStage.close();
        Stage stage = new Stage();
        stage.setTitle("Snake");
        scene = new Scene(loadFXML("mainMenu"), App.ScreenWidth, App.ScreenHigh);
        stage.setResizable(false);
        stage.setOnCloseRequest( ev -> {
            System.exit(0);
        });
        stage.setScene(scene);
        stage.show();
    }
}