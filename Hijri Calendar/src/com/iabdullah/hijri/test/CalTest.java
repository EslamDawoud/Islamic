package com.iabdullah.hijri.test;

import java.util.Calendar;

import org.tamrah.islamic.hijri.HijraCalendar;

public class CalTest {
	static String[] weekDays = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
	static HijraCalendar quraCalendar;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		quraCalendar = HijraCalendar.getInstance();
		print();
		quraCalendar.add(Calendar.MONTH, -6);
		print();
	}
	private static void print(){
		System.out.println("Umm AlQura: " + weekDays[quraCalendar.get(Calendar.DAY_OF_WEEK)-1] + " " +
				quraCalendar.get(Calendar.DAY_OF_MONTH) + ", " + (quraCalendar.get(Calendar.MONTH)) + " " +
				quraCalendar.get(Calendar.YEAR));
	}
}
