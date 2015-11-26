package com.hong.design.builder.builder;

import com.hong.design.builder.bean.Reservation;
import com.hong.design.builder.exception.BuilderException;

import java.util.Date;

/**
 * bean构建抽象类
 * Created by Hongwei on 2015/11/26.
 */
public abstract class AbstractReservationBuilder {
    protected static final int MINHEAD = 25;           // 最少观看人数
    protected static final double MINTOTAL = 495.95;   // 最少总费用

    protected Date date;                  // 表演时间
    protected int headcount;              // 场地容纳总人数
    protected String city;                // 表演城市
    protected double dollarsPerHead;      // 每个人的费用
    protected boolean hasSite;            // 是否有地点

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

    public abstract Reservation build() throws BuilderException;
}
