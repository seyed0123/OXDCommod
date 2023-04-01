package com.example.digikala;

import java.util.Date;
import java.util.UUID;

abstract class Eatable extends Product{
    private String RawMaterial;
    private String ExpirationDate;
    private String ProductionDate;
    private boolean isManufacturedByFactory;

    public Eatable(String name, int price, int amount, String comment, String brand, UUID sellerID, String rawMaterial, String expirationDate, String productionDate, boolean isManufacturedByFactory) {
        super(name, price, amount, comment, brand, sellerID);
        RawMaterial = rawMaterial;
        ExpirationDate = expirationDate;
        ProductionDate = productionDate;
        this.isManufacturedByFactory = isManufacturedByFactory;
    }

    public String getRawMaterial() {
        return RawMaterial;
    }

    public void setRawMaterial(String rawMaterial) {
        RawMaterial = rawMaterial;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        ExpirationDate = expirationDate;
    }

    public String getProductionDate() {
        return ProductionDate;
    }

    public void setProductionDate(String productionDate) {
        ProductionDate = productionDate;
    }

    public boolean isManufacturedByFactory() {
        return isManufacturedByFactory;
    }

    public void setManufacturedByFactory(boolean manufacturedByFactory) {
        isManufacturedByFactory = manufacturedByFactory;
    }

    @Override
    public String toString() {
        return
                "Eatable{" +
                "RawMaterial='" + RawMaterial + '\'' +
                ", ExpirationDate=" + ExpirationDate +
                ", ProductionDate=" + ProductionDate +
                ", isManufacturedByFactory=" + isManufacturedByFactory +
                '}';
    }
}
