package com.example.digikala;

import javafx.util.Pair;

import java.io.Serializable;
import java.util.Map.Entry;
import java.time.LocalDateTime;
import java.util.*;

public class Store implements Serializable {
    public final String[] CAPTCHA= {"226md.png","22d5n.png","2356g.png","23mdg.png","23n88.png","243mm.png","244e2.png","245y5.png","24f6w.png","24pew.png","25257.png"};
    private final HashMap<UUID,User> users;
    private final HashMap<UUID,Admin> admins;
    private final HashMap<UUID,Seller> sellers;
    private final HashMap<UUID,Order> orders;
    private final HashMap<UUID,Product> products;
    private final HashMap<String,UUID> usernames;
    private final HashSet<UUID> ban;
    private double profit;
    private final String webAddress;
    private final long supportNumber;
    private final ArrayList<String> log;
    private final String owner;
    private final Pair<Double,Double> location;

    public Store(String webAddress, long supportNumber, String owner,double lat,double lon) {
        this.webAddress = webAddress;
        this.supportNumber = supportNumber;
        this.owner = owner;
        location= new Pair<>(lat,lon);
        this.users=new HashMap<>();
        this.admins=new HashMap<>();
        this.sellers= new HashMap<>();
        this.orders= new HashMap<>();
        this.products= new HashMap<>();
        this.usernames=new HashMap<>();
        this.ban=new HashSet<>();
        this.log=new ArrayList<>();
    }

    public String getWebAddress() {
        return webAddress;
    }

    public long getSupportNumber() {
        return supportNumber;
    }

    public String getOwner() {
        return owner;
    }

    public Pair<Double, Double> getLocation() {
        return location;
    }

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
        addSellerConfirm(temp);
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
            if(users.get(person).isBanned())
                return new Pair<>("banUser",null);
            if(users.get(person).checkPassword(password)) {
                return new Pair<String, UUID>("user", person);
            }
        }else if(sellers.containsKey(person))
        {
            if(sellers.get(person).isBanned())
            {
                return new Pair<String,UUID>("banSeller",null);
            }if(!sellers.get(person).isVerified())
                return new Pair<>("verifySeller",null);
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
            return null;
    }
    public ArrayList<String> bans()
    {
        ArrayList<String> ret = new ArrayList<>();
        for(UUID uuid : ban)
        {
            if(isUserExist(uuid))
                ret.add(findUser(uuid).toString());
            else
                ret.add(findSeller(uuid).toString());
        }
        return ret;
    }
    public ArrayList<String> users()
    {
        ArrayList<String> ret = new ArrayList<>();
        for(User user : users.values())
        {
            ret.add(user.toString());
        }
        return ret;
    }
    public ArrayList<String> sellers()
    {
        ArrayList<String> ret = new ArrayList<>();
        for(Seller seller : sellers.values())
        {
            ret.add(seller.toString());
        }
        return ret;
    }
    public ArrayList<String> orders()
    {
        ArrayList<String> ret = new ArrayList<>();
        for(Order order : orders.values())
        {
            if(order.isVerified())
                ret.add(order.toString());
        }
        return ret;
    }
    public ArrayList<String> products()
    {
        ArrayList<String> ret = new ArrayList<>();
        for(Product product : products.values())
        {
            if(!product.isRemove())
                ret.add(product.TOString());
        }
        return ret;
    }
    public ArrayList<String> log(){ return log; }
    public boolean isUserExist(UUID user){return users.containsKey(user);}
    public boolean isSellerExist(UUID seller){return !sellers.containsKey(seller);}
    public boolean isProductExist(UUID product){return products.containsKey(product);}
    public double getProfit() {
        return profit;
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
        UUID temp = products.get(product).getSellerID();
        sellers.get(temp).removeProduct(product);
        sellers.get(temp).addNotification(product +"has been removed successfully.");
        findProduct(product).setRemove(true);
        log.add(product +"has been removed successfully in "+LocalDateTime.now()+".");

    }
    public void sendSellerOrder(UUID seller ,Product product)
    {
        Seller temp = sellers.get(seller);
        temp.addWait(product);
        Admin.addSellerRequests(new Pair<UUID,UUID>(seller,product.getUuid()));
        Admin.addNotification(temp.getUuid()+" wants to add "+ temp.getWaitProduct(product.getUuid()));
        log.add(temp.getUuid()+" wants to add "+ temp.getWaitProduct(product.getUuid())+" in "+LocalDateTime.now()+".");
    }
    public void sendOrder(UUID user)
    {
        User temp = users.get(user);
        Order order= new Order(LocalDateTime.now(),temp.order(),user,temp.getTotalPriceOrder());
        this.orders.put(order.getUuid(),order);
        Admin.addOrder(order.getUuid());
        Admin.addNotification("an Order were received from "+temp.getUsername()+". check it.");
        log.add("an order was sent by "+temp.getUuid()+" in "+LocalDateTime.now() + ":"+order.getUuid());
    }
    public void verifyOrderUser(UUID order)
    {
        Order temp =this.orders.get(order);
        profit+=(temp.getTotalPrice()/11.0);
        temp.verify();
        this.users.get(temp.getUser()).verifyOrder(temp);
        this.users.get(temp.getUser()).addNotification("Your order request was approved");
        log.add("an order was verify by admin in"+LocalDateTime.now()+" :"+ temp.getUuid());
    }
    public void cancelOrderUser(UUID order,String reason)
    {
        Order temp = orders.get(order);
        this.users.get(temp.getUser()).cancelOrder();
        this.log.add("order "+temp+" was canceled in "+LocalDateTime.now()+".");
        this.users.get(temp.getUser()).addNotification("Your order request was not approved due to"+ reason);
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
    public void cancelWalletReq(Pair<UUID,Integer> request,String reason)
    {
        this.users.get(request.getKey()).addNotification("Your request for an increase in money hasn't been approved due to"+ reason);
        log.add(request.getValue()+" wasn't added to "+users.get(request.getKey()).getUuid()+" in "+LocalDateTime.now()+".");
    }
    public void verifySellerReq(Pair<UUID,UUID> request)
    {
        Seller temp = sellers.get(request.getKey());
        temp.addProduct(request.getValue());
        this.products.put(temp.getWaitProduct(request.getValue()).getUuid(),temp.getWaitProduct(request.getValue()));
        temp.addNotification("Your order request for add " +temp.getWaitProduct(request.getValue()).getUuid() +" was approved");
        log.add("the order request for add " +temp.getWaitProduct(request.getValue()).getUuid() +" was approved in "+LocalDateTime.now()+".");
        temp.removeWaitProduct(request.getValue());
    }
    public void cancelSellerReq(Pair<UUID,UUID> request,String reason)
    {
        Seller temp = sellers.get(request.getKey());
        temp.addNotification("Your order request for add " +temp.getWaitProduct(request.getValue()).getUuid() +" wasn't approved due to "+reason);
        log.add("the order request for add " +temp.getWaitProduct(request.getValue()).getUuid() +" wasn't approved");
        //this.sellers.get(request.getKey()).removeWaitProduct(request.getValue());
    }
    public void verifySubscriptionReq(UUID user)
    {
        User temp = users.get(user);
        temp.setSubscription(true);
        temp.addNotification("Your request for subscription has been approved.");
        log.add("the request for subscription from"+temp.getUuid() +" has been approved in "+LocalDateTime.now()+".");
    }
    public void cancelSubscriptionReq(UUID user,String reason)
    {
        User temp = users.get(user);
        temp.addNotification("Your request for subscription hasn't been approved due to "+reason);
        log.add("the request for subscription from"+temp.getUuid() +" hasn't been approved in "+LocalDateTime.now()+".");
    }
    public void addSubscriptions(UUID user)
    {
        Admin.addSubscriptions(user);
        Admin.addNotification("a request to subscription was sent by "+ user);
        log.add("a request to subscription was sent by "+ user+" in "+LocalDateTime.now()+".");
    }
    public void addSellerConfirm(Seller seller)
    {
        Admin.addSellerConfirm(seller.getUuid());
        Admin.addNotification(seller+" wants confirmation for activity");
        log.add("a request sent by seller for confirmation in"+LocalDateTime.now()+".");
    }
    public void verifySellerConfirm(UUID seller)
    {
        findSeller(seller).setVerified(true);
        findSeller(seller).addNotification("now you have permission to sell products.");
        log.add("admin allows "+seller+" to sell product in "+LocalDateTime.now()+".");
    }
    public void cancelSellerConfirm(UUID seller)
    {
        log.add("admin reject "+findSeller(seller)+"in "+LocalDateTime.now()+".");
        sellers.remove(seller);
    }
    public void addRefundReq(Pair<UUID,UUID> request)
    {
        Admin.addRefundReq(request);
        Admin.addNotification("an refund request received");
        log.add("an request for refunding an order received in"+LocalDateTime.now()+".");
    }
    public void verifyRefund(Pair<UUID,UUID> request)
    {
        findOrder(request.getValue()).refund();
        profit-=findOrder(request.getValue()).getTotalPrice()/11.0;
        log.add(request.getValue()+"has been refunded in "+LocalDateTime.now()+".");
    }
    public void cancelRefund(Pair<UUID,UUID> request , String reason)
    {
        findUser(request.getKey()).addNotification("your request for refunding an order hasn't been approved due to"+reason);
        log.add("a request for refunding"+findOrder(request.getValue())+" hasn't been approved due to "+reason+"in "+LocalDateTime.now()+".");
    }
    public void ban(UUID person)
    {
        if(users.containsKey(person))
        {
            users.get(person).setBanned(true);
            users.get(person).addNotification("You have been suspended by OXDEye :)");
        }else
        {
            sellers.get(person).setBanned(true);
            sellers.get(person).addNotification("You have been suspended by OXDEye :)");
        }
        ban.add(person);
        log.add(person +" have been suspended by OXDEye in"+LocalDateTime.now()+".");
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
        ban.remove(person);
        log.add("The charges of "+person +" have been cleared.");
    }
    public UUID findByInfo(String username)
    {
        return (usernames.get(username));
    }
    private int numberOfMatch(ArrayList<String> substr,Product product)
    {
        int ret=0;
        String productName=product.getName();
        for(String temp :substr)
        {
            if(productName.contains(temp))
                ret++;
        }
        return ret;
    }
    public ArrayList<UUID> findProductByInfo(String str)
    {
        ArrayList<String> substr = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = i+1; j <= str.length(); j++) {
                substr.add(str.substring(i,j));
            }
        }
        HashMap<UUID, Integer> nums = new HashMap<>();
        for(Product product: products.values())
        {
            if(!product.isRemove()) {
                int num = numberOfMatch(substr, product);
                nums.put(product.getUuid(), num);
            }
        }
        List<Entry<UUID,Integer>> list = new ArrayList<>(nums.entrySet());
        Collections.sort(list, new Comparator<Entry<UUID,Integer>>(){
            public int compare(Entry<UUID,Integer> o1 , Entry<UUID , Integer> o2)
            {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        ArrayList<UUID> sorted = new ArrayList<>();
        for(Entry<UUID,Integer> entry :list)
            sorted.add(entry.getKey());
        return sorted;
    }
    public ArrayList<UUID> offers()
    {
        ArrayList <UUID> ret=  new ArrayList<>();
        int counter = 0 ;
        for(Product product : products.values())
        {
            if(counter>=10)
                break;
            if(product.getDiscount()>0 && !product.isRemove())
            {
                counter++;
                ret.add(product.getUuid());
            }
        }
        return ret;
    }
}