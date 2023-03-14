package com.example.digikala;

import java.util.Date;

abstract class Eatable extends Product{
    private String RawMaterial;
    private Date ExpirationDate;
    private Date ProductionDate;
    private boolean isManufacturedByFactory;

    public Eatable(String name, int price, int amount, String comment,String brand, String rawMaterial, Date expirationDate, Date productionDate, boolean isManufacturedByFactory) {
        super(name, price, amount, comment,brand);
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
        return super.toString()+
                "Eatable{" +
                "RawMaterial='" + RawMaterial + '\'' +
                ", ExpirationDate=" + ExpirationDate +
                ", ProductionDate=" + ProductionDate +
                ", isManufacturedByFactory=" + isManufacturedByFactory +
                '}';
    }
}
