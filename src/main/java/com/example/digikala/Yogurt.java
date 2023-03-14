package com.example.digikala;

import java.util.Date;

public class Yogurt extends Eatable{
    private String yogurtType;
    private String taste;
    private String fatAmount;

    public Yogurt(String name, int price, int amount, String comment, String brand, String rawMaterial, Date expirationDate, Date productionDate, boolean isManufacturedByFactory, String yogurtType, String taste, String fatAmount) {
        super(name, price, amount, comment, brand, rawMaterial, expirationDate, productionDate, isManufacturedByFactory);
        this.yogurtType = yogurtType;
        this.taste = taste;
        this.fatAmount = fatAmount;
    }

    public String getYogurtType() {
        return yogurtType;
    }

    public void setYogurtType(String yogurtType) {
        this.yogurtType = yogurtType;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getFatAmount() {
        return fatAmount;
    }

    public void setFatAmount(String fatAmount) {
        this.fatAmount = fatAmount;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Yogurt{" +
                "yogurtType='" + yogurtType + '\'' +
                ", taste='" + taste + '\'' +
                ", fatAmount='" + fatAmount + '\'' +
                '}';
    }
}
