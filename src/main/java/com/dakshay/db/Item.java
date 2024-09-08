package com.dakshay.db;


import lombok.Data;

import java.util.UUID;

@Data
public class Item extends BaseBean{

    private String name;

    public Item(String name) {
        super(UUID.randomUUID().toString(), System.currentTimeMillis());
        this.name = name;
    }
}
