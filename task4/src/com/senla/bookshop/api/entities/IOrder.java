package com.senla.bookshop.api.entities;

import com.senla.bookshop.entity.EStatusOrder;
import com.senla.bookshop.resources.Date;

public interface IOrder extends IBaseEntity {
	IBuyer getBuyer();

	Integer getId();

	void setId(Integer id);

	void setBuyer(IBuyer buyer);

	IBook[] getBooks();

	void setBooks(IBook[] books);

	Integer getPrice();

	void setPrice(Integer price);

	Date getDate();

	void setDate(Date date);

	EStatusOrder getStatus();

	void setStatus(EStatusOrder status);

	void cancelOrder();

	void deliverOrder();

}
