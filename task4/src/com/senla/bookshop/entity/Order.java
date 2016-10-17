package com.senla.bookshop.entity;

import java.util.Comparator;

import com.senla.bookshop.api.entities.*;
import com.senla.bookshop.manager.BookManager;
import com.senla.bookshop.manager.BuyerManager;
import com.senla.bookshop.resources.Date;

public class Order extends BaseEntity implements IOrder {
	private Integer id;
	private IBuyer buyer;
	private IBook[] books;
	private Integer price;
	private Date date;
	private EStatusOrder status;
	private BookManager bookManager = new BookManager();
	private BuyerManager buyerManager = new BuyerManager();

	public Order(Integer id, IBuyer buyer, IBook[] books, Date date, EStatusOrder status) {
		super();
		this.id= id;
		this.buyer = buyerManager.getById(buyer.getId());
		this.books = new Book[books.length];
		for (int i = 0; i < books.length; i++) {
			this.books[i] = bookManager.getBook((Book) books[i]);
		}
		for (int i = 0; i < books.length; i++) {
			if (!bookManager.isInStock((Book) books[i])) {
				this.bookManager.submitApplication((Book) books[i]);
			}
			books[i].addRequest();
		}
		this.price = 0;
		for (IBook book : this.books) {
			this.price += book.getPrice();
		}
		this.date = date;
		this.status = status;
	}

	public Order(String description) {
		createEntity(description);
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		this.id = Integer.parseInt(stringOrder[j++]);
		this.buyer = buyerManager.getById(Integer.parseInt(stringOrder[j++]));
		this.books = new Book[Integer.parseInt(stringOrder[j++])];
		for (int i = 0; i < this.books.length; i++) {
			String nameB = stringOrder[j++];
			String authorB = stringOrder[j++];
			Date dateB = new Date(Integer.parseInt(stringOrder[j++]), Integer.parseInt(stringOrder[j++]),
					Integer.parseInt(stringOrder[j++]));
			Integer priceB = Integer.parseInt(stringOrder[j++]);
			Book newBook = new Book(nameB, authorB, dateB, priceB);
			books[i] = bookManager.getBook(newBook);
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
		return this.id == ((Order)obj).getId();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.id).append("/").append(this.buyer.getId()).append("/").append(this.books.length).append("/");
		for (IBook book : this.books) {
			builder.append(this.id).append("/").append(book.getName()).append("/").append(book.getAuthor()).append("/").append(book.getDateSupply())
					.append("/").append(book.getPrice()).append("/");
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

	@Override
	public String getDescription() {
		StringBuilder str = new StringBuilder();
		str.append("Name of Buyer: ").append(this.buyer.getName()).append(" Books: ");
		for (IBook book : this.books) {
			str.append(book.getName()).append(", ");
		}
		str.append(", Price: ").append(this.price).append(", Date: ").append(this.date).append(" Status: ")
				.append(this.status);
		return str.toString();
	}
}
