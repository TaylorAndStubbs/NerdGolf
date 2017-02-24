package com.taylorstubbs.nerdgolf.nerdgolf.models;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by taylorstubbs on 2/24/17.
 */

public class Game extends SugarRecord {
    Date date;
    String courseName;
    int totalHoleNumber;
    ArrayList<Hole> holes;

    public Game() {
        //has to be blank
    }

    public Game(String cName, int tHoleNumber) {
        date = new Date();
        courseName = cName;
        totalHoleNumber = tHoleNumber;
    }
}
