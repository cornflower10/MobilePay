package com.qingmang.baselibrary.utils;

import android.text.TextUtils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by caomingyu on 2016/11/30.
 */
public class DateUtil {

    /**
     * 知道某年的第N天，求出该天是几月几号
     * @param dayOfYear
     * @return
     */
    public static String fromDayOfYearGetDate(float dayOfYear) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_YEAR, (int) dayOfYear);
        Format f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(c.getTime());
    }

    /**
     * 求给定日期是第几天
     * @param dateStr
     * @param dateFormart
     * @return
     */
    public static float getDayOfYear(String dateStr,String dateFormart) {
        Calendar calendar = Calendar.getInstance();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormart);
            Date date = simpleDateFormat.parse(dateStr);
            calendar.setTime(date);
        } catch (Exception e) {
            LogManager.e(e.getMessage());
        }
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 当前时间前n个月日期yyyy-MM-dd
     * @param monthAgo
     * @return
     */
    public static String getAppointMonthAgoDate(int monthAgo) {
        Date dNow = new Date();   //当前时间
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.MONTH, monthAgo);  //设置为前3月
        return dateToString("yyyy-MM-dd",calendar.getTime());
    }

    /**
     * Date转指定格式String
     * @param dateFormat
     * @param date
     * @return
     */
    public static String dateToString(String dateFormat,Date date) {
        SimpleDateFormat sdf=new SimpleDateFormat(dateFormat); //设置时间格式
        String defaultEndDate = sdf.format(date); //格式化当前时间
        return defaultEndDate;
    }

    /**
     * 获取当前日期
     * @return
     */

    public static String getDate() {
        Calendar c = Calendar.getInstance();//
        return c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH) + 1)+"-"+c.get(Calendar.DAY_OF_MONTH); // 获取当前年份

    }

    /**
     * 获取当前年
     * @return
     */

    public static String getYear() {
        Calendar c = Calendar.getInstance();//
        return String.valueOf(c.get(Calendar.YEAR));// 获取当前年份

    }


    public static int iGetYear() {
        Calendar c = Calendar.getInstance();//
        return c.get(Calendar.YEAR);// 获取当前年份

    }

    public static int iGetMonth() {
        Calendar c = Calendar.getInstance();//
        return c.get(Calendar.MONTH)+1;//

    }

    public static int iGetMonthOfDay() {
        Calendar c = Calendar.getInstance();//
        return c.get(Calendar.DAY_OF_MONTH);//

    }

    public static String getMonthAndDay() {

            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
            String dateString = formatter.format(currentTime);
            return dateString;
    }


    public static String getYesterDay(){
        Date d=new Date(System.currentTimeMillis()-1000*60*60*24);
        SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
        return sp.format(d);
    }

    public static String chFormatEN(String date){
        String strDate = "";
        if(TextUtils.isEmpty(date))
            return "";
        else {
          String year =  date.replace("年","-");
            String mounth = year.replace("月","-");
            strDate =  mounth.replace("日","");
        return formatDate(strDate);

        }
    }



    public static String formatDate(String date){
        String [] dates = date.split("-");
        String m = dates[1];
        String d = dates[2];
        if((!m.startsWith("0"))&&Integer.valueOf(m)<10){
            m = "0"+m;
        }
        if((!d.startsWith("0"))&&Integer.valueOf(d)<10){
            d="0"+d;
        }
        return dates[0]+"-"+m+"-"+d;
    }

    public static String validateIdCard(String date){

        if(TextUtils.isEmpty(date))
            return "";
        else {
           String[] str =  date.split("-");
            if(str.length==2){
             return strFormatEN(str[1]);
            }

        }
        return "";
    }

    public static String strFormatEN(String date){
        String strDate = "";
        if(TextUtils.isEmpty(date))
            return "";
        else {
            strDate = date.replace(".","-");
        }
          return formatDate(strDate);
    }
}
