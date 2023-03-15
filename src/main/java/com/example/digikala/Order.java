package com.example.digikala;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

public class Order {
    private static Store store;
    private Date date;
    private final UUID uuid;
    private int totalPrice;
    private final HashSet<UUID> products;
    private boolean isVerified;
    private UUID user;
    private String stage;

    public Order(Date date, int totalPrice, HashSet<UUID> products, boolean isVerified, UUID user,Store store) {
        this.date = date;
        this.uuid = UUID.randomUUID();
        this.totalPrice = totalPrice;
        this.products = products;
        this.isVerified = isVerified;
        this.user = user;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("Order{" +
                "date=" + date +
                ", uuid=" + uuid +
                ", totalPrice=" + totalPrice +
                ", isVerified=" + isVerified +
                ", user=" + user +
                ", products={\n");
        for (UUID uuid : products) {
            ret.append(store.findProduct(uuid).toString()).append("\n");
        }
        ret.append("} }");
        return ret.toString();
    }
}
