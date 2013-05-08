package com.iabdullah.hijri.test;

import java.util.Calendar;

import org.tamrah.android.util.MonthDisplayHelper;
import org.tamrah.islamic.hijri.UmmAlQuraCalendar;

public class UmmAlQuraTest {

	public static void main(String[] args) throws Exception {
		//
		String[] weekDays = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		//Week start date. SUN = 1, MON = 2 etc..
		int weekStartDay = Calendar.SUNDAY;
		//
		UmmAlQuraCalendar quraCalendar = UmmAlQuraCalendar.getInstance();
		quraCalendar.setFirstDayOfWeek(weekStartDay);
		//
		System.out.println("Umm AlQura: " + weekDays[quraCalendar.get(Calendar.DAY_OF_WEEK)-1] + " " + quraCalendar.get(Calendar.DAY_OF_MONTH) + ", " + (quraCalendar.get(Calendar.MONTH)) + " " + quraCalendar.get(Calendar.YEAR));
		//
		Calendar calendar = quraCalendar.toGregorianCalendar();
		System.out.println("Gregorian: " + weekDays[calendar.get(Calendar.DAY_OF_WEEK)-1] + " " + calendar.get(Calendar.DAY_OF_MONTH) + ", " + (calendar.get(Calendar.MONTH)) + " " + calendar.get(Calendar.YEAR));
		//
		MonthDisplayHelper mHelper = new MonthDisplayHelper(quraCalendar.get(Calendar.YEAR), quraCalendar.get(Calendar.MONTH), weekStartDay, UmmAlQuraCalendar.getInstance());
		
		//6 weeks, 7 days
	    _calendar tmp3[][] = new _calendar[6][7];
	    
	    for(int week=0; week<tmp3.length; week++) {
	    	int n[] = mHelper.getDigitsForRow(week);
	    	for(int day=0; day<n.length; day++) {
	    		if(mHelper.isWithinCurrentMonth(week,day))
	    			tmp3[week][day] = new _calendar(n[day], true);
	    		else
	    			tmp3[week][day] = new _calendar(n[day]);
	    		
	    	}
	    }
	    int thisDay3 = 0;
	    if(mHelper.getYear()==quraCalendar.get(Calendar.YEAR) && mHelper.getMonth()==quraCalendar.get(Calendar.MONTH)) {
	    	thisDay3 = quraCalendar.get(Calendar.DAY_OF_MONTH);
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
		for (int week = 0; week < tmp3.length; week++) {
			for (int day = 0; day < tmp3[week].length; day++) {
				if(tmp3[week][day].day == thisDay3 && tmp3[week][day].thisMonth)
					System.out.print("[" + tmp3[week][day].day + "]\t");
				else if(tmp3[week][day].thisMonth)
					System.out.print(tmp3[week][day].day + "\t");
				else
					System.out.print(tmp3[week][day].day + "<\t");
			}
			System.out.println();
		}
	}

}
