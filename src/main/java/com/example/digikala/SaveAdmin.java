package com.example.digikala;

import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class SaveAdmin implements Serializable {
        private  ArrayList<String> notification;
        private  ArrayList<String> oldNotification;
        private  boolean firstTime=true;
        private  ArrayList<UUID> sellerConfirm;
        private  ArrayList<UUID> orders;
        private  ArrayList<Pair<UUID,Integer>> walletRequests;
        private  ArrayList<Pair<UUID,UUID>> sellerRequests;
        private  ArrayList<UUID> subscriptions;
        private  ArrayList<Pair<UUID,UUID>> refunds;

        public SaveAdmin(ArrayList<String> notification, ArrayList<String> oldNotification, boolean firstTime, ArrayList<UUID> sellerConfirm, ArrayList<UUID> orders, ArrayList<Pair<UUID, Integer>> walletRequests, ArrayList<Pair<UUID, UUID>> sellerRequests, ArrayList<UUID> subscriptions, ArrayList<Pair<UUID, UUID>> refunds) {
            this.notification = notification;
            this.oldNotification = oldNotification;
            this.firstTime = firstTime;
            this.sellerConfirm = sellerConfirm;
            this.orders = orders;
            this.walletRequests = walletRequests;
            this.sellerRequests = sellerRequests;
            this.subscriptions = subscriptions;
            this.refunds = refunds;
        }

    public ArrayList<String> getNotification() {
        return notification;
    }

    public ArrayList<String> getOldNotification() {
        return oldNotification;
    }

    public boolean isFirstTime() {
        return firstTime;
    }

    public ArrayList<UUID> getSellerConfirm() {
        return sellerConfirm;
    }

    public ArrayList<UUID> getOrders() {
        return orders;
    }

    public ArrayList<Pair<UUID, Integer>> getWalletRequests() {
        return walletRequests;
    }

    public ArrayList<Pair<UUID, UUID>> getSellerRequests() {
        return sellerRequests;
    }

    public ArrayList<UUID> getSubscriptions() {
        return subscriptions;
    }

    public ArrayList<Pair<UUID, UUID>> getRefunds() {
        return refunds;
    }
}
