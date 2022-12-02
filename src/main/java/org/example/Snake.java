package org.example;


import java.nio.channels.Pipe;
import java.util.SortedMap;

enum Towards {
    LEFT, RIGHT, UP, DOWN
}

public class Snake {
    private int xMax, yMax;

    private Towards toward;

    private Point head;

    public Snake(int xMax, int yMax, Towards toward) {
        this.xMax = xMax;
        this.yMax = yMax;
        this.toward = toward;
        head = new Point( (int)(xMax/2), (int)(yMax/2) );
        head.next = new Point( (int)(xMax/2), (int)(yMax/2) );
        head.next.next = new Point( (int)(xMax/2), (int)(yMax/2) );

//        Point pnext = head.next;
//        for (int i=0; i<5; i++) {
//            pnext = new Point((int) (xMax / 2), (int) (yMax / 2));
//            pnext = pnext.next;
//        }
    }

    public void setToward(Towards toward) {
        this.toward = toward;
    }

    public void move(){
        if(head.next!=null) {
            Point beforLast = head;
            Point last = head.next;
            while (last.next != null) {
                beforLast = last;
                last = last.next;
            }
            beforLast.next = null;
            last.next = head;
            head = last;
        }
        switch (toward){
            case LEFT:
                head.setXY(head.next.x-1, head.next.y);
                break;
            case RIGHT:
                head.setXY(head.next.x+1, head.next.y);
                break;
            case UP:
                head.setXY(head.next.x, head.next.y-1);
                break;
            case DOWN:
                head.setXY(head.next.x, head.next.y+1);
                break;
        }
        if(head.x<0) head.x=xMax-1;
        if(head.x>xMax-1) head.x=0;
        if(head.y<0) head.y=yMax-1;
        if(head.y>yMax-1) head.y=0;
    }

    public boolean checkCollision(){
        Point next = head.next;
        while (next.next != null){
            if(head.equal(next)) return true;
            next = next.next;
        }
        return false;
    }

    public void feed(){
        Point next = head.next;
        while (next.next != null){
            next = next.next;
        }
        next.next = new Point(next.x, next.y);
    }

    public int length(){
        int len=1;
        Point next = head;
        while (next.next != null){
            next = next.next;
            len++;
        }
        return len;
    }

    public Point[] getPointArray(){
        Point[] points = new Point[length()];
        Point next = head;
        for (int i=0; i<points.length; i++){
            points[i] = next;
            next = next.next;
        }
        return points;
    }

    public Towards getToward() {
        return toward;
    }

    public Point getHead() {
        return head;
    }
}
