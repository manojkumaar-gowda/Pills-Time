package com.huawei.batch6.pillstime;

import android.graphics.drawable.Drawable;

public class RefillPillsModel {

    private String pillsLeft;
    private String supplier;
    private String pillName;
    private String dose;
    private int color;

    public RefillPillsModel(String pillsLeft, String supplier, String pillName, String dose,int color) {
        this.pillsLeft = pillsLeft;
        this.supplier = supplier;
        this.pillName = pillName;
        this.dose = dose;
        this.color = color;

    }


    public String getPillsLeft() {
        return pillsLeft;
    }

    public void setPillsLeft(String pillsLeft) {
        this.pillsLeft = pillsLeft;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getColor() { return color; }

    public void setColor(int color) { this.color = color;}
}

