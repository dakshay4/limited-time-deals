package com.dakshay;

import com.dakshay.db.Deal;
import com.dakshay.db.Item;
import com.dakshay.db.User;
import com.dakshay.services.OneTimeDealPurchaseStrategy;
import com.dakshay.storage.DealStore;
import com.dakshay.storage.DealTransactionStore;
import com.dakshay.storage.ItemStore;
import com.dakshay.storage.UserStore;

public class Main {


    public static void main(String[] args) throws InterruptedException {

            DealTransactionStore dealTransactionStore = DealTransactionStore.getInstance();
            DealStore dealStore = DealStore.getInstance();
            ItemStore itemStore = ItemStore.getInstance();
            UserStore userStore = UserStore.getInstance();

            User user1 = userStore.createUser(new User("Dakshay", 9799477161L,"dakshay@outlook.in"));
            User user2 = userStore.createUser(new User("Nikhil", 979922222L,"nikhil@outlook.in"));

            Item item1 = itemStore.createItem(new Item("Bosch Washing Machine"));
            Item item2 =  itemStore.createItem(new Item("Apple Iphone 16"));

            Deal deal1 = dealStore.createDeal(new Deal(item1.getId(), 2, 2));
            Deal deal2 = dealStore.createDeal(new Deal(item2.getId(), 1, 5));
            Deal deal3 = dealStore.createDeal(new Deal(item2.getId(), 1, 1));


            OneTimeDealPurchaseStrategy oneTimeDealPurchaseStrategy = new OneTimeDealPurchaseStrategy(dealTransactionStore, dealStore);

            boolean isClaimedUser1Deal1 = oneTimeDealPurchaseStrategy.claimDeal(user1.getId(), deal1.getId());
            boolean isClaimedUser1Deal1Again = oneTimeDealPurchaseStrategy.claimDeal(user1.getId(), deal1.getId());//Same User Tries for Getting the Deal
            boolean isClaimedUser2Deal1 = oneTimeDealPurchaseStrategy.claimDeal(user2.getId(), deal1.getId());//Same User Tries for Getting the Deal

            boolean isClaimedUser1Deal2 = oneTimeDealPurchaseStrategy.claimDeal(user1.getId(), deal2.getId());
            boolean isClaimedUser2Deal2 = oneTimeDealPurchaseStrategy.claimDeal(user2.getId(), deal2.getId());

            Thread.sleep(61000);
            boolean isClaimedUser2Deal3 = oneTimeDealPurchaseStrategy.claimDeal(user1.getId(), deal3.getId());


        System.out.println( "The Deal Result when User 1 Tried to Claim Deal 1 : " + isClaimedUser1Deal1);
        System.out.println( "The Deal Result when User 1 Tried to Claim Deal 1 Again : " + isClaimedUser1Deal1Again);
        System.out.println( "The Deal Result when User 2 Tried to Claim Deal 1 : " + isClaimedUser2Deal1);
        System.out.println( "The Deal Result when User 1 Tried to Claim Deal 2 : " + isClaimedUser1Deal2);
        System.out.println( "The Deal Result when User 2 Tried to Claim Deal 2 : " + isClaimedUser2Deal2);
        System.out.println( "The Deal Result when User 2 Tried to Claim Deal 3 : " + isClaimedUser2Deal3);
    }
}