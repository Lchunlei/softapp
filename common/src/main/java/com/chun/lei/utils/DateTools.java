package com.chun.lei.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Created by lcl on 2020/5/1 0001
 */
public class DateTools {
    private static final String SORT_DATA_STR = "yyyy-MM-dd";
    private static final String LONG_DATA_STR = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_STR = "HH";

    public static String getIsSortDate(Date date,boolean bo){
        if(bo){
            SimpleDateFormat format = new SimpleDateFormat(SORT_DATA_STR);
            return format.format(date);
        }else {
            SimpleDateFormat format = new SimpleDateFormat(LONG_DATA_STR);
            return format.format(date);
        }
    }
    //早八点到晚八点判断
    public static Boolean isShowMv(){
        SimpleDateFormat format = new SimpleDateFormat(TIME_STR);
        Integer hh = Integer.parseInt(format.format(new Date()));
        if(hh>7&&hh<19){
            return false;
        }else {
            return true;
        }
    }

}
