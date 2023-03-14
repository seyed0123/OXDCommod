package com.example.digikala;

public class Pant extends Clothing{
    private String style;
    private String fitType;
    private String pocketType;

    public Pant(String name, int price, int amount, String comment, String brand, String color, String material, String size, String style, String style1, String fitType, String pocketType) {
        super(name, price, amount, comment, brand, color, material, size, style);
        this.style = style1;
        this.fitType = fitType;
        this.pocketType = pocketType;
    }

    @Override
    public String getStyle() {
        return style;
    }

    @Override
    public void setStyle(String style) {
        this.style = style;
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
                "style='" + style + '\'' +
                ", fitType='" + fitType + '\'' +
                ", pocketType='" + pocketType + '\'' +
                '}';
    }
}
