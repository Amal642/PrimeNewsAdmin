package com.revolt.primenewsadmin;

import com.google.firebase.database.Exclude;

public class News {
    private String name;
    private String imageURL;
    private String key;
    private String description;
    private String date;
    private int position;
    public News() {
        //empty constructor needed
    }
    public News (int position){
        this.position = position;
    }
    public News(String name, String imageUrl ,String Des,String date) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        this.name = name;
        this.imageURL = imageUrl;
        this.description = Des;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImageUrl() {
        return imageURL;
    }
    public void setImageUrl(String imageUrl) {
        this.imageURL = imageUrl;
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