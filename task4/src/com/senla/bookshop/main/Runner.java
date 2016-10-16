package com.senla.bookshop.main;

import com.senla.bookshop.entity.Book;
import com.senla.bookshop.manager.BookManager;

public class Runner {
	private static BookManager bookManager;

	public static void main(String[] args) {

		workBookManager();
	}

	private static void workBookManager() {
		Date date = new Date(16, 7, 2016);
		Book b1 = new Book("Anna Karenina", "Leo Tolstoy", date, 300_000);
		Book b2 = new Book("Hamlet", "William Shakespeare", date, 250_000);
		Book b3 = new Book("War and Peace", "Leo Tolstoy", date, 360_000);
		date = new Date(15, 7, 2016);
		Book b4 = new Book("The Great Gatsby", " F. Scott Fitzgerald", date, 180_000);
		bookManager = new BookManager();

		Book b5 = new Book("Madame Bovary", "Gustave Flaubert", date, 100_000);

		bookManager.showAllBooks();
		Printer.print("------------------------");
		bookManager.sortAlphabet();
		Printer.print("------------------------");
		bookManager.sortPrice();
		bookManager.addToStock(b4);
		bookManager.addToStock(b3);
		bookManager.submitApplication(b1);
		Printer.print("------------------------");
		bookManager.sortStock();
		Printer.print("------------------------");
		bookManager.sortDate();

	}

}
