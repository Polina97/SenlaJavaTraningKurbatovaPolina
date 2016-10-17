package com.senla.bookshop.main;

import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.entity.EStatusOrder;
import com.senla.bookshop.entity.Order;
import com.senla.bookshop.manager.BookManager;
import com.senla.bookshop.manager.OrderManager;
import com.senla.bookshop.resources.ArrayWorker;
import com.senla.bookshop.resources.Date;
import com.senla.bookshop.resources.Printer;

public class Runner {
	private static final String P = "------------------------";
	private static BookManager bookManager;
	private static OrderManager orderManager;
	static Date date = new Date(16, 7, 2016);
	static Date date2 = new Date(15, 7, 2016);
	static Date date3 = new Date(1, 9, 2015);

	private static Book b1 = new Book("Anna Karenina", "Leo Tolstoy", date, 300_000);
	private static Book b2 = new Book("Hamlet", "William Shakespeare", date, 250_000);
	private static Book b3 = new Book("War and Peace", "Leo Tolstoy", date, 360_000);
	private static Book b4 = new Book("The Great Gatsby", " F. Scott Fitzgerald", date2, 180_000);
	private static Book b5 = new Book("Madame Bovary", "Gustave Flaubert", date3, 100_000);
	private static Book b6 = new Book("Emma", "Jane Austen", date3, 240_000);

	
	

	public static void main(String[] args) {

		bookManager = new BookManager();
		workBookManager();
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
		Printer.print("List of old Books: ");
		ArrayWorker.showArray(bookManager.getOldBooks());
	}

	private static void workWithOrders() {

	}
}
