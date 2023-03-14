package com.example.digikala;

public class Ski extends SportEquip{
    private String length;
    private String color;
    private String brandCountry;

    public Ski(String name, int price, int amount, String comment, String brand, String sportType, String material, int weight, String length, String color, String brandCountry) {
        super(name, price, amount, comment, brand, sportType, material, weight);
        this.length = length;
        this.color = color;
        this.brandCountry = brandCountry;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrandCountry() {
        return brandCountry;
    }

    public void setBrandCountry(String brandCountry) {
        this.brandCountry = brandCountry;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Ski{" +
                "length='" + length + '\'' +
                ", color='" + color + '\'' +
                ", brandCountry='" + brandCountry + '\'' +
                '}';
    }
}
