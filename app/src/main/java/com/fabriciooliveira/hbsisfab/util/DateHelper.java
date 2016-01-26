package com.fabriciooliveira.hbsisfab.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fabriciooliveira on 1/26/16.
 */
public class DateHelper {

    public static String formatarDataLonga(long longValue) {
        Date date=new Date(longValue);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return simpleDateFormat.format(date);
    }

    public static Date formatarStringParaData(String data)  {
        DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        try {
            return (Date)formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
