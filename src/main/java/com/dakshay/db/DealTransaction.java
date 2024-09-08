package com.dakshay.db;


import lombok.Data;

import java.util.UUID;

@Data
public class DealTransaction extends BaseBean{

    private String userId;
    private String dealId;


    public DealTransaction(String userId, String dealId) {
        super(UUID.randomUUID().toString(), System.currentTimeMillis());
        this.userId = userId;
        this.dealId = dealId;
    }
}
