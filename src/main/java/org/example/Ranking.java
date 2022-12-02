package org.example;

import java.util.PriorityQueue;

public class Ranking {
    private int gameWidth, gameHigh, length;

    private Stream stream;

    private PriorityQueue<PlayerScore> ranking;

    public Ranking(int gameWidth, int gameHigh, int length) {
        ranking = new PriorityQueue();
        this.gameWidth = gameWidth;
        this.gameHigh = gameHigh;
        this.length = length;
    }

    public void addScore(PlayerScore ps){
        if(checkScore(ps)){
            ranking.add(ps);
            save();
        }
    }

    public PlayerScore[] toArray(){
        PriorityQueue<PlayerScore> temp = new PriorityQueue<>(ranking);
        PlayerScore[] rankingArray = new PlayerScore[temp.size()];
        for (int i=0; i<rankingArray.length; i++) {
            rankingArray[i] = temp.poll();
        }
        return rankingArray;
    }

    public boolean checkScore(PlayerScore ps){
        PlayerScore[] rank = toArray();
        if(rank.length>=length) {
            return rank[rank.length - 1].getScore() < ps.getScore();
        }else {
            return true;
        }
    }

    public String toString(){
        String temp = "";
        PlayerScore[] rank = toArray();
        for (int i=0; i< rank.length; i++){
            temp = temp + String.valueOf(i+1) + ". " + rank[i].toString() + "\n";
        }
        return temp;
    }

    public void load(String path){
        stream = new Stream(path);
        String name;
        int score;
        boolean loop = true;
        do{
            name = stream.readSegment();
            score = Integer.parseInt(stream.readSegment());
            if(name.equals("-1")){
                loop=false;
            }else {
                addScore(new PlayerScore(name, score));
            }
        }while (loop);
    }

    public void save(){
        PlayerScore[] rank = toArray();
        for (int i=0; i< rank.length; i++){
            stream.writeToFile(rank[i].getLine()+"\n");
        }
        stream.clear();
        stream.save();
    }
}
