package com.example.digikala;

import java.util.UUID;

public class Fish extends Pet{
    private String favoriteFood;
    private String bread;
    private boolean needsOxygenPump;

    public Fish(String name, int price, int amount, String comment, String brand, UUID sellerID, String isNeutered, String color, int age, String favoriteFood, String bread, boolean needsOxygenPump) {
        super(name, price, amount, comment, brand, sellerID, isNeutered, color, age);
        this.favoriteFood = favoriteFood;
        this.bread = bread;
        this.needsOxygenPump = needsOxygenPump;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public boolean isNeedsOxygenPump() {
        return needsOxygenPump;
    }

    public void setNeedsOxygenPump(boolean needsOxygenPump) {
        this.needsOxygenPump = needsOxygenPump;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Fish{" +
                "favoriteFood='" + favoriteFood + '\'' +
                ", bread='" + bread + '\'' +
                ", needsOxygenPump=" + needsOxygenPump +
                '}';
    }
}
