package com.example.digikala;

public class Motorcycle extends Vehicle{
    private String cargoSpace;
    private String jackType;
    private String StarterType;

    public Motorcycle(String name, int price, int amount, String comment, String brand, int weight, int numOfWheels, int numOfPassenger, String fuelType, String color, int maxSpeed, String cargoSpace, String jackType, String starterType) {
        super(name, price, amount, comment, brand, weight, numOfWheels, numOfPassenger, fuelType, color, maxSpeed);
        this.cargoSpace = cargoSpace;
        this.jackType = jackType;
        StarterType = starterType;
    }

    public String getCargoSpace() {
        return cargoSpace;
    }

    public void setCargoSpace(String cargoSpace) {
        this.cargoSpace = cargoSpace;
    }

    public String getJackType() {
        return jackType;
    }

    public void setJackType(String jackType) {
        this.jackType = jackType;
    }

    public String getStarterType() {
        return StarterType;
    }

    public void setStarterType(String starterType) {
        StarterType = starterType;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Motorcycle{" +
                "cargoSpace='" + cargoSpace + '\'' +
                ", jackType='" + jackType + '\'' +
                ", StarterType='" + StarterType + '\'' +
                '}';
    }
}
