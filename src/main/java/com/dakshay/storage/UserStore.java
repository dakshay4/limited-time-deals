package com.dakshay.storage;

import com.dakshay.db.Item;
import com.dakshay.db.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserStore {


    private static UserStore INSTANCE = null;
    private final Map<String, User> items;

    private UserStore(){
        this.items = new ConcurrentHashMap<>();
    }


    public static UserStore getInstance() {
        if(INSTANCE == null) {
            INSTANCE  = new UserStore();
        }
        return INSTANCE;
    }

    public User createUser(User user){
        items.put(user.getId(), user);
        System.out.println("Added User with Id: " + user.getId() + " and Name : " + user.getName());
        return user;
    }


    public User getUser(String userId) {
        return items.get(userId);
    }
}
