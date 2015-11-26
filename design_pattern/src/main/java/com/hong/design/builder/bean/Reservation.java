package com.hong.design.builder.bean;

import java.util.Date;

/**
 * 表演预订
 * Created by Hongwei on 2015/11/26.
 */
public class Reservation {
    private Date date;                  // 表演时间
    private int headcount;              // 场地容纳总人数
    private String city;                // 表演城市
    private double dollarsPerHead;      // 每个人的费用
    private boolean hasSite;            // 是否有地点

    public Reservation(Date date, int headcount, String city, double dollarsPerHead, boolean hasSite) {
        this.date = date;
        this.headcount = headcount;
        this.city = city;
        this.dollarsPerHead = dollarsPerHead;
        this.hasSite = hasSite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHeadcount() {
        return headcount;
    }

    public void setHeadcount(int headcount) {
        this.headcount = headcount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getDollarsPerHead() {
        return dollarsPerHead;
    }

    public void setDollarsPerHead(double dollarsPerHead) {
        this.dollarsPerHead = dollarsPerHead;
    }

    public boolean isHasSite() {
        return hasSite;
    }

    public void setHasSite(boolean hasSite) {
        this.hasSite = hasSite;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "date=" + date +
                ", headcount=" + headcount +
                ", city='" + city + '\'' +
                ", dollarsPerHead=" + dollarsPerHead +
                ", hasSite=" + hasSite +
                '}';
    }
}
