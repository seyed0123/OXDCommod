package com.example.digikala;

public class Moisturizer extends Cosmetic{
    private int volume;
    private String CompatibleWith;
    private String TypeOfEnclosure;

    public Moisturizer(String name, int price, int amount, String comment, String brand, String type, String brandCountry, boolean isOrganic, int volume, String compatibleWith, String typeOfEnclosure) {
        super(name, price, amount, comment, brand, type, brandCountry, isOrganic);
        this.volume = volume;
        CompatibleWith = compatibleWith;
        TypeOfEnclosure = typeOfEnclosure;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getCompatibleWith() {
        return CompatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        CompatibleWith = compatibleWith;
    }

    public String getTypeOfEnclosure() {
        return TypeOfEnclosure;
    }

    public void setTypeOfEnclosure(String typeOfEnclosure) {
        TypeOfEnclosure = typeOfEnclosure;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Moisturizer{" +
                "volume=" + volume +
                ", CompatibleWith='" + CompatibleWith + '\'' +
                ", TypeOfEnclosure='" + TypeOfEnclosure + '\'' +
                '}';
    }
}
