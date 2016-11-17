package com.senla.bookshop.entity;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entity.*;
import com.senla.bookshop.idgenerator.IdGenerator;
import com.senla.bookshop.idgenerator.TypeId;

public class Order extends BaseEntity implements IOrder {
	private Logger log = Logger.getLogger(Order.class);

	private static final long serialVersionUID = 1L;
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
		try {
			for (IBook book : books) {
				this.price += book.getPrice();
			}
		} catch (NullPointerException e) {
			log.error(e);
		}
		this.date = date;
		this.status = status;
	}

	public Order(Integer id, IBuyer buyer, List<IBook> books, Integer price, GregorianCalendar date,
			StatusOrder status) {
		this(id, buyer, books, date, status);
		this.price = price;
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
		builder.append(id).append(SPLITTER).append(buyer.getId()).append(SPLITTER).append(price).append(SPLITTER)
				.append(dateToString(date)).append(SPLITTER).append(status.toString()).append(SPLITTER)
				.append(books.size()).append(SPLITTER);
		for (IBook book : books) {
			builder.append(book.getId()).append(SECOND_SPLITTER);
		}
		return builder.toString();
	}

	@Override
	public IOrder clone() throws CloneNotSupportedException {
		List<IBook> books = new ArrayList<IBook>();
		for (IBook book : getBooks()) {
			books.add(book.clone());
		}
		IOrder order = (IOrder) super.clone();
		order.setId(IdGenerator.getId(TypeId.ORDER));
		order.setBuyer(buyer.clone());
		order.setBooks(books);
		order.setDate((GregorianCalendar) date.clone());
		order.setStatus(StatusOrder.valueOf(status.toString()));
		order.getBuyer().setOrder(order);
		return order;
	}

}
