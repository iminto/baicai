package com.baicai.corewith.util;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
* @Description: 封装给JSP的自定义函数，当然现在不使用JSP模板引擎了
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年5月7日 下午2:45:38 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
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
