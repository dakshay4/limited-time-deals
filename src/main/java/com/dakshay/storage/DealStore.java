package com.dakshay.storage;

import com.dakshay.db.Deal;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DealStore {


    private static DealStore INSTANCE = null;
    private Map<String, Deal> deals;

    private DealStore(){
        this.deals = new ConcurrentHashMap<>();
    }


    public static DealStore getInstance() {
        if(INSTANCE == null) {
            INSTANCE  = new DealStore();
        }
        return INSTANCE;
    }

    public Deal createDeal(Deal deal){
        deals.put(deal.getId(), deal);
        System.out.println("Added Deal with Id: " + deal.getId() + " and Item id : " + deal.getItemId());
        return deal;
    }


    public Deal getDeal(String dealId) {
        return deals.get(dealId);
    }
}
