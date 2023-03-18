package com.example.digikala;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

public class Order {
    private static Store store;
    private LocalDateTime date;
    private final UUID uuid;
    private int totalPrice=0;
    private final HashSet<UUID> products;
    private boolean isVerified;
    private UUID user;
    private String stage;

    public Order(LocalDateTime date, HashSet<UUID> products, UUID user,Store store) {
        this.date = date;
        this.uuid = UUID.randomUUID();
        this.products = products;
        this.user = user;
        for(UUID product:products)
        {
            totalPrice+=store.findProduct(product).getFinalPrice();
        }
    }

    public static void setStore(Store store) {
        Order.store = store;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isVerified() {
        return isVerified;
    }
    public void verify()
    {
        for (UUID product:products)
        {
            store.findProduct(product).setAmount(-1);
            store.findSeller(store.findProduct(product).getSellerID()).addWallet(store.findProduct(product).getFinalPrice());
        }
        isVerified=true;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public UUID getUser() {
        return user;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("Order{" +
                "date=" + date +
                ", uuid=" + uuid +
                ", totalPrice=" + totalPrice +
                ", isVerified=" + isVerified +
                ", user=" + user +
                ", user wallet= "+ store.findUser(user).getWallet()+
                ", products={\n");
        for (UUID uuid : products) {
            ret.append(store.findProduct(uuid).toString()).append("\n");
        }
        ret.append("} }");
        return ret.toString();
    }
}
