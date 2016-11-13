package com.senla.bookshop.resources;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.entity.*;
import com.senla.bookshop.main.Shop;

public class Parser {

	private static final String SPLITTER = ",";
	private static Logger log = Logger.getLogger(Parser.class.getName());

	public static IBook bookParser(String description) {
		try {
			String[] bookString = description.split(SPLITTER);
			Integer id = Integer.parseInt(bookString[0]);
			String name = bookString[1];
			String author = bookString[2];
			GregorianCalendar datePublication = BaseEntity.stringToDate(bookString[3]);
			GregorianCalendar dateOld = BaseEntity.stringToDate(bookString[4]);
			Integer price = Integer.parseInt(bookString[5]);
			Boolean inStock = false;
			if (bookString[6].equals("true")) {
				inStock = true;
			}
			Integer requests = Integer.parseInt(bookString[7]);
			Boolean application = false;
			if (bookString[8].equals("true")) {
				application = true;
			}
			return new Book(id, name, author, datePublication, dateOld, price, inStock, requests, application);
		} catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
			log.error(e);
			e.printStackTrace();
			return null;
		}
	}

	public static IBuyer buyerParser(String description) {
		try {
			String[] stringBuyer = description.split(SPLITTER);
			Integer id = Integer.parseInt(stringBuyer[0]);
			String name = stringBuyer[1];
			if (Integer.parseInt(stringBuyer[2]) != 0) {
				IOrder order = (Order) Shop.orderManager.getOrderById(Integer.parseInt(stringBuyer[2]));
				return new Buyer(id, name, order);
			}
			return new Buyer(id, name);
		} catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
			log.error(e);
			return null;
		}
	}

	public static IOrder orderParser(String description) {
		try {
			String[] stringOrder = description.split(SPLITTER);
			Integer id = Integer.parseInt(stringOrder[0]);
			IBuyer buyer = (Buyer) Shop.buyerManager.getById(Integer.parseInt(stringOrder[1]));
			Integer price = Integer.parseInt(stringOrder[2]);
			GregorianCalendar date = BaseEntity.stringToDate(stringOrder[3]);
			StatusOrder status = StatusOrder.valueOf(stringOrder[4]);
			List<IBook> books = null;
			if (stringOrder[5] != "0") {
				books = new ArrayList<IBook>();
				String[] stringBooks = stringOrder[6].split(BaseEntity.SECOND_SPLITTER);
				for (int i = 0; i < stringBooks.length; i++) {
					books.add((Book) Shop.bookManager.getById(Integer.parseInt(stringBooks[i])));
				}
			}
			return new Order(id, buyer, books, price, date, status);
		} catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			log.error(e);
			return null;
		}

	}
}
