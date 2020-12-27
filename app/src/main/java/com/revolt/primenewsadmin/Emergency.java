package com.revolt.primenewsadmin;

public class Emergency {
    String name,place,phn,id;

    public Emergency(){

    }

    public Emergency(String name, String place, String phn, String id) {
        this.name = name;
        this.place = place;
        this.phn = phn;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getPhn() {
        return phn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
