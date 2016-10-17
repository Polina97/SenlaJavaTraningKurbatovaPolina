package com.senla.bookshop.api.entities;

import com.senla.bookshop.resources.Date;

public interface IBook extends IBaseEntity {

	String getName();

	void setName(String name);

	String getAuthor();

	void setAuthor(String author);

	Date getDateSupply();

	void setDateSupply(Date dateSupply);

	int getPrice();

	void setPrice(int price);

	Date getDateOld();

	void setDateOld(Date dateOld);

	boolean isInStock();

	void setInStock(boolean inStock);

	int getRequests();

	void addRequest();

	boolean isApplication();

	void setApplication(boolean application);

	boolean isOld(Date today);

}
