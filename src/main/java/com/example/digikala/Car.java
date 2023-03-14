package com.example.digikala;

import javafx.util.Pair;
import java.util.UUID;

public class Car extends Vehicle{
    private String chassisType;
    private String gearboxType;
    private String interiorDesignStyle;

    public Car(String name, int price, int amount, String comment, String brand, int weight, int numOfWheels, int numOfPassenger, String fuelType, String color, int maxSpeed, String chassisType, String gearboxType, String interiorDesignStyle) {
        super(name, price, amount, comment, brand, weight, numOfWheels, numOfPassenger, fuelType, color, maxSpeed);
        this.chassisType = chassisType;
        this.gearboxType = gearboxType;
        this.interiorDesignStyle = interiorDesignStyle;
    }

    public String getChassisType() {
        return chassisType;
    }

    public void setChassisType(String chassisType) {
        this.chassisType = chassisType;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(String gearboxType) {
        this.gearboxType = gearboxType;
    }

    public String getInteriorDesignStyle() {
        return interiorDesignStyle;
    }

    public void setInteriorDesignStyle(String interiorDesignStyle) {
        this.interiorDesignStyle = interiorDesignStyle;
    }

    @Override
    public String toString() {
        return super.toString()+
                "car{" +
                "chassisType='" + chassisType + '\'' +
                ", gearboxType='" + gearboxType + '\'' +
                ", interiorDesignStyle='" + interiorDesignStyle + '\'' +
                '}';
    }
}
