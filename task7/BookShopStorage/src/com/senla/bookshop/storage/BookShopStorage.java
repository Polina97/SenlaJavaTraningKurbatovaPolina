package com.senla.bookshop.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entity.IBook;
import com.senla.bookshop.api.entity.IBuyer;
import com.senla.bookshop.api.entity.IOrder;
import com.senla.bookshop.api.storage.IBookShopStorage;

public class BookShopStorage implements IBookShopStorage, Serializable {
	
	private static final long serialVersionUID = 1L;
	private transient Logger log = Logger.getLogger(BookShopStorage.class);
	private static BookShopStorage storage;
	private List<IBook> books = new ArrayList<IBook>();
	private List<IBuyer> buyers = new ArrayList<IBuyer>();
	private List<IOrder> orders = new ArrayList<IOrder>();

	private BookShopStorage() {
	}
	

	@Override
	public List<IBook> getBooks() {
		return books;
	}

	@Override
	public void setBooks(List<IBook> books) {
		this.books = books;
	}

	@Override
	public List<IBuyer> getBuyers() {
		return buyers;
	}

	@Override
	public void setBuyers(List<IBuyer> buyers) {
		this.buyers = buyers;
	}

	@Override
	public List<IOrder> getOrders() {
		return orders;
	}

	@Override
	public void setOrders(List<IOrder> orders) {
		this.orders = orders;
	}

	@Override
	public Boolean addBook(IBook book) {
		try {
			Boolean answ = books.add(book);
			return answ;
		} catch (ClassCastException | NullPointerException | IllegalArgumentException e) {
			log.error(e);
			return false;
		}
	}

	@Override
	public Boolean addOrder(IOrder order) {
		try {
			Boolean answ = orders.add(order);
			return answ;
		} catch (ClassCastException | NullPointerException | IllegalArgumentException e) {
			log.error(e);
			return false;
		}
	}

	@Override
	public Boolean addBuyer(IBuyer buyer) {
		try {
			Boolean answ = buyers.add(buyer);
			return answ;
		} catch (ClassCastException | NullPointerException | IllegalArgumentException e) {
			log.error(e);
			return false;
		}
	}

	@Override
	public Boolean deleteBook(IBook book) {
		try {
			Boolean answ = books.remove(book);
			return answ;
		} catch (ClassCastException | NullPointerException e) {
			log.error(e);
			return false;
		}
	}

	@Override
	public Boolean deleteOrder(IOrder order) {
		try {
			Boolean answ = orders.remove(order);
			return answ;
		} catch (ClassCastException | NullPointerException e) {
			log.error(e);
			return false;
		}
	}

	@Override
	public Boolean deleteBuyer(IBuyer buyer) {
		try {
			Boolean answ = buyers.remove(buyer);
			return answ;
		} catch (ClassCastException | NullPointerException e) {
			log.error(e);
			return false;
		}
	}

	public static BookShopStorage getInstance() {
		if (storage == null) {
			storage = new BookShopStorage();
		}
		return storage;
	}
}
