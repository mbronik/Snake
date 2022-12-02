package org.example;

import java.util.Random;

public class Apple {
    private Point point;
    private boolean resize;
    private int maxSizeX, maxSizeY;

    public Apple(int maxSizeX, int maxSizeY) {
        this.maxSizeX = maxSizeX;
        this.maxSizeY = maxSizeY;
        point = new Point((int)(Math.random()*maxSizeX), (int)(Math.random()*maxSizeY));
    }

    public void generateNewApple(){
        point.setXY((int)(Math.random()*maxSizeX), (int)(Math.random()*maxSizeY));
        if ( (int)(Math.random()*11)==0 ){
            resize = false;
        } else {
            resize = true;
        }
    }

    public Point getPoint(){
        return point;
    }

    public boolean isResize() {
        return resize;
    }
}
