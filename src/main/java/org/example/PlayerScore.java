package org.example;

public class PlayerScore implements Comparable<PlayerScore>{
    private String nick;
    private int score;

    public PlayerScore(String nick, int score) {
        this.nick = nick;
        this.score = score;
    }

    public String getNick() {
        return nick;
    }

    public int getScore() {
        return score;
    }

    public String toString(){
        return nick + " - " + score;
    }

    @Override
    public int compareTo(PlayerScore p) {
        if(score>p.score){
            return -1;
        }else if (score<p.score){
            return 1;
        }else {
            return 0;
        }
    }

    public String getLine(){
        return nick + "|" + score;
    }

}
