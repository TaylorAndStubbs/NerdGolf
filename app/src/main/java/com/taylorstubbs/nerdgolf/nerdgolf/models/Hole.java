package com.taylorstubbs.nerdgolf.nerdgolf.models;

import com.orm.SugarRecord;

/**
 * Created by taylorstubbs on 2/24/17.
 */

public class Hole extends SugarRecord {
    int holeNum;
    int score;

    public Hole() {
        //has to be empty
    }

    public Hole(int hNum) {
        holeNum = hNum;
    }
}
