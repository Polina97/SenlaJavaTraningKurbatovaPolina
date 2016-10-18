package com.senla.bookshop.api.entities;

public interface IBaseEntity {
	final String SLASH = "/";

	String getType();

	/**
	 * This method initializes all fields of the string passed as a parameter
	 */

	String getDescription();

	@Override
	boolean equals(Object obj);

	@Override
	String toString();

}
