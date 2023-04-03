package com.example.digikala;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Order implements Serializable {
    private static Store store;
    private LocalDateTime date;
    private final UUID uuid;
    private int totalPrice=0;
    private final HashMap<UUID,Integer> products;
    private boolean isVerified;
    private UUID user;
    private String stage;

    public Order(LocalDateTime date, HashMap<UUID,Integer> products, UUID user) {
        this.date = date;
        this.uuid = UUID.randomUUID();
        this.products = products;
        this.user = user;
        for(UUID product:products.keySet())
        {
            totalPrice+= store.findProduct(product).getFinalPrice() * products.get(product);
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
        for (UUID product:products.keySet())
        {
            store.findProduct(product).setAmount(-1*products.get(product));
            store.findSeller(store.findProduct(product).getSellerID()).addWallet(store.findProduct(product).getFinalPrice()*products.get(product));
            store.findSeller(store.findProduct(product).getSellerID()).addNotification(products.get(product)+" * " + product + " has been sold ");
        }
        isVerified=true;
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
        for (UUID uuid : products.keySet()) {
            ret.append(store.findProduct(uuid).toString()).append(" * ").append(products.get(uuid)).append("\n");
        }
        ret.append("} }");
        return ret.toString();
    }
}
