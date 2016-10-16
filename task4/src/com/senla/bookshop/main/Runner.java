package com.senla.bookshop.main;

import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.entity.EStatusOrder;
import com.senla.bookshop.entity.Order;
import com.senla.bookshop.manager.BookManager;
import com.senla.bookshop.manager.OrderManager;

public class Runner {
	private static BookManager bookManager;
	private static OrderManager orderManager;
	static Date date = new Date(16, 7, 2016);
	private static Book b1 = new Book("Anna Karenina", "Leo Tolstoy", date, 300_000);
	private static Book b2 = new Book("Hamlet", "William Shakespeare", date, 250_000);
	private static Book b3 = new Book("War and Peace", "Leo Tolstoy", date, 360_000);
	static Date date2 = new Date(15, 7, 2016);
	private static Book b4 = new Book("The Great Gatsby", " F. Scott Fitzgerald", date2, 180_000);
	private static Book b5 = new Book("Madame Bovary", "Gustave Flaubert", date2, 100_000);

	public static void main(String[] args) {

		bookManager = new BookManager();
		workWithOrders();

		// workBookManager();
	}

	public static void workBookManager() {

		bookManager.showAllBooks();
		Printer.print("------------------------");
		bookManager.sortAlphabet();
		Printer.print("------------------------");
		bookManager.sortPrice();
		bookManager.addToStock(b1);
		bookManager.addToStock(b3);
		bookManager.submitApplication(b1);
		Printer.print("------------------------");
		bookManager.sortStock();
		Printer.print("------------------------");
		bookManager.sortDate();

	}

	private static void workWithOrders(){
		Buyer bu1 = new Buyer("Matt");
		Buyer bu2 = new Buyer("Scarlett");
		Order o1 = new Order(bu1, new Book[]{b1,b2}, date, EStatusOrder.KIT);
		Order o2 = new Order(bu2, new Book[]{b1,b3, b4}, date, EStatusOrder.KIT);
		Order o3 = new Order(bu2, new Book[]{b2,b3, b5}, date2, EStatusOrder.CANCELED);
		orderManager = new OrderManager(new Order[]{o1,o2});
		orderManager.add(o3);
		orderManager.showAllOrders();
		Printer.print("------------------------");
		orderManager.sortDate();
		Printer.print("------------------------");
		orderManager.sortPrice();
		Printer.print("------------------------");
		orderManager.sortStatus();
		
		
	}
}
