package com.example.digikala;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import static com.example.digikala.Main.store;

public class Seller extends Account implements Serializable {
    private String companyName;
    private final HashSet<UUID> products;
    private double wallet=0;
    private final ArrayList<String> notification;
    private final ArrayList<String> oldNotification;
    private final HashMap<UUID ,Product> waitForConfirm;
    private final ArrayList<String> ledger;
    private boolean banned=false;
    private boolean verified = false;
    private int sellerLevel=0;
    public Seller(String username, String password, String email, String companyName) {
        super(username,password,email);
        this.companyName = companyName;
        this.products=new HashSet<>();
        this.notification=new ArrayList<>();
        this.oldNotification=new ArrayList<>();
        this.waitForConfirm=new HashMap<>();
        this.ledger= new ArrayList<>();
    }
    public int getSellerLevel() {
        return sellerLevel;
    }
    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNotification() {
        if(notification.size()==0)
            return "";
        String ret = this.notification.get(0);
        this.notification.remove(0);
        this.oldNotification.add(ret);
        return ret;
    }
    public void addLedger(String fact){ledger.add(fact);}
    public ArrayList<String> getLedger(){return ledger;}
    public void addNotification(String notification) {
        this.notification.add(notification);
    }
    public ArrayList<String> getOldNotification() {
        return oldNotification;
    }
    public HashSet<UUID> getProduct() {
        return products;
    }
    public void removeWaitProduct(UUID product){this.waitForConfirm.remove(product);}
    public HashMap<UUID,Product> getWaitForConfirmComplete(){return waitForConfirm;}
    public Product getWaitProduct(UUID product)
    {
        return waitForConfirm.get(product);
    }
    public void addProduct(UUID product) {
        this.products.add(product);
    }
    public void addWait(Product product)
    {
        waitForConfirm.put(product.getUuid(),product);
    }
    public boolean haveProduct(UUID product){return !this.products.contains(product); }
    public void removeProduct(UUID product) {
        this.products.remove(product);
    }
    public double getWallet() {
        return wallet;
    }
    private void checkLevel()
    {
        if(this.wallet>2000000000)
            sellerLevel=8;
        else if(this.wallet>200000000)
            sellerLevel=7;
        else if(this.wallet>20000000)
            sellerLevel=6;
        else if(this.wallet>2000000)
            sellerLevel=5;
        else if(this.wallet>200000)
            sellerLevel=4;
        else if(this.wallet>20000)
            sellerLevel=3;
        else if(this.wallet>2000)
            sellerLevel=2;
        else if(this.wallet>400)
            sellerLevel=1;
        if(this.wallet<=400)
            sellerLevel=0;
    }
    public void removeWallet(double wallet)
    {
        this.wallet-=wallet;
        checkLevel();
    }
    public void addWallet(double wallet) {
        this.wallet += wallet;
        checkLevel();
    }
    public void makeDiscount(int discount,UUID product)
    {
        store.findProduct(product).setDiscount(discount);
    }
    @Override
    public String toString() {
        StringBuilder ret= new StringBuilder("Seller{" +
                super.toString()+
                ", companyName='" + companyName + '\'' +
                ", products={");
                for(UUID product : products)
                    ret.append(store.findProduct(product).toString()).append("\n");
                ret.append("}, wallet=").append(wallet).append('}');
        return ret.toString();
    }
}
