package com.lancoo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
   // private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:fff");
    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static String format(Date date){
        return format.format(date);
    }

	public static String doFormatDate(java.sql.Date date, String timeFormt) {
		// TODO Auto-generated method stub
		return null;
	}

}
