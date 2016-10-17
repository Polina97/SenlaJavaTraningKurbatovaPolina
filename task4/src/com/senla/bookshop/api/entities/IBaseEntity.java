package com.senla.bookshop.api.entities;

public interface IBaseEntity {

	String getType();

	/**
	 * This method initializes all fields of the string passed as a parameter
	 */
	void createEntity(String description);

	String getDescription();

	@Override
	boolean equals(Object obj);

	@Override
	String toString();

}
