package com.zyz.bookshopmanage.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/10 15:56
 */
public class DateConversion {

    public static String timeConversion(String time) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date =  df.parse(time);
        String myTime = df.format(date);
        return myTime;

    }
}
