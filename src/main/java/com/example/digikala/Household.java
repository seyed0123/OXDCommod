package com.example.digikala;

import java.util.UUID;

abstract class Household extends Product{
    private boolean isNeedsElectricity;
    private boolean isDecorative;
    private boolean isFurniture;
    private String color;

    public Household(String name, int price, int amount, String comment, String brand, UUID sellerID, boolean isNeedsElectricity, boolean isDecorative, boolean isFurniture, String color) {
        super(name, price, amount, comment, brand, sellerID);
        this.isNeedsElectricity = isNeedsElectricity;
        this.isDecorative = isDecorative;
        this.isFurniture = isFurniture;
        this.color = color;
    }

    public boolean isNeedsElectricity() {
        return isNeedsElectricity;
    }

    public void setNeedsElectricity(boolean needsElectricity) {
        isNeedsElectricity = needsElectricity;
    }

    public boolean isDecorative() {
        return isDecorative;
    }

    public void setDecorative(boolean decorative) {
        isDecorative = decorative;
    }

    public boolean isFurniture() {
        return isFurniture;
    }

    public void setFurniture(boolean furniture) {
        isFurniture = furniture;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Household{" +
                "isNeedsElectricity=" + isNeedsElectricity +
                ", isDecorative=" + isDecorative +
                ", isFurniture=" + isFurniture +
                ", color='" + color + '\'' +
                '}';
    }
}
