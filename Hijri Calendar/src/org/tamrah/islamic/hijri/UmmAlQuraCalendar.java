package org.tamrah.islamic.hijri;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Locale;


/**
 * @author abdullah alfadhel
 * @version 0.0.1
 **/

@SuppressWarnings("serial")
public class UmmAlQuraCalendar extends Calendar {
	
	/**
     * Value of the {@link #MONTH} field indicating the
     * first month of the year in the Hijri calendar.
     */
    public final static int MUHARRAM = 1;

    /**
     * Value of the {@link #MONTH} field indicating the
     * second month of the year in the Hijri calendar.
     */
    public final static int SAFAR = 2;

    /**
     * Value of the {@link #MONTH} field indicating the
     * third month of the year in the Hijri calendars.
     */
    public final static int RABI_I = 3;

    /**
     * Value of the {@link #MONTH} field indicating the
     * fourth month of the year in the Hijri calendar.
     */
    public final static int RABI_II = 4;

    /**
     * Value of the {@link #MONTH} field indicating the
     * fifth month of the year in the Hijri calendar.
     */
    public final static int JUMADA_I = 5;

    /**
     * Value of the {@link #MONTH} field indicating the
     * sixth month of the year in the Hijri calendar.
     */
    public final static int JUMADA_II = 6;

    /**
     * Value of the {@link #MONTH} field indicating the
     * seventh month of the year in the Hijri calendar.
     */
    public final static int RAJAB = 7;

    /**
     * Value of the {@link #MONTH} field indicating the
     * eighth month of the year in the Hijri calendar.
     */
    public final static int SHAABAN = 8;

    /**
     * Value of the {@link #MONTH} field indicating the
     * ninth month of the year in the Hijri calendar.
     */
    public final static int RAMADAN = 9;

    /**
     * Value of the {@link #MONTH} field indicating the
     * tenth month of the year in the Hijri calendar.
     */
    public final static int SHAWWAL = 10;

    /**
     * Value of the {@link #MONTH} field indicating the
     * eleventh month of the year in the Hijri calendar.
     */
    public final static int DHU_AL_QIDAH = 11;

    /**
     * Value of the {@link #MONTH} field indicating the
     * twelfth month of the year in the Hijri calendar.
     */
    public final static int DHU_AL_HIJJAH = 12;
    
    public static UmmAlQuraCalendar getInstance(){
		return new UmmAlQuraCalendar(Calendar.getInstance());
	}
	
	//
	public static UmmAlQuraCalendar getInstance(Locale locale){
		return new UmmAlQuraCalendar(Calendar.getInstance(locale));
	}
	
	public UmmAlQuraCalendar(Calendar calendar){
		int[] date = UmmALQura.gregorianToHijri(calendar.get(YEAR), calendar.get(MONTH)+1, calendar.get(DATE));
		set(YEAR, date[0]);
		set(MONTH, date[1]);
		set(DAY_OF_MONTH, date[2]);
		set(DAY_OF_WEEK, date[3]);
		//Set time
		for (int i = AM_PM; i < FIELD_COUNT; i++) {
			fields[i] = calendar.get(i);
		}
	}

	public UmmAlQuraCalendar(int year, int month, int day) {
		set(YEAR, year);
		set(MONTH, month);
		set(DAY_OF_MONTH, day);
	}
	
	public Calendar toGregorianCalendar(){
		Calendar calendar = Calendar.getInstance();
		int[] date = UmmALQura.hijriToGregorian(get(YEAR), get(MONTH), get(DATE));
		
		calendar.set(YEAR, date[0]);
		calendar.set(MONTH, date[1]);
		calendar.set(DAY_OF_MONTH, date[2]);
//		calendar.set(DAY_OF_WEEK, date[3]);
		//Set time
		for (int i = AM_PM; i < FIELD_COUNT; i++) {
			calendar.set(i, fields[i]);
		}
		
		return calendar;
	}

	@Override
	protected void computeTime() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void computeFields() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void set(int field, int value) {
		fields[field] = value;
		computeFields();
	}

	@Override
	public void add(int field, int amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void roll(int field, boolean up) {
		// TODO Auto-generated method stub

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
			ret = 1300;
			break;
		}
		return ret;
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
			ret = 1600;
			break;
//		case ERA:
//			ret = AH;
//			break;
		}
		return ret;
	}
	
	@Override
	public int getActualMaximum(int field) {
		int ret = 0;
		switch (field) {
		case DAY_OF_MONTH:
			ret = UmmALQura.hMonthLength(get(YEAR), get(MONTH));
			break;
		case DAY_OF_WEEK:
			ret = 7;
		case MONTH:
			ret = 12;
			break;
		case YEAR:
			ret = 1600;
			break;
		}
		return ret;
	}

	@Override
	public int getGreatestMinimum(int field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLeastMaximum(int field) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 
	 */
	private static class UmmALQura   
	{
		//
		private static Long longHolder;
		private static Double doubleHolder;
		
	    public static int[] gregorianToHijri(int yg, int mg, int dg){
	        int[] output = new int[4];
	    	int _yh = 0;
	        int _mh = 0;
	        int _dh = 0;
	        int _dayweek = 0;
	        RefSupport<Integer> refVar___0 = new RefSupport<Integer>(_yh);
	        RefSupport<Integer> refVar___1 = new RefSupport<Integer>(_mh);
	        RefSupport<Integer> refVar___2 = new RefSupport<Integer>(_dh);
	        RefSupport<Integer> refVar___3 = new RefSupport<Integer>(_dayweek);
	        int Found = 0;
	        try {
				Found = g2HA(yg,mg,dg,refVar___0,refVar___1,refVar___2,refVar___3);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        _yh = refVar___0.getValue();
	        _mh = refVar___1.getValue();
	        _dh = refVar___2.getValue();
	        _dayweek = refVar___3.getValue();
	        if (Found == 1)
	        {
	            output[0] = _yh;
	        	output[1] = _mh;
	        	output[2] = _dh;
	        	output[3] = _dayweek;
	        }
	        else
	        {
	        	output[0] = 0;
	        	output[1] = 0;
	        	output[2] = 0;
	        	output[3] = 0;
	        } 
	        return output;
	    }

	    public static int[] hijriToGregorian(int yh, int mh, int dh) {
	    	int[] output = new int[4];
	    	int _yg = 0;
	        int _mg = 0;
	        int _dg = 0;
	        int _dayweek = 0;
	        RefSupport<Integer> refVar___4 = new RefSupport<Integer>(yh);
	        RefSupport<Integer> refVar___5 = new RefSupport<Integer>(mh);
	        RefSupport<Integer> refVar___6 = new RefSupport<Integer>(dh);
	        RefSupport<Integer> refVar___7 = new RefSupport<Integer>(_yg);
	        RefSupport<Integer> refVar___8 = new RefSupport<Integer>(_mg);
	        RefSupport<Integer> refVar___9 = new RefSupport<Integer>(_dg);
	        RefSupport<Integer> refVar___10 = new RefSupport<Integer>(_dayweek);
	        int Found = 0;
	        try {
				Found = h2GA(refVar___4,refVar___5,refVar___6,refVar___7,refVar___8,refVar___9,refVar___10);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        yh = refVar___4.getValue();
	        mh = refVar___5.getValue();
	        dh = refVar___6.getValue();
	        _yg = refVar___7.getValue();
	        _mg = refVar___8.getValue();
	        _dg = refVar___9.getValue();
	        _dayweek = refVar___10.getValue();
	        if (Found == 1)
	        {
	            output[0] = _yg;
	        	output[1] = _mg;
	        	output[2] = _dg;
	        	output[3] = _dayweek;
	        }
	        else
	        {
	            output[0] = 0;
	        	output[1] = 0;
	        	output[2] = 0;
	        	output[3] = 0;
	        } 
	        return output;
	    }

	    /*
	      CopyRight by Fayez Alhargan, 2000
	      King Abdulaziz City for Science and Technology
	      Computer and Electronics Research Institute
	      Riyadh, Saudi Arabia
	      alhargan@kacst.edu.sa
	      Tel:4813770 Fax:4813764
	      This is a program that computes the Hijary dates based on the moonset
	      at Makkah.
	      version: 1.01
	      last modified 20-8-2000
	    */
	    private static int HStartYear = 1300;
	    private static int HEndYear = 1600;
	    private static int[] MonthMap = { 17749, 12971, 14647, 17078, 13686, 17260, 15189, 19114, 18774, 13470, 14685, 17082, 13749, 17322, 19275, 19094, 17710, 12973, 13677, 19290, 18258, 20261, 24202, 19734, 19030, 19125, 18100, 19881, 19346, 19237, 17995, 15003, 17242, 18137, 17876, 19877, 19786, 19093, 17718, 14709, 17140, 18153, 18132, 18089, 17717, 12893, 13501, 14778, 17332, 19305, 19242, 19029, 17581, 14941, 17114, 14041, 20138, 24212, 19754, 19542, 17582, 14957, 17770, 19797, 19786, 19091, 13611, 14939, 17722, 14005, 20137, 23890, 19753, 19029, 17581, 13677, 19178, 18148, 20177, 23970, 19114, 18778, 17114, 13753, 19378, 18276, 18121, 17749, 12971, 13531, 19130, 17844, 19881, 23890, 19109, 18733, 12909, 14573, 17114, 15061, 19109, 19019, 13463, 14647, 17078, 14709, 19817, 19794, 19605, 18731, 12891, 13531, 18901, 17874, 19877, 19786, 19093, 17741, 15021, 17322, 19410, 19396, 19337, 19093, 13613, 13741, 15210, 18132, 19913, 19858, 19110, 18774, 12974, 13677, 13162, 15189, 19114, 14669, 13469, 14685, 12986, 13749, 17834, 15701, 19098, 14638, 12910, 13661, 15066, 18132, 18085, 13643, 14999, 17742, 15022, 17836, 15273, 19858, 19237, 13899, 15531, 17754, 15189, 18130, 16037, 20042, 19093, 13613, 15021, 17260, 14169, 18130, 18069, 13613, 14939, 13498, 14778, 17332, 15209, 19282, 19110, 13494, 14701, 17132, 14041, 20146, 19796, 19754, 19030, 13486, 14701, 19818, 19284, 19241, 14995, 13611, 14935, 13622, 15029, 18090, 16019, 19733, 17963, 15451, 17722, 14005, 19890, 23908, 19753, 19029, 17581, 14701, 19178, 18152, 20177, 23972, 19786, 19050, 17114, 13753, 19314, 23400, 18129, 18005, 13483, 14683, 17082, 13749, 19881, 23890, 19622, 18766, 17518, 14685, 17626, 15061, 19114, 19021, 13467, 14647, 17590, 14709, 19818, 19794, 19109, 18763, 12971, 13659, 19161, 17874, 19909, 23954, 19237, 17749, 15029, 17844, 19369, 18338, 18245, 17811, 15019, 17622, 18902, 17874, 19365, 19274, 19093, 17581, 12637, 13021, 18906, 17844, 17833, 13613, 12891, 14519, 12662, 13677, 19306, 19146, 19094, 17707, 12635, 12987, 13750, 19882, 23444, 19782, 19085, 17709, 15005, 17754, 14165, 18249, 20243, 20042, 19094, 17750, 14005, 19370, 23444 };
	    private static short[] gmonth = { 31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 31 };
	    /* makes it circular m[0]=m[12] & m[13]=m[1] */
	    private static short[] smonth = { 31, 30, 30, 30, 30, 30, 29, 31, 31, 31, 31, 31, 31, 30 };
	    /* makes it circular m[0]=m[12] & m[13]=m[1]  */
	    //int  BH2GA(int yh,int mh,int *yg,int *mg, int *dg,int *dayweek);
	    //int  G2HA(int yg,int mg, int dg,int *yh,int *mh,int *dh,int *dayweek);
	    //int  H2GA(int *yh,int *mh,int *dh,int *yg,int *mg, int *dg,int *dayweek);
	    //void S2G(int ys,int ms,int ds,int *yg,int *mg,int *dg);
	    //void G2S(int yg,int mg,int dg,int *ys,int *ms,int *ds);
	    //double GCalendarToJD(int yg,int mg, double dg );
	    //double JDToGCalendar(double JD, int *yy,int *mm, int *dd);
	    //int GLeapYear(int year);
	    //void GDateAjust(int *yg,int *mg,int *dg);
	    //int DayWeek(long JulianD);
	    //double SCalendarToJD(int ys, int ms,double ds );
	    //void JDToSCalendar(double JD, int *ys, int *ms,int *ds);
	    //int HSLeapYear(long year);
	    //void SDateAjust(int *ys,int *ms,int *ds);
	    //void JDToHCalendar(double JD,int *yh,int *mh,int *dh);
	    //double HCalendarToJD(int yh,int mh,int dh);
	    //int HMonthLength(int yh,int mh);
	    //double MSCalendarToJD(int ys, int ms,double ds );
	    //void JDToMSCalendar(double JD, int *ys, int *ms,int *ds);
	    //int HMSLeapYear(long year);
	    //void MS2G(int ys,int ms,int ds,int *yg,int *mg,int *dg);
	    //void G2MS(int ys,int ms,int ds,int *yg,int *mg,int *dg);
	    //double ip(double x);
	    //int mod(double x, double y);
	    /****************************************************************************/
	    /* Name:    BH2GA                                                            */
	    /* Type:    Procedure                                                       */
	    /* Purpose: Finds Gdate(year,month,day) for Hdate(year,month,day=1)  	    */
	    /*   Computation Based  on Store data  MonthMap                             */
	    /* Arguments:                                                               */
	    /* Input: Hijrah  date: year:yh, month:mh                                   */
	    /* Output: Gregorian date: year:yg, month:mg, day:dg , day of week:dayweek  */
	    /*       and returns flag found:1 not found:0                               */
	    /****************************************************************************/
	    private static int bH2GA(int yh, int mh, RefSupport<Integer> yg, RefSupport<Integer> mg, RefSupport<Integer> dg, RefSupport<Integer> dayweek) throws Exception {
	        int flag, Dy, m, b;
	        long JD;
	        double GJD;
	        /* Make sure that the date is within the range of the tables */
	        if (mh < 1)
	        {
	            mh = 12;
	        }
	         
	        if (mh > 12)
	        {
	            mh = 1;
	        }
	         
	        if (yh < HStartYear)
	        {
	            yh = HStartYear;
	        }
	         
	        if (yh > HEndYear)
	        {
	            yh = HEndYear;
	        }
	        doubleHolder = hCalendarToJD(yh,1,1);
	        JD = doubleHolder.longValue();
	        /* estimate JD of the begining of the year */
	        Dy = MonthMap[yh - HStartYear] / 4096;
	        /* Mask 1111000000000000 */
	        GJD = JD - 3 + Dy;
	        /* correct the JD value from stored tables  */
	        b = MonthMap[yh - HStartYear];
	        b = b - Dy * 4096;
	        for (m = 1;m < mh;m++)
	        {
	            flag = b % 2;
	            /* Mask for the current month */
	            if (flag == 1)
	                Dy = 30;
	            else
	                Dy = 29; 
	            GJD = GJD + Dy;
	            /* Add the months lengths before mh */
	            b = (b - flag) / 2;
	        }
	        RefSupport<Integer> refVar___11 = new RefSupport<Integer>(yg.getValue());
	        RefSupport<Integer> refVar___12 = new RefSupport<Integer>(mg.getValue());
	        RefSupport<Integer> refVar___13 = new RefSupport<Integer>(dg.getValue());
	        jDToGCalendar(GJD,refVar___11,refVar___12,refVar___13);
	        yg.setValue(refVar___11.getValue());
	        mg.setValue(refVar___12.getValue());
	        dg.setValue(refVar___13.getValue());
	        doubleHolder = GJD;
	        JD = doubleHolder.longValue();
	        longHolder = (JD + 1) % 7;
	        dayweek.setValue(longHolder.intValue());
	        flag = 1;
	        return flag;
	    }

	    /* date has been found */
	    /****************************************************************************/
	    /* Name:    HMonthLength						    */
	    /* Type:    Function                                                        */
	    /* Purpose: Obtains the month length            		     	    */
	    /* Arguments:                                                               */
	    /* Input : Hijrah  date: year:yh, month:mh                                  */
	    /* Output:  Month Length                                                    */
	    /****************************************************************************/
	    private static int hMonthLength(int yh, int mh) {
	        int flag, Dy, N, m;
	        if (yh < HStartYear || yh > HEndYear)
	        {
	            flag = 0;
	            Dy = 0;
	        }
	        else
	        {
	            N = 1;
	            for (m = 1;m < mh;m++)
	                N = 2 * N;
	            flag = MonthMap[yh - HStartYear] & N;
	            /* Mask for the current month */
	            if (flag == 1)
	                Dy = 30;
	            else
	                Dy = 29; 
	        } 
	        return Dy;
	    }

	    /****************************************************************************/
	    /* Name:    G2HA                                                            */
	    /* Type:    Procedure                                                       */
	    /* Purpose: convert Gdate(year,month,day) to Hdate(year,month,day)          */
	    /* Arguments:                                                               */
	    /* Input: Gregorian date: year:yg, month:mg, day:dg                         */
	    /* Output: Hijrah  date: year:yh, month:mh, day:dh, day of week:dayweek     */
	    /*       and returns flag found:1 not found:0                               */
	    /****************************************************************************/
	    private static int g2HA(int yg, int mg, int dg, RefSupport<Integer> yh, RefSupport<Integer> mh, RefSupport<Integer> dh, RefSupport<Integer> dayweek) throws Exception {
	        int yh1 = 0, mh1 = 0, dh1 = 0;
	        int yh2, mh2, dh2;
	        int yg1 = 0, mg1 = 0, dg1 = 0;
	        int yg2 = 0, mg2 = 0, dg2 = 0;
	        int found;
	        int flag;
	        long J;
	        double GJD;
	        GJD = gCalendarToJD(yg, mg, dg + 0.5);
	        /* find JD of Gdate */
	        RefSupport<Integer> refVar___14 = new RefSupport<Integer>(yh1);
	        RefSupport<Integer> refVar___15 = new RefSupport<Integer>(mh1);
	        RefSupport<Integer> refVar___16 = new RefSupport<Integer>(dh1);
	        jDToHCalendar(GJD,refVar___14,refVar___15,refVar___16);
	        yh1 = refVar___14.getValue();
	        mh1 = refVar___15.getValue();
	        dh1 = refVar___16.getValue();
	        /* estimate the Hdate that correspond to the Gdate */
	        found = 0;
	        flag = 1;
	        while ((!(found == 1)) && (flag == 1))
	        {
	            /* start searching for the exact Hdate */
	            RefSupport<Integer> refVar___17 = new RefSupport<Integer>(yh1);
	            RefSupport<Integer> refVar___18 = new RefSupport<Integer>(mh1);
	            RefSupport<Integer> refVar___19 = new RefSupport<Integer>(dh1);
	            RefSupport<Integer> refVar___20 = new RefSupport<Integer>(yg1);
	            RefSupport<Integer> refVar___21 = new RefSupport<Integer>(mg1);
	            RefSupport<Integer> refVar___22 = new RefSupport<Integer>(dg1);
	            RefSupport<Integer> refVar___23 = new RefSupport<Integer>(dayweek.getValue());
	            flag = h2GA(refVar___17,refVar___18,refVar___19,refVar___20,refVar___21,refVar___22,refVar___23);
	            yh1 = refVar___17.getValue();
	            mh1 = refVar___18.getValue();
	            dh1 = refVar___19.getValue();
	            yg1 = refVar___20.getValue();
	            mg1 = refVar___21.getValue();
	            dg1 = refVar___22.getValue();
	            dayweek.setValue(refVar___23.getValue());
	            /* compute the exact correponding Gdate for the dh1-mh1-yh1 */
	            if (yg1 > yg)
	            {
	                dh1--;
	                if (dh1 < 1)
	                {
	                    dh1 = 29 + dh1;
	                    mh1--;
	                }
	                 
	            }
	             
	            if (yg1 < yg)
	            {
	                dh1++;
	                if (dh1 > 30)
	                {
	                    dh1 = dh1 - 30;
	                    mh1++;
	                }
	                 
	                if (dh1 == 30)
	                {
	                    dh2 = 1;
	                    mh2 = mh1 + 1;
	                    yh2 = yh1;
	                    if (mh2 > 12)
	                    {
	                        yh2++;
	                        mh2 = mh2 - 12;
	                    }
	                     
	                    RefSupport<Integer> refVar___24 = new RefSupport<Integer>(yh2);
	                    RefSupport<Integer> refVar___25 = new RefSupport<Integer>(mh2);
	                    RefSupport<Integer> refVar___26 = new RefSupport<Integer>(dh2);
	                    RefSupport<Integer> refVar___27 = new RefSupport<Integer>(yg2);
	                    RefSupport<Integer> refVar___28 = new RefSupport<Integer>(mg2);
	                    RefSupport<Integer> refVar___29 = new RefSupport<Integer>(dg2);
	                    RefSupport<Integer> refVar___30 = new RefSupport<Integer>(dayweek.getValue());
	                    flag = h2GA(refVar___24,refVar___25,refVar___26,refVar___27,refVar___28,refVar___29,refVar___30);
	                    yh2 = refVar___24.getValue();
	                    mh2 = refVar___25.getValue();
	                    dh2 = refVar___26.getValue();
	                    yg2 = refVar___27.getValue();
	                    mg2 = refVar___28.getValue();
	                    dg2 = refVar___29.getValue();
	                    dayweek.setValue(refVar___30.getValue());
	                    if (dg2 == dg)
	                    {
	                        mh1++;
	                        dh1 = 1;
	                    }
	                     
	                }
	                 
	            }
	             
	            /* check to see that if 30 is actually 1st of next month */
	            if (yg1 == yg)
	            {
	                if (mg1 > mg)
	                {
	                    dh1--;
	                    if (dh1 < 1)
	                    {
	                        dh1 = 29 + dh1;
	                        mh1--;
	                    }
	                     
	                }
	                 
	                if (mg1 < mg)
	                {
	                    dh1++;
	                    if (dh1 > 30)
	                    {
	                        dh1 = dh1 - 30;
	                        mh1++;
	                    }
	                     
	                    if (dh1 == 30)
	                    {
	                        dh2 = 1;
	                        mh2 = mh1 + 1;
	                        yh2 = yh1;
	                        if (mh2 > 12)
	                        {
	                            yh2++;
	                            mh2 = mh2 - 12;
	                        }
	                         
	                        RefSupport<Integer> refVar___31 = new RefSupport<Integer>(yh2);
	                        RefSupport<Integer> refVar___32 = new RefSupport<Integer>(mh2);
	                        RefSupport<Integer> refVar___33 = new RefSupport<Integer>(dh2);
	                        RefSupport<Integer> refVar___34 = new RefSupport<Integer>(yg2);
	                        RefSupport<Integer> refVar___35 = new RefSupport<Integer>(mg2);
	                        RefSupport<Integer> refVar___36 = new RefSupport<Integer>(dg2);
	                        RefSupport<Integer> refVar___37 = new RefSupport<Integer>(dayweek.getValue());
	                        flag = h2GA(refVar___31,refVar___32,refVar___33,refVar___34,refVar___35,refVar___36,refVar___37);
	                        yh2 = refVar___31.getValue();
	                        mh2 = refVar___32.getValue();
	                        dh2 = refVar___33.getValue();
	                        yg2 = refVar___34.getValue();
	                        mg2 = refVar___35.getValue();
	                        dg2 = refVar___36.getValue();
	                        dayweek.setValue(refVar___37.getValue());
	                        if (dg2 == dg)
	                        {
	                            mh1++;
	                            dh1 = 1;
	                        }
	                         
	                    }
	                     
	                }
	                 
	                /* check to see that if 30 is actually 1st of next month */
	                if (mg1 == mg && yg1 == yg)
	                {
	                    /* if the months are equal than adjust the days */
	                    found = 1;
	                    if (dg1 > dg)
	                    {
	                        dh1 = dh1 - (dg1 - dg);
	                        found = 0;
	                    }
	                     
	                    if (dg1 < dg)
	                    {
	                        dh1 = dh1 - (dg1 - dg);
	                        found = 0;
	                    }
	                     
	                    if (dh1 < 1)
	                    {
	                        dh1 = 29 + dh1;
	                        mh1--;
	                    }
	                     
	                    if (dh1 > 30)
	                    {
	                        dh1 = dh1 - 30;
	                        mh1++;
	                    }
	                     
	                    if (dh1 == 30)
	                    {
	                        dh2 = 1;
	                        mh2 = mh1 + 1;
	                        yh2 = yh1;
	                        if (mh2 > 12)
	                        {
	                            yh2++;
	                            mh2 = mh2 - 12;
	                        }
	                         
	                        RefSupport<Integer> refVar___38 = new RefSupport<Integer>(yh2);
	                        RefSupport<Integer> refVar___39 = new RefSupport<Integer>(mh2);
	                        RefSupport<Integer> refVar___40 = new RefSupport<Integer>(dh2);
	                        RefSupport<Integer> refVar___41 = new RefSupport<Integer>(yg2);
	                        RefSupport<Integer> refVar___42 = new RefSupport<Integer>(mg2);
	                        RefSupport<Integer> refVar___43 = new RefSupport<Integer>(dg2);
	                        RefSupport<Integer> refVar___44 = new RefSupport<Integer>(dayweek.getValue());
	                        flag = h2GA(refVar___38,refVar___39,refVar___40,refVar___41,refVar___42,refVar___43,refVar___44);
	                        yh2 = refVar___38.getValue();
	                        mh2 = refVar___39.getValue();
	                        dh2 = refVar___40.getValue();
	                        yg2 = refVar___41.getValue();
	                        mg2 = refVar___42.getValue();
	                        dg2 = refVar___43.getValue();
	                        dayweek.setValue(refVar___44.getValue());
	                        if (dg2 == dg)
	                        {
	                            mh1++;
	                            dh1 = 1;
	                        }
	                         
	                    }
	                     
	                }
	                 
	            }
	             
	            /* check to see that if 30 is actually 1st of next month */
	            if (mh1 < 1)
	            {
	                yh1--;
	                mh1 = 12 + mh1;
	            }
	             
	            if (mh1 > 12)
	            {
	                yh1++;
	                mh1 = mh1 - 12;
	            }
	             
	        }
	        doubleHolder = gCalendarToJD(yg, mg, dg) + 2;
	        J = doubleHolder.longValue();
	        longHolder = J % 7;
	        dayweek.setValue(longHolder.intValue());
	        yh.setValue(yh1);
	        mh.setValue(mh1);
	        dh.setValue(dh1);
	        return flag;
	    }

	    /****************************************************************************/
	    /* Name:    H2GA                                                            */
	    /* Type:    Procedure                                                       */
	    /* Purpose: convert Hdate(year,month,day) to Gdate(year,month,day)          */
	    /* Arguments:                                                               */
	    /* Input/Ouput: Hijrah  date: year:yh, month:mh, day:dh                     */
	    /* Output: Gregorian date: year:yg, month:mg, day:dg , day of week:dayweek  */
	    /*       and returns flag found:1 not found:0                               */
	    /* Note: The function will correct Hdate if day=30 and the month is 29 only */
	    /****************************************************************************/
	    private static int h2GA(RefSupport<Integer> yh, RefSupport<Integer> mh, RefSupport<Integer> dh, RefSupport<Integer> yg, RefSupport<Integer> mg, RefSupport<Integer> dg, RefSupport<Integer> dayweek) throws Exception {
	        int found, yh1 = 0, mh1 = 0, yg1 = 0, mg1 = 0, dg1 = 0, dw1 = 0;
	        /*find the date of the begining of the month*/
	        /* make sure values are within the allowed values */
	        if (dh.getValue() > 30)
	        {
	            dh.setValue(1);
	            mh.setValue(mh.getValue()+1);
	        }
	         
	        if (dh.getValue() < 1)
	        {
	            dh.setValue(1);
	            mh.setValue(mh.getValue()-1);
	        }
	         
	        if (mh.getValue() > 12)
	        {
	            mh.setValue(1);
	            yh.setValue(yh.getValue()+1);
	        }
	         
	        if (mh.getValue() < 1)
	        {
	            mh.setValue(12);
	            yh.setValue(yh.getValue()-1);
	        }
	         
	        RefSupport<Integer> refVar___45 = new RefSupport<Integer>(yg.getValue());
	        RefSupport<Integer> refVar___46 = new RefSupport<Integer>(mg.getValue());
	        RefSupport<Integer> refVar___47 = new RefSupport<Integer>(dg.getValue());
	        RefSupport<Integer> refVar___48 = new RefSupport<Integer>(dayweek.getValue());
	        found = bH2GA(yh.getValue(),mh.getValue(),refVar___45,refVar___46,refVar___47,refVar___48);
	        yg.setValue(refVar___45.getValue());
	        mg.setValue(refVar___46.getValue());
	        dg.setValue(refVar___47.getValue());
	        dayweek.setValue(refVar___48.getValue());
	        dg.setValue(dg.getValue() + dh.getValue() - 1);
	        RefSupport<Integer> refVar___49 = new RefSupport<Integer>(yg.getValue());
	        RefSupport<Integer> refVar___50 = new RefSupport<Integer>(mg.getValue());
	        RefSupport<Integer> refVar___51 = new RefSupport<Integer>(dg.getValue());
	        gDateAjust(refVar___49,refVar___50,refVar___51);
	        yg.setValue(refVar___49.getValue());
	        mg.setValue(refVar___50.getValue());
	        dg.setValue(refVar___51.getValue());
	        /* Make sure that dates are within the correct values */
	        dayweek.setValue(dayweek.getValue() + dh.getValue() - 1);
	        dayweek.setValue(dayweek.getValue() % 7);
	        /*find the date of the begining of the next month*/
	        if (dh.getValue() == 30)
	        {
	            mh1 = mh.getValue() + 1;
	            yh1 = yh.getValue();
	            if (mh1 > 12)
	            {
	                mh1 = mh1 - 12;
	                yh1++;
	            }
	             
	            RefSupport<Integer> refVar___52 = new RefSupport<Integer>(yg1);
	            RefSupport<Integer> refVar___53 = new RefSupport<Integer>(mg1);
	            RefSupport<Integer> refVar___54 = new RefSupport<Integer>(dg1);
	            RefSupport<Integer> refVar___55 = new RefSupport<Integer>(dw1);
	            found = bH2GA(yh1,mh1,refVar___52,refVar___53,refVar___54,refVar___55);
	            yg1 = refVar___52.getValue();
	            mg1 = refVar___53.getValue();
	            dg1 = refVar___54.getValue();
	            dw1 = refVar___55.getValue();
	            if (dg.getValue() == dg1)
	            {
	                yh.setValue(yh1);
	                mh.setValue(mh1);
	                dh.setValue(1);
	            }
	             
	        }
	         
	        return found;
	    }

	    /* Make sure that the month is 30days if not make adjustment */
	    /****************************************************************************/
	    /* Name:    S2G                                                             */
	    /* Type:    Procedure                                                       */
	    /* Purpose: convert SHdate(year,month,day) to Gdate(year,month,day)         */
	    /* Arguments:                                                               */
	    /* Input:  Solar Hijrah  date: year:ys, month:ms, day:ds                    */
	    /* Output: Gregorian date: year:yg, month:mg, day:dg                        */
	    /****************************************************************************/
	    private static void s2G(int ys, int ms, int ds, RefSupport<Integer> yg, RefSupport<Integer> mg, RefSupport<Integer> dg) throws Exception {
	        double SJD;
	        SJD = sCalendarToJD(ys, ms, ds);
	        RefSupport<Integer> refVar___56 = new RefSupport<Integer>(yg.getValue());
	        RefSupport<Integer> refVar___57 = new RefSupport<Integer>(mg.getValue());
	        RefSupport<Integer> refVar___58 = new RefSupport<Integer>(dg.getValue());
	        jDToGCalendar(SJD,refVar___56,refVar___57,refVar___58);
	        yg.setValue(refVar___56.getValue());
	        mg.setValue(refVar___57.getValue());
	        dg.setValue(refVar___58.getValue());
	    }

	    /****************************************************************************/
	    /* Name:    G2S                                                             */
	    /* Type:    Procedure                                                       */
	    /* Purpose: convert Gdate(year,month,day) to SHdate(year,month,day)         */
	    /* Arguments:                                                               */
	    /* Input: Gregorian date: year:yg, month:mg, day:dg                         */
	    /* Ouput: Solar Hijrah  date: year:ys, month:ms, day:ds                     */
	    /****************************************************************************/
	    private static void g2S(int yg, int mg, int dg, RefSupport<Integer> ys, RefSupport<Integer> ms, RefSupport<Integer> ds) throws Exception {
	        double SJD;
	        SJD = gCalendarToJD(yg, mg, dg + 0.5);
	        RefSupport<Integer> refVar___59 = new RefSupport<Integer>(ys.getValue());
	        RefSupport<Integer> refVar___60 = new RefSupport<Integer>(ms.getValue());
	        RefSupport<Integer> refVar___61 = new RefSupport<Integer>(ds.getValue());
	        jDToSCalendar(SJD,refVar___59,refVar___60,refVar___61);
	        ys.setValue(refVar___59.getValue());
	        ms.setValue(refVar___60.getValue());
	        ds.setValue(refVar___61.getValue());
	    }

	    /****************************************************************************/
	    /* Name:    JDToGCalendar						    */
	    /* Type:    Procedure                                                       */
	    /* Purpose: convert Julian Day  to Gdate(year,month,day)                    */
	    /* Arguments:                                                               */
	    /* Input:  The Julian Day: JD                                               */
	    /* Output: Gregorian date: year:yy, month:mm, day:dd                        */
	    /****************************************************************************/
	    private static double jDToGCalendar(double JD, RefSupport<Integer> yy, RefSupport<Integer> mm, RefSupport<Integer> dd) throws Exception {
	        double A, B, F;
	        int alpha, C, E;
	        long D, Z;
	        Z = (long)Math.floor(JD + 0.5);
	        F = (JD + 0.5) - Z;
	        alpha = (int)((Z - 1867216.25) / 36524.25);
	        A = Z + 1 + alpha - alpha / 4;
	        B = A + 1524;
	        C = (int)((B - 122.1) / 365.25);
	        D = (long)(365.25 * C);
	        E = (int)((B - D) / 30.6001);
	        Double holder = B - D - Math.floor(30.6001 * E) + F;
	        dd.setValue(holder.intValue());
	        if (E < 14)
	            mm.setValue(E - 1);
	        else
	            mm.setValue(E - 13); 
	        if (mm.getValue() > 2)
	            yy.setValue(C - 4716);
	        else
	            yy.setValue(C - 4715); 
	        F = F * 24.0;
	        return F;
	    }

	    /****************************************************************************/
	    /* Name:    GCalendarToJD						    */
	    /* Type:    Function                                                        */
	    /* Purpose: convert Gdate(year,month,day) to Julian Day            	    */
	    /* Arguments:                                                               */
	    /* Input : Gregorian date: year:yy, month:mm, day:dd                        */
	    /* Output:  The Julian Day: JD                                              */
	    /****************************************************************************/
	    private static double gCalendarToJD(int yy, int mm, double dd) throws Exception {
	        /* it does not take care of 1582correction assumes correct calender from the past  */
	        int A, B, m, y;
	        double T1, T2, Tr;
	        if (mm > 2)
	        {
	            y = yy;
	            m = mm;
	        }
	        else
	        {
	            y = yy - 1;
	            m = mm + 12;
	        } 
	        A = y / 100;
	        B = 2 - A + A / 4;
	        T1 = ip(365.25 * (y + 4716));
	        T2 = ip(30.6001 * (m + 1));
	        Tr = T1 + T2 + dd + B - 1524.5;
	        return Tr;
	    }

	    /****************************************************************************/
	    /* Name:    GLeapYear						            */
	    /* Type:    Function                                                        */
	    /* Purpose: Determines if  Gdate(year) is leap or not            	    */
	    /* Arguments:                                                               */
	    /* Input : Gregorian date: year				                    */
	    /* Output:  0:year not leap   1:year is leap                                */
	    /****************************************************************************/
	    private static int gLeapYear(int year) throws Exception {
	        int T;
	        T = 0;
	        if (year % 4 == 0)
	            T = 1;
	         
	        /* leap_year=1; */
	        if (year % 100 == 0)
	        {
	            T = 0;
	            /* years=100,200,300,500,... are not leap years */
	            if (year % 400 == 0)
	                T = 1;
	             
	        }
	         
	        return T;
	    }

	    /*  years=400,800,1200,1600,2000,2400 are leap years */
	    /****************************************************************************/
	    /* Name:    GDateAjust							    */
	    /* Type:    Procedure                                                       */
	    /* Purpose: Adjust the G Dates by making sure that the month lengths        */
	    /*	    are correct if not so take the extra days to next month or year */
	    /* Arguments:                                                               */
	    /* Input: Gregorian date: year:yg, month:mg, day:dg                         */
	    /* Output: corrected Gregorian date: year:yg, month:mg, day:dg              */
	    /****************************************************************************/
	    private static void gDateAjust(RefSupport<Integer> yg, RefSupport<Integer> mg, RefSupport<Integer> dg) throws Exception {
	        int dys;
	        /* Make sure that dates are within the correct values */
	        /*  Underflow  */
	        if (mg.getValue() < 1)
	        {
	            /* months underflow */
	            mg.setValue(12 + mg.getValue());
	            /* plus as the underflow months is negative */
	            yg.setValue(yg.getValue() - 1);
	        }
	         
	        if (dg.getValue() < 1)
	        {
	            /* days underflow */
	            mg.setValue(mg.getValue() - 1);
	            /* month becomes the previous month */
	            dg.setValue(gmonth[mg.getValue()] + dg.getValue());
	            /* number of days of the month less the underflow days (it is plus as the sign of the day is negative) */
	            if (mg.getValue() == 2)
	                dg.setValue(dg.getValue() + gLeapYear(yg.getValue()));
	             
	            if (mg.getValue() < 1)
	            {
	                /* months underflow */
	                mg.setValue(12 + mg.getValue());
	                /* plus as the underflow months is negative */
	                yg.setValue(yg.getValue() - 1);
	            }
	             
	        }
	         
	        /* Overflow  */
	        if (mg.getValue() > 12)
	        {
	            /* months */
	            mg.setValue(mg.getValue() - 12);
	            yg.setValue(yg.getValue() + 1);
	        }
	         
	        if (mg.getValue() == 2)
	            dys = gmonth[mg.getValue()] + gLeapYear(yg.getValue());
	        else
	            /* number of days in the current month */
	            dys = gmonth[mg.getValue()]; 
	        if (dg.getValue() > dys)
	        {
	            /* days overflow */
	            dg.setValue(dg.getValue() - dys);
	            mg.setValue(mg.getValue() + 1);
	            if (mg.getValue() == 2)
	            {
	                dys = gmonth[mg.getValue()] + gLeapYear(yg.getValue());
	                /* number of days in the current month */
	                if (dg.getValue() > dys)
	                {
	                    dg.setValue(dg.getValue() - dys);
	                    mg.setValue(mg.getValue() + 1);
	                }
	                 
	            }
	             
	            if (mg.getValue() > 12)
	            {
	                /* months */
	                mg.setValue(mg.getValue() - 12);
	                yg.setValue(yg.getValue() + 1);
	            }
	             
	        }
	         
	    }

	    /*
	      The day of the week is obtained as
	      Dy=(Julian+1)%7
	      Dy=0 Sunday
	      Dy=1 Monday
	      ...
	      Dy=6 Saturday
	    */
	    private static int dayWeek(long JulianD) throws Exception {
	        int Dy;
	        longHolder = (JulianD + 1) % 7;
	        Dy = longHolder.intValue();
	        return Dy;
	    }

	    /****************************************************************************/
	    /* Name:    HSLeapYear						            */
	    /* Type:    Function                                                        */
	    /* Purpose: Determines of  HSdate(year) is leap or not            	    */
	    /* Arguments:                                                               */
	    /* Input : Hijrah Solar date: year			                    */
	    /* Output:  0:year not leap   1:year is leap                                */
	    /****************************************************************************/
	    private static int hSLeapYear(long year) throws Exception {
	        /* Leap year test for hijrah solar years */
	        int r1, r2;
	        if (year == 0)
	            return 0;
	         
	        /* not leap year */
	        r1 = mod(year, 128);
	        r2 = mod(r1, 4);
	        if (r1 == 0)
	            return 0;
	         
	        /*  year is not leap */
	        if (r2 == 0)
	            return 1;
	         
	        return 0;
	    }

	    /* year is leap      */
	    /****************************************************************************/
	    /* Name:    SCalendarToJD						    */
	    /* Type:    Function                                                        */
	    /* Purpose: convert Sdate(year,month,day) to Julian Day            	    */
	    /* Arguments:                                                               */
	    /* Input : Hijrah Solar date: year:ys, month:ms, day:ds                     */
	    /* Output:  The Julian Day: JD                                              */
	    /****************************************************************************/
	    private static double sCalendarToJD(int ys, int ms, double ds) throws Exception {
	        /*
	          Given Solar Hijrah date find  JD
	         */
	        int a, b, m6;
	        double T1, T2 = 0, Tr;
	        a = (ys - 1) / 128;
	        b = (ys - 1) / 4;
	        b = b - a;
	        T1 = (ys - 1) * 365.0 + b + ds;
	        m6 = 29 + hSLeapYear(ys);
	        if (ms < 7)
	            T2 = 30 * (ms - 1);
	         
	        if (ms == 7)
	            T2 = 30.0 * 5 + m6;
	         
	        if (ms > 7)
	            T2 = 30.0 * 5 + m6 + 31 * (ms - 7);
	         
	        Tr = T1 + T2 + 1948506.0;
	        return Tr;
	    }

	    /*  Add JD for 23/9/622 the first Solar Hijrah date*/
	    /****************************************************************************/
	    /* Name:    JDToSCalendar						    */
	    /* Type:    Procedure                                                       */
	    /* Purpose: convert Julian Day to Sdate(year,month,day)  		    */
	    /* Arguments:                                                               */
	    /* Input:  The Julian Day: JD                                               */
	    /* Output : Solar Hijrah date: year:ys, month:ms, day:ds                    */
	    /****************************************************************************/
	    private static void jDToSCalendar(double JD, RefSupport<Integer> ys, RefSupport<Integer> ms, RefSupport<Integer> ds) throws Exception {
	        /*
	           From JD day find Solar Hijrah date
	         */
	        int r1, r2, m6;
	        double J, dd;
	        J = JD - 1948506;
	        /*  substract JD for 23/9/622 the first Solar Hijrah date*/
	        doubleHolder = J / 365;
	        ys.setValue(doubleHolder.intValue());
	        ys.setValue(ys.getValue()-1);
	        r1 = (ys.getValue() - mod(ys.getValue(), 128)) / 128;
	        /* Find the number of non-leap years divisible by 4*/
	        r2 = (ys.getValue() - mod(ys.getValue(), 4)) / 4;
	        /* Find the number of leap years */
	        J = J - (r2 - r1);
	        doubleHolder = J / 365;
	        ys.setValue(doubleHolder.intValue());
	        dd = J - ys.getValue() * 365.0;
	        doubleHolder = dd;
	        ds.setValue(doubleHolder.intValue());
	        ys.setValue(ys.getValue()+1);
	        if (ds.getValue() < 1)
	        {
	        	ys.setValue(ys.getValue()-1);
	            ds.setValue(ds.getValue() + 365);
	        }
	         
	        ms.setValue(1);
	        while (ds.getValue() > 30 && ms.getValue() < 6)
	        {
	        	ms.setValue(ms.getValue()+1);
	            ds.setValue(ds.getValue() - 30);
	        }
	        m6 = 29 + hSLeapYear(ys.getValue());
	        if (ds.getValue() > m6 && ms.getValue() == 6)
	        {
	        	ms.setValue(ms.getValue()+1);
	            ds.setValue(ds.getValue() - m6);
	        }
	         
	        while (ds.getValue() > 31)
	        {
	        	ms.setValue(ms.getValue()+1);
	            ds.setValue(ds.getValue() - 31);
	        }
	        if (ds.getValue() == 0)
	        	ds.setValue(ds.getValue()+1);
	         
	    }

	    /****************************************************************************/
	    /* Name:    SDateAjust							    */
	    /* Type:    Procedure                                                       */
	    /* Purpose: Adjust the S Dates by making sure that the month lengths        */
	    /*	    are correct if not so take the extra days to next month or year */
	    /* Arguments:                                                               */
	    /* Input: Hijrah Solar date: year:ys, month:ms, day:ds                      */
	    /* Output: corrected Hijrah Solar date: year:ys, month:ms, day:ds           */
	    /****************************************************************************/
	    private static void sDateAjust(RefSupport<Integer> ys, RefSupport<Integer> ms, RefSupport<Integer> ds) throws Exception {
	        /*
	             Adjust Solar Hijrah Dates
	           */
	        int dys;
	        /* Make sure that dates are within the correct values */
	        /*  Underflow  */
	        if (ms.getValue() < 1)
	        {
	            /* months underflow */
	            ms.setValue(12 + ms.getValue());
	            /* plus as the underflow months is negative */
	            ys.setValue(ys.getValue() - 1);
	        }
	         
	        if (ds.getValue() < 1)
	        {
	            /* days underflow */
	            ms.setValue(ms.getValue() - 1);
	            /* month becomes the previous month */
	            ds.setValue(smonth[ms.getValue()] + ds.getValue());
	            /* number of days of the month less the underflow days (it is plus as the sign of the day is negative) */
	            if (ms.getValue() == 6)
	                ds.setValue(ds.getValue() + hSLeapYear(ys.getValue()));
	             
	            if (ms.getValue() < 1)
	            {
	                /* months underflow */
	                ms.setValue(12 + ms.getValue());
	                /* plus as the underflow months is negative */
	                ys.setValue(ys.getValue() - 1);
	            }
	             
	        }
	         
	        /* Overflow  */
	        if (ms.getValue() > 12)
	        {
	            /* months */
	            ms.setValue(ms.getValue() - 12);
	            ys.setValue(ys.getValue() + 1);
	        }
	         
	        if (ms.getValue() == 6)
	            dys = smonth[ms.getValue()] + hSLeapYear(ys.getValue());
	        else
	            /* number of days in the current month */
	            dys = smonth[ms.getValue()]; 
	        if (ds.getValue() > dys)
	        {
	            /* days overflow */
	            ds.setValue(ds.getValue() - dys);
	            ms.setValue(ms.getValue() + 1);
	            if (ms.getValue() > 12)
	            {
	                /* months */
	                ms.setValue(ms.getValue() - 12);
	                ys.setValue(ys.getValue() + 1);
	            }
	             
	        }
	         
	    }

	    /****************************************************************************/
	    /* Name:    HCalendarToJD						    */
	    /* Type:    Function                                                        */
	    /* Purpose: convert Hdate(year,month,day) to estimated Julian Day     	    */
	    /* Arguments:                                                               */
	    /* Input : Hijrah  date: year:yh, month:mh, day:dh                          */
	    /* Output:  The Estimated Julian Day: JD                                    */
	    /****************************************************************************/
	    private static double hCalendarToJD(int yh, int mh, int dh) throws Exception {
	        /*
	           Estimating The JD for hijrah dates
	           this is an approximate JD for the given hijrah date
	         */
	        double md, yd;
	        md = (mh - 1.0) * 29.530589;
	        yd = (yh - 1.0) * 354.367068 + md + dh - 1.0;
	        yd = yd + 1948439.0;
	        return yd;
	    }

	    /*  add JD for 18/7/622 first Hijrah date */
	    /****************************************************************************/
	    /* Name:    JDToHCalendar						    */
	    /* Type:    Procedure                                                       */
	    /* Purpose: convert Julian Day to estimated Hdate(year,month,day)	    */
	    /* Arguments:                                                               */
	    /* Input:  The Julian Day: JD                                               */
	    /* Output : Hijrah date: year:yh, month:mh, day:dh                          */
	    /****************************************************************************/
	    private static void jDToHCalendar(double JD, RefSupport<Integer> yh, RefSupport<Integer> mh, RefSupport<Integer> dh) throws Exception {
	        /*
	           Estimating the hijrah dates from JD
	         */
	        double md, yd;
	        yd = JD - 1948439.0;
	        /*  subtract JD for 18/7/622 first Hijrah date*/
	        md = mod(yd,354.367068);
	        dh.setValue(mod(md + 0.5,29.530589) + 1);
	        doubleHolder = (md / 29.530589) + 1;
	        mh.setValue(doubleHolder.intValue());
	        yd = yd - md;
	        doubleHolder = yd / 354.367068 + 1;
	        yh.setValue(doubleHolder.intValue());
	        if (dh.getValue() > 30)
	        {
	            dh.setValue(dh.getValue() - 30);
	            mh.setValue(mh.getValue()+1);
	        }
	         
	        if (mh.getValue() > 12)
	        {
	            mh.setValue(mh.getValue() - 12);
	            yh.setValue(yh.getValue()+1);
	        }
	         
	    }

	    /**************************************************************************/
	    private static double ip(double x) throws Exception {
	        /* Purpose: return the integral part of a double value.     */
	        //  double  tmp;
	        // modf(x, &tmp);
	        //return tmp;
	        double result = round(x);
	        return result;
	    }

	    /**************************************************************************/
	    /*
	      Name: mod
	      Purpose: The mod operation for doubles  x mod y
	    */
	    private static int mod(double x, double y) throws Exception {
	        int r;
	        double d;
	        d = x / y;
	        doubleHolder = round(d);
	        r = doubleHolder.intValue();
	        if (r < 0)
	            r--;
	         
	        d = x - y * r;
	        doubleHolder = round(d);
	        r = doubleHolder.intValue();
	        return r;
	    }

	    /**************************************************************************/
	    /**************************************************************************/
	    /**************************************************************************/
	    /*              Modified Solar Hijrah Years                               */
	    /**************************************************************************/
	    private static int hMSLeapYear(long year) throws Exception {
	        /* Leap year test for hjirah Modified solar years */
	        int Leap;
	        longHolder = year + 622;
	        Leap = gLeapYear(longHolder.intValue());
	        return Leap;
	    }

	    // Leap year the same time as  Gyear leaps
	    private static double mSCalendToJD(double ys, int ms, int ds) throws Exception {
	        /*
	          Given Solar Hijrah date find  JD
	         */
	        int a, b, c, m6;
	        double T1, T2 = 0, Tr, yg;
	        yg = ys + 622;
	        doubleHolder = (yg - 1) / 100;
	        a = doubleHolder.intValue();
	        doubleHolder = (yg - 1) / 4;
	        b = doubleHolder.intValue();
	        doubleHolder = (yg - 1) / 400;
	        c = doubleHolder.intValue();
	        b = b + c - a - 151;
	        T1 = (ys - 1) * 365.0 + b + ds;
	        doubleHolder = round(ys);
	        m6 = 29 + hMSLeapYear(doubleHolder.longValue());
	        if (ms < 7)
	            T2 = 30 * (ms - 1);
	         
	        if (ms == 7)
	            T2 = 30.0 * 5 + m6;
	         
	        if (ms > 7)
	            T2 = 30.0 * 5 + m6 + 31 * (ms - 7);
	         
	        Tr = T1 + T2 + 1948506.0;
	        return Tr;
	    }

	    private static void jDToMSCalend(double JD, RefSupport<Integer> ys, RefSupport<Integer> ms, RefSupport<Integer> ds) throws Exception {
	        /*
	           From JD day find Solar Hijrah date
	         */
	        int r1, r2, r3, m6;
	        double J, dd, yg;
	        J = JD - 1948506;
	        /* substract JD for 23/9/622 */
	        doubleHolder = J / 365;
	        ys.setValue(doubleHolder.intValue());
	        ys.setValue(ys.getValue()-1);
	        yg = ys.getValue() + 622;
	        doubleHolder = round(yg - mod(yg, 400)) / 400;
	        r1 = doubleHolder.intValue();
	        doubleHolder = round(yg - mod(yg, 4)) / 4;
	        r2 = doubleHolder.intValue();
	        doubleHolder = round(yg - mod(yg, 100)) / 100;
	        r3 = doubleHolder.intValue();
	        J = J - (r2 + r1 - r3) + 151;
	        /* 151=number of leap days in 622years */
	        doubleHolder = round(J / 365);
	        ys.setValue(doubleHolder.intValue());
	        //double to Int32
	        dd = J - ys.getValue() * 365.0;
	        if (dd < 32)
	            dd = dd - hMSLeapYear(ys.getValue());
	         
	        ds.setValue((int)dd);
	        ys.setValue(ys.getValue()+1);
	        if (ds.getValue() < 1)
	        {
	            ys.setValue(ys.getValue()-1);
	            ds.setValue(ds.getValue() + 365);
	        }
	         
	        ms.setValue(1);
	        while ((ds.getValue()) > 30 && (ms.getValue()) < 6)
	        {
	        	ms.setValue(ms.getValue()+1);
	            ds.setValue(ds.getValue() - 30);
	        }
	        m6 = 29 + hMSLeapYear(ys.getValue());
	        if (ds.getValue() > m6 && ms.getValue() == 6)
	        {
	            ms.setValue(ms.getValue()+1);
	            ds.setValue(ds.getValue() - m6);
	        }
	         
	        while (ds.getValue() > 31 && ms.getValue() > 6)
	        {
	            ms.setValue(ms.getValue()+1);
	            ds.setValue(ds.getValue() - 31);
	        }
	        if (ds.getValue() == 0)
	        	ds.setValue(ds.getValue()+1);
	         
	    }

	    private static void mS2G(int ys, int ms, int ds, RefSupport<Integer> yg, RefSupport<Integer> mg, RefSupport<Integer> dg) throws Exception {
	        double SJD;
	        int h, m, s;
	        SJD = mSCalendToJD(ds, ms, ys);
	        RefSupport<Integer> refVar___62 = new RefSupport<Integer>(yg.getValue());
	        RefSupport<Integer> refVar___63 = new RefSupport<Integer>(mg.getValue());
	        RefSupport<Integer> refVar___64 = new RefSupport<Integer>(dg.getValue());
	        jDToGCalendar(SJD,refVar___62,refVar___63,refVar___64);
	        yg.setValue(refVar___62.getValue());
	        mg.setValue(refVar___63.getValue());
	        dg.setValue(refVar___64.getValue());
	    }

	    private static void g2MS(int yg, int mg, int dg, RefSupport<Integer> ys, RefSupport<Integer> ms, RefSupport<Integer> ds) throws Exception {
	        double SJD;
	        SJD = gCalendarToJD(yg, mg, dg + 0.5);
	        RefSupport<Integer> refVar___65 = new RefSupport<Integer>(ys.getValue());
	        RefSupport<Integer> refVar___66 = new RefSupport<Integer>(ms.getValue());
	        RefSupport<Integer> refVar___67 = new RefSupport<Integer>(ds.getValue());
	        jDToMSCalend(SJD,refVar___65,refVar___66,refVar___67);
	        ys.setValue(refVar___65.getValue());
	        ms.setValue(refVar___66.getValue());
	        ds.setValue(refVar___67.getValue());
	    }

	    
	    private static double round(double d, int decimalPlace) {
	        BigDecimal bd = new BigDecimal(d);
	        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
	        return bd.doubleValue();
	    }
	    
	    private static double round(double d) {
	        return round(d, 0);
	    }
	}
}
