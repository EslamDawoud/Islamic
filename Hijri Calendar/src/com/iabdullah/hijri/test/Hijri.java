package com.iabdullah.hijri.test;

import java.util.Calendar;

import org.tamrah.android.util.MonthDisplayHelper;
import org.tamrah.islamic.hijri.HijraCalendar;


public class Hijri {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int selectedMonth = -6;

		String[] weekDays = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		
		//
		Calendar gregorian = Calendar.getInstance();
		
		//Hijri Calendar
		HijraCalendar hijra = HijraCalendar.getInstance();
		hijra.add(Calendar.MONTH, selectedMonth);
		//
		//Week start date. SUN = 1, MON = 2 etc..
		int weekStartDay = Calendar.SUNDAY;
		hijra.setFirstDayOfWeek(weekStartDay);
	    
	    //
		System.out.println("Hijra: " + weekDays[hijra.get(Calendar.DAY_OF_WEEK)-1] + " " + hijra.get(Calendar.DAY_OF_MONTH) + ", " + (hijra.get(Calendar.MONTH)) + " " + hijra.get(Calendar.YEAR));
	    //
	    MonthDisplayHelper mHelper = new MonthDisplayHelper(hijra.get(Calendar.YEAR), hijra.get(Calendar.MONTH), weekStartDay, HijraCalendar.getInstance());
	    //6 weeks, 7 days
	    _calendar tmp[][] = new _calendar[6][7];
	    
	    for(int week=0; week<tmp.length; week++) {
	    	int n[] = mHelper.getDigitsForRow(week);
	    	for(int day=0; day<n.length; day++) {
	    		if(mHelper.isWithinCurrentMonth(week,day))
	    			tmp[week][day] = new _calendar(n[day], true);
	    		else
	    			tmp[week][day] = new _calendar(n[day]);
	    		
	    	}
	    }
	    
	    int thisDay = 0;
	    if(mHelper.getYear()==hijra.get(Calendar.YEAR) && mHelper.getMonth()==hijra.get(Calendar.MONTH)) {
	    	thisDay = hijra.get(Calendar.DAY_OF_MONTH);
	    }
	    
		//Calendar
	    for(int i = 0; i < weekDays.length; i++){
			int day = i + weekStartDay -1;
			if(day > weekDays.length-1)
				day -= weekDays.length;
			System.out.print(weekDays[day] + "\t");
		}
		System.out.println();
		//String[][] table = new String[6][7];
		for (int week = 0; week < tmp.length; week++) {
			for (int day = 0; day < tmp[week].length; day++) {
				if(tmp[week][day].day == thisDay && tmp[week][day].thisMonth)
					System.out.print("[" + tmp[week][day].day + "]\t");
				else if(tmp[week][day].thisMonth)
					System.out.print(tmp[week][day].day + "\t");
				else
					System.out.print(tmp[week][day].day + "<\t");
			}
			System.out.println();
		}
		
		System.out.println();
		//
		System.out.println("Gregorian: " + weekDays[gregorian.get(Calendar.DAY_OF_WEEK)-1] + " " + gregorian.get(Calendar.DAY_OF_MONTH) + ", " + (gregorian.get(Calendar.MONTH)) + " " + gregorian.get(Calendar.YEAR));
		//
		MonthDisplayHelper mHelper2 = new MonthDisplayHelper(gregorian.get(Calendar.YEAR), gregorian.get(Calendar.MONTH), weekStartDay, Calendar.getInstance());
		//6 weeks, 7 days
	    _calendar tmp2[][] = new _calendar[6][7];
	    
	    for(int week=0; week<tmp2.length; week++) {
	    	int n[] = mHelper2.getDigitsForRow(week);
	    	for(int day=0; day<n.length; day++) {
	    		if(mHelper2.isWithinCurrentMonth(week,day))
	    			tmp2[week][day] = new _calendar(n[day], true);
	    		else
	    			tmp2[week][day] = new _calendar(n[day]);
	    		
	    	}
	    }
	    int thisDay2 = 0;
	    if(mHelper2.getYear()==gregorian.get(Calendar.YEAR) && mHelper2.getMonth()==gregorian.get(Calendar.MONTH)) {
	    	thisDay2 = gregorian.get(Calendar.DAY_OF_MONTH);
	    }
	    //Calendar
	    for(int i = 0; i < weekDays.length; i++){
			int day = i + weekStartDay -1;
			if(day > weekDays.length-1)
				day -= weekDays.length;
			System.out.print(weekDays[day] + "\t");
		}
		System.out.println();
		//String[][] table = new String[6][7];
		for (int week = 0; week < tmp2.length; week++) {
			for (int day = 0; day < tmp2[week].length; day++) {
				if(tmp2[week][day].day == thisDay2 && tmp2[week][day].thisMonth)
					System.out.print("[" + tmp2[week][day].day + "]\t");
				else if(tmp2[week][day].thisMonth)
					System.out.print(tmp2[week][day].day + "\t");
				else
					System.out.print(tmp2[week][day].day + "<\t");
			}
			System.out.println();
		}
		
	}
}
