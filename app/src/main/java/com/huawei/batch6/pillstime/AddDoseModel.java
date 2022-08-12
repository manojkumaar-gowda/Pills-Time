package com.huawei.batch6.pillstime;

public class AddDoseModel {

    //Model attributes for a dose
    private String medicine;
    private String instructions;

    //Constructor for the attributes
    public AddDoseModel(String medicine, String instructions) {
        this.medicine = medicine;
        this.instructions = instructions;
    }

    //Getters and Setters for the attributes
    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }


}
