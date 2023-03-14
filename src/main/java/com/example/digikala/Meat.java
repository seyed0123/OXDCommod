package com.example.digikala;

import java.util.Date;

public class Meat extends Eatable{
    private String meatType;
    private boolean isFrozen;
    private boolean isHalfBaked;

    public Meat(String name, int price, int amount, String comment, String brand, String rawMaterial, Date expirationDate, Date productionDate, boolean isManufacturedByFactory, String meatType, boolean isFrozen, boolean isHalfBaked) {
        super(name, price, amount, comment, brand, rawMaterial, expirationDate, productionDate, isManufacturedByFactory);
        this.meatType = meatType;
        this.isFrozen = isFrozen;
        this.isHalfBaked = isHalfBaked;
    }

    public String getMeatType() {
        return meatType;
    }

    public void setMeatType(String meatType) {
        this.meatType = meatType;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    public boolean isHalfBaked() {
        return isHalfBaked;
    }

    public void setHalfBaked(boolean halfBaked) {
        isHalfBaked = halfBaked;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Meat{" +
                "meatType='" + meatType + '\'' +
                ", isFrozen=" + isFrozen +
                ", isHalfBaked=" + isHalfBaked +
                '}';
    }
}
