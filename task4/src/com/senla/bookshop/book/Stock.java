package com.senla.bookshop.book;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.danco.training.TextFileWorker;

public class Stock {
	private Book[] books;
	private Book[] oldBooks;
	private final Calendar CALENDAR = new GregorianCalendar();
	private Date nowDate;
	private TextFileWorker fileWorker;
	private final String PATH = "???";
	private final String ADD = " was added to Stock.";
	private final String IN_STOCK = " is already in Stock!";
	private final String DELETE = " was deleted from Stock.";
	

	public Stock() {
		nowDate = new Date(CALENDAR.get(Calendar.DAY_OF_MONTH), CALENDAR.get(Calendar.MONTH),
				CALENDAR.get(Calendar.YEAR));
		fileWorker = new TextFileWorker(PATH);
	}

	
	

	public Date getNowDate() {
		return nowDate;
	}

	public void add(Book b) {
		if (books != null) {
			if (!contains(b)) {
				Book[] books2 = new Book[books.length + 1];
				for (int i = 0; i < books.length; i++) {
					books2[i] = books[i];
				}
				books2[books.length] = b;
				books = books2;
				//write to file!
				fileWorker.writeToFile(toStringBooks());
				System.out.println(b.getName() + ADD);
			} else {
				System.out.println(b.getName() + IN_STOCK);
			}

		} else {
			books = new Book[1];
			books[0] = b;
			System.out.println(b.getName() + ADD);
		}
	}

	public void delete(Book b) {
		if (books != null && contains(b)) {
			Book[] books2 = new Book[books.length - 1];
			int i = 0;
			for (Book book : books) {
				if (!book.equals(b)) {
					books2[i++] = book;
				}
			}
			books = books2;
			System.out.println(b.getName() + DELETE);

		} else {
			System.out.println(b.getName() + IN_STOCK);
		}
	}

	public boolean contains(Book b) {
		boolean answer = false;
		for (Book book : books) {
			if (book.equals(b)) {
				answer = true;
				break;
			}
		}
		return answer;
	}

	public Book[] getOldBooks() {
		int counter = 0;
		for (Book b : books) {
			if (b.isOld(nowDate)) {
				++counter;
			}
		}
		if (counter != 0) {
			oldBooks = new Book[counter];
			int i = 0;
			for (Book b : books) {
				if (b.isOld(nowDate)) {
					oldBooks[i++] = b;
				}
			}
		}
		return oldBooks;
	}
	private String[] toStringBooks(){
		String [] bookString = new String[books.length];
		for(int i =0 ; i< books.length; i++){
			bookString[i] = books[i].toString();
		}
		return bookString;
	}

}
