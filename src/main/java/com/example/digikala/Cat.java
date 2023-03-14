package com.example.digikala;

public class Cat {
    private String favoriteFood;
    private String breed;

    public Cat(String favoriteFood, String breed) {
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
