package com.senla.bookshop.api.entities;

import java.util.GregorianCalendar;
import java.util.List;

import com.senla.bookshop.entity.StatusOrder;

public interface IOrder extends IBaseEntity {
	public IBuyer getBuyer();

	public void setId(Integer id);

	public void setBuyer(IBuyer buyer);

	public List<IBook> getBooks();

	public void setBooks(List<IBook> books);

	public Integer getPrice();

	public void setPrice(Integer price);

	public GregorianCalendar getDate();

	public void setDate(GregorianCalendar date);

	public StatusOrder getStatus();

	public void setStatus(StatusOrder status);

	public void addBook(IBook book);

	public IOrder clone(Integer id) throws CloneNotSupportedException;

}
