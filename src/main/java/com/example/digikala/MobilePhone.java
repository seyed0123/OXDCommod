package com.example.digikala;

public class MobilePhone extends Electronics{
    private int screenSize;
    private int batteryLife;
    private int cameraResolution;

    public MobilePhone(String name, int price, int amount, String comment, String brand, String batteryType, String color, String model, int screenSize, int batteryLife, int cameraResolution) {
        super(name, price, amount, comment, brand, batteryType, color, model);
        this.screenSize = screenSize;
        this.batteryLife = batteryLife;
        this.cameraResolution = cameraResolution;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    public int getCameraResolution() {
        return cameraResolution;
    }

    public void setCameraResolution(int cameraResolution) {
        this.cameraResolution = cameraResolution;
    }

    @Override
    public String toString() {
        return super.toString()+
                "MobilePhones{" +
                "screenSize=" + screenSize +
                ", batteryLife=" + batteryLife +
                ", cameraResolution=" + cameraResolution +
                '}';
    }
}
