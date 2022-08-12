package com.huawei.batch6.pillstime;


public class UpcomingEventsModel {

    //Model attributes for upcoming events
    private String time;
    private String meds;
    private int color;

    //Constructor for the attributes
    public UpcomingEventsModel(String time, String meds, int color) {
        this.time = time;
        this.meds = meds;
        this.color = color;
    }


    //Getters and Setters for the attributes
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMeds() {
        return meds;
    }

    public void setMeds(String meds) {
        this.meds = meds;
    }


}
