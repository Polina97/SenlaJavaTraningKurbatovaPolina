package com.senla.bookshop.resources;

import com.danco.training.TextFileWorker;
import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.entity.Order;

public class FileWorker {
	private static TextFileWorker textFileWorker;
	private static String[] booksString;
	private static String[] ordersString;
	private static String[] buyersString;
	public final String PATH_BOOK;
	private final String PATH_ORDER;
	private final String PATH_BUYER;

	
	public FileWorker(String pathBooks, String pathOrders, String pathBuyers) {
		this.PATH_BOOK = "src/Books.txt";
		this.PATH_ORDER = "src/Orders.txt";
		this.PATH_BUYER = "src/Buyers.txt";
	}

	public void writeBooks(IBook[] books) {
		textFileWorker = new TextFileWorker(this.PATH_BOOK);
		booksString = new String[books.length];
		for (int i = 0; i < books.length; i++) {
			booksString[i] = books[i].toString();
		}
		textFileWorker.writeToFile(booksString);
	}

	public IBook[] readBooks() {
		textFileWorker = new TextFileWorker(PATH_BOOK);
		booksString = textFileWorker.readFromFile();
		Book[] books = new Book[booksString.length];
		int i = 0;
		for (String bookString : booksString) {
			books[i++] = new Book(bookString);
		}
		return books;
	}

	public void writeOrders(IOrder[] orders) {
		textFileWorker = new TextFileWorker(PATH_ORDER);
		ordersString = new String[orders.length];
		for (int i = 0; i < orders.length; i++) {
			ordersString[i] = orders[i].toString();
		}
		textFileWorker.writeToFile(ordersString);
	}

	public IOrder[] readOrders() {
		textFileWorker = new TextFileWorker(PATH_ORDER);
		ordersString = textFileWorker.readFromFile();
		Order[] orders = new Order[ordersString.length];
		int i = 0;
		for (String buyerString : ordersString) {
			orders[i++] = new Order(buyerString, this);
		}
		return orders;
	}

	public void writeBuyer(IBuyer[] buyers) {
		textFileWorker = new TextFileWorker(PATH_BUYER);
		buyersString = new String[buyers.length];
		for (int i = 0; i < buyers.length; i++) {
			buyersString[i] = buyers[i].toString();
		}
		textFileWorker.writeToFile(buyersString);
	}

	public IBuyer[] readBuyers() {
		textFileWorker = new TextFileWorker(PATH_BUYER);
		buyersString = textFileWorker.readFromFile();
		IBuyer[] buyers = new Buyer[buyersString.length];
		int i = 0;
		for (String buyerString : buyersString) {
			buyers[i++] = new Buyer(buyerString, this);
		}
		return buyers;
	}

}
