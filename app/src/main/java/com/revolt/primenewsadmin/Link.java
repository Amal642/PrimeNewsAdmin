package com.revolt.primenewsadmin;

public class Link {

    String text,link,id;

    public Link(){

    }

    public Link(String text, String link, String id) {
        this.text = text;
        this.link = link;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public String getId() {
        return id;
    }
}
