package com.revolt.primenewsadmin;

public class Nattu {

    String name,desc,phone,imageurl,sadhanam,alav,price,place,id;

    public Nattu(){

    }

    public Nattu(String name, String desc, String phone, String imageurl,String sadhanam,String alav,String price,String place,String id) {
        this.name = name;
        this.desc = desc;
        this.phone = phone;
        this.imageurl = imageurl;
        this.sadhanam = sadhanam;
        this.alav=alav;
        this.price=price;
        this.place=place;
        this.id=id;
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

    public String getSadhanam() {
        return sadhanam;
    }

    public void setSadhanam(String sadhanam) {
        this.sadhanam = sadhanam;
    }

    public String getAlav() {
        return alav;
    }

    public void setAlav(String alav) {
        this.alav = alav;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

