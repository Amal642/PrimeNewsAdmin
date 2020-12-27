package com.revolt.primenewsadmin;

public class Media {

    String name,place,num,id;

    public Media(){

    }

    public Media(String name, String place, String num, String id) {
        this.name = name;
        this.place = place;
        this.num = num;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getNum() {
        return num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
