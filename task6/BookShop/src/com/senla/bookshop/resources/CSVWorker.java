package com.senla.bookshop.resources;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entities.IBaseEntity;
import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.entities.IOrder;

public class CSVWorker {
	private static CSVFileWorker fileWorker;
	private List<String> list;
	private Logger log = Logger.getLogger(CSVWorker.class.getName());

	public final String PATH_BOOK = "src/Books.csv";
	private final String PATH_ORDER = "src/Orders.csv";
	private final String PATH_BUYER = "src/Buyers.csv";

	public final static StringBuilder HEAD_BOOK = new StringBuilder();
	private final static StringBuilder HEAD_ORDER = new StringBuilder();
	private final static StringBuilder HEAD_BUYER = new StringBuilder();
	static {
		HEAD_BOOK.append("id").append(IBaseEntity.SPLITTER).append("name").append(IBaseEntity.SPLITTER).append("author")
				.append(IBaseEntity.SPLITTER).append("date Publication").append(IBaseEntity.SPLITTER).append("date Old")
				.append(IBaseEntity.SPLITTER).append("price").append(IBaseEntity.SPLITTER).append("inStock")
				.append(IBaseEntity.SPLITTER).append("requests").append(IBaseEntity.SPLITTER).append("application");
		HEAD_ORDER.append("id").append(IBaseEntity.SPLITTER).append("buyer id").append(IBaseEntity.SPLITTER)
				.append("price").append(IBaseEntity.SPLITTER).append("date").append(IBaseEntity.SPLITTER)
				.append("status").append(IBaseEntity.SPLITTER).append("count of books").append(IBaseEntity.SPLITTER)
				.append("books");
		HEAD_BUYER.append("id").append(IBaseEntity.SPLITTER).append("name").append(IBaseEntity.SPLITTER)
				.append("order");
	}

	public List<IBook> writeBooks(List<IBook> entities) {
		try {
			fileWorker = new CSVFileWorker(PATH_BOOK);
			list = new ArrayList<String>();
			for (IBook book : entities) {
				list.add(book.toString());
			}
			fileWorker.writeToCSV(list, HEAD_BOOK.toString());
			return entities;
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	public List<IOrder> writeOrders(List<IOrder> entities) {
		try {
			fileWorker = new CSVFileWorker(PATH_ORDER);
			list = new ArrayList<String>();
			for (IOrder order : entities) {
				list.add(order.toString());
			}
			fileWorker.writeToCSV(list, HEAD_ORDER.toString());
			return entities;
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	public  List<IBuyer> writeBuyers(List<IBuyer> entities) {
		try {
			fileWorker = new CSVFileWorker(PATH_BUYER);
			list = new ArrayList<String>();
			for (IBuyer buyer : entities) {
				list.add(buyer.toString());
			}
			fileWorker.writeToCSV(list, HEAD_BUYER.toString());
			return entities;
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	public List<IBook> readBooks() {
		try {
			fileWorker = new CSVFileWorker(PATH_BOOK);
			list = fileWorker.readeFromCSV();
			if (list != null) {
				List<IBook> books = new ArrayList<IBook>();
				for (String bookString : list) {
					books.add(Parser.bookParser(bookString));
				}
				return books;
			} else {
				return null;
			}
		} catch (NullPointerException e) {
			log.error(e);
			return null;
		}

	}

	public List<IOrder> readOrders() {
		try {
			fileWorker = new CSVFileWorker(PATH_ORDER);
			list = fileWorker.readeFromCSV();
			if (list.size() != 0) {
				List<IOrder> orders = new ArrayList<IOrder>();
				for (String orderString : list) {
					orders.add(Parser.orderParser(orderString));
				}
				return orders;
			}
		} catch (NullPointerException e) {
			log.error(e);
		}
		return null;
	}

	public List<IBuyer> readBuyers() {
		try {
			fileWorker = new CSVFileWorker(PATH_BUYER);
			list = fileWorker.readeFromCSV();
			if (list.size() != 0) {
				List<IBuyer> buyers = new ArrayList<IBuyer>();
				for (String buyerString : list) {
					buyers.add(Parser.buyerParser(buyerString));
				}
				return buyers;
			}
		} catch (NullPointerException e) {
			log.error(e);
		}
		return null;
	}

}
