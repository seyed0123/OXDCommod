package com.example.digikala;

import java.util.UUID;

public class Shovel extends Tool{
    private String handleType;
    private int length;
    private String MetalType;

    public Shovel(String name, int price, int amount, String comment, String brand, UUID sellerID, String powerSupply, int numberOfPeopleRequiredForUse, String application, boolean isPortable, String handleType, int length, String metalType) {
        super(name, price, amount, comment, brand, sellerID, powerSupply, numberOfPeopleRequiredForUse, application, isPortable);
        this.handleType = handleType;
        this.length = length;
        MetalType = metalType;
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getMetalType() {
        return MetalType;
    }

    public void setMetalType(String metalType) {
        MetalType = metalType;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Shovel{" +
                "handleType='" + handleType + '\'' +
                ", length=" + length +
                ", MetalType='" + MetalType + '\'' +
                '}';
    }
}
