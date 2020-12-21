package com.revolt.primenewsadmin;

public class AutoStatDetails {

    String busname,bservice,btime;
    String id;
    public AutoStatDetails(){

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

    public AutoStatDetails(String busname, String bservice, String btime, String id) {

        this.busname = busname;
        this.bservice = bservice;
        this.btime = btime;
        this.id = id;
    }

}
