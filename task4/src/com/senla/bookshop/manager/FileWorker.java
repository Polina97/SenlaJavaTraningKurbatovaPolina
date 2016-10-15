package com.senla.bookshop.manager;

import com.danco.training.TextFileWorker;
import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.entity.Book;

public class FileWorker {
	private static TextFileWorker fileWorker;
	private static String[] booksString ;
	private static final String PATH_BOOK = "../../../../Books.txt";
	private static final String  PATH_BUYER = "../../../../Byuers.txt";

	public static void writeBooks(IBook[] books) {
		fileWorker = new TextFileWorker(PATH_BOOK);
		booksString = new String[books.length];
		for(int i=0; i<books.length; i++){
			booksString[i] = books[i].toString();
		}
		fileWorker.writeToFile(booksString);
	}
	public static IBook[] readBooks(){
		fileWorker = new TextFileWorker(PATH_BOOK);
		booksString  = fileWorker.readFromFile();
		IBook[] books = new Book[booksString.length];
		int i=0;
		for(String bookString : booksString){
			books[i++] = new Book(bookString);
		}
		return books;
	}

}
