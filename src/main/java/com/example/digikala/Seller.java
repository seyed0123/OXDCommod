package com.example.digikala;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import static com.example.digikala.Main.store;

public class Seller implements Serializable {
    private final String username;
    private String password;
    private String email;
    private final UUID uuid;
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
        this.username = username;
        this.password = HashPassword(password);
        this.email = email;
        this.companyName = companyName;
        this.uuid=UUID.randomUUID();
        this.products=new HashSet<>();
        this.notification=new ArrayList<>();
        this.oldNotification=new ArrayList<>();
        this.waitForConfirm=new HashMap<>();
        this.ledger= new ArrayList<>();
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

    public int getSellerLevel() {
        return sellerLevel;
    }

    public void setSellerLevel(int sellerLevel) {
        this.sellerLevel = sellerLevel;
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

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public boolean haveProduct(UUID product){return this.products.contains(product); }
    public void removeProduct(UUID product) {
        this.products.remove(product);
    }
    public double getWallet() {
        return wallet;
    }
    private void checkLevel()
    {
        if(this.wallet<=400)
            sellerLevel=0;
        else if(this.wallet>400)
            sellerLevel=1;
        else if(this.wallet>2000)
            sellerLevel=2;
        else if(this.wallet>20000)
            sellerLevel=3;
        else if(this.wallet>200000)
            sellerLevel=4;
        else if(this.wallet>2000000)
            sellerLevel=5;
        else if(this.wallet>20000000)
            sellerLevel=6;
        else if(this.wallet>200000000)
            sellerLevel=7;
        else if(this.wallet>2000000000)
            sellerLevel=8;
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
    public void cancelDiscount(UUID product)
    {
        store.findProduct(product).setDiscount(0);
    }
    @Override
    public String toString() {
        StringBuilder ret= new StringBuilder("Seller{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", uuid=" + uuid +
                ", companyName='" + companyName + '\'' +
                ", products={");
                for(UUID product : products)
                    ret.append(store.findProduct(product).toString()).append("\n");
                ret.append("}, wallet=").append(wallet).append('}');
        return ret.toString();
    }
}
