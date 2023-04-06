package com.example.digikala;
import javafx.util.Pair;
import org.testng.annotations.Test;

import java.util.UUID;

import static com.example.digikala.Main.store;
import static org.testng.AssertJUnit.*;

public class Testing {

    @Test
    public void login() {
        store = new Store("www.OXDCommod.com",123123,"OXD-Seyed",43.78,-0.7);
        Admin.setStatics();
        store.addUser("u1","123",123,"shahr babak","@");
        assertEquals("user",(store.login("u1","123").getKey()));
        User user = store.findUser(store.login("u1","123").getValue());
        store.addAdmin("ad1","123","@");
        Admin admin = store.findAdmin(store.login("ad1","123").getValue());
        assertEquals("admin",store.login("ad1","123").getKey());
        store.ban(user.getUuid());
        assertEquals("banUser",store.login("u1","123").getKey());
        store.permit(user.getUuid());
        assertEquals("user",store.login("u1","123").getKey());
        assertNull(store.login("u2", "123"));
        store.addSeller("sel1","123","@","inc");
        assertEquals("verifySeller",store.login("sel1", "123").getKey());
        admin.sellerConfirmation(true,null);
        assertEquals("seller",store.login("sel1", "123").getKey());
        Seller seller = store.findSeller(store.login("sel1", "123").getValue());
        store.ban(seller.getUuid());
        assertEquals("banSeller",store.login("sel1","123").getKey());
        store.permit(seller.getUuid());
        assertEquals("seller",store.login("sel1","123").getKey());
    }
    @Test
    public void ordering(){
        store = new Store("www.OXDCommod.com",123123,"OXD-Seyed",43.78,-0.7);
        Admin.setStatics();
        store.addUser("u1","123",123,"shahr babak","@");
        store.addSeller("sel1","123","@","inc");
        store.addAdmin("ad1","123","@");
        User user = store.findUser(store.login("u1","123").getValue());
        Admin admin = store.findAdmin(store.login("ad1","123").getValue());
        admin.sellerConfirmation(true,null);
        Seller seller = store.findSeller(store.login("sel1", "123").getValue());
        SubCategory cat = new SubCategory("Tom",1000,5,"very bad","   ",seller.getUuid(),"Pet","Cat");
        store.sendSellerOrder(seller.getUuid(),cat);
        assertEquals(cat,seller.getWaitProduct(cat.getUuid()));
        admin.checkSellerReq(false,new Pair<>(seller.getUuid(),cat.getUuid()),null);
        cat.setBrand(":)");
        store.sendSellerOrder(seller.getUuid(),cat);
        admin.checkSellerReq(true,new Pair<>(seller.getUuid(),cat.getUuid()),null);
        assertEquals(cat,store.findProduct(cat.getUuid()));
        user.addCart(cat.getUuid());
        user.addCart(cat.getUuid());
        user.addCart(cat.getUuid());
        assertEquals(10000+(int) (1.1*3*1000) ,user.getTotalPriceOfCart());
        store.addWallet(new Pair<>(user.getUuid(),user.getTotalPriceOfCart()));
        admin.checkWalletReq(true,admin.walletRequestUUID(),null);
        assertEquals(10000+ (1.1*3*1000),user.getWallet());
        store.sendOrder(user.getUuid());
        assertEquals("",user.isOrderOK());
        admin.checkOrderUser(true,admin.orderRequestUUID(),null);
        assertEquals(0.0,user.getWallet());
        assertEquals(10000,user.getTotalPriceOfCart());
        assertEquals(3*1000,(int)seller.getWallet());
        assertEquals(300,(int)store.getProfit());
        assertEquals(2,cat.getAmount());
        assertEquals(2,seller.getSellerLevel());
        store.addSubscriptions(user.getUuid());
        admin.checkSubscriptionReq(true,admin.subscriptionRequestUUID(),null);
        assertTrue(user.getSubscription());
        user.addCart(cat.getUuid());
        user.addCart(cat.getUuid());
        assertEquals( (int)(1.1*2*1000),user.getTotalPriceOfCart());
        store.addWallet(new Pair<>(user.getUuid(),user.getTotalPriceOfCart()));
        admin.checkWalletReq(true,admin.walletRequestUUID(),null);
        store.sendOrder(user.getUuid());
        assertEquals("",user.isOrderOK());
        admin.checkOrderUser(true,admin.orderRequestUUID(),null);
        assertEquals(0.0,user.getWallet());
        assertEquals(0,user.getTotalPriceOfCart());
        assertEquals(5*1000,(int)seller.getWallet());
        assertEquals(500,(int)store.getProfit());
        assertEquals(0,cat.getAmount());
        assertEquals(2,seller.getSellerLevel());
        assertEquals(1.1*5*10,user.getUserXp()*1.0);
    }
    @Test
    public void refunding()
    {
        store = new Store("www.OXDCommod.com",123123,"OXD-Seyed",43.78,-0.7);
        Admin.setStatics();
        store.addUser("u1","123",123,"shahr babak","@");
        store.addSeller("sel1","123","@","inc");
        store.addAdmin("ad1","123","@");
        User user = store.findUser(store.login("u1","123").getValue());
        Admin admin = store.findAdmin(store.login("ad1","123").getValue());
        admin.sellerConfirmation(true,null);
        Seller seller = store.findSeller(store.login("sel1", "123").getValue());
        SubCategory cat = new SubCategory("Tom",1000,5,"very bad","   ",seller.getUuid(),"Pet","Cat");
        store.sendSellerOrder(seller.getUuid(),cat);
        admin.checkSellerReq(false,new Pair<>(seller.getUuid(),cat.getUuid()),null);
        cat.setBrand(":)");
        store.sendSellerOrder(seller.getUuid(),cat);
        admin.checkSellerReq(true,new Pair<>(seller.getUuid(),cat.getUuid()),null);
        user.addCart(cat.getUuid());
        user.addCart(cat.getUuid());
        user.addCart(cat.getUuid());
        store.addWallet(new Pair<>(user.getUuid(),user.getTotalPriceOfCart()));
        admin.checkWalletReq(true,admin.walletRequestUUID(),null);
        store.sendOrder(user.getUuid());
        admin.checkOrderUser(true,admin.orderRequestUUID(),null);
        store.addSubscriptions(user.getUuid());
        admin.checkSubscriptionReq(true,admin.subscriptionRequestUUID(),null);
        user.addCart(cat.getUuid());
        user.addCart(cat.getUuid());
        store.addWallet(new Pair<>(user.getUuid(),user.getTotalPriceOfCart()));
        admin.checkWalletReq(true,admin.walletRequestUUID(),null);
        store.sendOrder(user.getUuid());
        admin.checkOrderUser(true,admin.orderRequestUUID(),null);
        UUID order1 = null,order2=null;
        for (UUID uuid : user.getOrders()) {
            if(order1==null)
                order1 = uuid;
            else
                order2=uuid;
        }
        store.addRefundReq(new Pair<>(user.getUuid(),order1));
        admin.checkRefundReq(true,new Pair<>(user.getUuid(),order1),null);
        assertEquals(1.1*2*1000,user.getWallet());
        assertEquals(3*1000,(int)seller.getWallet());
        assertEquals(300,(int)store.getProfit());
        assertEquals(2,cat.getAmount());
        store.addRefundReq(new Pair<>(user.getUuid(),order2));
        admin.checkRefundReq(true,new Pair<>(user.getUuid(),order2),null);
        assertEquals(1.1*5*1000,user.getWallet());
        assertEquals(0.0,seller.getWallet());
        assertEquals(0.0,store.getProfit());
        assertEquals(5,cat.getAmount());
        assertEquals(0,user.getUserXp());
        store.removeProduct(cat.getUuid());
        assertFalse(store.isProductExist(cat.getUuid()));
    }
    @Test
    public void rateComment()
    {
        store = new Store("www.OXDCommod.com",123123,"OXD-Seyed",43.78,-0.7);
        Admin.setStatics();
        store.addUser("u1","123",123,"shahr babak","@");
        store.addUser("u2","123",123,"zanjan","@");
        store.addSeller("sel1","123","@","inc");
        store.addAdmin("ad1","123","@");
        User user1 = store.findUser(store.login("u1","123").getValue());
        User user2 = store.findUser(store.login("u2","123").getValue());
        Admin admin = store.findAdmin(store.login("ad1","123").getValue());
        admin.sellerConfirmation(true,null);
        Seller seller = store.findSeller(store.login("sel1", "123").getValue());
        SubCategory cat = new SubCategory("Tom",1000,5,"very bad","   ",seller.getUuid(),"Pet","Cat");
        store.sendSellerOrder(seller.getUuid(),cat);
        admin.checkSellerReq(true,new Pair<>(seller.getUuid(),cat.getUuid()),null);
        cat.setRate(5,user1.getUuid());
        assertTrue(cat.didRate(user1.getUuid()));
        cat.setRate(9,user2.getUuid());
        assertEquals(7.0,cat.getRate());
        cat.changeRate(3,user1.getUuid());
        assertEquals(3.0,cat.getUserRate(user1.getUuid()));
        assertEquals(6.0,cat.getRate());
        assertEquals(10,user1.getUserXp());
        cat.removeRate(user1.getUuid());
        assertEquals(9.0,cat.getRate());
        assertEquals(0,user1.getUserXp());
        cat.setComment("good",user1.getUuid());
        assertEquals("good",cat.getComment(user1.getUuid()));
        assertTrue(cat.didComment(user1.getUuid()));
        cat.setComment("bad",user1.getUuid());
        assertEquals("bad",cat.getComment(user1.getUuid()));
        assertEquals(10,user1.getUserXp());
        cat.removeComment(user1.getUuid());
        assertFalse(cat.didComment(user1.getUuid()));
        assertEquals(0,user1.getUserXp());
    }
}