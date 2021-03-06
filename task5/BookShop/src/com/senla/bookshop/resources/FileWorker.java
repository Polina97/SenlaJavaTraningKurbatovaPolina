package com.senla.bookshop.resources;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.danco.training.TextFileWorker;
import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.entities.IOrder;

public class FileWorker {
	private static Logger log = Logger.getLogger(FileWorker.class.getName());
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

	public void writeBooks(List<IBook> books) {
		textFileWorker = new TextFileWorker(this.pathBooks);
		booksString = new String[books.size()];
		for (int i = 0; i < books.size(); i++) {
			booksString[i] = books.get(i).toString();
		}
		textFileWorker.writeToFile(booksString);
	}

	public List<IBook> readBooks() {
		try {
			textFileWorker = new TextFileWorker(this.pathBooks);
			booksString = textFileWorker.readFromFile();
			List<IBook> books = new ArrayList<IBook>();
			for (String bookString : booksString) {
				books.add(Parser.bookParser(bookString));
			}
			return books;
		} catch (IllegalArgumentException e) {
			log.error(e);
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public void writeOrders(List<IOrder> orders) {
		try {
			textFileWorker = new TextFileWorker(this.pathOrders);
			ordersString = new String[orders.size()];
			for (int i = 0; i < orders.size(); i++) {
				ordersString[i] = orders.get(i).toString();
			}
			textFileWorker.writeToFile(ordersString);
		} catch (IllegalArgumentException e) {
			log.error(e);
		}

	}

	public List<IOrder> readOrders() {
		try {
			textFileWorker = new TextFileWorker(this.pathOrders);
			ordersString = textFileWorker.readFromFile();
			List<IOrder> orders = new ArrayList<IOrder >();

			for (String orderString : ordersString) {
				orders.add(Parser.orderParser(orderString));
			}
			return orders;
		} catch (IllegalArgumentException e) {
			log.error(e);
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public void writeBuyer(List<IBuyer> buyers) {
		try {
			textFileWorker = new TextFileWorker(this.pathBuyers);
			buyersString = new String[buyers.size()];
			for (int i = 0; i < buyers.size(); i++) {
				buyersString[i] = buyers.get(i).toString();
			}
			textFileWorker.writeToFile(buyersString);
		} catch (IllegalArgumentException e) {
			log.error(e);
		}
	}

	public List<IBuyer> readBuyers() {
		try {
		textFileWorker = new TextFileWorker(this.pathBuyers);
		buyersString = textFileWorker.readFromFile();
		List<IBuyer> buyers = new ArrayList<IBuyer>();
		
			for (String buyerString : buyersString) {
				buyers.add(Parser.buyerParser(buyerString));
			}
			return buyers;
		} catch(IllegalArgumentException e){
			log.error(e);
			return null;
		} 
		catch (Exception e) {
			return null;
		}
	}

}
