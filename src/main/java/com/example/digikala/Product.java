package com.example.digikala;

import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static com.example.digikala.Main.store;

abstract class Product implements Serializable {
    private String name;
    private UUID uuid;
    private double finalPrice;
    private double price;
    private int amount;
    private final UUID sellerID;
    private Pair<Double , Integer> rate;
    private String description;
    private int discount;
    private String brand;
    private final HashMap<UUID,Double> ratedUser;
    private String imageAddress;
    private final HashMap<UUID,String> comment;

    public Product(String name, int price, int amount, String description,String brand,UUID sellerID) {
        this.name = name;
        uuid = UUID.randomUUID();
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.brand=brand;
        this.finalPrice = price;
        this.sellerID=sellerID;
        this.ratedUser=new HashMap<>();
        this.comment= new HashMap<>();
    }

    public UUID getSellerID() {
        return sellerID;
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


    public double getFinalPrice() {
        return finalPrice;
    }

    public void setPrice(double price) {
        finalPrice=(price*(1-((double)this.discount/100)));
        this.price = price;
    }
    public double getPrice(){return price;}
    public int getAmount() {
        return amount;
    }
    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }
    public void setAmount(int amount) {
        this.amount = this.amount+amount;
    }
    public void setExactAmount(int amount){
        this.amount=amount;
    }
    public boolean didRate(UUID user){return ratedUser.containsKey(user);}
    public double getRate() {
        if(rate == null)
            return 0;
        return rate.getKey();
    }
    public void changeRate(double rate,UUID user)
    {
        this.rate=new Pair<>((this.rate.getKey()* this.rate.getValue()+rate-ratedUser.get(user))/this.rate.getValue(),this.rate.getValue());
        ratedUser.put(user,rate);
    }
    public double getUserRate(UUID user)
    {
        return ratedUser.get(user);
    }
    public void setRate(double rate,UUID user) {
        ratedUser.put(user,rate);
        if(this.rate!=null)
            this.rate = new Pair<>((this.rate.getKey()* this.rate.getValue()+rate)/ (this.rate.getValue()+1), this.rate.getValue()+1);
        else
            this.rate=new Pair<>(rate , 1);
    }
    public void removeRate(UUID user) {
        if(ratedUser.size()==0)
            rate=null;
        else {
            rate=new Pair<>((this.rate.getKey()* this.rate.getValue()-ratedUser.get(user))/ (this.rate.getValue()-1), this.rate.getValue()-1);
            ratedUser.remove(user);
        }
    }
    public boolean didComment(UUID user){return comment.containsKey(user);}
    public ArrayList<String> getComment(){return new ArrayList<>(comment.values());}
    public String getComment(UUID user) {return comment.get(user);}
    public void setComment(String comment,UUID user)
    {
        this.comment.put(user,comment);
    }
    public void removeComment(UUID user) {
        comment.remove(user);
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                ", finalPrice=" + finalPrice +
                ", amount=" + amount +
                ", rate=" + rate +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                ", brand='" + brand + '\'' +
                ", Seller=;"+store.findSeller(sellerID).getUsername()+'\''+
                '}';
    }

    public String TOString()
    {
        return "Product{" +
                "name='" + name + '\'' +
                ", uuid=" + uuid +
                ", finalPrice=" + finalPrice +
                ", price=" + price +
                ", amount=" + amount +
                ", rate=" + rate +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                ", brand='" + brand + '\'' +
                ", Seller=;"+store.findSeller(sellerID).getUsername()+'\''+
                '}';
    }
}
