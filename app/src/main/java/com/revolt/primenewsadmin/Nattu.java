package com.revolt.primenewsadmin;

public class Nattu {

    String name,desc,phone,imageurl;

    public Nattu(){

    }

    public Nattu(String name, String desc, String phone, String imageurl) {
        this.name = name;
        this.desc = desc;
        this.phone = phone;
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}

