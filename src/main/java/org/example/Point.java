package org.example;

public class Point {
    public int x,y;

    public Point next;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        next = null;
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equal(Point point){
        if(x==point.x && y== point.y){
            return true;
        }
        return false;
    }
}
