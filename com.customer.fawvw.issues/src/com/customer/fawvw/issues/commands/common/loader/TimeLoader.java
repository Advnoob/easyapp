package com.customer.fawvw.issues.commands.common.loader;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.customer.fawvw.issues.exception.FawvmLoaderException;


public class TimeLoader  {

	public static List<Integer> submited = new ArrayList<Integer>(); //���ύ
	public static List<Integer> analysed = new ArrayList<Integer>(); //�ѷ���
	public static List<Integer> apportioned = new ArrayList<Integer>(); //�ѷ���
	public static List<Integer> excogitation = new ArrayList<Integer>(); //�з���
	public static List<Integer> operation = new ArrayList<Integer>(); //��ʵʩ
	public static List<Integer> validated = new ArrayList<Integer>(); //����֤
	public static List<Integer> closed = new ArrayList<Integer>();  //�ѹر�
	
	@SuppressWarnings("unchecked")
	/**
	 * ��ȡʱ����
	 * 
	 * @param issueVos
	 * @return
	 */
	public static Map<String, Object> load(ArrayList<HashMap<String, Object>> values) throws Exception {
		try{

			//��ֵǰ�����listԭ��ֵ
			submited.clear();
			analysed.clear();
			apportioned.clear();
			excogitation.clear();
			operation.clear();
			validated.clear();
			closed.clear();
//System.out.println("submited = " + submited);
//System.out.println("analysed = " + analysed);
//System.out.println("apportioned = " + apportioned);
//System.out.println("excogitation = " + excogitation);
//System.out.println("operation = " + operation);
//System.out.println("validated = " + validated);
//System.out.println("closed = " + closed);
			
			Map<String, Object> timeValues = new HashMap<String, Object>();
			//��ȡ��С�����ύ���ں��������ʵ���������
			Map<String, String> time = new HashMap<String, String>();
			if (values.size() > 0) {
				time = getTime(values);
			}
			
			String startTime = time.get("start") + "";  
			String endTime = time.get("end") + "";  

//	System.out.println("startTime" + startTime); 
//	System.out.println("endTime" + endTime); 

			//��ȡʱ����

			List<Map<String, Integer>> weeks = new ArrayList<Map<String, Integer>>();
			if (!"".equals(startTime) && !"null".equals(startTime)  
					&& !"".equals(endTime) && !"null".equals(endTime)) {  

				weeks = collectWeek(startTime, endTime);

			}
			
//	System.out.println(weeks);

			// ��ʱ����ѭ��������    
			if (weeks != null && weeks.size() > 0) {
				int i = 0;
				//ѭ��ÿһ��
				for (Iterator iterator = weeks.iterator(); iterator.hasNext();) {

					Map<String, Integer> week = (Map<String, Integer>) iterator.next();
					Date sunday = getSunDayOfWeek(week.get("year"),week.get("week"));  
					
					Format format = new SimpleDateFormat("yyyy-MM-dd"); 
					String weekSunday = format.format(sunday);
					weekSunday += " 23:59"; 

					
//		System.out.println(week.get("year") + "year and " + week.get("week") + "week");
					submited.add(0);
					analysed.add(0);
					apportioned.add(0);
					excogitation.add(0);
					operation.add(0);
					validated.add(0);
					closed.add(0);
		
//		System.out.println("submited = " + submited + "\n"
//						+ "analysed = " + analysed + "\n"
//						+ "apportioned = " + apportioned + "\n"
//						+ "excogitation = " + excogitation + "\n"
//						+ "operation = " + operation + "\n"
//						+ "validated = " + validated + "\n"
//						+ "closed = " + closed);

					
					if(!"".equals(weekSunday)) { 
						operateTime(values, weekSunday, i);
					}
					i++;
				}
				
			}
			
//		System.out.println("timeValues = " + timeValues);
			System.out.println("��ʱ��ͳ��\n" 
					+ "���ύ�� " + submited + "\n"   
					+ "�ѷ����� " + analysed + "\n"   
					+ "�ѷ��ɣ�" + apportioned + "\n"   
					+ "�з�����" + excogitation + "\n"   
					+ "��ʵʩ��" + operation + "\n"   
					+ "����֤��" + validated + "\n"   
					+ "�ѹرգ�" + closed); 
			
			timeValues.put("weeks", weeks); 
			timeValues.put("submited", submited); 
			timeValues.put("analysed", analysed); 
			timeValues.put("apportioned", apportioned); 
			timeValues.put("excogitation", excogitation); 
			timeValues.put("operation", operation); 
			timeValues.put("validated", validated); 
			timeValues.put("closed", closed); 
	
			return timeValues;
			
		} catch (Exception e) {
			throw new FawvmLoaderException(e.getMessage());
		}
		
		
	}
	/*
	 * ������С�����ύ���ں��������ʵ��������ڻ�ȡʱ����
	 * 
	 */
	
	private static List<Map<String, Integer>> collectWeek(String startTime,
			String endTime) throws Exception {
//System.out.println("startTime = " + startTime);	
//System.out.println("endTime = " + endTime);	
		try {
			
			List<Map<String, Integer>> times = new ArrayList<Map<String, Integer>>();

			// int number =0;

			int startYear = getIntegerDateOfYear(startTime);
			int endYear = getIntegerDateOfYear(endTime);
			int startWeek = getIntegerWeekOfYear(startTime);
			int endWeek = getIntegerWeekOfYear(endTime);
//System.out.println("startYear = " + startYear);
//System.out.println("endYear = " + endYear);
//System.out.println("startWeek = " + startWeek);
//System.out.println("endWeek = " + endWeek);
			Map<String, Integer> week = null;

			if ((endYear - startYear) >= 0) {
//System.out.println("aaaa");
				if ((endYear - startYear) == 0 && (endWeek - startWeek) >= 0) {
					// ���Ǳ����
					for (int i = 0; i <= endWeek - startWeek; i++) {
						week = null;
						week = new HashMap<String, Integer>();
						week.put("week", startWeek + i); 
						week.put("year", endYear); 
						times.add(week);
					}
				} else if ((endYear - startYear) == 1) {
					// ��һ��
					int maxWeek = getMaxWeekNumOfYear(startYear);
					// �����ǰ����
					for (int i = startWeek; i <= maxWeek; i++) {
						week = null;
						week = new HashMap<String, Integer>();
						week.put("week", i); 
						week.put("year", startYear); 
						times.add(week);
					}
					// ����ĺ����(���Ǵ��㿪ʼ���𣿣���)
					for (int i = 1; i <= endWeek; i++) {
						week = null;
						week = new HashMap<String, Integer>();
						week.put("week", i); 
						week.put("year", endYear); 
						times.add(week);
					}
				} else {
					// ��Խ��������
					int maxWeek = getMaxWeekNumOfYear(startYear);
					// �����ǰ����
					for (int i = startWeek; i <= maxWeek; i++) {
						week = null;
						week = new HashMap<String, Integer>();
						week.put("week", i); 
						week.put("year", startYear); 
						times.add(week);
					}
					// �м�ε�ʱ�����
					for (int i = 1; (i + startYear) < endYear; i++) {
						int currentYear = i + startYear;
						
						for (int j = 1; j <= getMaxWeekNumOfYear(currentYear); j++) {
							week = null;
							week = new HashMap<String, Integer>();
							week.put("week", j); 
							week.put("year", currentYear); 
							times.add(week);
						}
					}
					// ����ĺ����()
					for (int i = 1; i <= endWeek; i++) {
						week = null;
						week = new HashMap<String, Integer>();
						week.put("week", i); 
						week.put("year", endYear); 
						times.add(week);
					}

				}
			}

			return times;
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new FawvmLoaderException(e.getMessage());
			
		}

		
	}

	

	/*
	 * ��������������б�ĳ�ܵ������յ����ڡ���I��ѭ��
	 * 
	 */


	public static void operateTime(ArrayList<HashMap<String, Object>> values, String sunday, int i) throws Exception {
//System.out.println("values = " + values);	
//System.out.println("values.zise = " + values.size());
		try {
			
			for (int k=0; k<values.size(); k++) {
//System.out.println("[" + i + "]--------begin cal time-------");
//System.out.println(values.get(k));
				//ѡ���ܵ����մ�������ر����ڣ��ѹر�+1
				if (!"".equals((String)(values.get(k)).get("fv9TimeChangeGreen")) &&  
						compareTime((String)(values.get(k)).get("fv9TimeChangeGreen"), sunday)){ 
					
					closed.add(i, closed.get(i) + 1);
					closed.remove(i+1);
					
//System.out.println("closed--ture"); 
//System.out.println("closed" + closed); 
				}
				
				//ѡ���ܵ����մ��ڷ�����֤���ڣ�С������ر����ڣ�����֤+1
				//����û�йرգ�������֤��������֤
				if ("".equals((String)(values.get(k)).get("fv9TimeChangeGreen")) &&  
						!"".equals((String)(values.get(k)).get("fv9TimeValidated")) &&  
						compareTime((String)(values.get(k)).get("fv9TimeValidated"), sunday)) { 
					
					validated.add(i, validated.get(i) + 1);
					validated.remove(i+1);
					
//System.out.println("validated--ture"); 
//System.out.println("validated" + validated); 
					
				} 
				
				if (!"".equals((String)(values.get(k)).get("fv9TimeValidated")) &&  
						!"".equals((String)(values.get(k)).get("fv9TimeChangeGreen")) &&							  
						compareTime(sunday, (String)(values.get(k)).get("fv9TimeChangeGreen")) && 
						compareTime((String)(values.get(k)).get("fv9TimeValidated"), sunday)) { 
					
					validated.add(i, validated.get(i) + 1);
					validated.remove(i+1);
					
//System.out.println("validated--ture"); 
//System.out.println("validated" + validated); 
				}
				
				
				
				//ѡ���ܵ������մ��ڷ���ʵʩ���ڣ�С�ڷ�����֤���ڣ�����ʵʩ+1
				if ("".equals((String)(values.get(k)).get("fv9TimeValidated")) &&    
						!"".equals((String)(values.get(k)).get("fv9TimeImplemented")) &&   
						compareTime((String)(values.get(k)).get("fv9TimeImplemented"), sunday)) { 
					operation.add(i, operation.get(i) + 1);
					operation.remove(i+1);

//System.out.println("operation--ture"); 
//System.out.println("operation" + operation);			 
					
				}
				
				if (!"".equals((String)(values.get(k)).get("fv9TimeImplemented")) &&  
						!"".equals((String)(values.get(k)).get("fv9TimeValidated")) &&  
						compareTime(sunday, (String)(values.get(k)).get("fv9TimeValidated")) && 
						compareTime((String)(values.get(k)).get("fv9TimeImplemented"), sunday)) { 
					
					operation.add(i, operation.get(i) + 1);
					operation.remove(i+1);

//System.out.println("operation--ture"); 
//System.out.println("operation" + operation);			 
				}
				
				//ѡ���ܵ������մ������������������ڣ�С��������ʵʩ���ڣ����з���+1
				if ("".equals((String)(values.get(k)).get("fv9TimeImplemented")) &&  
						!"".equals((String)(values.get(k)).get("fv9TimeChangeYellow")) &&  
						compareTime((String)(values.get(k)).get("fv9TimeChangeYellow"), sunday)) { 
					excogitation.add(i, excogitation.get(i) + 1);
					excogitation.remove(i+1);

//System.out.println("excogitation--ture"); 
//System.out.println("excogitation" + excogitation); 
					
				}
				if (!"".equals((String)(values.get(k)).get("fv9TimeChangeYellow")) &&  
						!"".equals((String)(values.get(k)).get("fv9TimeImplemented")) &&    
						compareTime(sunday, (String)(values.get(k)).get("fv9TimeImplemented")) && 
						compareTime((String)(values.get(k)).get("fv9TimeChangeYellow"), sunday)) { 
					
					excogitation.add(i, excogitation.get(i) + 1);
					excogitation.remove(i+1);

//System.out.println("excogitation--ture"); 
//System.out.println("excogitation" + excogitation); 
				}
				
				//ѡ���ܵ������մ�������ķ������ڣ�С����������������ڣ����ѷ���+1
				if ("".equals((String)(values.get(k)).get("fv9TimeChangeYellow")) &&  
						!"".equals((String)(values.get(k)).get("fv9TimeDispatched")) &&  
						compareTime((String)(values.get(k)).get("fv9TimeDispatched"), sunday)) { 
					apportioned.add(i, apportioned.get(i) + 1);
					apportioned.remove(i+1);

//System.out.println("apportioned - ture"); 
//System.out.println("apportioned" + apportioned); 
					
				}
				if (!"".equals((String)(values.get(k)).get("fv9TimeDispatched")) &&  
						!"".equals((String)(values.get(k)).get("fv9TimeChangeYellow")) &&  
						compareTime(sunday, (String)(values.get(k)).get("fv9TimeChangeYellow")) && 
						compareTime((String)(values.get(k)).get("fv9TimeDispatched"), sunday)) { 
					apportioned.add(i, apportioned.get(i) + 1);
					apportioned.remove(i+1);

//System.out.println("apportioned - ture"); 
//System.out.println("apportioned" + apportioned); 
				}
				
				//ѡ���ܵ������մ�������ķ������ڣ�С������ķ������ڣ����ѷ���+1
				if ("".equals((String)(values.get(k)).get("fv9TimeDispatched")) &&    
						!"".equals((String)(values.get(k)).get("fv9TimeAnalyzed")) &&    
						compareTime((String)(values.get(k)).get("fv9TimeAnalyzed"), sunday)) { 
					analysed.add(i, analysed.get(i) + 1);
					analysed.remove(i+1);

//System.out.println("analysed - ture"); 
//System.out.println("analysed-ss-"+ analysed); 
					
				}
				if (!"".equals((String)(values.get(k)).get("fv9TimeAnalyzed")) &&  
						!"".equals((String)(values.get(k)).get("fv9TimeDispatched")) &&  
						compareTime(sunday, (String)(values.get(k)).get("fv9TimeDispatched")) && 
						compareTime((String)(values.get(k)).get("fv9TimeAnalyzed"), sunday)) { 

					analysed.add(i, analysed.get(i) + 1);
					analysed.remove(i+1);

//System.out.println("analysed - ture"); 
//System.out.println("analysed-ss-"+ analysed); 
				}
				
				
				//ѡ���ܵ������մ�������������ڣ�С������ķ������ڣ������ύ+1
				if ("".equals((String)(values.get(k)).get("fv9TimeAnalyzed")) &&    
						!"".equals((String)(values.get(k)).get("fv9TimeChangeRed")) &&  
						compareTime((String)(values.get(k)).get("fv9TimeChangeRed"), sunday)) { 
					submited.add(i, submited.get(i) + 1);
					submited.remove(i+1);
					
//System.out.println("submited--ture");			 
//System.out.println("submited-ss-" + submited); 
				}
				if (!"".equals((String)(values.get(k)).get("fv9TimeChangeRed")) &&  
						compareTime(sunday, (String)(values.get(k)).get("fv9TimeAnalyzed")) && 
						compareTime((String)(values.get(k)).get("fv9TimeChangeRed"), sunday)) { 

					submited.add(i, submited.get(i) + 1);
					submited.remove(i+1);
					
//System.out.println("submited--ture");			 
//System.out.println("submited-ss-" + submited); 
				}
//System.out.println("[" + i + "]--------end cal time-------");				
				
	
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new FawvmLoaderException(e.getMessage());
			
		}
		

	}
	

	/**
	 * �����С�����ύ���ں��������ʵ���������
	 * 
	 * @param issueVos
	 * @return
	 */
	public static Map<String, String> getTime(ArrayList<HashMap<String, Object>> values) throws Exception {
		Map<String, String> time = new HashMap<String, String>();
		String start = ""; //�����ύ���� 
		String end = ""; // ����ʵ��������� 

		for (int i=0; i<values.size(); i++) {

System.out.println("fv9TimeChangeRed = " + (String)(values.get(i)).get("fv9TimeChangeRed"));
			//�ҵ���С�������ύ����
			if ("".equals(start) && !"".equals((String)(values.get(i)).get("fv9TimeChangeRed"))) {   
System.out.println("start == null && fv9TimeChangeRed != null");
				start = (String)(values.get(i)).get("fv9TimeChangeRed"); 
			}
			if (!"".equals(start) && !"".equals((String)(values.get(i)).get("fv9TimeChangeRed")) &&   
					!compareTime(start, (String)(values.get(i)).get("fv9TimeChangeRed") )) { 
System.out.println("start != null && fv9TimeChangeRed != null && start > fv9TimeChangeRed");
				start = (String)(values.get(i)).get("fv9TimeChangeRed"); 
			}
			
			//�ҳ���������ʵ���������
System.out.println("fv9SolDeadlineDate = " + (String)(values.get(i)).get("fv9SolDeadlineDate"));
			if ("".equals(end) && !"".equals((String)(values.get(i)).get("fv9SolDeadlineDate"))) {   
System.out.println("end == null && fv9SolDeadlineDate != null");
				end = (String)(values.get(i)).get("fv9SolDeadlineDate"); 
			}
			if(!"".equals(end) && !"".equals((String)(values.get(i)).get("fv9SolDeadlineDate")) &&   
					compareTime(end, (String)(values.get(i)).get("fv9SolDeadlineDate"))) { 
System.out.println("end != null && fv9SolDeadlineDate != null && end < fv9SolDeadlineDate");
				end = (String)(values.get(i)).get("fv9SolDeadlineDate"); 
			}
			
System.out.println(i + " start = " + start + "\t" + "end = " + end);


		}
//		���ǲ�����ʵ��������ڣ�ȡ��ǰ������Ϊ��������
		if ("".equals(end)) {
	System.out.println("���ǲ�����ʵ��������ڣ�ȡ��ǰ������Ϊ��������");
	
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			end = sdf.format(now);
		}
System.out.println("LAST start = " + start + "\t" + "end = " + end);
		time.put("start", start); 
		time.put("end", end); 

		return time;

	}
	
/**
 * @param date1
 * @param date2
 * @return
 * date1 <= date2  return true
 */
	private static boolean compareTime(String date1, String date2) throws Exception{
//System.out.println("date1 = " + date1);
//System.out.println("date2 = " + date2);
		try {
			Date startDate = new Date();
			Date endDate = new Date();
			if (!"".equals(date1)) { 
				startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date1); 
			}
			if (!"".equals(date2)) { 
				endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date2); 
			}
			
			//date1��date2����Ϊ�գ��Ƚϴ�С
			if (!"".equals(date1) && !"".equals(date2)) {  
				if ((startDate.getTime() - endDate.getTime()) / 24 / 1000 <= 0) {
					return true;
				}
			}
			//date1Ϊ�գ�date2��Ϊ�գ�����true
			if ("".equals(date1) && !"".equals(date2)) {  
				return true;
			}
			//date1��Ϊ�գ�date2Ϊ�գ�����
			if (!"".equals(date1) && "".equals(date2)) {  
				return false;
			}
			//date1 date2��Ϊ��
			if ("".equals(date1) && "".equals(date2)) {  
				return false;
			}
		
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("�Ƚ�ʱ��ʧ�ܣ���ο���־"); 
		}
		return false;
	}
	/**
	 * �õ�ĳ��ĳ�ܵĵ�һ��
	 */
	public static Date getSunDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getSunDayOfWeek(cal.getTime());
	}

	/**
	 * ȡ��ָ�����������ܵ�����
	 */
	@SuppressWarnings("static-access")
	public static Date getSunDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.SUNDAY); // Monday
		return c.getTime();
	}

	/**
	 * ȡ�õ�ǰ�����Ƕ�����
	 */
	public static String getWeekOfYear(String dateStr) {
		Calendar c = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Date date = sdf.parse(dateStr);

			c = new GregorianCalendar();
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.setMinimalDaysInFirstWeek(7);
			c.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "KW" + String.valueOf(c.get(Calendar.WEEK_OF_YEAR)); 
	}

	/**
	 * ȡ�õ�ǰ�����Ƕ�����
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * �õ�ĳһ���ܵ�����
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

		return getWeekOfYear(c.getTime());
	}

	/**
	 * ȡ�õ�ǰ���ڵ���
	 */
	public static int getIntegerDateOfYear(String dateStr) {
		Calendar c = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Date date = sdf.parse(dateStr);

			c = new GregorianCalendar();
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.setMinimalDaysInFirstWeek(7);
			c.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c.get(Calendar.YEAR);
	}
	
	/**
	 * ȡ�õ�ǰ�����Ƕ�����
	 */
	public static int getIntegerWeekOfYear(String dateStr) {
		Calendar c = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Date date = sdf.parse(dateStr);

			c = new GregorianCalendar();
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.setMinimalDaysInFirstWeek(7);
			c.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c.get(Calendar.WEEK_OF_YEAR);
	}
	

}
