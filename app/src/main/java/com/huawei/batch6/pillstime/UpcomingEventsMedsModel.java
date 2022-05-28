package com.huawei.batch6.pillstime;

public class UpcomingEventsMedsModel {
    private String brand;
    private String medicine;
    private String dose;

    public UpcomingEventsMedsModel(String brand, String medicine, String dose) {
        this.brand = brand;
        this.medicine = medicine;
        this.dose = dose;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }
}
