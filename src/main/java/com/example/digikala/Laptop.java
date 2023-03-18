package com.example.digikala;

import java.util.UUID;

public class Laptop extends Electronics{
    private int ram;
    private int storage;
    private String operatingSystem;

    public Laptop(String name, int price, int amount, String comment, String brand, UUID sellerID, String batteryType, String color, String model, int ram, int storage, String operatingSystem) {
        super(name, price, amount, comment, brand, sellerID, batteryType, color, model);
        this.ram = ram;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Laptop{" +
                "ram=" + ram +
                ", storage=" + storage +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }
}
