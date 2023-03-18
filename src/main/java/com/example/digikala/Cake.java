package com.example.digikala;

import java.util.Date;
import java.util.UUID;

public class Cake extends Eatable{
    private String taste;
    private boolean isNutCake;
    private int numberInPackage;

    public Cake(String name, int price, int amount, String comment, String brand, UUID sellerID, String rawMaterial, Date expirationDate, Date productionDate, boolean isManufacturedByFactory, String taste, boolean isNutCake, int numberInPackage) {
        super(name, price, amount, comment, brand, sellerID, rawMaterial, expirationDate, productionDate, isManufacturedByFactory);
        this.taste = taste;
        this.isNutCake = isNutCake;
        this.numberInPackage = numberInPackage;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public boolean isNutCake() {
        return isNutCake;
    }

    public void setNutCake(boolean nutCake) {
        isNutCake = nutCake;
    }

    public int getNumberInPackage() {
        return numberInPackage;
    }

    public void setNumberInPackage(int numberInPackage) {
        this.numberInPackage = numberInPackage;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Cake{" +
                "taste='" + taste + '\'' +
                ", isNutCake=" + isNutCake +
                ", numberInPackage=" + numberInPackage +
                '}';
    }
}
