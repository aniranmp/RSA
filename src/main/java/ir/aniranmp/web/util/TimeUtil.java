package ir.aniranmp.web.util;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.PersianCalendar;
import com.ibm.icu.util.ULocale;

import java.util.Date;

public class TimeUtil {

    public static final long TIME_UNIT_1_MIN = 60 * 1000; // 1 M

    public static long convertMinToMillis(long timeInMinute) {
        return timeInMinute * TIME_UNIT_1_MIN;
    }

    public static long nMinuteAfterNow(long timeInMinute) {
        return getNowTime() + convertMinToMillis(timeInMinute);
    }


    public static Date getNowTimeStamp() {
        return new Date();
    }

    public static long getNowTime() {
        return getNowTimeStamp().getTime();
    }

    public static Date getDateFromTime(long time) {
        return new Date(time);
    }

    public static String printDate(long time) {
        return printDate(getDateFromTime(time));
    }

    public static String printDate(Date date) {
        return date.toString();
    }

    public static String printPersianDate(long time) {
        return printPersianDate(getDateFromTime(time));
    }

    public static String printPersianDate(Date date) {
        DateFormat df = getPersianDate().getDateTimeFormat(DateFormat.FULL, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        return df.format(date);
    }


    public static PersianCalendar getPersianDate(Date date) {
        return new PersianCalendar(date);
    }

    public static PersianCalendar getPersianDate() {
        return new PersianCalendar();
    }
}