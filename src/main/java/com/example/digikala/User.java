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
    private final HashMap<UUID, Integer> cart;
    private final TreeSet<UUID> lastSeen;
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
        this.cart= new HashMap<>();
        this.lastSeen=new TreeSet<>();
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
    public TreeSet<UUID> getLastSeen() {
        return lastSeen;
    }
    public void addLastSeen(UUID lastSeen) {
        if(this.lastSeen.size()>6)
        {
            this.lastSeen.pollLast();
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
    public boolean getWait(){return waitForVerify;}
    public int cartExist(UUID product){
        return cart.getOrDefault(product, 0);
    }
    public HashMap<UUID,Integer> getCart() {
        return cart;
    }
    public void addCart(UUID product) {
        if(cartExist(product)!=0)
            this.cart.put(product,cart.get(product)+1);
        else
            this.cart.put(product,1);
    }
    public void reduceCart(UUID product)
    {
        if(cartExist(product)==1)
            removeCart(product);
        else
            cart.put(product,cart.get(product)-1);
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
            return "";
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
    public boolean isInFavorite(UUID product){return this.favorite.contains(product);}
    public boolean getSubscription() {
        return subscription;
    }
    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }
    public String isOrderOK()
    {
        StringBuilder ret = new StringBuilder();
        for (UUID product : cart.keySet())
        {
            if(store.findProduct(product).getAmount()<=cart.get(product))
                ret.append(product.toString()).append("\n");
        }
        return ret.toString();
    }
    public HashMap<UUID,Integer> order()
    {
        waitForVerify=true;
        return new HashMap<>(cart);
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
                for(UUID product : cart.keySet())
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
