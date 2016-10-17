package com.senla.bookshop.main;

import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.EStatusOrder;
import com.senla.bookshop.entity.Order;
import com.senla.bookshop.manager.BookManager;
import com.senla.bookshop.manager.BuyerManager;
import com.senla.bookshop.manager.OrderManager;
import com.senla.bookshop.resources.Date;
import com.senla.bookshop.resources.FileWorker;
import com.senla.bookshop.resources.Printer;

public class Runner {
	private static final String P = "------------------------";
	private static BookManager bookManager;
	private static OrderManager orderManager;
	private static BuyerManager buyerManager;
	private static FileWorker fileWorker;

	static Date date = new Date(16, 7, 2016);
	static Date date2 = new Date(15, 7, 2016);
	static Date date3 = new Date(1, 9, 2015);

	private static Book b1 = new Book("Anna Karenina", "Leo Tolstoy", date, 300_000);
	private static Book b2 = new Book("Hamlet", "William Shakespeare", date, 250_000);
	private static Book b3 = new Book("War and Peace", "Leo Tolstoy", date, 360_000);
	private static Book b4 = new Book("The Great Gatsby", " F. Scott Fitzgerald", date2, 180_000);
	private static Book b5 = new Book("Madame Bovary", "Gustave Flaubert", date3, 300_000);
	private static Book b6 = new Book("Emma", "Jane Austen", date3, 240_000);
	private static Order o1;
	private static Order o2;
	private static Order o3;
	private static Order o4;
	private static Order o5;

	public static void main(String[] args) {
		fileWorker = new FileWorker(args[0], args[1], args[2]);
		createOrders(fileWorker);
		bookManager = new BookManager(fileWorker);
		workBookManager();
		orderManager = new OrderManager(new Order[] { o1, o2, o3, o4 }, fileWorker);
		workWithOrders();
	}

	private static void createOrders(FileWorker fileWorker) {
		o1 = new Order(123, buyerManager.getById(111), new Book[] { b2, b3 }, date, EStatusOrder.KIT, fileWorker);
		o2 = new Order(124, buyerManager.getById(112), new Book[] { b2, b5 }, date3, EStatusOrder.KIT, fileWorker);
		o3 = new Order(125, buyerManager.getById(113), new Book[] { b4, b5 }, date2, EStatusOrder.DELIVERED,
				fileWorker);
		o4 = new Order(126, buyerManager.getById(112), new Book[] { b3, b4, b5 }, date2, EStatusOrder.DELIVERED,
				fileWorker);
		o5 = new Order(127, buyerManager.getById(113), new Book[] { b2, b4, b5 }, date, EStatusOrder.DELIVERED,
				fileWorker);
	}

	public static void workBookManager() {
		Printer.print("List of books: ");
		bookManager.showAllBooks();
		Printer.print(P);
		Printer.print("Sort alphabetically:");
		bookManager.sortAlphabet();
		Printer.print(P);
		Printer.print("Sort by price:");
		bookManager.sortPrice();
		Printer.print(P);
		Printer.print("Sort by date supply:");
		bookManager.sortDate();
		Printer.print(P);
		Printer.print("Add book \"War and Peace\" to the stock.");
		bookManager.addToStock(b3);
		Printer.print("Submit application on book : \"Anna Karenina\"");
		bookManager.submitApplication(b1);
		Printer.print(b1.getDescription());
		Printer.print(P);
		Printer.print("Sort by the presence of stock.");
		bookManager.sortStock();
		Printer.print(P);
		Printer.print("Number of requests for the book: \"Hamlet\": " + bookManager.getBook(b2).getRequests());
		Printer.print("Description of Book  \"War and Peace\": " + bookManager.getBook(b3).getDescription());
		Printer.print("Add book Emma to stock.");
		bookManager.addToStock(b6);
		Printer.print("Delete book from stock: \"Anna Karenina\"");
		bookManager.deleteFromStock(b1);
		Printer.print("List of old Books sorted by Date: ");
		bookManager.sortDateOld();
		Printer.print(P);
		Printer.print("List of old Books sorted by price: ");
		bookManager.sortPriceOld();
		Printer.print(P);
	}

	private static void workWithOrders() {
		Printer.print("List of orders sorted by Date: ");
		orderManager.sortDate();
		Printer.print(P);
		Printer.print("List of orders sorted by Price: ");
		orderManager.sortPrice();
		Printer.print(P);
		Printer.print("List of orders sorted by Status: ");
		orderManager.sortStatus();
		Printer.print(P);
		Printer.print("List of delivered orders sorted by Date: ");
		orderManager.sortDateDelivered();
		Printer.print(P);
		Printer.print("List of delivered orders sorted by Price: ");
		orderManager.sortPriceDelivered();
		Printer.print(P);
		Printer.print("General price: ");
		Printer.print(orderManager.getGeneralPrice());
		Printer.print(P);
		Printer.print("Number of delivered orders: ");
		Printer.print(orderManager.getDeliveredOrders().length);
		Printer.print(P);
		Printer.print("Detalies ofn order 124: ");
		Printer.print(orderManager.getOrderById(124).getDescription());
		Printer.print(P);
		Printer.print("Add new order 127: ");
		orderManager.add(o5);
		Printer.print(P);
		Printer.print("Deliver order 123: ");
		orderManager.deliverOrder(123);
		Printer.print(P);
		Printer.print("Cancel order 124: ");
		orderManager.cancelOrder(124);
	}
}
