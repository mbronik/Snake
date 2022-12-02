package org.example;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.IOException;


public class SnakeGraphic extends Thread{

    private static final int Frequency = 100;

    private static int score = 0;

    private static Label title;
    private static String strTitle;
    private int gameWidth, gameHigh, ScreenFactor;

    private static Rectangle[][] rectMap;
    private static Snake snake;
    private static Apple apple;
    public static boolean beLife;


    public SnakeGraphic(int gameWidth, int gameHigh, int ScreenFactor){
        this.gameHigh = gameHigh;
        this.gameWidth = gameWidth;
        this.ScreenFactor = ScreenFactor;
        rectMap = new Rectangle[gameWidth][gameHigh];
    }


    public void init(Scene scene) {
        snake = new Snake(gameWidth, gameHigh, Towards.RIGHT);
        apple = new Apple(gameWidth, gameHigh);

        StackPane sp = new StackPane();
        sp.setStyle("-fx-background-color: #49A93F;");

        score = 0;
        GridPane gp = new GridPane();
        title = new Label();
        title.setStyle("-fx-font-weight: bold;");
        title.setFont(new Font(24));
        title.setText(strTitle);
        title.setAlignment(Pos.BOTTOM_LEFT);

        gp.setHgap(0);
        gp.setVgap(0);
        gp.setGridLinesVisible(true);

        rectMap = new Rectangle[gameWidth][gameHigh];

        for (int y = 0; y < gameHigh; y++) {
            for (int x = 0; x < gameWidth; x++) {
                Rectangle rect = new Rectangle(ScreenFactor, ScreenFactor);
                rect.setFill(Color.TRANSPARENT);
                rectMap[x][y] = rect;
                gp.add(rectMap[x][y], x, y);
            }
        }

        sp.getChildren().addAll(title, gp);

        scene.setRoot(sp);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.RIGHT) {
                    if (snake.getToward() != Towards.LEFT) {
                        snake.setToward(Towards.RIGHT);
                    }
                } else if (code == KeyCode.LEFT) {
                    if (snake.getToward() != Towards.RIGHT) {
                        snake.setToward(Towards.LEFT);
                    }
                } else if (code == KeyCode.UP) {
                    if (snake.getToward() != Towards.DOWN) {
                        snake.setToward(Towards.UP);
                    }
                } else if (code == KeyCode.DOWN) {
                    if (snake.getToward() != Towards.UP) {
                        snake.setToward(Towards.DOWN);
                    }
                } else if (code == KeyCode.Q ){
                    stop();
                    try {
                        App.setRoot("mainMenu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void run() {
        strTitle = "Score: "+ score;
        boolean beLife = true;
        while (beLife) {

            for (int y = 0; y < gameHigh; y++) {
                for (int x = 0; x < gameWidth; x++) {
                    rectMap[x][y].setFill(Color.TRANSPARENT);
                }
            }

            rectMap[apple.getPoint().x][apple.getPoint().y].setFill(Color.color(1, apple.isResize()?0.2:0.6, 0.3, 1));

            if( apple.getPoint().equal( snake.getHead() ) ){
                score++;
                if(apple.isResize()) snake.feed();
                apple.generateNewApple();

                strTitle = "Score: "+ score;
            }

            Point[] points = snake.getPointArray();
            for (int i = 0; i < points.length; i++) {
                rectMap[points[i].x][points[i].y].setFill(Color.color(i==0?0.6:0.7, 0.5, 0.3, 1));
            }

            try {
                Thread.sleep(Frequency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            snake.move();

            if(snake.checkCollision()){
                beLife = false;
                try {
//                    MainMenuController mmc = new MainMenuController();
//                    mmc.setScore();
                    App.setRoot("gameOver");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Game over, your score is " + score + "!");
        App.addToRanking(score);
    }
}
