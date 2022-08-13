package com.huawei.batch6.pillstime;

import android.widget.ArrayAdapter;

public class AddDoseModel {

    //Model attributes for a dose
    private String fullMedicineName;
    private int dose;
    private String doseUnits;
    private int noOfUnitsLeft;
    private String instructions;
    private String dayTypeValue;
    private int[] daysSelected;

    //Constructor for the attributes
    public AddDoseModel(String fullMedicineName, int dose, String doseUnits, int noOfUnitsLeft, String instructions, String dayTypeValue, int[] daysSelected) {
        this.fullMedicineName = fullMedicineName;
        this.dose = dose;
        this.doseUnits = doseUnits;
        this.noOfUnitsLeft = noOfUnitsLeft;
        this.instructions = instructions;
        this.dayTypeValue = dayTypeValue;
        this.daysSelected = daysSelected;
    }


    //Getters and Setters for the attributes


    public String getFullMedicineName() {
        return fullMedicineName;
    }

    public void setFullMedicineName(String fullMedicineName) {
        this.fullMedicineName = fullMedicineName;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public String getDoseUnits() {
        return doseUnits;
    }

    public void setDoseUnits(String doseUnits) {
        this.doseUnits = doseUnits;
    }

    public int getNoOfUnitsLeft() {
        return noOfUnitsLeft;
    }

    public void setNoOfUnitsLeft(int noOfUnitsLeft) {
        this.noOfUnitsLeft = noOfUnitsLeft;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getDayTypeValue() {
        return dayTypeValue;
    }

    public void setDayTypeValue(String dayTypeValue) {
        this.dayTypeValue = dayTypeValue;
    }

    public int[] getDaysSelected() {
        return daysSelected;
    }

    public void setDaysSelected(int[] daysSelected) {
        this.daysSelected = daysSelected;
    }
}
