package com.huawei.batch6.pillstime;

public class RefillPillsModel {

    //Model attributes for a medicineRefill
    private String pillsLeft;
    private String supplier;
    private String pillName;
    private String dose;
    private int color;

    //Constructor for the attributes
    public RefillPillsModel(String pillsLeft, String supplier, String pillName, String dose, int color) {
        this.pillsLeft = pillsLeft;
        this.supplier = supplier;
        this.pillName = pillName;
        this.dose = dose;
        this.color = color;

    }

    //Getters and Setters for the attributes
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

    public void setColor(int color) { this.color = color; }
}

