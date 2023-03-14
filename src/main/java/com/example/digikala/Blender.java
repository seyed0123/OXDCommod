package com.example.digikala;

public class Blender extends Household{
   private String motorPower;
   private int capacity;
   private int numberOfSpeedStings;
   private String typeOfBlade;

    public Blender(String name, int price, int amount, String comment, String brand, boolean isNeedsElectricity, boolean isDecorative, boolean isFurniture, String color, String motorPower, int capacity, int numberOfSpeedStings, String typeOfBlade) {
        super(name, price, amount, comment, brand, isNeedsElectricity, isDecorative, isFurniture, color);
        this.motorPower = motorPower;
        this.capacity = capacity;
        this.numberOfSpeedStings = numberOfSpeedStings;
        this.typeOfBlade = typeOfBlade;
    }

    public String getMotorPower() {
        return motorPower;
    }

    public void setMotorPower(String motorPower) {
        this.motorPower = motorPower;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNumberOfSpeedStings() {
        return numberOfSpeedStings;
    }

    public void setNumberOfSpeedStings(int numberOfSpeedStings) {
        this.numberOfSpeedStings = numberOfSpeedStings;
    }

    public String getTypeOfBlade() {
        return typeOfBlade;
    }

    public void setTypeOfBlade(String typeOfBlade) {
        this.typeOfBlade = typeOfBlade;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Blender{" +
                "motorPower='" + motorPower + '\'' +
                ", capacity=" + capacity +
                ", numberOfSpeedStings=" + numberOfSpeedStings +
                ", typeOfBlade='" + typeOfBlade + '\'' +
                '}';
    }
}
