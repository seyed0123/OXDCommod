package com.example.digikala;

import java.util.UUID;

public class Shirt extends Clothing{
    private String pattern;
    private int sleeveLength;
    private String neckLineType;

    public Shirt(String name, int price, int amount, String comment, String brand, UUID sellerID, String color, String material, String size, String style, String pattern, int sleeveLength, String neckLineType) {
        super(name, price, amount, comment, brand, sellerID, color, material, size, style);
        this.pattern = pattern;
        this.sleeveLength = sleeveLength;
        this.neckLineType = neckLineType;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(int sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public String getNeckLineType() {
        return neckLineType;
    }

    public void setNeckLineType(String neckLineType) {
        this.neckLineType = neckLineType;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Shirt{" +
                "pattern='" + pattern + '\'' +
                ", sleeveLength=" + sleeveLength +
                ", neckLineType='" + neckLineType + '\'' +
                '}';
    }
}
