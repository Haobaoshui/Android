package xyz.hbs.huarongdao.utility;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeSql {
    public static Timestamp Now() {
        Date date = new Date();
        Timestamp tt = new Timestamp(date.getTime());
        return tt;
    }

    public static Timestamp GetDate(Date date) {
        Timestamp tt = new Timestamp(date.getTime());
        return tt;
    }

    public static Date GetDate(Timestamp ts) {
        return ts;
    }


    public static Date GetDate(String strdate) {

        if (strdate == null)
            return new Date();

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return df1.parse(strdate);


        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Timestamp GetDateTime(String strdate) {

        if (strdate == null)
            return Now();

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = df1.parse(strdate);
            Timestamp tt = new Timestamp(date.getTime());
            return tt;

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Timestamp GetDateTimeNotIncludingSecond(String strdate) {

        if (strdate == null)
            return Now();

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date;
        try {
            date = df1.parse(strdate);
            Timestamp tt = new Timestamp(date.getTime());
            return tt;

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
