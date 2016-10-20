package com.senla.bookshop.manager;

import java.util.Arrays;
import java.util.GregorianCalendar;

import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.managers.IBookManager;
import com.senla.bookshop.comparators.BookComparator;
import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.main.Runner;
import com.senla.bookshop.resources.ArrayWorker;
import com.senla.bookshop.resources.Printer;

public class BookManager implements IBookManager {
	private IBook[] books;
	private final GregorianCalendar TODAY = new GregorianCalendar();

	public BookManager() {
		this.books = Runner.fileWorker.readBooks();
	}

	public BookManager(Book[] books) {
		this();
		for (Book book : books) {
			add(book);
		}
	}

	public IBook[] getBooks() {
		return books;
	}

	public void setBooks(Book[] books) {
		this.books = books;
	}

	@Override
	public void add(BaseEntity book) {
		this.books = ArrayWorker.addBook((Book) book, this.books);
		Runner.fileWorker.writeBooks(this.books);

	}

	@Override
	public void delete(BaseEntity entity) {
		this.books = ArrayWorker.deleteBook((Book) entity, this.books);
		Runner.fileWorker.writeBooks(this.books);
	}

	@Override
	public void showAll() {
		ArrayWorker.showArray(this.books);

	}

	@Override
	public IBook getById(Integer id) {
		for (int i = 0; i < this.books.length; i++) {
			if (books[i].getId().equals(id)) {
				return books[i];
			}
		}
		return null;
	}

	@Override
	public IBook[] getOldBooks() {
		IBook[] oldBooks = null;
		for (IBook b : this.books) {
			if (b.isOld(TODAY)) {
				oldBooks = ArrayWorker.addBook(b, oldBooks);
			}
		}
		return oldBooks;
	}

	@Override
	public IBook[] getStockBooks() {
		IBook[] stockBooks = null;
		for (IBook b : this.books) {
			if (b.isInStock()) {
				stockBooks = ArrayWorker.addBook(b, stockBooks);
			}
		}
		return stockBooks;
	}

	@Override
	public IBook[] getApplicationBooks() {
		IBook[] applicationBooks = null;
		for (IBook book : this.books) {
			if (book.isApplication()) {
				applicationBooks = ArrayWorker.addBook(book, applicationBooks);
			}
		}
		return applicationBooks;
	}

	@Override
	public void addToStock(Integer id) {
		if (ArrayWorker.contains(getById(id), this.books)) {
			for (int i = 0; i < this.books.length; i++) {
				if (this.books[i] != null && this.books[i].equals(getById(id))) {
					this.books[i].setInStock(true);
					this.books[i].setApplication(false);
					Printer.print("Book " + books[i].getName() + " was added to stock!");
				}
			}

			Runner.fileWorker.writeBooks(this.books);
		}

	}

	@Override
	public void deleteFromStock(Integer id) {
		if (ArrayWorker.contains(getById(id), this.books)) {
			for (int i = 0; i < this.books.length; i++) {
				if (this.books[i] != null && this.books[i].equals(getById(id))) {
					this.books[i].setInStock(false);
					Printer.print("Book " + books[i].getName() + " was deleted from stock!");
				}
			}
			Runner.fileWorker.writeBooks(this.books);
		}
	}

	@Override
	public Boolean isInStock(IBook book) {
		return ArrayWorker.contains(book, getStockBooks());
	}

	@Override
	public void submitApplication(Integer id) {
		for (int i = 0; i < this.books.length; i++) {
			if (this.books[i] != null && this.books[i].getId().equals(id)) {
				this.books[i].setApplication(true);
				Printer.print("You left the book " + id + " application.");
			}
		}
		Runner.fileWorker.writeBooks(this.books);
	}

	@Override
	public void sortBooks(TypeBookComparator comparator) {
		Arrays.sort((Book[]) this.books, new BookComparator(comparator));
		ArrayWorker.showArray(this.books);
	}

	public void sortOldBooks(TypeBookComparator comparator) {
		IBook[] oldBooks = getOldBooks();
		Arrays.sort((Book[]) oldBooks, new BookComparator(comparator));
		ArrayWorker.showArray(oldBooks);
	}

}
