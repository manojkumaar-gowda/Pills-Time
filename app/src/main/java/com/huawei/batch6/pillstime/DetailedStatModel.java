package com.huawei.batch6.pillstime;


public class DetailedStatModel {

    //Model attributes for a Detailed Statistics
    private String time;
    private String date;
    private String takenMeds;
    private String leftMeds;
    private int color;

    //Constructor for the attributes
    public DetailedStatModel(String time, String date, String takenMeds, String leftMeds, int color) {
        this.time = time;
        this.date = date;
        this.takenMeds = takenMeds;
        this.leftMeds = leftMeds;
        this.color = color;
    }

    //Getters and Setters for the attributes
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTakenMeds() {
        return takenMeds;
    }

    public void setTakenMeds(String takenMeds) {
        this.takenMeds = takenMeds;
    }

    public String getLeftMeds() {
        return leftMeds;
    }

    public void setLeftMeds(String leftMeds) {
        this.leftMeds = leftMeds;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


}
