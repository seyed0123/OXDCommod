package com.example.digikala;

import java.util.UUID;

public class Carpet extends Household{
    private String typeOfWarpYarn;
    private boolean abilityWash;
    private int Density;

    public Carpet(String name, int price, int amount, String comment, String brand, UUID sellerID, boolean isNeedsElectricity, boolean isDecorative, boolean isFurniture, String color, String typeOfWarpYarn, boolean abilityWash, int density) {
        super(name, price, amount, comment, brand, sellerID, isNeedsElectricity, isDecorative, isFurniture, color);
        this.typeOfWarpYarn = typeOfWarpYarn;
        this.abilityWash = abilityWash;
        Density = density;
    }

    public String getTypeOfWarpYarn() {
        return typeOfWarpYarn;
    }

    public void setTypeOfWarpYarn(String typeOfWarpYarn) {
        this.typeOfWarpYarn = typeOfWarpYarn;
    }

    public boolean isAbilityWash() {
        return abilityWash;
    }

    public void setAbilityWash(boolean abilityWash) {
        this.abilityWash = abilityWash;
    }

    public int getDensity() {
        return Density;
    }

    public void setDensity(int density) {
        Density = density;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Carpet{" +
                "typeOfWarpYarn='" + typeOfWarpYarn + '\'' +
                ", abilityWash=" + abilityWash +
                ", Density=" + Density +
                '}';
    }
}
