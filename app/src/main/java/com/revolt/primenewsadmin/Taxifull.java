package com.revolt.primenewsadmin;

public class Taxifull {
    String busname,bservice,btime;
    String id;

    public Taxifull() {

    }

    public String getBusname() {
        return busname;
    }

    public String getBservice() {
        return bservice;
    }

    public String getBtime() {
        return btime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Taxifull(String busname, String bservice, String btime, String id) {
        this.busname = busname;
        this.bservice = bservice;
        this.btime = btime;
        this.id = id;
    }
}
