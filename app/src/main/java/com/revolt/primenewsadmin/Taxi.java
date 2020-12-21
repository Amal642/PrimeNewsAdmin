package com.revolt.primenewsadmin;

import com.google.firebase.database.Exclude;

public class Taxi {

    private String name;
    private String key;
    private int position;
    public Taxi() {
        //empty constructor needed
    }
    public Taxi (int position){
        this.position = position;
    }
    public Taxi(String name) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        this.name = name;

    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}
