package com.taylorstubbs.nerdgolf.nerdgolf.models;

import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.taylorstubbs.nerdgolf.nerdgolf.utils.SQLUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * The game object.
 */

public class Game extends SugarRecord {
    Boolean inProgress;
    String date;
    String courseName;
    int totalHoleNumber;

    public Game() {
        //has to be blank
    }

    public Game(String cName, int tHoleNumber) {
        inProgress = true;
        date = (new Date()).toString();
        courseName = cName;
        totalHoleNumber = tHoleNumber;

        //save to create id
        this.save();

        //Initialize holes
        for (int i = 0; i < totalHoleNumber; i++) {
            Hole hole = new Hole(this.getId(), i);
            hole.save();
        }

        //Save game
        this.saveHoles();
    }

    public String getDate() {
        return date;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getTotalHoleNumber() {
        return totalHoleNumber;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTotalHoleNumber(int totalHoleNumber) {
        this.totalHoleNumber = totalHoleNumber;
    }

    public Boolean getInProgress() {
        return inProgress;
    }

    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }

    public List<Hole> getHoles() {
        return SQLUtil.getAllHolesFromGame(getId());
    }

    public void saveHoles() {
        List<Hole> holes = getHoles();
        for (int i = 0; i < holes.size(); i++) {
            SQLUtil.getHoleFromId(holes.get(i).getId()).save();
        }
    }
}
