package com.senla.bookshop.api.entities;

import java.util.GregorianCalendar;

import com.senla.bookshop.entity.EStatusOrder;

public interface IOrder extends IBaseEntity {
	public IBuyer getBuyer();

	public Integer getId();

	public void setId(Integer id);

	public void setBuyer(IBuyer buyer);

	public IBook[] getBooks();

	public void setBooks(IBook[] books);

	public Integer getPrice();

	public void setPrice(Integer price);

	public GregorianCalendar getDate();

	public void setDate(GregorianCalendar date);

	public EStatusOrder getStatus();

	public void setStatus(EStatusOrder status);

	public void addBook(IBook book);

}
