package com.hong.design.builder.builder;

import com.hong.design.builder.bean.Reservation;
import com.hong.design.builder.exception.BuilderException;

/**
 * Created by Hongwei on 2015/11/26.
 */
public class UnforgivingBuilder extends AbstractReservationBuilder {
    @Override
    public Reservation build() throws BuilderException {
        if (date == null)
            throw new BuilderException("Valid date not null");
        if (city == null)
            throw new BuilderException("Valid city not null");
        if (headcount < MINHEAD)
            throw new BuilderException("Mininum headcount is " + MINHEAD);
        if (dollarsPerHead * headcount < MINTOTAL)
            throw new BuilderException("Mininum total is " + MINTOTAL);

        return new Reservation(date, headcount, city, dollarsPerHead, hasSite);
    }
}
