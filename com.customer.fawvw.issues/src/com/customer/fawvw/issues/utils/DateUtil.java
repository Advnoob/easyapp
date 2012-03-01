package com.customer.fawvw.issues.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static String getSysDate() {
		Date now = new Date(); 
		DateFormat df = DateFormat.getDateInstance();
		String sysDate = df.format(now);
		return sysDate;
	}
	
	public static Date getSystTime() {
		return new Date();
	}

    public static String getCurrentYear() {
    	
    	return getSysDate().split("-")[0];
	}
	
	public static Boolean betweenStartAndEnd(String selectTime, String startTime, String endTime) {
			
		if (DateUtil.compareTime(startTime, selectTime) && 
				DateUtil.compareTime(selectTime, endTime)) { 
			return true;
		}
		
		return false;
	}
	
	public static String transformTime(String oldTime, String pattern) {
//		System.out.println("oldTime = " + oldTime);
		String newTime = oldTime;
		if ("MM.dd".equals(pattern) && !"".equals(oldTime)) {
			newTime = oldTime.split(" ")[0].split("-")[1] + "." + oldTime.split(" ")[0].split("-")[2];
		}
		if ("YYYY.MM.dd".equals(pattern) && !"".equals(oldTime)) {
			newTime = oldTime.split(" ")[0].split("-")[0] + "." + oldTime.split(" ")[0].split("-")[1] + "." + oldTime.split(" ")[0].split("-")[2];
		}
		if ("YY".equals(pattern) && !"".equals(oldTime)) {
			newTime = oldTime.split(" ")[0].split("-")[0].substring(2, 4);
		}
//		System.out.println("newTime = " + newTime);
		return newTime;
		
	}
	/**
	 * date1 < date2 ���� true
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareTime(String date1, String date2) {
		Date startDate = new Date();
		Date endDate = new Date();
		try {
			if (!"".equals(date1)) { //$NON-NLS-1$
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date1); //$NON-NLS-1$
			}
			if (!"".equals(date2)) { //$NON-NLS-1$
				 endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date2); //$NON-NLS-1$
			}
			if ((startDate.getTime() - endDate.getTime())/3600/24/1000 <= 0) {
				return true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	 /** 
	 * ȡ�õ�ǰ�����Ƕ����� 
	 * ����Ϊ�����ַ���
	 * ���ΪKW...
	 */ 
	 public static String getWeekOfYear(String dateStr) { 
		Calendar c = null;
		try {
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			 Date date = sdf.parse(dateStr);

			 c = new GregorianCalendar(); 
			 c.setFirstDayOfWeek(Calendar.MONDAY); 
			 c.setMinimalDaysInFirstWeek(7); 
			 c.setTime (date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		 return "KW" + String.valueOf(c.get(Calendar.WEEK_OF_YEAR)); 
	 } 
	 
	/**
	 * ��ȡ�������ڵ�����
	 * ����Ϊ��������
	 */
	public static int getWeekOfYear2(Date date) {
		  Calendar c = new GregorianCalendar();
		  c.setFirstDayOfWeek(Calendar.MONDAY);
		  c.setMinimalDaysInFirstWeek(7);
		  c.setTime(date);

		  return c.get(Calendar.WEEK_OF_YEAR);
	}
	
    /**
     * �������ڻ�ȡ������
     */
    public static int getYearByDate(Date date) {
    	Calendar c = new GregorianCalendar();
		  c.setFirstDayOfWeek(Calendar.MONDAY);
		  c.setMinimalDaysInFirstWeek(7);
		  c.setTime(date);

		  return c.get(Calendar.YEAR);
	}

	 /** 
	 * �õ�ĳһ���ܵ����� 
	 */ 
	 public static int getMaxWeekNumOfYear(int year) { 
		 Calendar c = new GregorianCalendar(); 
		 c.set(year, Calendar.DECEMBER, 31, 23, 59, 59); 
	
		 return getWeekOfYear2(c.getTime()); 
	 } 

	 /** 
	 * �õ�ĳ��ĳ�ܵĵ�һ�� 
	 */ 
	 public static Date getFirstDayOfWeek(int year, int week) { 
		 Calendar c = new GregorianCalendar(); 
		 c.set(Calendar.YEAR, year); 
		 c.set (Calendar.MONTH, Calendar.JANUARY); 
		 c.set(Calendar.DATE, 1); 
	
		 Calendar cal = (GregorianCalendar) c.clone(); 
		 cal.add(Calendar.DATE, week * 7); 
	
		 return getFirstDayOfWeek(cal.getTime ()); 
	 } 

	 /** 
	 * �õ�ĳ��ĳ�ܵ����һ�� 
	 */ 
	 public static Date getLastDayOfWeek(int year, int week) { 
		 Calendar c = new GregorianCalendar(); 
		 c.set(Calendar.YEAR, year); 
		 c.set(Calendar.MONTH, Calendar.JANUARY); 
		 c.set(Calendar.DATE, 1); 
	
		 Calendar cal = (GregorianCalendar) c.clone(); 
		 cal.add(Calendar.DATE , week * 7); 
	
		 return getLastDayOfWeek(cal.getTime()); 
	 } 

	 /** 
	 * ȡ��ָ�����������ܵĵ�һ�� 
	 */ 
	 public static Date getFirstDayOfWeek(Date date) { 
		 Calendar c = new GregorianCalendar(); 
		 c.setFirstDayOfWeek(Calendar.MONDAY); 
		 c.setTime(date); 
		 c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday 
		 return c.getTime (); 
	 } 

	 /** 
	 * ȡ��ָ�����������ܵ����һ�� 
	 */ 
	 public static Date getLastDayOfWeek(Date date) { 
		 Calendar c = new GregorianCalendar(); 
		 c.setFirstDayOfWeek(Calendar.MONDAY); 
		 c.setTime(date); 
		 c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday 
		 return c.getTime(); 
	 } 
	 
	 /** 
	 * ȡ�õ�ǰ���������ܵĵ�һ�� 
	 */ 
	 public static Date getFirstDayOfWeek() { 
		 Calendar c = new GregorianCalendar(); 
		 c.setFirstDayOfWeek(Calendar.MONDAY); 
		 c.setTime(new Date()); 
		 c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday 
		 return c.getTime (); 
	 } 

	 /** 
	 * ȡ�õ�ǰ���������ܵ����һ�� 
	 */ 
	 public static Date getLastDayOfWeek() { 
		 Calendar c = new GregorianCalendar(); 
		 c.setFirstDayOfWeek(Calendar.MONDAY); 
		 c.setTime(new Date()); 
		 c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday 
		 return c.getTime(); 
	 } 

	 /**
	  * �Ƚ������������������
	  */
	 public static int dateDiff(Date startDate, Date endDate) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		long nd = 1000*24*60*60; //һ��ĺ�����
		long nh = 1000*60*60; //һСʱ�ĺ�����
		long nm = 1000*60; //һ���ӵĺ�����
		long ns = 1000;//һ���ӵĺ�����

		long diff = 0l;
		long day = 0l;
		if (startDate!= null && endDate != null) {
			diff = endDate.getTime() - startDate.getTime();
		}
		
		day = diff/nd;//����������
		long hour = diff%nd/nh;//��������Сʱ
		long min = diff%nd%nh/nm;//�������ٷ���
		long sec = diff%nd%nh%nm/ns;//����������
		 
		return new Long(day).intValue();
	}
	
	 /**
	  * �Ƚ����������������  
	  * �Ƚ��������ڵĴ�С
	  */
	 public static int dateDiffSecond(Date startDate, Date endDate) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		long nd = 1000*24*60*60; //һ��ĺ�����
		long nh = 1000*60*60; //һСʱ�ĺ�����
		long nm = 1000*60; //һ���ӵĺ�����
		long ns = 1000;//һ���ӵĺ�����

		long diff = 0l;
		//long day = 0l;
		if (startDate!= null && endDate != null) {
			diff = endDate.getTime() - startDate.getTime();
		}
		
		//day = diff/nd;//����������
		long hour = diff%nd/nh;//��������Сʱ
		long min = diff%nd%nh/nm;//�������ٷ���
		long sec = diff%nd%nh%nm/ns;//����������
		 
		return new Long(sec).intValue();
	}
	
	 public static String transDateToString(Date date, String format) {
		 SimpleDateFormat sdf = new SimpleDateFormat(format);
		 return sdf.format(date);
		
	}
}
