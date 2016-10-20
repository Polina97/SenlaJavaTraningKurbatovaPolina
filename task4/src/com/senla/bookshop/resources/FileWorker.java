package com.senla.bookshop.resources;

import com.danco.training.TextFileWorker;
import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.entity.Order;

public class FileWorker {
	public final String PATH_BOOK = "src/Books.txt";
	private final String PATH_ORDER = "src/Orders.txt";
	private final String PATH_BUYER = "src/Buyers.txt";

	public String pathBooks;
	private String pathOrders;
	private String pathBuyers;

	private static TextFileWorker textFileWorker;

	private String[] booksString;
	private String[] ordersString;
	private String[] buyersString;

	public FileWorker(String pathBooks, String pathOrders, String pathBuyers) {
		if (pathBooks != null && pathOrders != null && pathBuyers != null) {
			this.pathBooks = pathBooks;
			this.pathOrders = pathOrders;
			this.pathBuyers = pathBuyers;
		} else {
			this.pathBooks = PATH_BOOK;
			this.pathOrders = PATH_ORDER;
			this.pathBuyers = PATH_BUYER;
		}
	}

	public void writeBooks(IBook[] books) {
		textFileWorker = new TextFileWorker(this.pathBooks);
		int counter = 0;
		for (IBook b : books) {
			if (b != null) {
				counter++;
			}
		}
		booksString = new String[counter];
		int j = 0;
		for (int i = 0; i < books.length; i++) {
			if (books[i] != null) {
				booksString[j++] = books[i].toString();
			}
		}
		textFileWorker.writeToFile(booksString);
	}

	public IBook[] readBooks() {
		textFileWorker = new TextFileWorker(this.pathBooks);
		booksString = textFileWorker.readFromFile();
		if (booksString.length != 0) {
			IBook[] books = new Book[booksString.length];
			int i = 0;
			for (String bookString : booksString) {
				books[i++] = Parser.bookParser(bookString);
			}
			return books;
		}
		return null;
	}

	public void writeOrders(IOrder[] orders) {

		textFileWorker = new TextFileWorker(this.pathOrders);
		int counter = 0;
		for (IOrder b : orders) {
			if (b != null) {
				counter++;
			}
		}
		ordersString = new String[counter];
		int j = 0;
		for (int i = 0; i < orders.length; i++) {
			if (orders[i] != null) {
				ordersString[j++] = orders[i].toString();
			}
		}
		textFileWorker.writeToFile(ordersString);
	}

	public IOrder[] readOrders() {
		textFileWorker = new TextFileWorker(this.pathOrders);
		ordersString = textFileWorker.readFromFile();
		if (ordersString.length != 0) {
			IOrder[] orders = new Order[ordersString.length];
			int i = 0;
			for (String orderString : ordersString) {
					orders[i++] = Parser.orderParser(orderString);
			}
			return orders;
		}
		return null;
	}

	public void writeBuyer(IBuyer[] buyers) {
		textFileWorker = new TextFileWorker(this.pathBuyers);
		int counter = 0;
		for (IBuyer b : buyers) {
			if (b != null) {
				counter++;
			}
		}
		if (counter != 0) {
			buyersString = new String[counter];
			int j = 0;
			for (IBuyer b : buyers) {
				if (b != null) {
					buyersString[j++] = b.toString();
				}
			}
		}
		textFileWorker.writeToFile(buyersString);
	}

	public IBuyer[] readBuyers() {
		textFileWorker = new TextFileWorker(this.pathBuyers);
		buyersString = textFileWorker.readFromFile();
		if (buyersString.length != 0) {
			IBuyer[] buyers = new Buyer[buyersString.length];
			int i = 0;
			for (String buyerString : buyersString) {
				buyers[i++] = Parser.buyerParser(buyerString);
			}
			return buyers;
		}
		return null;
	}

}
