package com.hong.design.builder;

import com.hong.design.builder.bean.Reservation;
import com.hong.design.builder.builder.AbstractReservationBuilder;
import com.hong.design.builder.builder.UnforgivingBuilder;
import com.hong.design.builder.parser.ReservationParser;

/**
 * 构建者模式的意图是将类的构建逻辑转移到类的实例化外部
 *
 * 客户端类
 *
 * Created by Hongwei on 2015/11/26.
 */
public class Client {
    public static void main(String[] args) {
        String sample = "Date, Nov 27, Headcount, 250, City, Springfield, DollarsPerHead, 9.95, HasSite, False";
        AbstractReservationBuilder builder = new UnforgivingBuilder();
        try {
            new ReservationParser(builder).parse(sample);
            Reservation reservation = builder.build();
            System.out.println("Unforgiving builder: " + reservation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
