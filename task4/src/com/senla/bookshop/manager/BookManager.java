package com.senla.bookshop.manager;

import java.util.Arrays;

import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.managers.IBookManager;
import com.senla.bookshop.comparators.BookComparator;
import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.resources.ArrayWorker;
import com.senla.bookshop.resources.Date;
import com.senla.bookshop.resources.FileWorker;

public class BookManager implements IBookManager {
	private IBook[] books;
	private IBook[] oldBooks;
	private IBook[] stockBooks;
	private IBook[] applicationBooks;
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
			this.books = ArrayWorker.addBook(book, (Book[]) this.books);
		}
		this.oldBooks = getOldBooks();
		this.stockBooks = getStockBooks();
		this.applicationBooks = getApplicationBooks();
		fileWorker.writeBooks(this.books);
	}

	public IBook[] getBooks() {
		return books;
	}

	public void setBooks(Book[] books) {
		this.books = books;
	}

	public IBook getBook(Book book) {
		for (int i = 0; i < this.books.length; i++) {
			if (books[i].equals(book)) {
				return books[i];
			}
		}
		return null;
	}

	@Override
	public void add(BaseEntity book) {
		this.books = ArrayWorker.addBook((Book) book,this.books);
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
	public IBook[] getOldBooks() {
		for (IBook b : this.books) {
			if (b.isOld(nowDate)) {
				this.oldBooks = ArrayWorker.addBook(b, this.oldBooks);
			}
		}
		return oldBooks;
	}

	@Override
	public void setOldBooks(IBook[] oldBooks) {
		this.oldBooks = oldBooks;
	}

	@Override
	public IBook[] getStockBooks() {
		for (IBook b : this.books) {
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
	public IBook[] getApplicationBooks() {
		for (IBook book : this.books) {
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
	public boolean isInStock(IBook book) {
		return ArrayWorker.contains(book, this.stockBooks);
	}

	@Override
	public void sortBooks(TypeBookComparator comparator) {
		Arrays.sort((Book[])this.books,new BookComparator(comparator) );
		ArrayWorker.showArray(this.books);
		fileWorker.writeBooks(this.books);
	}

}
