package com.revolt.primenewsadmin;

public class Blood {

    String name,place,num,id;

    public Blood(){

    }

    public Blood(String name, String place, String num, String id) {
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
}
