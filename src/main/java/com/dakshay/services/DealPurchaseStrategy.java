package com.dakshay.services;

import com.dakshay.db.Deal;
import com.dakshay.db.DealTransaction;
import com.dakshay.db.User;
import com.dakshay.storage.DealStore;
import com.dakshay.storage.DealTransactionStore;

public abstract class DealPurchaseStrategy {

    public DealPurchaseStrategy(DealTransactionStore dealTransactionStore) {
        this.dealTransactionStore = dealTransactionStore;
    }

    public final DealTransactionStore dealTransactionStore;

    public boolean claimDeal(String userId, String dealId){
        validateStrategy(userId,dealId);
        DealTransaction dealTransaction = new DealTransaction(userId, dealId);
        dealTransactionStore.createDealTransaction(dealTransaction);
        return false;
    }

    public abstract boolean validateStrategy(String user, String deal);

}
