package com.senla.bookshop.api.entity;

import java.util.GregorianCalendar;

public interface IBaseEntity {
	public final String SPLITTER = ",";
	public final static String SECOND_SPLITTER  =".";
	public final String SPACE = " ";
	public final String BOOK = "Book";
	public final String ORDER = "Order";
	public final String BUYER = "Buyer";


	public String dateToString(GregorianCalendar calendar);

	public String getType();

	public Integer getId();

	public String getDescription();

	@Override
	public boolean equals(Object obj);

	@Override
	public String toString();


}
