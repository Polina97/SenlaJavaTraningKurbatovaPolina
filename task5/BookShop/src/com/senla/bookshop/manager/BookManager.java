package com.senla.bookshop.manager;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.managers.IBookManager;
import com.senla.bookshop.comparators.BookComparator;
import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.main.Shop;

public class BookManager implements IBookManager {
	private List<IBook> books;
	private final GregorianCalendar TODAY = new GregorianCalendar();
	private static Logger log = Logger.getLogger(BookManager.class.getName());

	public BookManager() {
		books = Shop.fileWorker.readBooks();
	}

	public BookManager(List<IBook> books) {
		this();
		books.addAll(books);
	}

	@Override
	public List<IBook> getBooks() {
		return books;
	}

	@Override
	public Boolean add(BaseEntity book) throws Exception {
		try {
			Boolean answ = books.add((IBook) book);
			Shop.fileWorker.writeBooks(this.books);
			return answ;
		} catch (ClassCastException | NullPointerException | IllegalArgumentException e) {
			log.error(e);
			throw new Exception(ADD_EXCEPTION);
		}
	}

	@Override
	public Boolean delete(BaseEntity entity) throws Exception {
		try {
			Boolean answ = books.remove(entity);
			Shop.fileWorker.writeBooks(books);
			return answ;
		} catch (ClassCastException | NullPointerException e) {
			log.error(e);
			throw new Exception(DELETE_EXCEPTION);
		}
	}

	@Override
	public IBook getById(Integer id) {
		return books.get(id);
	}

	@Override
	public List<IBook> getOldBooks() {
		List<IBook> oldBooks = new ArrayList<IBook>();
		for (IBook b : books) {
			if (b.isOld(TODAY)) {
				oldBooks.add(b);
			}
		}
		return oldBooks;
	}

	@Override
	public List<IBook> getStockBooks() {
		List<IBook> stockBooks = new ArrayList<IBook>();
		try {
			for (IBook b : books) {
				if (b.isInStock()) {
					stockBooks.add(b);
				}
			}
		} catch (NullPointerException e) {
			log.error(e);
		}
		return stockBooks;
	}

	@Override
	public List<IBook> getApplicationBooks() {
		List<IBook> applicationBooks = new ArrayList<IBook>();
		try {
			for (IBook book : books) {
				if (book.isApplication()) {
					applicationBooks.add(book);
				}
			}
		} catch (UnsupportedOperationException | NullPointerException e) {
			log.error(e);
		}
		return applicationBooks;
	}

	@Override
	public void addToStock(Integer id) throws Exception {
		try {
			getById(id).setInStock(true);
			getById(id).setApplication(false);
			Shop.fileWorker.writeBooks(books);
		} catch (NullPointerException e) {
			log.error(e);
			throw new Exception(e);
		}
	}

	@Override
	public void deleteFromStock(Integer id) throws Exception {
		try {
			getById(id).setInStock(false);
			Shop.fileWorker.writeBooks(books);
		} catch (NullPointerException e) {
			log.error(e);
			throw new Exception(e);
		}

	}

	@Override
	public void submitApplication(Integer id) throws Exception {
		try {
			getById(id).setApplication(true);
			Shop.fileWorker.writeBooks(books);
		} catch (NullPointerException e) {
			log.error(e);
			throw new Exception(e);
		}
	}

	@Override
	public List<IBook> sortBooks(TypeBookComparator comparator) throws Exception {
		try {
			books.sort(new BookComparator(comparator));
			return books;
		} catch (NullPointerException e) {
			log.error(e);
			throw new Exception(e);
		}
	}

	@Override
	public List<IBook> sortOldBooks(TypeBookComparator comparator) throws Exception {
		try {
			List<IBook> oldBooks = getOldBooks();
			oldBooks.sort(new BookComparator(comparator));
			return oldBooks;
		} catch (NullPointerException e) {
			log.error(e);
			throw new Exception(e);
		}
	}



}
