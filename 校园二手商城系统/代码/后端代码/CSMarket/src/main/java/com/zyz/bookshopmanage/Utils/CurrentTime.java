package com.zyz.bookshopmanage.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 静小文
 * @version 1.0
 * @data 2022/9/9 10:36
 */
public class CurrentTime {
    public static String getCurrentTime() {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleFormatter.format(new Date());
        return time;
    }
}
