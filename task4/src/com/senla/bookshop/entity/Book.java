package com.senla.bookshop.entity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.senla.bookshop.api.entities.IBook;

public class Book extends BaseEntity implements IBook {
	private Integer id;
	private String name;
	private String author;
	private GregorianCalendar datePublication;
	private GregorianCalendar dateOld;
	private Integer price;
	private Boolean inStock;
	private Integer requests;
	private Boolean application;

	public Book(Integer id, String name, String author, GregorianCalendar datePublication, GregorianCalendar dateSupply,
			Integer price) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.datePublication = datePublication;
		this.dateOld = dateSupply;
		this.dateOld.add(Calendar.MONTH, 6);
		this.price = price;
		this.inStock = false;
		this.requests = 0;
		this.application = false;
	}

	public Book(Integer id, String name, String author, GregorianCalendar datePublication, GregorianCalendar dateOld,
			Integer price, Boolean inStock, Integer requests, Boolean application) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.datePublication = datePublication;
		this.dateOld = dateOld;
		this.price = price;
		this.inStock = inStock;
		this.requests = requests;
		this.application = application;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void steId(Integer id) {
		this.id = id;
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
	public GregorianCalendar getDatePublication() {
		return datePublication;
	}

	@Override
	public void setDatePublication(GregorianCalendar datePublication) {
		this.datePublication = datePublication;
	}

	@Override
	public GregorianCalendar getDateOld() {
		return dateOld;
	}

	@Override
	public void setDateOld(GregorianCalendar dateOld) {
		this.dateOld = dateOld;

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
	public Boolean isInStock() {
		return inStock;
	}

	@Override
	public void setInStock(Boolean inStock) {
		this.inStock = inStock;

	}

	@Override
	public int getRequests() {
		return requests;
	}

	@Override
	public void addRequest() {
		this.requests++;

	}

	@Override
	public Boolean isApplication() {
		return application;
	}

	@Override
	public void setApplication(Boolean application) {
		this.application = application;

	}

	@Override
	public Boolean isOld(GregorianCalendar today) {
		return this.dateOld.before(today);
	}

	@Override
	public String getDescription() {
		StringBuilder str = new StringBuilder();
		str.append("Name: ").append(this.name).append(", Author: ").append(this.author)
				.append(", Price ").append(this.price).append(", is in Stock: ").append(this.inStock)
				.append(", Requests: ").append(this.requests).append(", Application: ").append(this.application);
		return str.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		return builder.append((this.id)).append(SLASH).append(this.name).append(SLASH).append(this.author).append(SLASH)
				.append(dateToString(this.datePublication)).append(SLASH).append(dateToString(this.dateOld))
				.append(SLASH).append(price).append(SLASH).append(inStock).append(SLASH).append(requests).append(SLASH)
				.append(application).toString();
	}

	@Override
	public boolean equals(Object obj) {
		return ((Book) obj).getName().equals(this.name) && ((Book) obj).getAuthor().equals(this.author)
				&& ((Book) obj).getPrice() == this.price;
	}

}
