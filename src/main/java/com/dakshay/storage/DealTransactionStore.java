package com.dakshay.storage;

import com.dakshay.db.Deal;
import com.dakshay.db.DealTransaction;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DealTransactionStore {



    private static DealTransactionStore INSTANCE = null;
    private final Map<String, List<DealTransaction>> dealTransaction;
    private final Set<String> uniqieLock;

    private DealTransactionStore(){
        this.dealTransaction = new ConcurrentHashMap<>();
        this.uniqieLock = new HashSet<>();
    }


    public static DealTransactionStore getInstance() {

            if (INSTANCE == null) {
                INSTANCE = new DealTransactionStore();
            }
            return INSTANCE;

    }

    public boolean createDealTransaction(DealTransaction dealTransaction) {
        String userId = dealTransaction.getUserId();
        String dealId = dealTransaction.getDealId();
        if(uniqieLock.contains(uniqueKey(userId,dealId))) return false;
        else uniqieLock.add(uniqueKey(userId,dealId));
        List<DealTransaction> dealTransactions = this.dealTransaction.getOrDefault(dealTransaction.getDealId(),new ArrayList<>());
        dealTransactions.add(dealTransaction);
        this.dealTransaction.put(dealTransaction.getDealId(), dealTransactions);
        return true;
    }

    public List<DealTransaction> getDealTransactions(String dealId) {
        return dealTransaction.containsKey(dealId) ? dealTransaction.get(dealId) : new ArrayList<>();
    }

    public String uniqueKey(String userId, String dealId){
        return userId + "-" + dealId;
    }


}
