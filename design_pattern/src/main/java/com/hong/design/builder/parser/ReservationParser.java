package com.hong.design.builder.parser;

import com.hong.design.builder.builder.AbstractReservationBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 构建向导
 * Created by Hongwei on 2015/11/26.
 */
public class ReservationParser {
    private AbstractReservationBuilder builder;

    public ReservationParser(AbstractReservationBuilder builder) {
        this.builder = builder;
    }

    public void parse(String s) throws ParseException {
        String[] tokens = s.split(", *");
        for (int i = 0; i < tokens.length; i += 2) {
            String type = tokens[i];
            String val = tokens[i + 1];

            if ("date".compareToIgnoreCase(type) == 0) {
                Calendar now = Calendar.getInstance();
                DateFormat formatter = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH);
                Date d = formatter.parse(val + ", " + now.get(Calendar.YEAR));
                builder.setDate(d);
            } else if ("headcount".compareToIgnoreCase(type) == 0)
                builder.setHeadcount(Integer.parseInt(val));
            else if ("city".compareToIgnoreCase(type) == 0)
                builder.setCity(val);
            else if ("dollarsPerHead".compareToIgnoreCase(type) == 0)
                builder.setDollarsPerHead(Double.parseDouble(val));
            else if ("hasSite".compareToIgnoreCase(type) == 0)
                builder.setHasSite(val.equalsIgnoreCase("true"));
        }
    }
}
