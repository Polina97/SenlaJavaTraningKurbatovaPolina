package com.senla.bookshop.utils;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entity.IBook;
import com.senla.bookshop.api.entity.IBuyer;
import com.senla.bookshop.api.entity.IOrder;
import com.senla.bookshop.api.entity.StatusOrder;
import com.senla.bookshop.api.manager.IBookManager;
import com.senla.bookshop.api.manager.IBuyerManager;
import com.senla.bookshop.api.manager.IOrderManager;
import com.senla.bookshop.di.DIBookShop;

public class Parser {
	private static final String SECOND_SPLITTER = "/";
	private static final String SPLITTER = ",";
	private static Logger log = Logger.getLogger(Parser.class);
	private static IBookManager bookManager = (IBookManager) DIBookShop.load(IBookManager.class.getName());
	private static IOrderManager orderManager = (IOrderManager) DIBookShop.load(IOrderManager.class.getName());
	private static IBuyerManager buyerManager = (IBuyerManager) DIBookShop.load(IBuyerManager.class.getName());

	public static IBook bookParser(String description) {
		IBook book = (IBook) DIBookShop.load(IBook.class.getName());
		try {
			String[] bookString = description.split(SPLITTER);
			book.setId(Integer.parseInt(bookString[0]));
			book.setName(bookString[1]);
			book.setAuthor(bookString[2]);
			GregorianCalendar datePublication = stringToDate(bookString[3]);
			book.setDatePublication(datePublication);
			GregorianCalendar dateOld = stringToDate(bookString[4]);
			book.setDateOld(dateOld);
			book.setPrice(Integer.parseInt(bookString[5]));
			Boolean inStock = false;
			if (bookString[6].equals("true")) {
				inStock = true;
			}
			book.setInStock(inStock);
			book.setRequests(Integer.parseInt(bookString[7]));
			Boolean application = false;
			if (bookString[8].equals("true")) {
				application = true;
			}
			book.setApplication(application);
			return book;
		} catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
			log.error(e);
			e.printStackTrace();
			return null;
		}
	}

	public static IBuyer buyerParser(String description) {
		try {
			IBuyer buyer = (IBuyer) DIBookShop.load(IBuyer.class.getName());
			String[] stringBuyer = description.split(SPLITTER);
			buyer.setId(Integer.parseInt(stringBuyer[0]));
			buyer.setName(stringBuyer[1]);
			if (Integer.parseInt(stringBuyer[2]) != 0) {
				IOrder order = orderManager.getOrderById(Integer.parseInt(stringBuyer[2]));
				buyer.setOrder(order);
			}
			return buyer;
		} catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
			log.error(e);
			return null;
		}
	}

	public static IOrder orderParser(String description) {
		IOrder order = (IOrder) DIBookShop.load(IOrder.class.getName());
		try {
			String[] stringOrder = description.split(SPLITTER);
			order.setId(Integer.parseInt(stringOrder[0]));
			IBuyer buyer = buyerManager.getById(Integer.parseInt(stringOrder[1]));
			order.setBuyer(buyer);
			order.setPrice(Integer.parseInt(stringOrder[2]));
			GregorianCalendar date =stringToDate(stringOrder[3]);
			order.setDate(date);
			order.setStatus(StatusOrder.valueOf(stringOrder[4]));
			List<IBook> books = null;
			if (stringOrder[5] != "0") {
				books = new ArrayList<IBook>();
				String[] stringBooks = stringOrder[6].split(SECOND_SPLITTER);
				for (int i = 0; i < stringBooks.length; i++) {
					books.add((IBook) bookManager.getById(Integer.parseInt(stringBooks[i])));
				}
			}
			order.setBooks(books);
			return order;
		} catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			log.error(e);
			return null;
		}

	}

	public static GregorianCalendar stringToDate(String date) {
		String[] dates = date.split(SECOND_SPLITTER);
		return new GregorianCalendar(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]),
				Integer.parseInt(dates[2]));
	}
}
