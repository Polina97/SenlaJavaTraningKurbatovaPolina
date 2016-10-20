package com.senla.bookshop.api.entities;

import java.util.GregorianCalendar;

public interface IBaseEntity {
	public final String SLASH = "/";

	public String getType();

	public String dateToString(GregorianCalendar calendar);

	public String getDescription();

	@Override
	public boolean equals(Object obj);

	@Override
	public String toString();

}
