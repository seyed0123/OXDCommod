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
    private UUID lastSeen;
    private int wallet;
    private final ArrayList<String> notification;
    private final ArrayList<String> oldNotification;
    private final HashSet<UUID> orders;
    private final HashSet<UUID> favorite;
    private Date subscription;
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
    public void setWaitForVerify(boolean waitForVerify) {
        this.waitForVerify = waitForVerify;
    }
    public UUID getLastSeen() {
        return lastSeen;
    }
    public void setLastSeen(UUID lastSeen) {
        this.lastSeen = lastSeen;
    }
    public String getUsername() {
        return username;
    }
    public UUID getUuid() {
        return uuid;
    }
    public void setUsername(String username) {
        this.username = username;
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
    public HashSet<UUID> getCart() {
        return cart;
    }
    public void addCart(UUID product) {
        this.cart.add(product);
    }
    public void removeCart(UUID product) {
        this.cart.remove(product);
    }
    public int getWallet() {
        return wallet;
    }
    public void addWallet(int wallet) {
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
    public HashSet<UUID> getFavorite() {
        return favorite;
    }
    public void addFavorite(UUID product) {
        this.favorite.add(product);
    }
    public void removeFavorite(UUID product) {
        this.favorite.remove(product);
    }
    public Date getSubscription() {
        return subscription;
    }
    public void setSubscription(Date subscription) {
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
        wallet-=order.getTotalPrice();
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
                    ret.append(store.findProduct(product).toString).append("\n");
                ret.append("}, lastSeen=").append(lastSeen).append(", wallet=").append(wallet).append(", orders={");
                for(UUID order:orders)
                    ret.append(store.findOrder(order).toString).append("\n");
                ret.append("{, favorite={");
                for(UUID favorite:this.favorite)
                    ret.append(store.findProduct(favorite).toString).append("\n");
                ret.append("}, subscription=").append(subscription.toString()).append('}');
                return ret.toString();
    }
}
