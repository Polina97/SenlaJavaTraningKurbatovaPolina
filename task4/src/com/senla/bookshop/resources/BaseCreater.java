package com.senla.bookshop.resources;

import java.util.GregorianCalendar;

import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.entity.Order;
import com.senla.bookshop.entity.StatusOrder;
import com.senla.bookshop.manager.BookManager;
import com.senla.bookshop.manager.BuyerManager;
import com.senla.bookshop.manager.OrderManager;

public class BaseCreater {
	private static GregorianCalendar date = new GregorianCalendar(2015, 11, 13);
	private static GregorianCalendar date2 = new GregorianCalendar(2016, 7, 12);
	private static GregorianCalendar date3 = new GregorianCalendar(2016, 10, 20);
	private static GregorianCalendar date4 = new GregorianCalendar(2016, 1, 16);

	private static Book b1 = new Book(101, "Anna Karenina", "Leo Tolstoy", date, date2, 300_000, true, 2, false);
	private static Book b2 = new Book(102, "Hamlet", "William Shakespeare", date4, date2, 250_000, true, 3, false);
	private static Book b3 = new Book(103, "War and Peace", "Leo Tolstoy", date, date3, 360_000, false, 0, true);
	private static Book b4 = new Book(104, "The Great Gatsby", "F.Scott Fitzgerald", date4, date2, 180_000, false, 2,
			true);
	private static Book b5 = new Book(105, "Madame Bovary", "Gustave Flaubert", date4, date3, 300_000, true, 1, false);
	private static Book b6 = new Book(106, "Emma", "Jane Austen", date, date3, 240_000, false, 1, true);

	private static Buyer buyer1 = new Buyer(11, "Matt");
	private static Buyer buyer2 = new Buyer(12, "Nataly");
	private static Buyer buyer3 = new Buyer(13, "Tim");

	private static Order o1 = new Order(1001, buyer1, new Book[] { b1, b1, b2 }, date, StatusOrder.KIT);
	private static Order o2 = new Order(1002, buyer2, new Book[] { b2, b4, b5 }, date2, StatusOrder.DELIVERED);
	private static Order o3 = new Order(1003, buyer3, new Book[] { b2, b4 }, date4, StatusOrder.DELIVERED);
	private static Order o4 = new Order(1004, buyer1, new Book[] { b2, b6 }, date, StatusOrder.CANCELED);

	public static void createAll() {
		@SuppressWarnings("unused")
		BookManager bookManager = new BookManager(new Book[] { b1, b2, b3, b4, b5, b6 });
		BuyerManager buyerManager = new BuyerManager(new Buyer[] { buyer1, buyer2, buyer3 });
		OrderManager orderManager = new OrderManager();
		orderManager.add(o1);
		buyerManager.addOrderBuyer(o1);
		orderManager.add(o2);
		buyerManager.addOrderBuyer(o2);
		orderManager.add(o3);
		buyerManager.addOrderBuyer(o3);
		orderManager.add(o4);
		buyerManager.addOrderBuyer(o4);

	}

}
