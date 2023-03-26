package com.example.digikala;

import java.util.Date;
import java.util.UUID;

abstract class Eatable extends Product{
    private String RawMaterial;
    private Date ExpirationDate;
    private Date ProductionDate;
    private boolean isManufacturedByFactory;

    public Eatable(String name, int price, int amount, String comment, String brand, UUID sellerID, String rawMaterial, Date expirationDate, Date productionDate, boolean isManufacturedByFactory) {
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

    public Date getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        ExpirationDate = expirationDate;
    }

    public Date getProductionDate() {
        return ProductionDate;
    }

    public void setProductionDate(Date productionDate) {
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
