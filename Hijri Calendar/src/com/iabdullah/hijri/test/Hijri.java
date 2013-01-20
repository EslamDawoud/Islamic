package com.iabdullah.hijri.test;

import java.util.Calendar;

import org.abdullah.islamic.hijri.HijraCalendar;

import com.iabdullah.android.util.HijraMonthDisplayHelper;


public class Hijri {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int selectedMonth = 0;

		String[] weekDays = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		//
		HijraCalendar todayDate = HijraCalendar.getInstance();
		//Hijri Calendar
		HijraCalendar showedDate = HijraCalendar.getInstance();
		showedDate.add(HijraCalendar.MONTH, selectedMonth);
		
		//Week start date. SUN = 1, MON = 2 etc..
		int weekStartDay = HijraCalendar.SATURDAY;
		//
		Calendar todayDate2 = todayDate.toGregorianCalendar();
		System.out.println(weekDays[todayDate2.get(HijraCalendar.DAY_OF_WEEK)-1] + " " + todayDate2.get(HijraCalendar.DAY_OF_MONTH) + ", " + (todayDate2.get(HijraCalendar.MONTH)) + " " + todayDate2.get(HijraCalendar.YEAR));
		//
		System.out.println(weekDays[todayDate.get(HijraCalendar.DAY_OF_WEEK)] + " " + todayDate.get(HijraCalendar.DAY_OF_MONTH) + ", " + todayDate.get(HijraCalendar.MONTH) + " " + todayDate.get(HijraCalendar.YEAR));
		
		HijraMonthDisplayHelper mHelper = new HijraMonthDisplayHelper(showedDate.get(HijraCalendar.YEAR), showedDate.get(HijraCalendar.MONTH), weekStartDay);
		
		class _calendar {
	    	public int day;
	    	public boolean thisMonth;
	    	public _calendar(int d, boolean b) {
	    		day = d;
	    		thisMonth = b;
	    	}
	    	public _calendar(int d) {
	    		this(d, false);
	    	}
	    };
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
	    if(mHelper.getYear()==todayDate.get(HijraCalendar.YEAR) && mHelper.getMonth()==todayDate.get(HijraCalendar.MONTH)) {
	    	thisDay = todayDate.get(HijraCalendar.DAY_OF_MONTH);
	    }
	    
		//Calendar
	    for(int i = 0; i < weekDays.length; i++){
			int day = i + weekStartDay - 1;
			if(day > weekDays.length-1)
				day -= weekDays.length;
			System.out.print(weekDays[day] + "\t");
		}
		System.out.println();
		//String[][] table = new String[6][7];
		for (int week = 0; week < tmp.length; week++) {
			int n[] = mHelper.getDigitsForRow(week);
			for (int day = 0; day < tmp[week].length; day++) {
				if(mHelper.isWithinCurrentMonth(week,day))
	    			tmp[week][day] = new _calendar(n[day], true);
	    		else
	    			tmp[week][day] = new _calendar(n[day]);
				if(tmp[week][day].day == thisDay && tmp[week][day].thisMonth)
					System.out.print("[" + tmp[week][day].day + "]\t");
				else
					System.out.print(tmp[week][day].day + "\t");
			}
			System.out.println();
		}
	}
}
