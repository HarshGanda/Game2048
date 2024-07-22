package org.machinecoding.controllers;

import org.machinecoding.observer.GameObserver;

public class ScoreController implements GameObserver {

    int score;

    public ScoreController() {
        this.score = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void update(int points) {
        setScore(getScore() + points);
    }
}
