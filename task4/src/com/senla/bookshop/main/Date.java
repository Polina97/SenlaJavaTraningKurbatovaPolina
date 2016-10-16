package com.senla.bookshop.main;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date implements Comparable<Date> {
	private int day;
	private int month;
	private int year;
	private StringBuilder builder;
	private final static Calendar CALENDAR = new GregorianCalendar();

	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public Date(String st) {
		String[] nubers = st.split("/");
		day = Integer.parseInt(nubers[0]);
		month = Integer.parseInt(nubers[1]);
		year = Integer.parseInt(nubers[2]);
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		builder = new StringBuilder();
		return builder.append(day).append("/").append(month).append("/").append(year).toString();
	}

	public Date afterSixMonth() {
		int m, y;
		if (month > 6) {
			y = year + 1;
			m = 6 - (12 - month);
		} else {
			m = month + 6;
			y = year;
		}
		return new Date(day, m, y);
	}

	public boolean isLess(Date date) {
		if (year == date.getYear()) {
			if (month == date.getMonth()) {
				if (day < date.getDay()) {
					return true;
				} else {
					return false;
				}
			} else {
				if (month < date.getMonth()) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			if (year < date.getYear()) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Date) {
			return ((Date) obj).getDay() == day && ((Date) obj).getMonth() == month && ((Date) obj).getYear() == year;
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(Date newDate) {
		if (this.year == newDate.getYear()) {
			if (this.month == newDate.getMonth()) {
				return this.day - newDate.getDay();
			} else {
				return this.month - newDate.getMonth();
			}
		}else {
			return this.year - newDate.getYear();
		}
	}
	public static Date getNowDate(){
		return new Date(CALENDAR.get(Calendar.DAY_OF_MONTH), CALENDAR.get(Calendar.MONTH),
				CALENDAR.get(Calendar.YEAR));
	}

}
