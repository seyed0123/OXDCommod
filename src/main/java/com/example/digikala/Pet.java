package com.example.digikala;

import java.util.UUID;

abstract class Pet extends Product{
    private String isNeutered;
    private String color;
    private int age;

    public Pet(String name, int price, int amount, String comment, String brand, UUID sellerID, String isNeutered, String color, int age) {
        super(name, price, amount, comment, brand, sellerID);
        this.isNeutered = isNeutered;
        this.color = color;
        this.age = age;
    }

    public String getIsNeutered() {
        return isNeutered;
    }

    public void setIsNeutered(String isNeutered) {
        this.isNeutered = isNeutered;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return
                "Pet{" +
                "isNeutered='" + isNeutered + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                '}';
    }
}
