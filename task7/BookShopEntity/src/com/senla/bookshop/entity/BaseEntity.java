package com.senla.bookshop.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.senla.bookshop.api.entity.IBaseEntity;

public abstract class BaseEntity implements IBaseEntity, Serializable {

	private static final long serialVersionUID = 1L;
	@Override
	public String dateToString(GregorianCalendar calendar) {
		StringBuilder builder = new StringBuilder();
		if(calendar != null){
			builder.append(calendar.get(Calendar.YEAR)).append(SECOND_SPLITTER).append(calendar.get(Calendar.MONTH))
			.append(SECOND_SPLITTER).append(calendar.get(Calendar.DAY_OF_MONTH));
		return builder.toString();
		}else{
			return SPACE;
		}
	}

	@Override
	public String getType() {
		if (this instanceof Book) {
			return BOOK;
		} else if (this instanceof Order) {
			return ORDER;
		} else if (this instanceof Buyer) {
			return BUYER;
		} else
			return null;
	}


	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract String toString();

}
