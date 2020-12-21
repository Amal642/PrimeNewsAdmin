package com.revolt.primenewsadmin;

public class Announcements {

    private String title;
    private String description;
    private String id;

    public Announcements(){

    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public Announcements(String title, String description, String id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }
}
