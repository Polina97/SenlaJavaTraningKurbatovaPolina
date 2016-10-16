package com.senla.bookshop.entity;

import java.util.Comparator;

import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.main.Date;

public class Book extends BaseEntity implements IBook {
	private String name;
	private String author;
	private Date dateSupply;
	private Integer price;
	private Date dateOld;
	private boolean inStock;
	private Integer requests;
	private boolean application;

	public Book(String name, String author, Date dateSupply, Integer price) {
		this.name = name;
		this.author = author;
		this.dateSupply = dateSupply;
		this.price = price;
		this.dateOld = dateSupply.afterSixMonth();
		this.inStock = false;
		this.requests = 0;
		this.application = false;
	}

	public Book(String description) {
		createEntity(description);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public Date getDateSupply() {
		return dateSupply;
	}

	@Override
	public void setDateSupply(Date dateSupply) {
		this.dateSupply = dateSupply;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public Date getDateOld() {
		return dateOld;
	}

	@Override
	public void setDateOld(Date dateOld) {
		this.dateOld = dateOld;
	}

	@Override
	public boolean isInStock() {
		return inStock;
	}

	@Override
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	@Override
	public int getRequests() {
		return requests;
	}

	@Override
	public void setRequests(int requests) {
		this.requests = requests;
	}

	@Override
	public boolean isApplication() {
		return application;
	}

	@Override
	public void setApplication(boolean application) {
		this.application = application;
	}

	@Override
	public boolean isOld(Date today) {
		if (today.isLess(dateOld)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		return builder.append(name).append("/").append(author).append("/").append(dateSupply).append("/").append(price)
				.append("/").append(dateOld).append("/").append(inStock).append("/").append(requests).append("/")
				.append(application).toString();
	}

	@Override
	public boolean equals(Object obj) {
		return ((Book) obj).getName().equals(this.name) && ((Book) obj).getAuthor().equals(this.author)
				&& ((Book) obj).getPrice() == this.price;
	}

	@Override
	public void createEntity(String description) {
		String[] book = description.split("/");
		this.name = book[0];
		this.author = book[1];
		this.dateSupply = new Date(Integer.parseInt(book[2]), Integer.parseInt(book[3]), Integer.parseInt(book[4]));
		this.price = Integer.parseInt(book[5]);
		this.dateOld = new Date(Integer.parseInt(book[6]), Integer.parseInt(book[7]), Integer.parseInt(book[8]));
		if (book[9].equals("true")) {
			this.inStock = true;
		} else {
			this.inStock = false;
		}
		requests = Integer.parseInt(book[10]);
		if (book[11].equals("true")) {
			this.application = true;
		} else {
			this.application = false;
		}

	}

	public static Comparator<Book> AlphabetComparator = new Comparator<Book>() {

		@Override
		public int compare(Book arg0, Book arg1) {
			return arg0.getName().compareTo(arg1.getName());
		}
	};
	public static Comparator<Book> PriceComparator = new Comparator<Book>() {

		@Override
		public int compare(Book o1, Book o2) {
			return o1.getPrice() - o2.getPrice();
		}
	};
	public static Comparator<Book> DateComparator = new Comparator<Book>() {

		@Override
		public int compare(Book o1, Book o2) {
			return o1.getDateSupply().compareTo(o2.getDateSupply());
		}
	};
	public static Comparator<Book> StockComparator = new Comparator<Book>() {

		@Override
		public int compare(Book o1, Book o2) {
			int a1 = 0, a2 = 0;
			if (o1.isInStock() == true) {
				a1 = 1;
			}
			if (o2.isInStock() == true) {
				a2 = 1;
			}
			return a1 - a2;
		}
	};

}
