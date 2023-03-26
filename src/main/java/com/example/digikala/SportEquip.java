package com.example.digikala;

import java.util.UUID;

abstract class SportEquip extends Product{
    private String SportType;
    private String material;
    private int weight;

    public SportEquip(String name, int price, int amount, String comment, String brand, UUID sellerID, String sportType, String material, int weight) {
        super(name, price, amount, comment, brand, sellerID);
        SportType = sportType;
        this.material = material;
        this.weight = weight;
    }

    public String getSportType() {
        return SportType;
    }

    public void setSportType(String sportType) {
        SportType = sportType;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return
                "SportEquip{" +
                "SportType='" + SportType + '\'' +
                ", material='" + material + '\'' +
                ", weight=" + weight +
                '}';
    }
}
