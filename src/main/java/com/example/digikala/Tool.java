package com.example.digikala;

import java.util.UUID;

abstract class Tool extends Product{
    private String powerSupply;
    private int numberOfPeopleRequiredForUse;
    private String application;
    private boolean isPortable;

    public Tool(String name, int price, int amount, String comment, String brand, UUID sellerID, String powerSupply, int numberOfPeopleRequiredForUse, String application, boolean isPortable) {
        super(name, price, amount, comment, brand, sellerID);
        this.powerSupply = powerSupply;
        this.numberOfPeopleRequiredForUse = numberOfPeopleRequiredForUse;
        this.application = application;
        this.isPortable = isPortable;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(String powerSupply) {
        this.powerSupply = powerSupply;
    }

    public int getNumberOfPeopleRequiredForUse() {
        return numberOfPeopleRequiredForUse;
    }

    public void setNumberOfPeopleRequiredForUse(int numberOfPeopleRequiredForUse) {
        this.numberOfPeopleRequiredForUse = numberOfPeopleRequiredForUse;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public boolean isPortable() {
        return isPortable;
    }

    public void setPortable(boolean portable) {
        isPortable = portable;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Tool{" +
                "powerSupply='" + powerSupply + '\'' +
                ", numberOfPeopleRequiredForUse=" + numberOfPeopleRequiredForUse +
                ", application='" + application + '\'' +
                ", isPortable=" + isPortable +
                '}';
    }
}
