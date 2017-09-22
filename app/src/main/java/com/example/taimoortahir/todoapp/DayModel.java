package com.example.taimoortahir.todoapp;

/**
 * Created by TaimoorTahir on 15/09/2017.
 */

public class DayModel {
    String weekDay;

    public DayModel(){
    }

    public DayModel(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getweekDay() {
        return weekDay;
    }

    public void setweekDay(String weekDay) {
        this.weekDay = weekDay;
    }
}
