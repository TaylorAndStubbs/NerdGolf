package com.taylorstubbs.nerdgolf.nerdgolf.models;

import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by taylorstubbs on 2/24/17.
 */

public class Game extends SugarRecord {
    String courseName;
    int totalHoleNumber;
    ArrayList<Hole> holes;

    public Game() {
        //has to be blank
    }

    public Game(String cName, int tHoleNumber) {
        courseName = cName;
        totalHoleNumber = tHoleNumber;
    }
}
