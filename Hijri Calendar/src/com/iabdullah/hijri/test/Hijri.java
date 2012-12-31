package com.iabdullah.hijri.test;

import java.islamic.hijri.HijriCalendar;
import java.util.Calendar;


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
		int weeksInMonth = 6;
		//
		HijriCalendar todayDate = HijriCalendar.getInstance();
		//Hijri Calendar
		HijriCalendar showedDate = HijriCalendar.getInstance();
		showedDate.add(HijriCalendar.MONTH, selectedMonth);
		//Gregorian Calendar from showedDate
		Calendar gerCalendar = showedDate.toGregorianCalendar();
		//Get first day of this month in Hijri Calendar
		gerCalendar.add(Calendar.DAY_OF_YEAR, - (showedDate.get(HijriCalendar.DAY_OF_MONTH) -1 ));
		//First day of month Hijri Calendar
		HijriCalendar firstOfMonth = new HijriCalendar(gerCalendar);
		//Previous month
		HijriCalendar preMonth = new HijriCalendar(gerCalendar);
		preMonth.add(HijriCalendar.MONTH, -1);
		
		//Week start date. SUN = 0, MON = 1 etc..
		int weekStartDay = 6;
		//Flag
		boolean atMonth = false;
		//Previous month length
		int pre_month_length = preMonth.getActualMaximum(HijriCalendar.DAY_OF_MONTH);
		//Previous counter
		int pre_month_day = 0;
		//This month counter
		int month_day = 1;
		//Next month counter
		int next_month_day = 1;
		
		for(int i = 0; i < weekDays.length; i++){
			int day = i + weekStartDay;
			if(day > weekDays.length-1)
				day -= weekDays.length;
			System.out.print(weekDays[day] + "\t");
		}
		System.out.println();
		
		for(int week = 0; week < weeksInMonth; week++){
			for(int day = 0; day < weekDays.length; day++){
				if(!atMonth && ((day + weekStartDay)%weekDays.length) == firstOfMonth.get(HijriCalendar.DAY_OF_WEEK))
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
							pre_month_day = pre_month_length - ((weekDays.length - weekStartDay) + firstOfMonth.get(HijriCalendar.DAY_OF_WEEK) - 1);
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
	}
}
