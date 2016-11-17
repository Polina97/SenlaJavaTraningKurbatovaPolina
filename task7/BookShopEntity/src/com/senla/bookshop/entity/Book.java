
package com.senla.bookshop.entity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.senla.bookshop.api.entity.IBook;
import com.senla.bookshop.config.Config;
import com.senla.bookshop.idgenerator.IdGenerator;
import com.senla.bookshop.idgenerator.TypeId;

public class Book extends BaseEntity implements IBook, Cloneable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String author;
	private GregorianCalendar datePublication;
	private GregorianCalendar dateOld;
	private Integer price;
	private Boolean inStock;
	private Integer requests;
	private Boolean application;
	private Integer monthOld;

	public Book(Integer id, String name, String author, GregorianCalendar datePublication, GregorianCalendar dateSupply,
			Integer price) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.datePublication = datePublication;
		this.dateOld = dateSupply;
		this.monthOld = Config.getInstance().MONTH_OLD;
		this.dateOld.roll(Calendar.MONTH, monthOld);
		this.price = price;
		this.inStock = false;
		this.requests = 0;
		this.application = false;
	}

	public Book(Integer id, String name, String author, GregorianCalendar datePublication, GregorianCalendar dateOld,
			Integer price, Boolean inStock, Integer requests, Boolean application) {
		this(id, name, author, datePublication, dateOld, price);
		this.dateOld = dateOld;
		this.inStock = inStock;
		this.requests = requests;
		this.application = application;
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
	public Integer getPrice() {
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
	public Integer getRequests() {
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
	public String getDescription() {
		StringBuilder str = new StringBuilder();
		str.append("Name: ").append(name).append(", Author: ").append(author).append(", Price ").append(this.price)
				.append(", is in Stock: ").append(inStock).append(", Date: ").append(dateToString(datePublication))
				.append(", Requests: ").append(requests).append(", Application: ").append(application);
		return str.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		return builder.append((id)).append(SPLITTER).append(name).append(SPLITTER).append(author).append(SPLITTER)
				.append(dateToString(datePublication)).append(SPLITTER).append(dateToString(dateOld)).append(SPLITTER)
				.append(price).append(SPLITTER).append(inStock).append(SPLITTER).append(requests).append(SPLITTER)
				.append(application).toString();
	}

	@Override
	public boolean equals(Object obj) {
		return ((Book) obj).getName().equals(name) && ((Book) obj).getAuthor().equals(author)
				&& ((Book) obj).getPrice() == price;
	}

	@Override
	public IBook clone() throws CloneNotSupportedException {
		IBook book = (IBook) super.clone();
		book.setId(IdGenerator.getId(TypeId.BOOK));
		book.setDateOld((GregorianCalendar) dateOld.clone());
		return book;
	}

	@Override
	public void setRequests(Integer requests) {
		this.requests = requests;
	}

}
