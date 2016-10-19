package com.senla.bookshop.resources;

import java.util.GregorianCalendar;
import com.senla.bookshop.entity.*;
import com.senla.bookshop.manager.BookManager;
import com.senla.bookshop.manager.OrderManager;

public class Parser {

	private static final String SLASH = "/";

	public static Book bookParser(String description) {
		String[] bookString = description.split(SLASH);
		Integer id = Integer.parseInt(bookString[0]);
		String name = bookString[1];
		String author = bookString[2];
		GregorianCalendar datePublication = new GregorianCalendar(Integer.parseInt(bookString[3]),
				Integer.parseInt(bookString[4]), Integer.parseInt(bookString[5]));
		GregorianCalendar dateOld = new GregorianCalendar(Integer.parseInt(bookString[6]),
				Integer.parseInt(bookString[7]), Integer.parseInt(bookString[8]));
		Integer price = Integer.parseInt(bookString[9]);
		Boolean inStock = false;
		if (bookString[10].equals("true")) {
			inStock = true;
		}
		Integer requests = Integer.parseInt(bookString[11]);
		Boolean application = false;
		if (bookString[12].equals("true")) {
			application = true;
		}
		return new Book(id, name, author, datePublication, dateOld, price, inStock, requests, application);
	}

	public static Buyer buyerParser(String description) {
		String[] stringBuyer = description.split(SLASH);
		Integer id = Integer.parseInt(stringBuyer[0]);
		String name = stringBuyer[1];
		Order[] orders = new Order[Integer.parseInt(stringBuyer[2])];
		OrderManager orderManager = new OrderManager();
		for (int i = 0, j = 3; i < orders.length; i++, j++) {
			orders[i] = (Order) orderManager.getOrderById(Integer.parseInt(stringBuyer[j]));
		}
		return new Buyer(id, name, orders);
	}

	public static Order orderParser(String description){
		String[] stringOrder = description.split(SLASH);
		Integer id = Integer.parseInt(stringOrder[0]);
		Buyer buyer = new Buyer(Integer.parseInt(stringOrder[1]), stringOrder[2]);
		Integer price = Integer.parseInt(stringOrder[3]);
		GregorianCalendar date  = new GregorianCalendar(Integer.parseInt(stringOrder[4]),
				Integer.parseInt(stringOrder[5]), Integer.parseInt(stringOrder[6]));
		EStatusOrder status = EStatusOrder.valueOf(stringOrder[7]);
		Book[] books = new Book[Integer.parseInt(stringOrder[8])];
		BookManager bookManager = new BookManager();
		for(int i=0, j=4; i<books.length; i++,j++){
			books[i] = (Book) bookManager.getById(Integer.parseInt(stringOrder[j]));
		}
		return new Order(id, buyer, books, price, date, status);
		
	}
}
