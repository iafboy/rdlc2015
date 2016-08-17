package com.expocms.server.db.model;

import java.io.Serializable;

public class RdDays implements Serializable {

	private static final long serialVersionUID = -7395919635927866043L;

	private int year;
	private int dayOfYear;
	private int working;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getDayOfYear() {
		return dayOfYear;
	}
	public void setDayOfYear(int dayOfYear) {
		this.dayOfYear = dayOfYear;
	}
	
	public int getWorking() {
		return working;
	}
	public void setWorking(int working) {
		this.working = working;
	}
	
	@Override
	public String toString() {
		return "year = " + year + ", dayOfYear = " + dayOfYear + ", working = " + (working == 1 ? "true" : "false");
	}
	
}
