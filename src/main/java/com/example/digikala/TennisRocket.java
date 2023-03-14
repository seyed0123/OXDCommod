package com.example.digikala;

public class TennisRocket extends SportEquip{
    private int length;
    private int gripSize;
    private String gripType;

    public TennisRocket(String name, int price, int amount, String comment, String brand, String sportType, String material, int weight, int length, int gripSize, String gripType) {
        super(name, price, amount, comment, brand, sportType, material, weight);
        this.length = length;
        this.gripSize = gripSize;
        this.gripType = gripType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getGripSize() {
        return gripSize;
    }

    public void setGripSize(int gripSize) {
        this.gripSize = gripSize;
    }

    public String getGripType() {
        return gripType;
    }

    public void setGripType(String gripType) {
        this.gripType = gripType;
    }

    @Override
    public String toString() {
        return super.toString()+
                "TennisRocket{" +
                "length=" + length +
                ", gripSize=" + gripSize +
                ", gripType='" + gripType + '\'' +
                '}';
    }
}
