package com.example.digikala;

import javafx.util.Pair;
import java.util.UUID;

abstract class Product {
    private String name;
    private UUID uuid;
    private double finalPrice;
    private double price;
    private int amount;
    private final UUID sellerID;
    private Pair<Double, Integer> rate;
    private String comment;
    private int discount;
    private String brand;

    public Product(String name, int price, int amount, String comment,String brand,UUID sellerID) {
        this.name = name;
        uuid = UUID.randomUUID();
        this.price = price;
        this.amount = amount;
        this.comment = comment;
        this.brand=brand;
        finalPrice = price;
        this.sellerID=sellerID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setPrice(double price) {
        finalPrice=(price*(1-((double)this.discount/100)));
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = this.amount+amount;
    }

    public double getRate() {
        if(rate == null)
            return -10;
        return rate.getKey();
    }

    public void setRate(double rate) {
        if(this.rate!=null)
            this.rate = new Pair<>((this.rate.getKey()* this.rate.getValue()+rate)/ (this.rate.getValue()+1), this.rate.getValue()+1);
        else
            this.rate=new Pair<>(rate , 1);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.finalPrice=(this.price*(1-((double)discount/100)));
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", uuid=" + uuid +
                ", finalPrice=" + finalPrice +
                ", price=" + price +
                ", amount=" + amount +
                ", rate=" + rate +
                ", comment='" + comment + '\'' +
                ", discount=" + discount +
                ", brand='" + brand + '\'' +
                '}';
    }
}
