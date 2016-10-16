package com.senla.bookshop.manager;

import com.danco.training.TextFileWorker;
import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.Order;

public class FileWorker {
	private static TextFileWorker fileWorker;
	private static String[] booksString;
	private static String[] ordersString;
	private static final String PATH_BOOK = "src/Books.txt";
	private static final String PATH_ORDER = "src/Orders.txt";

	public static void writeBooks(IBook[] books) {
		fileWorker = new TextFileWorker(PATH_BOOK);
		booksString = new String[books.length];
		for (int i = 0; i < books.length; i++) {
			booksString[i] = books[i].toString();
		}
		fileWorker.writeToFile(booksString);
	}

	public static Book[] readBooks() {
		fileWorker = new TextFileWorker(PATH_BOOK);
		booksString = fileWorker.readFromFile();
		Book[] books = new Book[booksString.length];
		int i = 0;
		for (String bookString : booksString) {
			books[i++] = new Book(bookString);
		}
		return books;
	}

	public static void writeOrders(IOrder[] orders) {
		fileWorker = new TextFileWorker(PATH_ORDER);
		ordersString = new String[orders.length];
		for (int i = 0; i < orders.length; i++) {
			ordersString[i] = orders[i].toString();
		}
		fileWorker.writeToFile(ordersString);
	}

	public static Order[] readOrders() {
		fileWorker = new TextFileWorker(PATH_ORDER);
		ordersString = fileWorker.readFromFile();
		Order[] orders = new Order[ordersString.length];
		int i = 0;
		for (String buyerString : ordersString) {
			orders[i++] = new Order(buyerString);
		}
		return orders;
	}

}
