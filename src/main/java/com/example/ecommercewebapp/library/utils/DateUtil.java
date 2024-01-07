package com.example.ecommercewebapp.library.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private DateUtil() {
    }
    public static Date trim(Date date){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }
    public static Date getNightTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        return calendar.getTime();
    }

    public static String dateToLocalDateString(Date date) {
        return date != null ? new java.sql.Date(date.getTime()).toLocalDate().toString() : null;
    }
}
