package com.baicai.util;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.util.HtmlUtils;

public class JspUtil {
	public static String encode(String param){  
        try {
			return URLEncoder.encode(param,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return param;
		} 
    }
	
	/**
	 * 根据相对时间戳算出天数
	 * @param time
	 * @return
	 */
	public static Integer day(Integer time){
		int day=(int) Math.floor(time/86400);
		return day;
	}
	
	/**
	 * 获取相对时间，针对现在的时间戳
	 * @param time
	 * @return
	 */
	public static Integer rday(Integer time){
		int day=(int) Math.floor((time-CommonUtil.getTime())/86400);
		return day;
	}
	
	public static BigDecimal scale(BigDecimal b1,BigDecimal b2){
		return b1.divide(b2,5).multiply(BigDecimal.valueOf(100));
	}
	
	
	public static String time2Date(Integer time,String format){
		if(time==null) return "";
		String date="";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		date = sdf.format(new Date(time*1000l));
		return date; 
	}
	
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
	 * 调用SpringMVC自带的过滤函数
	 * @param input
	 * @return
	 */
	public static String htmlEscape(String input){
		return HtmlUtils.htmlEscape(input);
	}
	
	public static Integer timestams(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return (int) (calendar.getTime().getTime()/1000l);
	}

}
