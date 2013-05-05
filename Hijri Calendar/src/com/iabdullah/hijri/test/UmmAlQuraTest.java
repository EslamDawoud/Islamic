package com.iabdullah.hijri.test;

import java.util.Calendar;

import org.tamrah.islamic.hijri.UmmAlQuraCalendar;

import NIC.UmmALQura;

public class UmmAlQuraTest {

	public static void main(String[] args) throws Exception {
		int yg = 2013;
		int mg = 5;
		int dg = 5;
		int[] hijri = UmmALQura.gregorianToHijri(yg, mg, dg);
		System.out.println(hijri[0] + "-" + hijri[1] + "-" + hijri[2]);
		//
		int yh2 = 1434;
		int mh2 = 6;
		int dh2 = 25;
		int[] greg = UmmALQura.hijriToGregorian(yh2, mh2, dh2);
		System.out.println(greg[0] + "-" + greg[1] + "-" + greg[2]);
		//
		UmmAlQuraCalendar quraCalendar = UmmAlQuraCalendar.getInstance();
		System.out.println(quraCalendar.get(Calendar.YEAR) + "-" + quraCalendar.get(Calendar.MONTH) + "-" + quraCalendar.get(Calendar.DATE));
		
		Calendar calendar = quraCalendar.toGregorianCalendar();
		System.out.println(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DATE));
	}

}
