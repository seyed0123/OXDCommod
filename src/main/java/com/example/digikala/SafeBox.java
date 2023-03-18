package com.example.digikala;

import java.util.UUID;

public class SafeBox extends Tool{
    private int weight;
    private int NumberOfLocks;
    private String type;
    private int volume;

    public SafeBox(String name, int price, int amount, String comment, String brand, UUID sellerID, String powerSupply, int numberOfPeopleRequiredForUse, String application, boolean isPortable, int weight, int numberOfLocks, String type, int volume) {
        super(name, price, amount, comment, brand, sellerID, powerSupply, numberOfPeopleRequiredForUse, application, isPortable);
        this.weight = weight;
        NumberOfLocks = numberOfLocks;
        this.type = type;
        this.volume = volume;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getNumberOfLocks() {
        return NumberOfLocks;
    }

    public void setNumberOfLocks(int numberOfLocks) {
        NumberOfLocks = numberOfLocks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return super.toString()+
                "SafeBox{" +
                "weight=" + weight +
                ", NumberOfLocks=" + NumberOfLocks +
                ", type='" + type + '\'' +
                ", volume=" + volume +
                '}';
    }
}
