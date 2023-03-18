package com.example.digikala;

import java.util.UUID;

public class Sunglass extends Clothing{
    private String lensType;
    private int Prescription;
    private String lensMaterial;

    public Sunglass(String name, int price, int amount, String comment, String brand, UUID sellerID, String color, String material, String size, String style, String lensType, int prescription, String lensMaterial) {
        super(name, price, amount, comment, brand, sellerID, color, material, size, style);
        this.lensType = lensType;
        Prescription = prescription;
        this.lensMaterial = lensMaterial;
    }

    public void setLensType(String lensType) {
        this.lensType = lensType;
    }

    public void setPrescription(int prescription) {
        Prescription = prescription;
    }

    public void setLensMaterial(String lensMaterial) {
        this.lensMaterial = lensMaterial;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Sunglass{" +
                "lensType='" + lensType + '\'' +
                ", Prescription=" + Prescription +
                ", lensMaterial='" + lensMaterial + '\'' +
                '}';
    }
}
