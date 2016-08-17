package com.expocms.server.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.expocms.server.db.dao.RdDaysMapper;
import com.expocms.server.db.model.RdDays;

public final class ToolDays {
	
	private static ToolDays toolDays_ = null;
	
	private Map<Integer, Map<Integer, RdDays>> yearsDaysMap_ = null;
	
	private ToolDays() {}
	
	public static ToolDays instance() {
		if(toolDays_ == null)
			toolDays_ = new ToolDays();
		return toolDays_;
	}
	
	private Map<Integer, RdDays> getDaysMap(final RdDaysMapper daysMapper, final int year) {
		if(yearsDaysMap_ == null)
			yearsDaysMap_ = new HashMap<Integer, Map<Integer, RdDays>>();
		
		Map<Integer, RdDays> daysMap = yearsDaysMap_.get(year);
		
		if(daysMap == null) {
			daysMap = new HashMap<Integer, RdDays>();
			yearsDaysMap_.put(year, daysMap);
			
			List<RdDays> daysList = daysMapper.queryByYear(year);
			if(daysList == null || daysList.size() == 0)
				return null;
			
			for(int i = 0; i < daysList.size(); i ++) {
				RdDays rdDay = daysList.get(i);
				daysMap.put(rdDay.getDayOfYear(), rdDay);
			}
		}
		
		return daysMap;
	}
	
	public synchronized RdDays findClosedWorkingDay(final RdDaysMapper daysMapper, final Calendar startDate) {
		if(daysMapper == null)
			return null;
		if(startDate == null)
			return null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(startDate.getTimeInMillis());
		
		while(true) {
			int year = calendar.get(Calendar.YEAR);
			int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
			
			Map<Integer, RdDays> daysMap = getDaysMap(daysMapper, year);
			if(daysMap == null)
				break;
			
			RdDays rdDay = daysMap.get(dayOfYear);
			if(rdDay == null)
				break;
			if(rdDay.getWorking() == 1)
				return rdDay;
			
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		return null;
	}
	
	public synchronized boolean isWorkingDay(final RdDaysMapper daysMapper, final Calendar today) {
		if(daysMapper == null)
			return false;
		if(today == null)
			return false;
		
		int year = today.get(Calendar.YEAR);
		int dayOfYear = today.get(Calendar.DAY_OF_YEAR);
		
		Map<Integer, RdDays> daysMap = getDaysMap(daysMapper, year);
		if(daysMap == null)
			return false;
		
		RdDays rdDay = daysMap.get(dayOfYear);
		if(rdDay == null)
			return false;
		
		return rdDay.getWorking() == 1;
	}
	
	public static int compareDate(final Date d1, final Date d2) {
		if(d1 == null || d2 == null)
			return 0;
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		c1.setTime(d1);
		c2.setTime(d2);
		
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);
		
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);
		
		return c1.compareTo(c2);
	}
	
	public static Calendar getTodayCalendar() {
		Date today = new Date(System.currentTimeMillis());
		return getCalendar(today);
	}
	
	public static Calendar getCalendar(final Date date) {
		if(date == null)
			return null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar;
	}
	
	public static Calendar getCalendar(final String date) {
		if(date == null || date.equals(""))
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			//e.printStackTrace();
			return null;
		}
		
		return getCalendar(d);
	}
	
	public static Calendar getNextDay(final Calendar startDate) {
		return getDayAfter(startDate, 1);
	}
	
	public static Date getDayAfter(final Date startDate, final int dayNum) {
		if(startDate == null)
			return null;
		if(dayNum <= 0)
			return startDate;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		return getDayAfter(calendar, dayNum).getTime();
	}
	
	public static Calendar getDayAfter(final Calendar startDate, final int dayNum) {
		if(startDate == null)
			return null;
		if(dayNum <= 0)
			return startDate;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(startDate.getTimeInMillis());
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		calendar.add(Calendar.DAY_OF_YEAR, dayNum);
		return calendar;
	}
	
	public static int getDatesBetweenOffset(final Date date1, final Date date2) {
		if(date1 == null || date2 == null)
			return 0;
		
		return getDatesBetweenOffset(getCalendar(date1), getCalendar(date2));
	}
	
	public static int getDatesBetweenOffset(final Calendar date1, final Calendar date2) {
		if(date1 == null || date2 == null)
			return 0;

		long t1 = date1.getTimeInMillis();
		long t2 = date2.getTimeInMillis();
		int betweenDays = (int)(Math.abs(t2 - t1) / (1000 * 60 * 60 * 24) + 0.5);
		
		return betweenDays;
	}

	public static void main (final String[] args) {
		
		Calendar today = getTodayCalendar();
		today.set(Calendar.YEAR, 2016);
		today.set(Calendar.MONTH, 0);
		today.set(Calendar.DAY_OF_MONTH, 1);
		int day = today.get(Calendar.DAY_OF_YEAR);
		System.out.println("day: " + day);
		
		
		//Date date = new Date(System.currentTimeMillis());
		//System.out.println(date.getYear() + 1900 + ", " + date.get);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		int year = cal.get(Calendar.YEAR);
		System.out.println(year + ", " + dayOfYear);
		
		String s = String.format("%04d-%03d", year, dayOfYear);
		System.out.println(s);
		
		cal.setTime(new Date());
		System.out.println("original date: " + cal.getTime().toString());
		cal.add(Calendar.DAY_OF_MONTH, 30);
		System.out.println("aftercal date: " + cal.getTime().toString());
	}
	
}
