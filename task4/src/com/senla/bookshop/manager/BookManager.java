package com.senla.bookshop.manager;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.danco.training.TextFileWorker;
import com.senla.bookshop.api.entities.IBaseEntity;
import com.senla.bookshop.api.managers.IBookManager;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.main.Date;

public class BookManager implements IBookManager{
	private ArrayWorker books;
	private final String PATH = "../../../../Books.txt";
	private static TextFileWorker fileWorker;
	
	private final Calendar CALENDAR = new GregorianCalendar();
	private Date nowDate;
	
	public BookManager() {
		nowDate =  new Date(CALENDAR.get(Calendar.DAY_OF_MONTH), CALENDAR.get(Calendar.MONTH),
				CALENDAR.get(Calendar.YEAR));
		fileWorker = new TextFileWorker(PATH);
	}
	@Override
	public void add(IBaseEntity book) {
		books.addEntity(book);
		FileWorker.writeBooks((Book[])books.getArray());
		
	}
	@Override
	public void delete(IBaseEntity entity) {
		// TODO Auto-generated method stub
		
	}
	
	
//	public Book[] getOldBooks() {
//		int counter = 0;
//		for (Book b : books) {
//			if (b.isOld(nowDate)) {
//				++counter;
//			}
//		}
//		if (counter != 0) {
//			oldBooks = new Book[counter];
//			int i = 0;
//			for (Book b : books) {
//				if (b.isOld(nowDate)) {
//					oldBooks[i++] = b;
//				}
//			}
//		}
//		return oldBooks;
//	}

}
