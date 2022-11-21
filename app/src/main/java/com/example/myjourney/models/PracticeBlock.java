package com.example.myjourney.models;

public class PracticeBlock {
    int week;
    double pace;
    double distance;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public double getPace() {
        return pace;
    }

    public void setPace(double pace) {
        this.pace = pace;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public PracticeBlock (int week , double pace , double distance){
        this.distance=distance;
        this.pace=pace;
        this.week=week;
    }
}
