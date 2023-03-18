package com.example.digikala;

import java.util.UUID;

public class Camera extends Electronics{
    private String lensType;
    private String sensorType;
    private String  zoomRange;

    public Camera(String name, int price, int amount, String comment, String brand, UUID sellerID, String batteryType, String color, String model, String lensType, String sensorType, String zoomRange) {
        super(name, price, amount, comment, brand, sellerID, batteryType, color, model);
        this.lensType = lensType;
        this.sensorType = sensorType;
        this.zoomRange = zoomRange;
    }

    public String getLensType() {
        return lensType;
    }

    public void setLensType(String lensType) {
        this.lensType = lensType;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getZoomRange() {
        return zoomRange;
    }

    public void setZoomRange(String zoomRange) {
        this.zoomRange = zoomRange;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Camera{" +
                "lensType='" + lensType + '\'' +
                ", sensorType='" + sensorType + '\'' +
                ", zoomRange='" + zoomRange + '\'' +
                '}';
    }
}
