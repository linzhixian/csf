package com.lzx.framework.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p/>
 * 把日期转成需要个显示格式
 * </p>
 *
 * @author lzx
 * @version $Id: DateFormatUtil.java 7 2009-06-25 07:12:21Z lzx $
 */
public class DateFormatUtil {
    private static final DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateFormat shortformat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    private static final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static final DateFormat shortTimeFormat = new SimpleDateFormat("HH:mm");
    private static final DateFormat dateFormat =new SimpleDateFormat("yyyy/MM/dd");
    private static final DateFormat dateFormat2 =new SimpleDateFormat("yyyy-MM-dd");  
    private static final DateFormat mothTimeformat = new SimpleDateFormat("MM-dd HH:mm");

    private static final DateFormat format3 = new SimpleDateFormat("yyyyMMddHHmmss");
    
    
    private static final DateFormat dateFormat3 =new SimpleDateFormat("yyyyMMdd");
    
    public static Date parser4(String text) throws ParseException {
    	return format3.parse(text);
    }
    public static Date parser3(String text) throws ParseException {
    	return dateFormat3.parse(text);
    }
    /**
     * 给一个：yyyymmdd 的int,返回前一天的yyyymmdd
     * @author Administrator 2015年7月2日上午11:34:27
     * @throws ParseException 
     */
    public static int addDate(int date,int days) throws ParseException {
	Date d=dateFormat3.parse(String.valueOf(date));
	Calendar c=Calendar.getInstance();
	c.setTime(d);
	c.add(Calendar.DAY_OF_YEAR, days);
	return Integer.parseInt(dateFormat3.format(c.getTime()));
    }
    /**
     * 给你一个yyyy-MM-dd HH:mm:ss 的字符串 返回日期
     * @param text
     * @return
     * @throws ParseException
     */
    public static long parser(String text) throws ParseException {
    	return shortformat.parse(text).getTime();
    }
    public static long parserShort(String text) throws ParseException {
    	return timeFormat.parse(text).getTime();
    }
    public static long parserShortest(String text) throws ParseException {
    	return shortTimeFormat.parse(text).getTime();
    }
    public static Date parserShortestDate(String text) throws ParseException {
    	return shortTimeFormat.parse(text);
    }
    /**
     * 返回时间格式：小时：分钟:秒
     * 
     * @param date
     * @return
     */
    public static String getFormatTime24(Date date) {
        return timeFormat.format(date);
    }
    public static String getFormat3Time(Date date) {
        return format3.format(date);
    }
    /**
     * 返回时间格式：小时：分钟
     * 
     * @param date
     * @return
     */
    public static String getFormatShortTime24(Date date) {
        return shortTimeFormat.format(date);
    }
    /**
     * 返回日期格式：yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String getFormatDateTime(Date date) {
        return format.format(date);
    }
    //返回日期格式：MM-dd HH:mm:ss
    public static String getFormatDateTimeMathTime(Date date) {
    	return mothTimeformat.format(date);
    }
    /**
     * 返回日期格式：yyyy-MM-dd
     * 
     * @param date
     * @return
     */
    public static String getFormatDate(Date date) {
        return dateFormat.format(date);
    }
    /**
     * 返回日期格式：yyyy-MM-dd HH:mm
     * 
     * @param date
     * @return
     */
    public static String getFormatShortDateTime(Date date) {
        return shortformat.format(date);
        
    }
    /**
     * 返回日期格式：yyyy-MM-dd
     * 
     * @param date
     * @return
     */
    public static String getDateFormat2(Date date) {
        return dateFormat2.format(date);
        
    }
    
    
    /**
     * 返回日期格式：yyyy-MM-dd HH:mm
     * 
     * @param date
     * @return
     */
    public static String getFormatShortDateTime(long date) {
        return shortformat.format(new Date(date));
        
    }
    /**
     * 返回日期格式：yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String getFormatDateTime(long date) {
        return format.format(new Date(date));
        
    }
    /**
     * 返回日期格式：yyyyMMdd
     * 
     * @param date
     * @return
     */
    public static String getFormatYYMMDD(Date d) {
        return dateFormat3.format(d);
        
    }
	//weekOfYear:一年里的第几周，周几
    /**
     * 
    * @Description: 根据一年里的第几周，周几 返回具体日期
    * @param     weekOfYear 一年里的第几周；dayOfWeek 周几
    * @return Date    返回类型 
    * @throws
     */
	public  static Date getDateByWeekOfYear(int weekOfYear,int dayOfWeek) {
		//外国习惯周日是第一天，转成按中国习惯，周一是第一天
		if(dayOfWeek<7) {
			dayOfWeek++;
		} else if(dayOfWeek==7) {
			weekOfYear++;
			dayOfWeek=1;
		}
		Calendar c=Calendar.getInstance();
		c.set(Calendar.WEEK_OF_YEAR, weekOfYear);
		c.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		return c.getTime();
	}
	/**
	 * 
	* @Description: 把国外周几改为中国习惯的周几
	 */
	public static int toChinaWeekDay(int english_weekDay) {
		//System.out.println("toChinaWeekDay:"+english_weekDay);
		if(english_weekDay>1) {
			return english_weekDay-1;
		} else if(english_weekDay==1) {
			return 7;
		} else {
			return -1;
		}
	}
	public  static Date getDateByWeekOfYear(int weekOfYear,int interalWeek,int dayOfWeek) {
		Calendar c=Calendar.getInstance();
		int week=c.get(Calendar.WEEK_OF_YEAR);
		System.out.println(week);
		int resultResult=weekOfYear;
		while(true) {
			if(resultResult>=week) {
				break;
			} else {
				resultResult=resultResult+interalWeek+1;
			}
		}
		return getDateByWeekOfYear(resultResult,dayOfWeek);
	}
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }
    public static boolean isToday(long timemills) {
    	Calendar c=Calendar.getInstance();
    	c.setTimeInMillis(timemills);
    	Calendar today=Calendar.getInstance();
    	return today.get(Calendar.DAY_OF_YEAR)==c.get(Calendar.DAY_OF_YEAR);
    }
	public static void main(String[] args) throws ParseException {
		System.out.println(getFormatDateTime(getDateByWeekOfYear(22,1)));
		System.out.println(getFormatDateTime(getDateByWeekOfYear(1,3,6)));
		Calendar c=Calendar.getInstance();
		
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		System.out.println("D:"+daysBetween(new Date(),new Date()));
	}
	
}
