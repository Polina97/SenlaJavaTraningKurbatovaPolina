package com.senla.bookshop.resources;


import java.util.Calendar;
import java.util.GregorianCalendar;

import com.senla.bookshop.api.entities.IBaseEntity;
import com.senla.bookshop.entity.BaseEntity;

public class Printer {
	public static void print(Object obj) {
		System.out.println(obj);
	}

	public static void print() {
		System.out.println();
	}
	public static void printAdd(IBaseEntity entity){
		System.out.println(entity.getType()+" was added!");
	}

	public static void printDelete(IBaseEntity entity){
		System.out.println(entity.getType()+" was deleted!");
	}

	public static String dateToString(GregorianCalendar calendar){
		StringBuilder builder = new StringBuilder();
		return builder.append(calendar.get(Calendar.YEAR)).append(BaseEntity.SLASH).append(calendar.get(Calendar.MONTH))
				.append(BaseEntity.SLASH).append(calendar.get(Calendar.DAY_OF_MONTH)).toString();
	}
}
