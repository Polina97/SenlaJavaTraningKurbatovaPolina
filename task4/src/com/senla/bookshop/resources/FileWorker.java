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
		booksString = new String[books.length];
		for (int i = 0; i < books.length; i++) {
			booksString[i] = books[i].toString();
		}
		textFileWorker.writeToFile(booksString);
	}

	public IBook[] readBooks() {
		textFileWorker = new TextFileWorker(this.pathBooks);
		booksString = textFileWorker.readFromFile();
		IBook[] books = new Book[booksString.length];
		int i = 0;
		for (String bookString : booksString) {
			books[i++] = Parser.bookParser(bookString);
		}
		return books;
	}

	public void writeOrders(IOrder[] orders) {
		textFileWorker = new TextFileWorker(this.pathOrders);
		ordersString = new String[orders.length];
		for (int i = 0; i < orders.length; i++) {
			ordersString[i] = orders[i].toString();
		}
		textFileWorker.writeToFile(ordersString);
	}

	public IOrder[] readOrders() {
		textFileWorker = new TextFileWorker(this.pathOrders);
		ordersString = textFileWorker.readFromFile();
		IOrder[] orders = new Order[ordersString.length];
		int i = 0;
		for (String orderString : ordersString) {
			orders[i++] = Parser.orderParser(orderString);
		}
		return orders;
	}

	public void writeBuyer(IBuyer[] buyers) {
		textFileWorker = new TextFileWorker(this.pathBuyers);
		buyersString = new String[buyers.length];
		for (int i = 0; i < buyers.length; i++) {
			buyersString[i] = buyers[i].toString();
		}
		textFileWorker.writeToFile(buyersString);
	}

	public IBuyer[] readBuyers() {
		textFileWorker = new TextFileWorker(this.pathBuyers);
		buyersString = textFileWorker.readFromFile();
		IBuyer[] buyers = new Buyer[buyersString.length];
		int i = 0;
		for (String buyerString : buyersString) {
			buyers[i++] = Parser.buyerParser(buyerString);
		}
		return buyers;
	}

}
