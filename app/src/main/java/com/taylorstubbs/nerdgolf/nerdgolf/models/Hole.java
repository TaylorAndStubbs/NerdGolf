package com.taylorstubbs.nerdgolf.nerdgolf.models;

import com.orm.SugarRecord;

/**
 * Created by taylorstubbs on 2/24/17.
 */

public class Hole extends SugarRecord {
    int holeNum;
    int score;
    int par;

    public Hole() {
        //has to be empty
    }

    public Hole(int hNum) {
        holeNum = hNum;
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
}
