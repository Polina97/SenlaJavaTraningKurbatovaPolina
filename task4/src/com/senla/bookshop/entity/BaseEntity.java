package com.senla.bookshop.entity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.senla.bookshop.api.entities.IBaseEntity;

public abstract class BaseEntity implements IBaseEntity {

	private final String BOOK = "Book";
	private final String ORDER = "Order";
	private final String BUYER = "Buyer";

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
	public String dateToString(GregorianCalendar calendar) {
		StringBuilder builder = new StringBuilder();
		return builder.append(calendar.get(Calendar.YEAR)).append(BaseEntity.SLASH).append(calendar.get(Calendar.MONTH))
				.append(BaseEntity.SLASH).append(calendar.get(Calendar.DAY_OF_MONTH)).toString();
	}

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract String toString();

}
