package com.iabdullah.hijri.test;

public class _calendar {
	public int day;
	public boolean thisMonth;
	public _calendar(int d, boolean b) {
		day = d;
		thisMonth = b;
	}
	public _calendar(int d) {
		this(d, false);
	}

}
