package com.senla.bookshop.resources;

import com.danco.training.TextFileWorker;
import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.entity.Order;

public class FileWorker {
	private static TextFileWorker fileWorker;
	private static String[] booksString;
	private static String[] ordersString;
	private static String[] buyersString;
	private final String PATH_BOOK;
	private final String PATH_ORDER;
	private final String PATH_BUYER;

	
	public FileWorker(String pATH_BOOK, String pATH_ORDER, String pATH_BUYER) {
		PATH_BOOK = pATH_BOOK;
		PATH_ORDER = pATH_ORDER;
		PATH_BUYER = pATH_BUYER;
	}

	public void writeBooks(IBook[] books) {
		fileWorker = new TextFileWorker(PATH_BOOK);
		booksString = new String[books.length];
		for (int i = 0; i < books.length; i++) {
			booksString[i] = books[i].toString();
		}
		fileWorker.writeToFile(booksString);
	}

	public Book[] readBooks() {
		fileWorker = new TextFileWorker(PATH_BOOK);
		booksString = fileWorker.readFromFile();
		Book[] books = new Book[booksString.length];
		int i = 0;
		for (String bookString : booksString) {
			books[i++] = new Book(bookString);
		}
		return books;
	}

	public void writeOrders(IOrder[] orders) {
		fileWorker = new TextFileWorker(PATH_ORDER);
		ordersString = new String[orders.length];
		for (int i = 0; i < orders.length; i++) {
			ordersString[i] = orders[i].toString();
		}
		fileWorker.writeToFile(ordersString);
	}

	public Order[] readOrders() {
		fileWorker = new TextFileWorker(PATH_ORDER);
		ordersString = fileWorker.readFromFile();
		Order[] orders = new Order[ordersString.length];
		int i = 0;
		for (String buyerString : ordersString) {
			orders[i++] = new Order(buyerString);
		}
		return orders;
	}

	public void writeBuyer(Buyer[] buyers) {
		fileWorker = new TextFileWorker(PATH_BUYER);
		buyersString = new String[buyers.length];
		for (int i = 0; i < buyers.length; i++) {
			buyersString[i] = buyers[i].toString();
		}
		fileWorker.writeToFile(buyersString);
	}

	public Buyer[] readBuyers() {
		fileWorker = new TextFileWorker(PATH_BUYER);
		buyersString = fileWorker.readFromFile();
		Buyer[] buyers = new Buyer[buyersString.length];
		int i = 0;
		for (String buyerString : buyersString) {
			buyers[i++] = new Buyer(buyerString);
		}
		return buyers;
	}

}
