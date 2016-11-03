package com.senla.bookshop.resources;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.entity.*;
import com.senla.bookshop.manager.BookManager;
import com.senla.bookshop.manager.OrderManager;

public class Parser {
	private static Logger log = Logger.getLogger(Parser.class.getName());
	private static final String SLASH = "/";
	private static final String ERROR = "Entity creation error";

	public static Book bookParser(String description) throws Exception {
		try {
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
		} catch (NumberFormatException | NullPointerException e) {
			log.error(ERROR, e);
			throw new Exception("Book wasn't created: " + e);
		}

	}

	public static Buyer buyerParser(String description) throws Exception {
		try {
			String[] stringBuyer = description.split(SLASH);
			Integer id = Integer.parseInt(stringBuyer[0]);
			String name = stringBuyer[1];
			List<IOrder> orders = new ArrayList<IOrder>();
			if (Integer.parseInt(stringBuyer[2]) != 0) {
				OrderManager orderManager = new OrderManager();
				for (int i = 3; i < Integer.parseInt(stringBuyer[2]); i++) {
					orders.add(orderManager.getOrderById(Integer.parseInt(stringBuyer[i])));
				}
			}
			return new Buyer(id, name, orders);
		} catch (NumberFormatException | NullPointerException e) {
			log.error(ERROR, e);
			throw new Exception("Buyer wasn't created: " + e);
		}
	}

	public static Order orderParser(String description) throws Exception {
		try {
			String[] stringOrder = description.split(SLASH);
			Integer id = Integer.parseInt(stringOrder[0]);
			IBuyer buyer = new Buyer(Integer.parseInt(stringOrder[1]), stringOrder[2]);
			Integer price = Integer.parseInt(stringOrder[3]);
			GregorianCalendar date = new GregorianCalendar(Integer.parseInt(stringOrder[4]),
					Integer.parseInt(stringOrder[5]), Integer.parseInt(stringOrder[6]));
			StatusOrder status = StatusOrder.valueOf(stringOrder[7]);
			List<IBook> books = new ArrayList<IBook>();
			if (stringOrder[8] != "0") {
				BookManager bookManager = new BookManager();
				for (int i = 4; i < Integer.parseInt(stringOrder[8]); i++) {
					books.add(bookManager.getById(Integer.parseInt(stringOrder[i])));
				}
			}
			return new Order(id, buyer, books, price, date, status);
		} catch (NumberFormatException | NullPointerException e) {
			log.error(ERROR, e);
			throw new Exception("Order wasn't created: " + e);
		}

	}
}
