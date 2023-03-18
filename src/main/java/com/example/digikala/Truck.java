package com.example.digikala;

import java.util.UUID;

public class Truck extends Vehicle{
    private int LoadCarryingCapacity;
    private int NumberAxis;
    private String gearboxType;

    public Truck(String name, int price, int amount, String comment, String brand, UUID sellerID, int weight, int numOfWheels, int numOfPassenger, String fuelType, String color, int maxSpeed, int loadCarryingCapacity, int numberAxis, String gearboxType) {
        super(name, price, amount, comment, brand, sellerID, weight, numOfWheels, numOfPassenger, fuelType, color, maxSpeed);
        LoadCarryingCapacity = loadCarryingCapacity;
        NumberAxis = numberAxis;
        this.gearboxType = gearboxType;
    }

    public int getLoadCarryingCapacity() {
        return LoadCarryingCapacity;
    }

    public void setLoadCarryingCapacity(int loadCarryingCapacity) {
        LoadCarryingCapacity = loadCarryingCapacity;
    }

    public int getNumberAxis() {
        return NumberAxis;
    }

    public void setNumberAxis(int numberAxis) {
        NumberAxis = numberAxis;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(String gearboxType) {
        this.gearboxType = gearboxType;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Truck{" +
                "LoadCarryingCapacity=" + LoadCarryingCapacity +
                ", NumberAxis=" + NumberAxis +
                ", gearboxType='" + gearboxType + '\'' +
                '}';
    }
}
