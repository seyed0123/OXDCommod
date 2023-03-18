package com.example.digikala;

import java.util.UUID;

public class FaceMask extends Cosmetic{
    private String materials;
    private String suitableFor;
    private int volume;

    public FaceMask(String name, int price, int amount, String comment, String brand, UUID sellerID, String type, String brandCountry, boolean isOrganic, String materials, String suitableFor, int volume) {
        super(name, price, amount, comment, brand, sellerID, type, brandCountry, isOrganic);
        this.materials = materials;
        this.suitableFor = suitableFor;
        this.volume = volume;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getSuitableFor() {
        return suitableFor;
    }

    public void setSuitableFor(String suitableFor) {
        this.suitableFor = suitableFor;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return super.toString()+
                "FaceMask{" +
                "materials='" + materials + '\'' +
                ", suitableFor='" + suitableFor + '\'' +
                ", volume=" + volume +
                '}';
    }
}
