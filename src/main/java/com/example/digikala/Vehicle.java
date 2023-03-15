package com.example.digikala;

import java.util.UUID;

abstract class Vehicle extends Product{
    private int weight;
    private int numOfWheels;
    private int numOfPassenger;
    private String fuelType;
    private String color;
    private int maxSpeed;

    public Vehicle(String name, int price, int amount, String comment, String brand, UUID sellerID, int weight, int numOfWheels, int numOfPassenger, String fuelType, String color, int maxSpeed) {
        super(name, price, amount, comment, brand, sellerID);
        this.weight = weight;
        this.numOfWheels = numOfWheels;
        this.numOfPassenger = numOfPassenger;
        this.fuelType = fuelType;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getNumOfWheels() {
        return numOfWheels;
    }

    public void setNumOfWheels(int numOfWheels) {
        this.numOfWheels = numOfWheels;
    }

    public int getNumOfPassenger() {
        return numOfPassenger;
    }

    public void setNumOfPassenger(int numOfPassenger) {
        this.numOfPassenger = numOfPassenger;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString()+
                "vehicle{" +
                "weight=" + weight +
                ", numOfWheels=" + numOfWheels +
                ", numOfPassenger=" + numOfPassenger +
                ", fuelType='" + fuelType + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
