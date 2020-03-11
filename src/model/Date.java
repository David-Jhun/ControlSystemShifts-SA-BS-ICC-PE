package model;

import java.util.Calendar;

public class Date {
	
	private int day;
	private int month;
	private int year;
	
	private Hour hour;
	
	public Date( Calendar c ) {
		c = Calendar.getInstance();
		day = c.DAY_OF_MONTH;
	}

}
