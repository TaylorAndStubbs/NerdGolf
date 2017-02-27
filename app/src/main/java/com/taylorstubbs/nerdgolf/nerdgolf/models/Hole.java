package com.taylorstubbs.nerdgolf.nerdgolf.models;

import com.orm.SugarRecord;

/**
 * The hole object.
 */

public class Hole extends SugarRecord {
    int holeNum;
    int score;
    int par;
    long game;

    public Hole() {
        //has to be empty
    }

    public Hole(long gameId, int hNum) {
        game = gameId;
        holeNum = hNum;
        score = 0;
        par = 1;
    }

    public int getHoleNum() {
        return holeNum;
    }

    public void setHoleNum(int holeNum) {
        this.holeNum = holeNum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public long getGame() {
        return game;
    }

    public void setGame(long game) {
        this.game = game;
    }

    public void increaseScore() {
        setScore(getScore() + 1);
    }

    public void decreaseScore() {
        setScore(getScore() - 1);
    }
}
