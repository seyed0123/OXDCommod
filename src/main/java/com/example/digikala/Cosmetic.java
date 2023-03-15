package com.example.digikala;

import java.util.UUID;

abstract class Cosmetic extends Product{
    private String type;
    private String brandCountry;
    boolean isOrganic;


    public Cosmetic(String name, int price, int amount, String comment, String brand, UUID sellerID, String type, String brandCountry, boolean isOrganic) {
        super(name, price, amount, comment, brand, sellerID);
        this.type = type;
        this.brandCountry = brandCountry;
        this.isOrganic = isOrganic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrandCountry() {
        return brandCountry;
    }

    public void setBrandCountry(String brandCountry) {
        this.brandCountry = brandCountry;
    }

    public boolean isOrganic() {
        return isOrganic;
    }

    public void setOrganic(boolean organic) {
        isOrganic = organic;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Cosmetic{" +
                "type='" + type + '\'' +
                ", brandCountry='" + brandCountry + '\'' +
                ", isOrganic=" + isOrganic +
                '}';
    }
}
