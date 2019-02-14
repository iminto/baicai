package com.baicai.corewith.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
* @Description: 时间助手类
* @author 猪肉有毒 waitfox@qq.com  
* @date 2015年8月9日 下午8:18:57 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
public class TimeHepler {

	// 日期时间类型格式
	private static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// 日期类型格式
	private static String DATE_FORMAT = "yyyy-MM-dd";
	// 时间类型的格式
	private static String TIME_FORMAT = "HH:mm:ss";
	
	//注意SimpleDateFormat不是线程安全的
    private static ThreadLocal<SimpleDateFormat> ThreadDateTime = new ThreadLocal<SimpleDateFormat>();
    private static ThreadLocal<SimpleDateFormat> ThreadDate     = new ThreadLocal<SimpleDateFormat>();
    private static SimpleDateFormat DateTimeInstance() {
        SimpleDateFormat df = ThreadDateTime.get();
        if (df == null) {
            df = new SimpleDateFormat(DATETIME_FORMAT);
            ThreadDateTime.set(df);
        }
        return df;
    }

    private static SimpleDateFormat DateInstance() {
        SimpleDateFormat df = ThreadDate.get();
        if (df == null) {
            df = new SimpleDateFormat(DATE_FORMAT);
            ThreadDate.set(df);
        }
        return df;
    }

	/**
	 * 获取当前时间戳
	 * 
	 * @return int
	 */
	public static int getUnixTime() {
		return (int) (System.currentTimeMillis() / 1000);
	}
	
	/**
     * 获取当前日期时间
     *
     * @return 返回当前时间的字符串值
     */
    public static String DateTime() {
        return DateTimeInstance().format(new Date());
    }
	/**
	 * 将时间字符串解析为时间戳
	 * 
	 * @param dateStr 时间，如 2015-07-22
	 * @param format 格式，如 yyyy-MM-dd
	 * @return
	 */
	public static int date2Time(String dateStr, String format) {
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat(format);
		try {
			date = sdf.parse(dateStr);
			return (int) (date.getTime() / 1000);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static int dateTime2int(String dateTime){
		try {
			return (int)DateTimeInstance().parse(dateTime).getTime()/1000;
		} catch (ParseException e) {
			return 0;
		}
	}

	/**
	 * 时间戳转换为时间字符串
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public String time2Str(int time, String format) {
		if (format == null) {
			format = DATETIME_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String date = sdf.format(new Date(time * 1000));
		return date;
	}

	/**
	 * 时间戳转换为字符串
	 * 
	 * @param time 时间戳
	 */
	public String time2Str(int time, int mode) {
		if (mode == 1) {
			return time2Str(time, DATE_FORMAT);
		} else if (mode == 2) {
			return time2Str(time, TIME_FORMAT);
		} else {
			return time2Str(time, DATETIME_FORMAT);
		}
	}

	/**
	 * 将一个字符串转换为指定的日期格式，返回Date类型对象
	 * 
	 * @param dateStr 日期字符串
	 * @param format 日期格式
	 * @return Date类型对象
	 */
	public static Date stringToDate(String dateStr, String format) {
		Date date = new Date();
		// 注意format的格式要与日期String的格式相匹配
		DateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return date;
		}
	}

	/**
	 * 将一个Date类型对象转换为指定格式的字符串
	 * 
	 * @param date Date类型对象
	 * @param format 指定的字符串格式
	 * @return String字符串
	 */
	public static String dateToString(Date date, String format) {
		DateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	 /**
     * 在当前时间的基础上加或减去几天
     *
     * @param day
     * @return
     */
    public static Date day(int day) {
        Calendar Cal = java.util.Calendar.getInstance();
        Cal.setTime(new Date());
        Cal.add(Calendar.DAY_OF_YEAR, day);
        return Cal.getTime();
    }
    
    /**
     * 在指定的时间上加或减去几天
     *
     * @param date
     * @param day
     * @return
     */
    public static Date day(Date date, int day) {
        Calendar Cal = java.util.Calendar.getInstance();
        Cal.setTime(date);
        Cal.add(Calendar.DAY_OF_YEAR, day);
        return Cal.getTime();
    }
    
    /**
     * 判断字符串是否为日期字符串
     *
     * @param date 日期字符串
     * @return true or false
     */
    public static boolean isDate(String date) {
        try {
            DateTimeInstance().parse(date);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 时间date1和date2的时间差-单位秒
     *
     * @param date1
     * @param date2
     * @return 秒
     */
    public static long Subtract(Date date1, Date date2) {
        long cha = (date2.getTime() - date1.getTime()) / 1000;
        return cha;
    }
    
    /**
     * 时间date1和date2的时间差-单位秒
     *
     * @param date1
     * @param date2
     * @return 秒
     */
    public static long Subtract(String date1, String date2) {
        long rs = 0;
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000;
            rs = cha;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    /**
     * 获取俩个时间的查结果用时秒表示
     *
     * @param date1
     * @param date2
     * @return 几小时:几分钟:几秒钟
     * @Summary:此处可以讲计算结果包装成一个结构体返回便于格式化
     */
    public static String SubtractTime(String date1, String date2) {
        String result = "";
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long sss = (end.getTime() - start.getTime()) / 1000;
            int hh = (int) sss / (60 * 60);
            int mm = (int) (sss - hh * 60 * 60) / (60);
            int ss = (int) (sss - hh * 60 * 60 - mm * 60);
            result = hh + ":" + mm + ":" + ss;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取俩个时间的查结果用时秒表示
     *
     * @param date1
     * @param date2
     * @return 几天-几小时:几分钟:几秒钟
     * @Summary:此处可以讲计算结果包装成一个结构体返回便于格式化
     */
    public static String SubtractDate(String date1, String date2) {
        String result = "";
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long sss = (end.getTime() - start.getTime()) / 1000;
            int dd = (int) sss / (60 * 60 * 24);
            int hh = (int) (sss - dd * 60 * 60 * 24) / (60 * 60);
            int mm = (int) (sss - dd * 60 * 60 * 24 - hh * 60 * 60) / (60);
            int ss = (int) (sss - dd * 60 * 60 * 24 - hh * 60 * 60 - mm * 60);
            result = dd + "-" + hh + ":" + mm + ":" + ss;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 获取俩个时间之前的相隔的天数
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static int subDay(Date startTime, Date endTime) {
        int      days = 0;
        Calendar can1 = Calendar.getInstance();
        can1.setTime(startTime);
        Calendar can2 = Calendar.getInstance();
        can2.setTime(endTime);
        int year1 = can1.get(Calendar.YEAR);
        int year2 = can2.get(Calendar.YEAR);

        Calendar can = null;
        if (can1.before(can2)) {
            days -= can1.get(Calendar.DAY_OF_YEAR);
            days += can2.get(Calendar.DAY_OF_YEAR);
            can = can1;
        } else {
            days -= can2.get(Calendar.DAY_OF_YEAR);
            days += can1.get(Calendar.DAY_OF_YEAR);
            can = can2;
        }
        for (int i = 0; i < Math.abs(year2 - year1); i++) {
            days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
            can.add(Calendar.YEAR, 1);
        }

        return days;
    }
    
    /**
     * 获取俩个时间之前的相隔的天数
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static int subDay(String startTime, String endTime) {
        int days = 0;
        try {
            Date date1 = DateInstance().parse(DateInstance().format(DateTimeInstance().parse(startTime)));
            Date date2 = DateInstance().parse(DateInstance().format(DateTimeInstance().parse(endTime)));
            Calendar can1 = Calendar.getInstance();
            can1.setTime(date1);
            Calendar can2 = Calendar.getInstance();
            can2.setTime(date2);
            int year1 = can1.get(Calendar.YEAR);
            int year2 = can2.get(Calendar.YEAR);

            Calendar can = null;
            if (can1.before(can2)) {
                days -= can1.get(Calendar.DAY_OF_YEAR);
                days += can2.get(Calendar.DAY_OF_YEAR);
                can = can1;
            } else {
                days -= can2.get(Calendar.DAY_OF_YEAR);
                days += can1.get(Calendar.DAY_OF_YEAR);
                can = can2;
            }
            for (int i = 0; i < Math.abs(year2 - year1); i++) {
                days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
                can.add(Calendar.YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return days;
    }
    
    public static Date getDateStart(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat f=DateInstance();
		try {
			date=f.parse(f.format(date)+" 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getDateEnd(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat f=DateInstance();
		try {
			date=f.parse(f.format(date)+" 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	} 
}
