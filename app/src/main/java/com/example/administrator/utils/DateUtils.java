package com.example.administrator.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2018/4/30 0030.
 */

public class DateUtils {
    public static String getDateString(String pattern) {

        SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dateFormat.applyPattern(pattern);
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }
}
