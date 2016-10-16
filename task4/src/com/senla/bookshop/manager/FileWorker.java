package com.senla.bookshop.manager;

import com.danco.training.TextFileWorker;
import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.Buyer;

public class FileWorker {
	private static TextFileWorker fileWorker;
	private static String[] booksString;
	private static String[] buyersString;
	private static final String PATH_BOOK = "src/Books.txt";
	private static final String PATH_BUYER = "src/Buyers.txt";

	public static void writeBooks(IBook[] books) {
		fileWorker = new TextFileWorker(PATH_BOOK);
		booksString = new String[books.length];
		for (int i = 0; i < books.length; i++) {
			booksString[i] = books[i].toString();
		}
		fileWorker.writeToFile(booksString);
	}

	public static Book[] readBooks() {
		fileWorker = new TextFileWorker(PATH_BOOK);
		booksString = fileWorker.readFromFile();
		Book[] books = new Book[booksString.length];
		int i = 0;
		for (String bookString : booksString) {
			books[i++] = new Book(bookString);
		}
		return books;
	}

	public static void writeBuyers(IBuyer[] buyers) {
		fileWorker = new TextFileWorker(PATH_BUYER);
		buyersString = new String[buyers.length];
		for (int i = 0; i < buyers.length; i++) {
			buyersString[i] = buyers[i].toString();
		}
		fileWorker.writeToFile(buyersString);
	}

	public static IBuyer[] readBuyers() {
		fileWorker = new TextFileWorker(PATH_BUYER);
		buyersString = fileWorker.readFromFile();
		IBuyer[] buyers = new Buyer[buyersString.length];
		int i = 0;
		for (String buyerString : buyersString) {
			buyers[i++] = new Buyer(buyerString);
		}
		return buyers;
	}

}
