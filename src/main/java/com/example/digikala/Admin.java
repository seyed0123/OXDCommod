package com.example.digikala;

import javafx.util.Pair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Admin {
    private static Store store;
    private final String  username;
    private String password;
    private String email;
    private final UUID uuid;
    private final ArrayList<String> notification;
    private final ArrayList<String> oldNotification;
    private final ArrayList<UUID> orders;
    private final ArrayList<Pair<UUID,Integer>> walletRequest;
    private final ArrayList<Pair<UUID,UUID>> sellerRequest;
    private final ArrayList<UUID> subscription;

    public Admin(String username, String password, String email) {
        this.username = username;
        this.password = HashPassword(password);
        this.email = email;
        uuid=UUID.randomUUID();
        notification=new ArrayList<>();
        oldNotification=new ArrayList<>();
        orders=new ArrayList<>();
        walletRequest=new ArrayList<>();
        sellerRequest=new ArrayList<>();
        subscription=new ArrayList<>();
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void checkOrderUser(boolean response,UUID order)
    {
        this.orders.remove(order);
        if(response)
        {
            store.verifyOrderUser(order);
        }else
        {
            store.cancelOrderUser(order);
        }
    }
    public void checkWalletReq(boolean response,Pair<UUID,Integer> request)
    {
        this.walletRequest.remove(request);
        if(response)
        {
            store.verifyWalletReq(request);
        }else
        {
            store.cancelWalletReq(request);
        }
    }
    public void checkSellerOrder(boolean response,Pair<UUID,UUID> request)
    {
        this.sellerRequest.remove(request);
        if(response)
        {
            store.verifySellerReq(request);
        }else
        {
            store.cancelSellerReq(request);
        }
    }
    public void checkSubscriptionOrder(boolean response,UUID user)
    {
        this.subscription.remove(user);
        if(response)
        {
            store.verifySubscriptionReq(user);
        }else
        {
            store.cancelSubscriptionReq(user);
        }
    }
}
