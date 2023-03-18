package com.example.digikala;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class User {
    private static Store store;
    private final String username;
    private String password;
    private String email;
    private final UUID uuid;
    private int phoneNumber;
    private String address;
    private final HashSet<UUID> cart;
    private Vector<UUID> lastSeen;
    private double wallet;
    private final ArrayList<String> notification;
    private final ArrayList<String> oldNotification;
    private final HashSet<UUID> orders;
    private final HashSet<UUID> favorite;
    private boolean subscription=false;
    private boolean banned=false;
    private boolean waitForVerify;

    public User(String username, String password, int phoneNumber, String address,String email) {
        this.username = username;
        this.password = HashPassword(password);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email=email;
        this.uuid= UUID.randomUUID();
        this.notification= new ArrayList<>();
        this.oldNotification= new ArrayList<>();
        this.orders = new HashSet<>();
        this.favorite = new HashSet<>();
        this.cart= new HashSet<>();
        this.lastSeen=new Vector<>();
    }
    public static void setStore(Store store) {
        User.store = store;
    }

    private String HashPassword(String passwordToHash)
    {
        String generatedPassword = null;
        try
        {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(passwordToHash.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //System.out.println(generatedPassword);
        return generatedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public boolean setPassword(String password , String oldPassword)
    {
        if(!Objects.equals(HashPassword(oldPassword),this.password))
            return false;
        this.password=HashPassword(password);
        return true;
    }
    public boolean checkPassword(String password)
    {
        String genPass=HashPassword(password);
        return Objects.equals(this.password, genPass);
    }
    public Vector<UUID> getLastSeen() {
        return lastSeen;
    }
    public void addLastSeen(UUID lastSeen) {
        if(this.lastSeen.size()>3)
        {
            this.lastSeen.removeElementAt(2);
        }
        this.lastSeen.add(lastSeen);
    }
    public String getUsername() {
        return username;
    }
    public UUID getUuid() {
        return uuid;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public boolean cartExist(UUID product){return cart.contains(product);}
    public HashSet<UUID> getCart() {
        return cart;
    }
    public void addCart(UUID product) {
        this.cart.add(product);
    }
    public void removeCart(UUID product) {
        this.cart.remove(product);
    }
    public double getWallet() {
        return wallet;
    }
    public void addWallet(double wallet) {
        this.wallet += wallet;
    }
    public String getNotification() {
        if(notification.size()==0)
            return null;
        String ret = this.notification.get(0);
        this.notification.remove(0);
        this.oldNotification.add(ret);
        return ret;
    }
    public void addNotification(String notification) {
        this.notification.add(notification);
    }
    public ArrayList<String> getOldNotification() {
        return oldNotification;
    }
    public HashSet<UUID> getOrders() {
        return orders;
    }
    public boolean favoriteExist(UUID product){return favorite.contains(product);}
    public HashSet<UUID> getFavorite() {
        return favorite;
    }
    public void addFavorite(UUID product) {
        this.favorite.add(product);
    }
    public void removeFavorite(UUID product) {
        this.favorite.remove(product);
    }
    public boolean getSubscription() {
        return subscription;
    }
    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }
    public HashSet<UUID> order()
    {
        waitForVerify=true;
        return this.cart;
    }
    public void verifyOrder(Order order)
    {
        this.waitForVerify=false;
        this.orders.add(order.getUuid());
        cart.clear();
        wallet-=order.getTotalPrice()*1.1;
    }
    public void cancelOrder()
    {
        waitForVerify=false;
    }
    @Override
    public String toString() {
        StringBuilder ret= new StringBuilder("User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", uuid=" + uuid +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", cart={");
                for(UUID product : cart)
                    ret.append(store.findProduct(product).toString()).append("\n");
                ret.append("}, lastSeen=").append(lastSeen).append(", wallet=").append(wallet).append(", orders={");
                for(UUID order:orders)
                    ret.append(store.findOrder(order).toString()).append("\n");
                ret.append("{, favorite={");
                for(UUID favorite:this.favorite)
                    ret.append(store.findProduct(favorite).toString()).append("\n");
                ret.append("}, subscription=").append(subscription).append('}');
                return ret.toString();
    }
}
