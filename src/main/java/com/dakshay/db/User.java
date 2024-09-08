package com.dakshay.db;


import lombok.Data;

import java.util.UUID;

@Data
public class User extends BaseBean{

    private String name;
    private Long phoneNo;
    private String email;

    public User(String name, Long phoneNo, String email) {
        super(UUID.randomUUID().toString(), System.currentTimeMillis());
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
    }
}
