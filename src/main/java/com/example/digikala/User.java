package com.example.digikala;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import org.json.JSONObject;
import static com.example.digikala.Main.store;

public class User extends Account implements Serializable {
    private int phoneNumber;
    private String address;
    private final HashMap<UUID, Integer> cart;
    private int totalPriceOfCart;
    private  final TreeSet<UUID> lastSeen;
    private double wallet = 0;
    private final ArrayList<String> notification;
    private final ArrayList<String> oldNotification;
    private final HashSet<UUID> orders;
    private final HashSet<UUID> favorite;
    private boolean subscription=false;
    private boolean banned=false;
    private boolean waitForVerify;
    private Pair<Double,Double> location;
    private int shippingCost=10000;
    private int userXp=0;
    public User(String username, String password, int phoneNumber, String address,String email) {
        super(username,password,email);
        this.phoneNumber = phoneNumber;
        this.address = address;
        findLocation();
        this.notification= new ArrayList<>();
        this.oldNotification= new ArrayList<>();
        this.orders = new HashSet<>();
        this.favorite = new HashSet<>();
        this.cart= new HashMap<>();
        this.lastSeen=new TreeSet<>();
    }


    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
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
        findLocation();
    }

    public int getUserXp() {
        return userXp;
    }

    public void addUserXp(int userXp) {
        this.userXp += userXp;
    }
    public void removeUserXp(int xp) {
        this.userXp-=xp; }
    public boolean getWait(){return waitForVerify;}
    public void addTotalPrice(double totalPrice){this.totalPriceOfCart+= (int) (1.1*totalPrice);}
    public int cartExist(UUID product){
        return cart.getOrDefault(product, 0);
    }
    public HashMap<UUID,Integer> getCart() {
        return cart;
    }
    public void addCart(UUID product) {
        addTotalPrice(store.findProduct(product).getFinalPrice());
        if(cartExist(product)!=0)
            this.cart.put(product,cart.get(product)+1);
        else
            this.cart.put(product,1);
    }
    public void reduceCart(UUID product)
    {
        addTotalPrice(-1*store.findProduct(product).getFinalPrice());
        if(cartExist(product)==1)
            removeCart(product);
        else
            cart.put(product,cart.get(product)-1);
    }
    public void removeCart(UUID product) {
        addTotalPrice(-1*store.findProduct(product).getFinalPrice()*cart.get(product));
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
    public void removeOrder(UUID order){orders.remove(order);}
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

    public int getShippingCost() {
        return shippingCost;
    }

    public String isOrderOK()
    {
        StringBuilder ret = new StringBuilder();
        for (UUID product : cart.keySet())
        {
            if(store.findProduct(product).getAmount()<cart.get(product))
                ret.append(store.findProduct(product).TOString()).append("\n");
        }
        return ret.toString();
    }
    public boolean isHasLocation(){ return (location ==null); }
    public int getTotalPriceOrder() { return totalPriceOfCart; }
    public int getTotalPriceOfCart(){
        if(!subscription)
            return totalPriceOfCart+shippingCost;
        else
            return totalPriceOfCart;
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
        wallet-=getTotalPriceOfCart();
        userXp+=totalPriceOfCart/100;
        totalPriceOfCart=0;
    }
    public void cancelOrder()
    {
        waitForVerify=false;
    }
    private void calShippingCost()
    {
        double dLat=Math.toRadians(store.getLocation().getKey() - location.getKey());
        double dLon = Math.toRadians(store.getLocation().getValue() - location.getValue());
        double a = Math.sin(dLat /2 ) * Math.sin(dLat/2) + Math.cos(Math.toRadians(store.getLocation().getKey())) * Math.cos(Math.toRadians(location.getKey())) * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2* Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        shippingCost = (int) Math.floor(c * 6371);
    }
    private void findLocation()
    {
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + "4170999efb19400b9e921713232302" + "&q=" + address +"&aqi=no");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            connection.disconnect();
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            location = new Pair<>(jsonObject.getJSONObject("location").getDouble("lat"),jsonObject.getJSONObject("location").getDouble("lon"));
            calShippingCost();
        } catch (Exception ignored) {
        }
    }
    @Override
    public String toString() {
        StringBuilder ret= new StringBuilder("User{" +
                super.toString()+
                ", XP="+userXp+
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", cart={");
                for(UUID product : cart.keySet())
                    ret.append(store.findProduct(product).toString()).append("\n");
                ret.append("}, lastSeen=").append(lastSeen).append(", wallet=").append(wallet).append(", orders={");
                for(UUID order:orders)
                    ret.append(store.findOrder(order).toString()).append("\n");
                ret.append("}, favorite={");
                for(UUID favorite:this.favorite)
                    ret.append(store.findProduct(favorite).toString()).append("\n");
                ret.append("}, subscription=").append(subscription).append('}');
                return ret.toString();
    }
}
