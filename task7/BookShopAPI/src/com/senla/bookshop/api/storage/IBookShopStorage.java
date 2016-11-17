package com.senla.bookshop.api.storage;

import java.util.List;

import com.senla.bookshop.api.entity.IBook;
import com.senla.bookshop.api.entity.IBuyer;
import com.senla.bookshop.api.entity.IOrder;

public interface IBookShopStorage {
	public List<IBook> getBooks();

	public void setBooks(List<IBook> books);

	public List<IBuyer> getBuyers();

	public void setBuyers(List<IBuyer> buyers);

	public List<IOrder> getOrders();

	public void setOrders(List<IOrder> orders);

	public Boolean addBook(IBook book);

	public Boolean addOrder(IOrder order);

	public Boolean addBuyer(IBuyer buyer);

	public Boolean deleteBook(IBook book);

	public Boolean deleteOrder(IOrder order);

	public Boolean deleteBuyer(IBuyer buyer);


}
