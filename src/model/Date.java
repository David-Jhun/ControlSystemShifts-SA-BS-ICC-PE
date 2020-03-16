package model;

import java.time.LocalDate;

public class Date {
	
	private int day;
	private int month;
	private int year;
	
	private Hour hour;
	
	public Date( LocalDate date ) {
		date = LocalDate.now();
		day = date.getDayOfMonth();
		month = date.getMonthValue();
		year = date.getYear();
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Hour getHour() {
		return hour;
	}

	public void setHour(Hour hour) {
		this.hour = hour;
	}
	
}
