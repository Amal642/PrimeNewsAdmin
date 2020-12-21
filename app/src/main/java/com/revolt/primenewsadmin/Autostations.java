package com.revolt.primenewsadmin;

import com.google.firebase.database.Exclude;

public class Autostations {
    private String name;
    private String key;
    private int position;
    public Autostations() {
        //empty constructor needed
    }
    public Autostations (int position){
        this.position = position;
    }
    public Autostations(String name) {
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
