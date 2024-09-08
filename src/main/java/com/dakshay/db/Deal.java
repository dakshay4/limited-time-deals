package com.dakshay.db;


import lombok.Data;

import java.util.UUID;

@Data
public class Deal extends BaseBean{


    private String itemId; //Weak Association Aggregation
    private int quantity;
    private int endTime; // In Minutes

    public Deal(String itemId, int quantity, int endTime) {
        super(UUID.randomUUID().toString(), System.currentTimeMillis());
        this.itemId = itemId;
        this.quantity = quantity;
        this.endTime = endTime;
    }
}
