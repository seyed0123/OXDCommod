package com.example.digikala;

public class SoccerBall extends SportEquip{
    private String pattern;
    private int year;
    private String placeOfUse;

    public SoccerBall(String name, int price, int amount, String comment, String brand, String sportType, String material, int weight, String pattern, int year, String placeOfUse) {
        super(name, price, amount, comment, brand, sportType, material, weight);
        this.pattern = pattern;
        this.year = year;
        this.placeOfUse = placeOfUse;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlaceOfUse() {
        return placeOfUse;
    }

    public void setPlaceOfUse(String placeOfUse) {
        this.placeOfUse = placeOfUse;
    }

    @Override
    public String toString() {
        return super.toString()+
                "SoccerBall{" +
                "pattern='" + pattern + '\'' +
                ", year=" + year +
                ", placeOfUse='" + placeOfUse + '\'' +
                '}';
    }
}
