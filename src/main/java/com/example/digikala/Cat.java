package com.example.digikala;

import java.util.UUID;

public class Cat extends Pet{
    private String favoriteFood;
    private String breed;

    public Cat(String name, int price, int amount, String comment, String brand, UUID sellerID, String isNeutered, String color, int age, String favoriteFood, String breed) {
        super(name, price, amount, comment, brand, sellerID, isNeutered, color, age);
        this.favoriteFood = favoriteFood;
        this.breed = breed;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Cat{" +
                "favoriteFood='" + favoriteFood + '\'' +
                ", breed='" + breed + '\'' +
                '}';
    }
}
