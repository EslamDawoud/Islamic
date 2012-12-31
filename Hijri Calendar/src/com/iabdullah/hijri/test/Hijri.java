package com.iabdullah.hijri.test;

import java.util.Calendar;

import org.abdullah.islamic.hijri.HijriCalendar;

import com.iabdullah.android.util.HijriMonthDisplayHelper;


public class Hijri {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int selectedMonth = 1;
//		if(request.getParameter("month") != null)
//			selectedMonth = Integer.parseInt(request.getParameter("month"));

		String[] weekDays = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
//		int weeksInMonth = 6;
		//
		HijriCalendar todayDate = HijriCalendar.getInstance();
		//Hijri Calendar
		HijriCalendar showedDate = HijriCalendar.getInstance();
		showedDate.add(HijriCalendar.MONTH, selectedMonth);
		//Gregorian Calendar from showedDate
//		Calendar gerCalendar = showedDate.toGregorianCalendar();
		//Get first day of this month in Hijri Calendar
//		gerCalendar.add(Calendar.DAY_OF_YEAR, - (showedDate.get(HijriCalendar.DAY_OF_MONTH) -1 ));
		//First day of month Hijri Calendar
//		HijriCalendar firstOfMonth = new HijriCalendar(gerCalendar);
		//Previous month
//		HijriCalendar preMonth = new HijriCalendar(gerCalendar);
//		preMonth.add(HijriCalendar.MONTH, -1);
		
		//Week start date. SUN = 1, MON = 2 etc..
		int weekStartDay = HijriCalendar.SATURDAY;
/*		//Flag
		boolean atMonth = false;
		//Previous month length
		int pre_month_length = preMonth.getActualMaximum(HijriCalendar.DAY_OF_MONTH);
		//Previous counter
		int pre_month_day = 0;
		//This month counter
		int month_day = 1;
		//Next month counter
		int next_month_day = 1;
*/		
		//
		Calendar todayDate2 = Calendar.getInstance();
		System.out.println(weekDays[todayDate2.get(HijriCalendar.DAY_OF_WEEK)-1] + " " + todayDate2.get(HijriCalendar.DAY_OF_MONTH) + ", " + (todayDate2.get(HijriCalendar.MONTH) + 1) + " " + todayDate2.get(HijriCalendar.YEAR));
		//
		System.out.println(weekDays[todayDate.get(HijriCalendar.DAY_OF_WEEK)-1] + " " + todayDate.get(HijriCalendar.DAY_OF_MONTH) + ", " + todayDate.get(HijriCalendar.MONTH) + " " + todayDate.get(HijriCalendar.YEAR));
		
		HijriMonthDisplayHelper mHelper = new HijriMonthDisplayHelper(showedDate.get(HijriCalendar.YEAR), showedDate.get(HijriCalendar.MONTH), weekStartDay);
		
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
	    if(mHelper.getYear()==todayDate.get(HijriCalendar.YEAR) && mHelper.getMonth()==todayDate.get(HijriCalendar.MONTH)) {
	    	thisDay = todayDate.get(HijriCalendar.DAY_OF_MONTH);
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
		/*
		for(int i = 0; i < weekDays.length; i++){
			int day = i + weekStartDay - 1;
			if(day > weekDays.length-1)
				day -= weekDays.length;
			System.out.print(weekDays[day] + "\t");
		}
		System.out.println();
		
		for(int week = 0; week < weeksInMonth; week++){
			for(int day = 0; day < weekDays.length; day++){
				if(!atMonth && ((day + weekStartDay - 1)%weekDays.length) == firstOfMonth.get(HijriCalendar.DAY_OF_WEEK))
					atMonth = true;
				if(month_day > showedDate.getActualMaximum(HijriCalendar.DAY_OF_MONTH))
					atMonth = false;
				if(atMonth){
					if(month_day == todayDate.get(HijriCalendar.DAY_OF_MONTH) && showedDate.get(HijriCalendar.MONTH) == todayDate.get(HijriCalendar.MONTH)
							&& showedDate.get(HijriCalendar.YEAR) == todayDate.get(HijriCalendar.YEAR))
						System.out.print("[" + month_day + "]\t");
					else
						System.out.print( month_day + "\t");
					month_day++;
				}else{
					if(month_day == 1){
						if(pre_month_day == 0)
							pre_month_day = pre_month_length - ((weekDays.length - weekStartDay) + firstOfMonth.get(HijriCalendar.DAY_OF_WEEK));
						System.out.print("-" + pre_month_day + "\t");
						pre_month_day++;
					}else{
						System.out.print("+" + next_month_day + "\t");
						next_month_day++;
					}
				}
			}
			System.out.println();
		}
		*/
	}
}
