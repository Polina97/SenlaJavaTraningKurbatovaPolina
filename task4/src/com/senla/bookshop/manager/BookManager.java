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
	private FileWorker fileWorker;

	public BookManager(FileWorker fileWorker) {
		this.fileWorker = fileWorker;
		this.nowDate = Date.getNowDate();
		this.books = this.fileWorker.readBooks();
		this.oldBooks = getOldBooks();
		this.stockBooks = getStockBooks();
		this.applicationBooks = getApplicationBooks();
	}

	public BookManager(Book[] books, FileWorker fileWorker) {
		this.fileWorker = fileWorker;
		nowDate = Date.getNowDate();
		this.books = fileWorker.readBooks();
		for (Book book : books) {
			this.books = ArrayWorker.addBook(book, this.books);
		}
		this.oldBooks = getOldBooks();
		this.stockBooks = getStockBooks();
		this.applicationBooks = getApplicationBooks();
		fileWorker.writeBooks(this.books);
	}

	public Book[] getBooks() {
		return books;
	}

	public void setBooks(Book[] books) {
		this.books = books;
	}

	public Book getBook(Book book) {
		for (int i = 0; i < this.books.length; i++) {
			if (books[i].equals(book)) {
				return books[i];
			}
		}
		return null;
	}

	@Override
	public void add(BaseEntity book) {
		this.books = ArrayWorker.addBook((Book) book, this.books);
		this.oldBooks = getOldBooks();
		this.stockBooks = getStockBooks();
		this.applicationBooks = getApplicationBooks();
		fileWorker.writeBooks(this.books);

	}

	@Override
	public void delete(BaseEntity entity) {
		this.books = ArrayWorker.deleteBook((Book) entity, this.books);
		this.oldBooks = getOldBooks();
		this.stockBooks = getStockBooks();
		this.applicationBooks = getApplicationBooks();
		fileWorker.writeBooks(this.books);

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
		if (!ArrayWorker.contains(book, this.books)) {
			this.add((BaseEntity) book);
		}
		for (int i = 0; i < books.length; i++) {
			if (this.books[i].equals(book)) {
				this.books[i].setInStock(true);
				this.books[i].setApplication(false);
			}
			fileWorker.writeBooks(this.books);
		}

	}

	@Override
	public void deleteFromStock(IBook book) {
		if (!ArrayWorker.contains(book, this.books)) {
			this.add((BaseEntity) book);
		}
		for (int i = 0; i < books.length; i++) {
			if (this.books[i].equals(book)) {
				this.books[i].setInStock(false);
			}
		}
		fileWorker.writeBooks(this.books);
	}

	@Override
	public void submitApplication(Book book) {
		if (!ArrayWorker.contains(book, this.books)) {
			this.add((BaseEntity) book);
		}
		for (int i = 0; i < books.length; i++) {
			if (this.books[i].equals(book) && !this.books[i].isInStock()) {
				this.books[i].setApplication(true);
			}
		}
		fileWorker.writeBooks(this.books);
	}

	@Override
	public boolean isInStock(Book book) {
		return ArrayWorker.contains(book, this.stockBooks);
	}

	@Override
	public void sortAlphabet() {
		Arrays.sort(this.books, Book.AlphabetComparator);
		ArrayWorker.showArray(this.books);
		fileWorker.writeBooks(this.books);
	}

	@Override
	public void sortPrice() {
		Arrays.sort(this.books, Book.PriceComparator);
		ArrayWorker.showArray(this.books);
		fileWorker.writeBooks(this.books);
	}

	@Override
	public void sortDate() {
		Arrays.sort(this.books, Book.DateComparator);
		ArrayWorker.showArray(this.books);
		fileWorker.writeBooks(this.books);
	}

	@Override
	public void sortStock() {
		this.books = fileWorker.readBooks();
		Arrays.sort(this.books, Book.StockComparator);
		ArrayWorker.showArray(this.books);
		fileWorker.writeBooks(this.books);
	}

	@Override
	public void sortDateOld() {
		Arrays.sort(this.oldBooks, Book.DateComparator);
		ArrayWorker.showArray(this.oldBooks);
	}

	@Override
	public void sortPriceOld() {
		Arrays.sort(this.oldBooks, Book.PriceComparator);
		ArrayWorker.showArray(this.oldBooks);
	}
}
