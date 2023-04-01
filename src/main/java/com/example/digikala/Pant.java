package com.example.digikala;

import java.util.UUID;

public class Pant extends Clothing{
    private String fitType;
    private String pocketType;

    public Pant(String name, int price, int amount, String comment, String brand, UUID sellerID, String color, String material, String size, String style, String fitType, String pocketType) {
        super(name, price, amount, comment, brand, sellerID, color, material, size, style);
        this.fitType = fitType;
        this.pocketType = pocketType;
    }
    public String getFitType() {
        return fitType;
    }

    public void setFitType(String fitType) {
        this.fitType = fitType;
    }

    public String getPocketType() {
        return pocketType;
    }

    public void setPocketType(String pocketType) {
        this.pocketType = pocketType;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Pant{" +
                ", fitType='" + fitType + '\'' +
                ", pocketType='" + pocketType + '\'' +
                '}';
    }
}
