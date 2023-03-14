package com.example.digikala;

public class Fish {
    private String favoriteFood;
    private String bread;
    private boolean needsOxygenPump;

    public Fish(String favoriteFood, String bread, boolean needsOxygenPump) {
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
