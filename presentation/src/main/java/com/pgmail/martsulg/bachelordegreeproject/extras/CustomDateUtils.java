package com.pgmail.martsulg.bachelordegreeproject.extras;

import android.annotation.SuppressLint;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by g_washingt0n on 20.05.2018.
 */
@SuppressLint("DefaultLocale")
public class CustomDateUtils {

    public static String millisToTime(long millis) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(millis);
//        String str = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
        @SuppressLint("DefaultLocale") String str = String.format("%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));

        return str;
    }

    public static String millisToDate(long millis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
//        String str = cal.get(Calendar.DAY_OF_MONTH) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR);
        return String.format("%02d.%02d", cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH)) + "." + cal.get(Calendar.YEAR);
    }

    public static long timeToMillis(int hours, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        return calendar.getTimeInMillis();
    }

    public static String timeToFormattedStr(int h, int m) {
        return String.format("%02d:%02d", h, m);
    }


}
