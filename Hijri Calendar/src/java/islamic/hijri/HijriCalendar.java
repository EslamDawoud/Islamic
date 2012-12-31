package java.islamic.hijri;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HijriCalendar extends Calendar {
	private static final long serialVersionUID = -2374940775782533985L;
	/* Absolute date of start of Islamic calendar (July 19, 622 Gregorian) */
	private final int HIJRI_EPOCH = 227015;
	public final int HIJRI_MONTH = 142930;
	public final int HIJRI_DAY = 142931;
	public final int HIJRI_YEAR = 142939;

	public final int BH = 142940; // Before Hijrah
	public final int AH = 142941; // After Hijrah

	// public final int ERA = 142942;

	public static HijriCalendar getInstance(){
		return new HijriCalendar(Calendar.getInstance());
	}
	
	public HijriCalendar(HijriDay day, HijriMonth month, int year) {
		set(year, month.getMonthAsNumber(), day.getDayAsNumber());
	}

	public HijriCalendar(int year, int month, int date) {
		//set(year, month, day);
		set(YEAR, year);
        set(MONTH, month);
        set(DATE, date);
	}

	public HijriCalendar(Calendar cal) {
		convertGregorianToHijri(cal);

	}

	@Override
	public void add(int field, int amount) {
		switch (field) {
		case DAY_OF_MONTH:
			Calendar calendarDAY_OF_MONTH = toGregorianCalendar();
			calendarDAY_OF_MONTH.add(DAY_OF_MONTH, amount);
			convertGregorianToHijri(calendarDAY_OF_MONTH);
			break;
		case DAY_OF_YEAR:
			Calendar calendarDAY_OF_YEAR = toGregorianCalendar();
			calendarDAY_OF_YEAR.add(DAY_OF_YEAR, amount);
			convertGregorianToHijri(calendarDAY_OF_YEAR);
			break;
		case MONTH:
			if(amount > 0){
				//plus: add more months.
				int actualAmountOfDays = 0;
				for(int i = 0; i < amount; i++){
					if(get(MONTH)+i < 12)
						actualAmountOfDays += numberOfHijriDays(get(MONTH)+i, get(YEAR));
					else
						actualAmountOfDays += numberOfHijriDays((get(MONTH)+i%12), get(YEAR) + (get(MONTH)+i/12));
				}
				Calendar nextMonth = toGregorianCalendar();
				nextMonth.add(DAY_OF_YEAR, actualAmountOfDays);
				convertGregorianToHijri(nextMonth);
			}else if(amount < 0){
				//minus: remove months.
				int actualAmountOfDays = 0;
				for(int i = 1; i <= -amount; i++){
					if(get(MONTH)-i < 1){
						int month = (-(get(MONTH)-i%-12)) == 0?12:(-(get(MONTH)-i%-12));
						actualAmountOfDays += numberOfHijriDays(month , get(YEAR) - (get(MONTH)-i/-12));
					}else
						actualAmountOfDays += numberOfHijriDays(get(MONTH)-i, get(YEAR));
				}
				Calendar preMonth = toGregorianCalendar();
				preMonth.add(DAY_OF_YEAR, -actualAmountOfDays);
				convertGregorianToHijri(preMonth);
			}
			break;
		case YEAR:
			add(MONTH, amount * getActualMaximum(MONTH));
			break;
		default:
			set(field, get(field) + amount);
			break;
		}
	}

	@Override
	public int getGreatestMinimum(int field) {
		return 0;
	}

	@Override
	public int getLeastMaximum(int field) {
		return 0;
	}

	@Override
	public int getMaximum(int field) {
		int ret = 0;
		switch (field) {
		case DAY_OF_MONTH:
			ret = 30;
			break;
		case DAY_OF_WEEK:
			ret = 7;
		case MONTH:
			ret = 12;
			break;
		case YEAR:
			ret = 9999;
			break;
		case ERA:
			ret = AH;
			break;
		}
		return ret;
	}

	@Override
	public int getMinimum(int field) {
		int ret = 0;
		switch (field) {
		case DAY_OF_MONTH:
		case DAY_OF_WEEK:
		case MONTH:
			ret = 1;
			break;
		case YEAR:
			ret = 0;
			break;
		case ERA:
			ret = BH;
		}
		return ret;
	}
	
	@Override
	public int getActualMaximum(int field) {
		int ret = 0;
		switch (field) {
		case DAY_OF_MONTH:
			ret = numberOfHijriDays(get(MONTH), get(YEAR));
			break;
		case DAY_OF_WEEK:
			ret = 7;
		case MONTH:
			ret = 12;
			break;
		case YEAR:
			ret = 9999;
			break;
		case ERA:
			ret = AH;
			break;
		}
		return ret;
	}
	
	@Override
	public void roll(int field, boolean up) {
	}
	
	public Calendar toGregorianCalendar() {
		GregorianCalendar ret;
		int day = get(DAY_OF_MONTH);
		int month = get(MONTH);
		int year  = get(YEAR);
		
		int gDay = 0;
		int gMonth = 0;
		int gYear = 0;
		int absDate;

		if (year < 0) {
			year++;
		}
		
		absDate = absoluteHijriDate(day, month, year);
		
		gYear = (int) Math.floor(absDate / 366);
		/* Search forward year by year from approximate year */
		while (absDate >= absoluteGregorianDate(1, 1, gYear + 1)) {
			gYear++;
		}
		/* Search forward month by month from January */
		gMonth = 1;
		while (absDate >= absoluteGregorianDate(numberOfGregorianDays(gMonth, gYear), gMonth, gYear)) {
			gMonth++;
		}
		gMonth++;
		gDay = absDate - absoluteGregorianDate(1, gMonth, gYear) + 1;
		if (gYear <= 0) {
			gYear = ((gYear -1) * -1);
		}
		ret = new GregorianCalendar(gYear, gMonth, gDay);
		return ret;
	}

	protected void convertGregorianToHijri(Calendar cal) {
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);

		int hDay = 1;
		int hMonth = 1;
		int hYear = 0;
		int absDate;
		boolean preEpoch = false;
		if (year < 0)
			year++;
		absDate = absoluteGregorianDate(day, month, year);
		/* Search forward/backward year by year from approximate year */
		if (absDate < HIJRI_EPOCH) {
			hYear = 0;
			while (absDate <= absoluteHijriDate(1, 1, hYear)) {
				hYear--;
			}
		} else {
			hYear = (int) Math.floor((absDate - HIJRI_EPOCH - 1) / 355);
			while (absDate >= absoluteHijriDate(1, 1, hYear + 1)) {
				hYear++;
			}
		}
		/* Search forward/backward month by month from Muharram */
		hMonth = 1;
		while (absDate >= absoluteHijriDate(numberOfHijriDays(hMonth, hYear), hMonth,
				hYear)) {
			hMonth++;
		}

		hDay = absDate - absoluteHijriDate(1, hMonth, hYear) + 1;

		/* Account for Pre-Hijrah date correction, year 0 entry */
		if (hYear <= 0) {
			preEpoch = true;
			hYear = ((hYear - 1) * -1);
		}
		set(DAY_OF_MONTH, hDay);
		set(MONTH, hMonth);
		set(YEAR, hYear);
		if (preEpoch) {
			set(ERA, BH);
		} else {
			set(ERA, AH);
		}
		set(DAY_OF_WEEK, (Math.abs(absDate % 7)));

	}

	/* Determine if Hijri passed-in year is a leap year */
	protected boolean isLeapYear(int year) {
		if (Math.abs(((11 * year) + 14) % 30) < 11) {
			return true;
		} else {
			return false;
		}
	}

	/* Determine the number of days in passed-in gregorianmonth/year */
	protected int numberOfGregorianDays(int month, int year) {
		int y = Math.abs(year);

		/* Compute the last date of the month for the Gregorian calendar. */
		switch (month) {
		case 2:
			if ((((y % 4) == 0) && ((y % 100) != 0)) || ((y % 400) == 0))
				return 29;
			else
				return 28;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		default:
			return 31;
		}
	}

	/* Determine the number of days in passed-in hijri month/year */
	protected int numberOfHijriDays(int month, int year) {
		if (((month % 2) == 1) || ((month == 12) && isLeapYear(year))) {
			return 30;
		} else {
			return 29;
		}
	}

	protected int absoluteGregorianDate(int day, int month, int year) {
		int N = day; /* days this month */
		int m;

		for (m = month; m > 0; m--)
			/* days in prior months this year */
			N += numberOfGregorianDays(m, year);

		return (int) (N // days this year
				+ 365 * (year - 1) // previous years days ignoring leap
				+ Math.floor((year - 1) / 4) // Julian leap days before this
												// year..
				- Math.floor((year - 1) / 100) // ..minus prior century years...
		+ Math.floor((year - 1) / 400)); // ..plus prior years divisible by 400
	}

	protected int absoluteHijriDate(int day, int month, int year) {
		int ret = 0;
		ret = (int)(day + (29 * (month - 1)) + Math.floor((double) month / 2)
				+ (354 * (year - 1)) + Math.floor((3 + (11 * year)) / 30)
				+ HIJRI_EPOCH - 1);
		return ret;
	}

	@Override
	protected void computeFields() {
	}

	@Override
	protected void computeTime() {
		// TODO Auto-generated method stub

	}

}
