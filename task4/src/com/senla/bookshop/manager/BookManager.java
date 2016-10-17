package com.senla.bookshop.manager;

import java.util.Arrays;

import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.managers.IBookManager;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.resources.ArrayWorker;
import com.senla.bookshop.resources.Date;
import com.senla.bookshop.resources.FileWorker;

public class BookManager implements IBookManager {
	private Book[] books;
	private Book[] oldBooks;
	private Book[] stockBooks;
	private Book[] applicationBooks;
	private Date nowDate;

	public BookManager() {
		this.nowDate = Date.getNowDate();
		this.books = FileWorker.readBooks();
		this.oldBooks = getOldBooks();
		this.stockBooks = getStockBooks();
		this.applicationBooks = getApplicationBooks();
	}

	public BookManager(Book[] books) {
		nowDate = Date.getNowDate();
		this.books = FileWorker.readBooks();
		for (Book book : books) {
			this.books = ArrayWorker.addBook(book, this.books);
		}
		this.oldBooks = getOldBooks();
		this.stockBooks = getStockBooks();
		this.applicationBooks = getApplicationBooks();
		FileWorker.writeBooks(this.books);
	}

	public Book getBook(Book book){
		for(int i=0; i< this.books.length; i++){
			if(books[i].equals(book)){
				return books[i];
			}
		}return null;
	}
	@Override
	public void add(BaseEntity book) {
		this.books = ArrayWorker.addBook((Book) book, this.books);
		FileWorker.writeBooks(this.books);

	}

	@Override
	public void delete(BaseEntity entity) {
		ArrayWorker.deleteBook((Book) entity, this.books);
		FileWorker.writeBooks(this.books);

	}

	@Override
	public void showAllBooks() {
		ArrayWorker.showArray(this.books);
	}

	@Override
	public Book[] getOldBooks() {
		for (Book b : this.books) {
			if (b.isOld(nowDate)) {
				this.oldBooks = ArrayWorker.addBook(b, this.oldBooks);
			}
		}
		return oldBooks;
	}

	@Override
	public void setOldBooks(Book[] oldBooks) {
		this.oldBooks = oldBooks;
	}

	@Override
	public Book[] getStockBooks() {
		for (Book b : this.books) {
			if (b.isInStock()) {
				this.stockBooks = ArrayWorker.addBook(b, this.stockBooks);
			}
		}
		return this.stockBooks;
	}

	@Override
	public void setStockBooks(Book[] stockBooks) {
		this.stockBooks = stockBooks;
	}

	@Override
	public Book[] getApplicationBooks() {
		for (Book book : this.books) {
			if (book.isApplication()) {
				this.applicationBooks = ArrayWorker.addBook(book, this.applicationBooks);
			}
		}
		return this.applicationBooks;
	}

	@Override
	public void addToStock(IBook book) {
		if (ArrayWorker.contains(book, this.books)) {
			for (int i = 0; i < books.length; i++) {
				if (this.books[i].equals(book)) {
					this.books[i].setInStock(true);
					this.books[i].setApplication(false);
				}
			}
			FileWorker.writeBooks(this.books);
		}

	}

	@Override
	public void deleteFromStock(IBook book) {
		if (ArrayWorker.contains(book, this.books)) {
			for (int i = 0; i < books.length; i++) {
				if (this.books[i].equals(book)) {
					this.books[i].setInStock(false);
				}
			}
		}
		FileWorker.writeBooks(this.books);
	}

	@Override
	public void submitApplication(Book book) {
		if (ArrayWorker.contains(book, this.books)) {
			for (int i = 0; i < books.length; i++) {
				if (this.books[i].equals(book) && !this.books[i].isInStock()) {
					this.books[i].setApplication(true);
				}
			}
		}
		FileWorker.writeBooks(this.books);
	}

	@Override
	public boolean isInStock(Book book){
		return ArrayWorker.contains(book, this.getStockBooks());
	}
	@Override
	public void sortAlphabet() {
		Arrays.sort(this.books, Book.AlphabetComparator);
		ArrayWorker.showArray(this.books);
		FileWorker.writeBooks(this.books);
	}

	@Override
	public void sortPrice() {
		Arrays.sort(this.books, Book.PriceComparator);
		ArrayWorker.showArray(this.books);
		FileWorker.writeBooks(this.books);
	}

	@Override
	public void sortDate() {
		Arrays.sort(this.books, Book.DateComparator);
		ArrayWorker.showArray(this.books);
		FileWorker.writeBooks(this.books);
	}

	@Override
	public void sortStock() {
		this.books = FileWorker.readBooks();
		Arrays.sort(this.books, Book.StockComparator);
		ArrayWorker.showArray(this.books);
		FileWorker.writeBooks(this.books);
	}

}
