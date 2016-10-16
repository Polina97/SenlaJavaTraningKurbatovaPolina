package com.senla.bookshop.api.entities;

import com.senla.bookshop.entity.EStatusOrder;
import com.senla.bookshop.main.Date;

public interface IOrder extends IBaseEntity {
	IBuyer getBuyer();

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

}
