package com.example.digikala;

public class Dog extends Pet{
    private String breed;
    private String favoriteFood;

    public Dog(String name, int price, int amount, String comment, String brand, String isNeutered, String color, int age, String breed, String favoriteFood) {
        super(name, price, amount, comment, brand, isNeutered, color, age);
        this.breed = breed;
        this.favoriteFood = favoriteFood;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Dog{" +
                "breed='" + breed + '\'' +
                ", favoriteFood='" + favoriteFood + '\'' +
                '}';
    }
}
