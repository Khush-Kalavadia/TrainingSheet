package com.java.concurrency;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil
{
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    private static Calendar cal;

    public static final String showTime()
    {
        try
        {
            cal = Calendar.getInstance();

            return sdf.format(cal.getTime());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
