package com.senla.bookshop.entity;

import java.util.GregorianCalendar;
import java.util.List;

import com.senla.bookshop.api.entities.*;

public class Order extends BaseEntity implements IOrder {
	private Integer id;
	private IBuyer buyer;
	private List<IBook> books;
	private Integer price;
	private GregorianCalendar date;
	private StatusOrder status;

	public Order(Integer id, IBuyer buyer, List<IBook> books, GregorianCalendar date, StatusOrder status) {
		this.id = id;
		this.buyer = buyer;
		this.books = books;
		this.price = 0;
		for (IBook book : books) {
			this.price += book.getPrice();
		}
		this.date = date;
		this.status = status;
	}

	public Order(Integer id, IBuyer buyer, List<IBook> books, Integer price, GregorianCalendar date,
			StatusOrder status) {
		this.id = id;
		this.buyer = buyer;
		this.books = books;
		this.price = price;
		this.date = date;
		this.status = status;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public IBuyer getBuyer() {
		return buyer;
	}

	@Override
	public void setBuyer(IBuyer buyer) {
		this.buyer = buyer;
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
	public Integer getPrice() {
		return price;
	}

	@Override
	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public GregorianCalendar getDate() {
		return date;
	}

	@Override
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	@Override
	public StatusOrder getStatus() {
		return status;
	}

	@Override
	public void setStatus(StatusOrder status) {
		this.status = status;
	}

	@Override
	public void addBook(IBook book) {
		this.books.add(book);
	}

	@Override
	public String getDescription() {
		StringBuilder str = new StringBuilder();
		str.append("Name of Buyer: ").append(this.buyer.getName()).append(" Books: ");
		for (IBook book : this.books) {
			str.append(book.getName()).append(", ");
		}
		str.append("Price: ").append(this.price).append(", Date: ").append(dateToString(this.date)).append(" Status: ")
				.append(this.status);
		return str.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return this.id == ((Order) obj).getId();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.id).append(SLASH).append(this.buyer.getId()).append(SLASH).append(this.buyer.getName())
				.append(SLASH).append(this.price).append(SLASH).append(dateToString(this.date)).append(SLASH)
				.append(this.status.toString()).append(SLASH).append(this.books.size()).append(SLASH);
		for (IBook book : this.books) {
			builder.append(book.getId()).append(SLASH);
		}
		return builder.toString();
	}

}
