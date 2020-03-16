package model;

import java.time.LocalTime;

public class Hour {
	
	private int hour;
	private int minutes;
	private int seconds;
	
	public Hour( LocalTime date ) {
		date = LocalTime.now();
		hour = date.getHour();
		
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	

}
