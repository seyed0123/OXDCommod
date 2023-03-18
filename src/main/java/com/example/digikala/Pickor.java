package com.example.digikala;

import java.util.UUID;

public class Pickor extends Tool{
    private int hitRate;
    private String DrillType;
    private int voltage;

    public Pickor(String name, int price, int amount, String comment, String brand, UUID sellerID, String powerSupply, int numberOfPeopleRequiredForUse, String application, boolean isPortable, int hitRate, String drillType, int voltage) {
        super(name, price, amount, comment, brand, sellerID, powerSupply, numberOfPeopleRequiredForUse, application, isPortable);
        this.hitRate = hitRate;
        DrillType = drillType;
        this.voltage = voltage;
    }

    public int getHitRate() {
        return hitRate;
    }

    public void setHitRate(int hitRate) {
        this.hitRate = hitRate;
    }

    public String getDrillType() {
        return DrillType;
    }

    public void setDrillType(String drillType) {
        DrillType = drillType;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Pickor{" +
                "hitRate=" + hitRate +
                ", DrillType='" + DrillType + '\'' +
                ", voltage=" + voltage +
                '}';
    }
}
