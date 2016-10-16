package com.senla.bookshop.entity;

import java.util.Comparator;

import com.senla.bookshop.api.entities.*;
import com.senla.bookshop.main.Date;

public class Order extends BaseEntity implements IOrder {
	private IBuyer buyer;
	private IBook[] books;
	private Integer price;
	private Date date;
	private EStatusOrder status;

	public Order(IBuyer buyer, IBook[] books, Date date, EStatusOrder status) {
		super();
		this.buyer = buyer;
		this.books = books;
		this.price =0;
		for (IBook book : this.books) {
			this.price += book.getPrice();
		}
		this.date = date;
		this.status = status;
	}

	public Order(String description) {
		createEntity(description);
	}

	public IBuyer getBuyer() {
		return buyer;
	}

	public void setBuyer(IBuyer buyer) {
		this.buyer = buyer;
	}

	public IBook[] getBooks() {
		return books;
	}

	public void setBooks(IBook[] books) {
		this.books = books;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EStatusOrder getStatus() {
		return status;
	}

	public void setStatus(EStatusOrder status) {
		this.status = status;
	}

	@Override
	public void createEntity(String description) {
		String[] stringOrder = description.split("/");
		int j = 0;
		this.buyer = new Buyer(stringOrder[j++]);
		this.books = new Book[Integer.parseInt(stringOrder[j++])];
		for (int i = 0; i < this.books.length; i++) {
			String nameB = stringOrder[j++];
			String authorB = stringOrder[j++];
			Date dateB = new Date(Integer.parseInt(stringOrder[j++]), Integer.parseInt(stringOrder[j++]),
					Integer.parseInt(stringOrder[j++]));
			Integer priceB = Integer.parseInt(stringOrder[j++]);
			books[i] = new Book(nameB, authorB, dateB, priceB);
			books[i].addRequest();
		}
		this.price = Integer.parseInt(stringOrder[j++]);
		this.date = new Date(Integer.parseInt(stringOrder[j++]), Integer.parseInt(stringOrder[j++]),
				Integer.parseInt(stringOrder[j++]));
		switch (stringOrder[j++]) {
		case "KIT":
			this.status = EStatusOrder.KIT;
			break;
		case "DELIVERED":
			this.status = EStatusOrder.DELIVERED;
			break;
		case "CANCELED":
			this.status = EStatusOrder.CANCELED;
			break;
		default:
			break;
		}

	}

	@Override
	public boolean equals(Object obj) {
		return this.buyer.equals(((Order) obj).getBuyer()) && this.price == ((Order) obj).getPrice()
				&& this.date.equals(((Order) obj).getDate())
				&& this.status.toString().equals(((Order) obj).getStatus().toString());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.buyer.getName()).append("/").append(this.books.length).append("/");
		for (IBook book : this.books) {
			builder.append(book.getName()).append("/").append(book.getAuthor()).append("/")
					.append(book.getDateSupply()).append("/").append(book.getPrice()).append("/");
		}
		return builder.append("/").append(this.price).append("/").append(this.date.toString()).append("/")
				.append(this.status.toString()).toString();
	}

	@Override
	public void cancelOrder() {
		this.status = EStatusOrder.CANCELED;
	}

	public static Comparator<Order> DateComparator = new Comparator<Order>() {

		@Override
		public int compare(Order o1, Order o2) {
			return o1.getDate().compareTo(o2.getDate());
		}
	};
	public static Comparator<Order> PriceComparator = new Comparator<Order>() {

		@Override
		public int compare(Order o1, Order o2) {
			return o1.getPrice() - o2.getPrice();
		}
	};
	public static Comparator<Order> StatusComparator = new Comparator<Order>() {

		@Override
		public int compare(Order o1, Order o2) {
			return o1.getStatus().compareTo(o2.getStatus());
		}
	};
}
