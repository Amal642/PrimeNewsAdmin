package com.revolt.primenewsadmin;

public class BusStatDetails {

    String busname,bservice,btime,bclass,broute;
    String id;

    public BusStatDetails() {

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

    public String getBclass() {
        return bclass;
    }

    public String getBroute() {
        return broute;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BusStatDetails(String busname, String bservice, String btime, String bclass, String broute, String id) {
        this.busname = busname;
        this.bservice = bservice;
        this.btime = btime;
        this.bclass = bclass;
        this.broute = broute;
        this.id = id;
    }

}
