package com.example.digikala;

import javafx.util.Pair;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Admin implements Serializable {
    private static Store store;
    private final String  username;
    private String password;
    private String email;
    private final UUID uuid;
    private static ArrayList<String> notification;
    private static ArrayList<String> oldNotification;
    private static boolean firstTime=true;
    private static ArrayList<UUID> sellerConfirm;
    private static ArrayList<UUID> orders;
    private static ArrayList<Pair<UUID,Integer>> walletRequests;
    private static ArrayList<Pair<UUID,UUID>> sellerRequests;
    private static ArrayList<UUID> subscriptions;

    public Admin(String username, String password, String email) {
        this.username = username;
        this.password = HashPassword(password);
        this.email = email;
        uuid=UUID.randomUUID();
    }
    public static void setStatics(Store store)
    {
        if(!firstTime)
            return;
        Admin.store = store;
        notification=new ArrayList<>();
        oldNotification=new ArrayList<>();
        orders=new ArrayList<>();
        walletRequests=new ArrayList<>();
        sellerRequests=new ArrayList<>();
        subscriptions=new ArrayList<>();
        sellerConfirm= new ArrayList<>();
        firstTime=false;
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
    public String getUsername() {
        return username;
    }
    public UUID getUuid() {
        return uuid;
    }
    public static String getNotification() {
        if(notification.size()==0)
            return "";
        String ret = Admin.notification.get(0);
        Admin.notification.remove(0);
        Admin.oldNotification.add(ret);
        return ret;
    }
    public static void addNotification(String notification) {
        Admin.notification.add(notification);
    }
    public static ArrayList<String> getOldNotification() {
        return Admin.oldNotification;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public UUID orderRequestUUID(){return orders.get(0);}
    public String orderRequest()
    {
        if(orders.size()==0)
            return "";
        return store.findOrder(Admin.orders.get(0)).toString();
    }
    public Pair<UUID,Integer> walletRequestUUID(){return walletRequests.get(0);}
    public String walletRequest() {
        if(walletRequests.size()==0)
            return "";
        return store.findUser(walletRequests.get(0).getKey()).getUsername()+walletRequests.get(0).getValue();
    }
    public Pair<UUID,UUID> sellerRequestUUID(){return sellerRequests.get(0);}
    public String sellerRequest()
    {
        if(sellerRequests.size()==0)
            return "";
        return store.findSeller(sellerRequests.get(0).getKey()).getUsername()+"wants to add"+store.findSeller(sellerRequests.get(0).getKey()).getWaitProduct(sellerRequests.get(0).getValue()).TOString();
    }
    public static boolean isUserSendSubs(UUID user){return subscriptions.contains(user);}
    public UUID subscriptionRequestUUID(){return subscriptions.get(0);}
    public String subscriptionRequest()
    {
        if(subscriptions.size()==0)
            return "";
        return subscriptions.get(0).toString();
    }
    public void checkOrderUser(boolean response,UUID order,String reason)
    {
        Admin.orders.remove(order);
        if(response)
        {
            store.verifyOrderUser(order);
        }else
        {
            store.cancelOrderUser(order,reason);
        }
    }
    public void checkWalletReq(boolean response,Pair<UUID,Integer> request,String reason)
    {
        Admin.walletRequests.remove(request);
        if(response)
        {
            store.verifyWalletReq(request);
        }else
        {
            store.cancelWalletReq(request,reason);
        }
    }
    public void checkSellerReq(boolean response,Pair<UUID,UUID> request,String reason)
    {
        Admin.sellerRequests.remove(request);
        if(response)
        {
            store.verifySellerReq(request);
        }else
        {
            store.cancelSellerReq(request,reason);
        }
    }
    public void checkSubscriptionReq(boolean response,UUID user,String reason)
    {
        Admin.subscriptions.remove(user);
        if(response)
        {
            store.verifySubscriptionReq(user);
        }else
        {
            store.cancelSubscriptionReq(user,reason);
        }
    }
    public String sellerConfirmRequest(){
        return store.findSeller(sellerConfirm.get(0)).toString();
    }
    public UUID sellerConfirmRequestUUID()
    {
        return sellerConfirm.get(0);
    }
    public void sellerConfirmation(boolean response , UUID seller)
    {
        if(seller==null)
            seller=Admin.sellerConfirm.get(0);
        Admin.sellerConfirm.remove(seller);
        if(response)
        {
            store.verifySellerConfirm(seller);
        }else
        {
            store.cancelSellerConfirm(seller);
        }
    }
    public static void addOrder(UUID order)
    {
        Admin.orders.add(order);
    }
    public static void addSellerConfirm(UUID seller){Admin.sellerConfirm.add(seller);}
    public static void addWalletRequest(Pair<UUID,Integer> walletRequest)
    {
        Admin.walletRequests.add(walletRequest);
    }
    public static void addSellerRequests(Pair<UUID,UUID> sellerRequest)
    {
        Admin.sellerRequests.add(sellerRequest);
    }
    public static void addSubscriptions(UUID subscription)
    {
        Admin.subscriptions.add(subscription);
    }
    public String checkUser(UUID user)
    {
        return store.findUser(user).toString();
    }
    public String checkSeller(UUID seller)
    {
        return store.findSeller(seller).toString();
    }
    public String checkProduct(UUID product)
    {
        return store.findProduct(product).TOString();
    }
}
