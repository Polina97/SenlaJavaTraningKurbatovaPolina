package com.senla.bookshop.api.entities;

import java.util.GregorianCalendar;

public interface IBook extends IBaseEntity {
	public void setId(Integer id);

	public String getName();

	public void setName(String name);

	public String getAuthor();

	public void setAuthor(String author);

	public GregorianCalendar getDatePublication();

	public void setDatePublication(GregorianCalendar datePublication);

	public GregorianCalendar getDateOld();

	public void setDateOld(GregorianCalendar dateOld);

	public Integer getPrice();

	public void setPrice(int price);

	public Boolean isInStock();

	public void setInStock(Boolean inStock);

	public Integer getRequests();

	public void addRequest();

	public Boolean isApplication();

	public void setApplication(Boolean application);

	public Boolean isOld(GregorianCalendar today);

	public IBook clone() throws CloneNotSupportedException;

}
