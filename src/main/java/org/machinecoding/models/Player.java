package org.machinecoding.models;

public class Player {
    int id;
    String name;
    int score;

    public Player(String name) {
        id = 1;
        this.name = name;
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
