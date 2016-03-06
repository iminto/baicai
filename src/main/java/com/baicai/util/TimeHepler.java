package com.baicai.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @Description: 时间助手类
* @author 猪肉有毒 waitfox@qq.com  
* @date 2015年8月9日 下午8:18:57 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
public class TimeHepler {
	/**
	 * 获取当前时间戳
	 * @return int
	 */
	public static int getUnixTime(){
		return (int) (System.currentTimeMillis()/1000);
	}
	
	/**
	 * 将时间字符串解析为时间戳
	 * @param dateStr 时间，如 2015-07-22
	 * @param format 格式，如 yyyy-MM-dd
	 * @return
	 */
	public static Integer Date2Time(String dateStr,String format){ 
        Date date = new Date();   
        DateFormat sdf = new SimpleDateFormat(format);  
        try {  
            date = sdf.parse(dateStr);  
           return (int) (date.getTime()/1000);  
        } catch (Exception e) {  
            return null; 
        }  
	}
	
	/**
	 * 时间戳转换为时间字符串
	 * @param time
	 * @param format
	 * @return
	 */
	public String  Time2Str(int time,String format){
		if(format==null){
			format="yyyy/MM/dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date = sdf.format(new Date(time*1000));
		return date;
	}
	
	/** 
     * 将一个字符串转换为指定的日期格式，返回Date类型对象 
     * @param dateStr 日期字符串 
     * @param format 日期格式 
     * @return Date类型对象 
     */  
	public static Date stringToDate(String dateStr,String format){  
        Date date = new Date();    
        //注意format的格式要与日期String的格式相匹配    
        DateFormat sdf = new SimpleDateFormat(format);    
        try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return date;
		}    
    }
	
	/** 
     * 将一个Date类型对象转换为指定格式的字符串 
     * @param date Date类型对象 
     * @param format 指定的字符串格式 
     * @return String字符串 
     */  
	public static String dateToString(Date date,String format){  
        DateFormat sdf = new SimpleDateFormat(format);    
        return sdf.format(date);    
    }  
}
