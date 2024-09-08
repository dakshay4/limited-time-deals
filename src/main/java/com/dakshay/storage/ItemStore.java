package com.dakshay.storage;

import com.dakshay.db.Deal;
import com.dakshay.db.Item;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ItemStore {


    private static ItemStore INSTANCE = null;
    private final Map<String, Item> items;

    private ItemStore(){
        this.items = new ConcurrentHashMap<>();
    }


    public static ItemStore getInstance() {
        if(INSTANCE == null) {
            INSTANCE  = new ItemStore();
        }
        return INSTANCE;
    }

    public Item createItem(Item item){
        items.put(item.getId(), item);
        System.out.println("Added Item with Id: " + item.getId() + " and Name : " + item.getName());
        return item;
    }


    public Item getItem(String itemId) {
        return items.get(itemId);
    }
}
