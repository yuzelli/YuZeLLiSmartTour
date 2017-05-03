package com.example.yuzelli.SmartTour.entity;

import java.io.Serializable;

/**
 * Created by 51644 on 2017/4/29.
 */

public class Gather implements Serializable {

    private int number;
    private String name;
    private String beginTime;
    private String endTime;
    private String loctionX;
    private String loctionY;
    private String price;
    private String phone;
    private String address;

    public Gather(int number, String name, String beginTime, String endTime, String loctionX, String loctionY, String price, String phone, String address) {
        this.number = number;
        this.name = name;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.loctionX = loctionX;
        this.loctionY = loctionY;
        this.price = price;
        this.phone = phone;
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLoctionX() {
        return loctionX;
    }

    public void setLoctionX(String loctionX) {
        this.loctionX = loctionX;
    }

    public String getLoctionY() {
        return loctionY;
    }

    public void setLoctionY(String loctionY) {
        this.loctionY = loctionY;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
