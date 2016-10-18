package com.senla.bookshop.resources;

import java.util.GregorianCalendar;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.Buyer;

public class Parser {

	private final String SLASH = "/";

	public Book bookParser(String description) {
		String[] bookString = description.split(SLASH);
		String name = bookString[0];
		String author = bookString[1];
		GregorianCalendar datePublication = new GregorianCalendar(Integer.parseInt(bookString[2]),
				Integer.parseInt(bookString[3]), Integer.parseInt(bookString[4]));
		GregorianCalendar dateOld = new GregorianCalendar(Integer.parseInt(bookString[5]),
				Integer.parseInt(bookString[6]), Integer.parseInt(bookString[7]));
		Integer price = Integer.parseInt(bookString[8]);
		Boolean inStock = false;
		if (bookString[9].equals("true")) {
			inStock = true;
		}
		Integer requests = Integer.parseInt(bookString[10]);
		Boolean application = false;
		if (bookString[11].equals("true")) {
			application = true;
		}
		return new Book(name, author, datePublication, dateOld, price, inStock, requests, application);
	}
	public Buyer buyerParser(String description){
		
	}
}
