package com.mh.web.security.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeGet {

    public static String getCurrentTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

}
