package com.example.digikala;

import java.util.UUID;

public class TV extends Household{
    private String screenTechnology;
    private String imageQuality;
    private int screenSize;

    public TV(String name, int price, int amount, String comment, String brand, UUID sellerID, boolean isNeedsElectricity, boolean isDecorative, boolean isFurniture, String color, String screenTechnology, String imageQuality, int screenSize) {
        super(name, price, amount, comment, brand, sellerID, isNeedsElectricity, isDecorative, isFurniture, color);
        this.screenTechnology = screenTechnology;
        this.imageQuality = imageQuality;
        this.screenSize = screenSize;
    }

    public String getScreenTechnology() {
        return screenTechnology;
    }

    public void setScreenTechnology(String screenTechnology) {
        this.screenTechnology = screenTechnology;
    }

    public String getImageQuality() {
        return imageQuality;
    }

    public void setImageQuality(String imageQuality) {
        this.imageQuality = imageQuality;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return super.toString()+
                "TV{" +
                "screenTechnology='" + screenTechnology + '\'' +
                ", imageQuality='" + imageQuality + '\'' +
                ", screenSize=" + screenSize +
                '}';
    }
}
