package com.revolt.primenewsadmin;

public class Feedback {

    String name,text,id;

    public Feedback(){

    }

    public Feedback(String name, String text, String id) {
        this.name = name;
        this.text = text;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }
}
