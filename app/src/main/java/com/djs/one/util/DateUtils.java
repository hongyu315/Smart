package com.djs.one.util;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {


    public static long strToLong(String format, String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new SimpleDateFormat(format).parse(str));
            return c.getTimeInMillis();
        }catch (Exception e) {

        }
        return 0;
    }

    public static String longToFormatterDate(long value) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(value);
        return dateString;
    }
}
