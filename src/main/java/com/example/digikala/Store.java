package com.example.digikala;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.*;

public class Store {
    private Store store;
    private final HashMap<UUID,User> users;
    private final HashMap<UUID,Admin> admins;
    private final HashMap<UUID,Seller> sellers;
    private final HashMap<UUID,Order> orders;
    private final HashMap<UUID,Product> products;
    private final HashMap<String,UUID> usernames;
    private double profit;
    private String webAddress;
    private int supportNumber;
    private HashSet<String> log;
    private String owner;

    public Store(String webAddress, int supportNumber, String owner) {
        this.webAddress = webAddress;
        this.supportNumber = supportNumber;
        this.owner = owner;
        this.users=new HashMap<>();
        this.admins=new HashMap<>();
        this.sellers= new HashMap<>();
        this.orders= new HashMap<>();
        this.products= new HashMap<>();
        this.usernames=new HashMap<>();
        Admin.setStatics();
    }
    public void setStore(Store store){this.store=store;}
    public void addUser(String username, String password , int phoneNumber , String address , String email)
    {
        User temp = new User(username, password,phoneNumber,address,email);
        users.put(temp.getUuid(),temp);
        usernames.put(username,temp.getUuid());
        log.add(temp.getUuid()+" created an account as User.");
    }
    public void addSeller(String username , String password ,String email,String companyName)
    {
        Seller temp = new Seller(username ,password, email ,companyName);
        sellers.put(temp.getUuid(),temp);
        usernames.put(username,temp.getUuid());
        log.add(temp.getUuid()+" created an account as Seller.");
    }
    public void addAdmin(String username , String password ,String email)
    {
        Admin temp = new Admin(username , password , email);
        admins.put(temp.getUuid(),temp);
        usernames.put(username,temp.getUuid());
        log.add(temp.getUuid()+" created an account as Admin.");
    }
    public boolean IsUsernameExist(String username)
    {
        return usernames.containsKey(username);
    }
    public Pair<String,UUID> login(String username,String password)
    {
        UUID person=usernames.get(username);
        if(users.containsKey(person))
        {
            if(users.get(person).checkPassword(password))
            {
                return new Pair<String,UUID>("user",person);
            }
        }else if(sellers.containsKey(person))
        {
            if(sellers.get(person).checkPassword(password))
            {
                return new Pair<String,UUID>("seller",person);
            }

        }else if(admins.containsKey(person))
        {
            if(admins.get(person).checkPassword(password))
            {
                return new Pair<String,UUID>("admin",person);
            }
        }
            return new Pair<>("",null);
    }
    public boolean isUserExist(UUID user)
    {
        return users.containsKey(user);
    }
    public boolean isSellerExist(UUID Seller)
    {
        return sellers.containsKey(Seller);
    }
    public boolean isProductExist(UUID Product)
    {
        return products.containsKey(Product);
    }
    public User findUser(UUID user)
    {
        return users.get(user);
    }
    public Product findProduct(UUID product)
    {
        return products.get(product);
    }
    public Admin findAdmin(UUID admin)
    {
        return admins.get(admin);
    }
    public Seller findSeller(UUID seller)
    {
        return sellers.get(seller);
    }
    public Order findOrder(UUID order)
    {
        return orders.get(order);
    }
    public void removeProduct(UUID product)
    {
        sellers.get(products.get(product).getSellerID()).removeProduct(product);
        sellers.get(products.get(product).getSellerID()).addNotification(product +"has been removed successfully.");
        products.remove(product);
        log.add(product +"has been removed successfully in "+LocalDateTime.now()+".");

    }
    public void sendSellerOrder(UUID seller ,Product product)
    {
        sellers.get(seller).addWait(product);
        Admin.addSellerRequests(new Pair<UUID,UUID>(seller,product.getUuid()));
        Admin.addNotification(sellers.get(seller).getUuid()+" wants to add "+ sellers.get(seller).getWaitProduct(product.getUuid()));
        log.add(sellers.get(seller).getUuid()+" wants to add "+ sellers.get(seller).getWaitProduct(product.getUuid())+" in "+LocalDateTime.now()+".");
    }
    public void sendOrder(UUID user)
    {
        User temp = users.get(user);
        Order order= new Order(LocalDateTime.now(),temp.order(),user,store);
        this.orders.put(order.getUuid(),order);
        Admin.addOrder(order.getUuid());
        Admin.addNotification("an Order were received from "+temp.getUsername()+". check it.");
        log.add("an order was sent by "+temp.getUuid()+" in "+LocalDateTime.now() + ":"+order.getUuid());
    }
    public void verifyOrderUser(UUID order)
    {
        Order temp =this.orders.get(order);
        profit+=temp.getTotalPrice()*0.1;
        temp.verify();
        this.users.get(temp.getUser()).verifyOrder(temp);
        this.users.get(temp.getUser()).addNotification("Your order request was approved");
        log.add("an order was verify by admin in"+LocalDateTime.now()+" :"+ temp.getUuid());
    }
    public void cancelOrderUser(UUID order)
    {
        this.users.get(orders.get(order).getUser()).cancelOrder();
        this.log.add("order "+orders.get(order)+" was canceled in "+LocalDateTime.now()+".");
        this.users.get(orders.get(order).getUser()).addNotification("Your order request was not approved");
        this.orders.remove(order);
    }
    public void addWallet(Pair<UUID,Integer> request)
    {
        Admin.addNotification(users.get(request.getKey()).getUsername()+" wants to add "+ request.getValue() +" to wallet");
        Admin.addWalletRequest(request);
        log.add(users.get(request.getKey()).getUuid()+" wants to add "+ request.getValue() +" to wallet in "+ LocalDateTime.now()+".");
    }
    public void verifyWalletReq(Pair<UUID, Integer> request)
    {
        this.users.get(request.getKey()).addWallet(request.getValue());
        this.users.get(request.getKey()).addNotification("Your request for an increase in money has been approved.");
        log.add(request.getValue()+" was added to "+users.get(request.getKey()).getUuid()+" in "+LocalDateTime.now()+".");
    }
    public void cancelWalletReq(Pair<UUID,Integer> request)
    {
        this.users.get(request.getKey()).addNotification("Your request for an increase in money hasn't been approved.");
        log.add(request.getValue()+" wasn't added to "+users.get(request.getKey()).getUuid()+" in "+LocalDateTime.now()+".");
    }
    public void verifySellerReq(Pair<UUID,UUID> request)
    {
        this.sellers.get(request.getKey()).addProduct(request.getValue());
        this.products.put(sellers.get(request.getKey()).getWaitProduct(request.getValue()).getUuid(),sellers.get(request.getKey()).getWaitProduct(request.getValue()));
        this.sellers.get(request.getKey()).addNotification("Your order request for add " +sellers.get(request.getKey()).getWaitProduct(request.getValue()).getUuid() +" was approved");
        log.add("the order request for add " +sellers.get(request.getKey()).getWaitProduct(request.getValue()).getUuid() +" was approved in "+LocalDateTime.now()+".");
    }
    public void cancelSellerReq(Pair<UUID,UUID> request)
    {
        this.sellers.get(request.getKey()).addNotification("Your order request for add " +sellers.get(request.getKey()).getWaitProduct(request.getValue()).getUuid() +" wasn't approved");
        log.add("the order request for add " +sellers.get(request.getKey()).getWaitProduct(request.getValue()).getUuid() +" wasn't approved");
    }
    public void verifySubscriptionReq(UUID user)
    {
        this.users.get(user).setSubscription(true);
        this.users.get(user).addNotification("Your request for subscription has been approved.");
        log.add("the request for subscription from"+this.users.get(user).getUuid() +" has been approved in "+LocalDateTime.now()+".");
    }
    public void cancelSubscriptionReq(UUID user)
    {
        this.users.get(user).addNotification("Your request for subscription hasn't been approved.");
        log.add("the request for subscription from"+this.users.get(user).getUuid() +" hasn't been approved in "+LocalDateTime.now()+".");
    }
    public void addSubscriptions(UUID user)
    {
        Admin.addSubscriptions(user);
        Admin.addNotification("a request to subscription was sent by "+ user);
        log.add("a request to subscription was sent by "+ user+" in "+LocalDateTime.now()+".");
    }
    public void ban(UUID person)
    {
        if(users.containsKey(person))
        {
            users.get(person).setBanned(true);
            users.get(person).addNotification("You have been suspended by digiEye :)");
        }else
        {
            sellers.get(person).setBanned(true);
            sellers.get(person).addNotification("You have been suspended by digiEye :)");
        }
        log.add(person +" have been suspended by digiEye in"+LocalDateTime.now()+".");
    }
    public void permit(UUID person)
    {
        if(users.containsKey(person))
        {
            users.get(person).setBanned(false);
            users.get(person).addNotification("Your charges have been cleared.");
        }else
        {
            sellers.get(person).setBanned(false);
            sellers.get(person).addNotification("Your charges have been cleared.");
        }
        log.add("The charges of "+person +" have been cleared.");
    }
    public User findUserByInfo(String username)
    {
        return findUser(usernames.get(username));
    }
    public Seller findSellerByInfo(String username)
    {
        return findSeller(usernames.get(username));
    }
    private int numberOfMatch(String str,UUID product)
    {
        int ret=0;
        ArrayList<String> substr = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = i+1; j <= str.length(); j++) {
                substr.add(str.substring(i,j));
            }
        }
        String productName=findProduct(product).getName();
        for(String temp :substr)
        {
            if(productName.contains(temp))
                ret++;
        }
        return ret;
    }
    public HashSet<UUID> findProductByInfo(String name)
    {
        int max1=Integer.MIN_VALUE,max2=Integer.MIN_VALUE,max3=Integer.MIN_VALUE;
        HashSet<UUID> ret = new HashSet<>();
        HashMap<Integer, UUID> nums = new HashMap<>();
        for(Product product: products.values())
        {
            int num=numberOfMatch(name,product.getUuid());
            if(max1<num) {
                if(max2<num) {
                    max1=max2;
                    if(max3<num) {
                        max2=max3;
                        max3=num;
                    }else {
                        max2=num;
                    }
                }else {
                    max1 = num;
                }
            }
            nums.put(num,product.getUuid());
        }
        if(max1!=Integer.MIN_VALUE)
            ret.add(nums.get(max1));
        if(max2!=Integer.MIN_VALUE)
            ret.add(nums.get(max2));
        if(max3!=Integer.MIN_VALUE)
            ret.add(nums.get(max3));
        return ret;
    }
    public HashSet<UUID> recommend(UUID user)
    {
        Vector<UUID> last=findUser(user).getLastSeen();
        HashSet<UUID> ret = new HashSet<>();
        for(UUID product:last)
        {
            ret.addAll(findProductByInfo(findProduct(product).getName()));
        }
        return ret;
    }
    public void updateProduct(Product product)
    {
        products.put(product.getUuid(),product);
        log.add(product.getUuid()+ " has been changed in"+LocalDateTime.now()+".");
    }
}
