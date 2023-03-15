package com.example.digikala;

import java.util.UUID;

abstract class Electronics extends Product{
    private String batteryType;
    private String color;
    private String model;

    public Electronics(String name, int price, int amount, String comment, String brand, UUID sellerID, String batteryType, String color, String model) {
        super(name, price, amount, comment, brand, sellerID);
        this.batteryType = batteryType;
        this.color = color;
        this.model = model;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Electronics{" +
                "batteryType='" + batteryType + '\'' +
                ", color='" + color + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
