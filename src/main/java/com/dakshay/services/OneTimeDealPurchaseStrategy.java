package com.dakshay.services;

import com.dakshay.db.Deal;
import com.dakshay.db.DealTransaction;
import com.dakshay.db.User;
import com.dakshay.storage.DealStore;
import com.dakshay.storage.DealTransactionStore;

import java.util.List;

public class OneTimeDealPurchaseStrategy extends DealPurchaseStrategy{

    private final DealStore dealStore;

    public OneTimeDealPurchaseStrategy(DealTransactionStore dealTransactionStore, DealStore dealStore) {
        super(dealTransactionStore);
        this.dealStore = dealStore;
    }

    @Override
    public boolean claimDeal(String userId, String dealId) {
        boolean validated = validateStrategy(userId, dealId);
        if(validated) {
            DealTransaction dealTransaction = new DealTransaction(userId, dealId);
            return dealTransactionStore.createDealTransaction(dealTransaction);
        }
        return false;
    }

    @Override
    public boolean validateStrategy(String userId, String dealId) {
       Deal deal = dealStore.getDeal(dealId);
       if( (deal.getCreatedAt() + (long) deal.getEndTime() *60*1000) < System.currentTimeMillis()) {
           System.out.println("Deal has been ended");
           return false;
       }

       List<DealTransaction> dealTransactions = dealTransactionStore.getDealTransactions(dealId);
       if(deal.getQuantity()<=dealTransactions.size()){
           System.out.println("Deal Item is Out of Stock.");
           return false;
       }

       if(dealTransactions.stream().anyMatch(dealTransaction -> userId.equals(dealTransaction.getUserId()))) {
           System.out.println("User has already Claimed the deal.");
           return false;
       }
       return true;

    }
}
