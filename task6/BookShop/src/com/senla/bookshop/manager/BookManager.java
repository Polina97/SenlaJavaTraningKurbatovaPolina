package com.senla.bookshop.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookconfiguration.conf.PropertyWorker;
import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.manager.IBookManager;
import com.senla.bookshop.comparators.BookComparator;
import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshop.entity.BaseEntity;

public class BookManager implements IBookManager, Serializable {
	private static final long serialVersionUID = 1L;
	private List<IBook> books;
	private final GregorianCalendar TODAY = new GregorianCalendar();
	private static Logger log = Logger.getLogger(BookManager.class.getName());

	public BookManager() {
		books = new ArrayList<IBook>();
	}

	public BookManager(List<IBook> books) {
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
			return answ;
		} catch (ClassCastException | NullPointerException | IllegalArgumentException e) {
			throw new Exception(e);
		}
	}

	@Override
	public Boolean delete(BaseEntity entity) throws Exception {
		try {
			Boolean answ = books.remove(entity);
			return answ;
		} catch (ClassCastException | NullPointerException e) {
			throw new Exception(e);
		}
	}

	@Override
	public IBook getById(Integer id) {
		try {
			for (IBook book : books) {
				if (book.getId().equals(id)) {
					return book;
				}
			}
		} catch (NullPointerException e) {
			log.error(e);
		}
		return null;
	}

	@Override
	public List<IBook> getOldBooks() {
		try {
			List<IBook> oldBooks = new ArrayList<IBook>();
			for (IBook b : books) {
				if (b.getDateOld().before(TODAY)) {
					oldBooks.add(b);
				}
			}
			return oldBooks;
		} catch (NullPointerException e) {
			return null;
		}
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
			if (PropertyWorker.getIsApplication()) {
				getById(id).setApplication(false);
			}
		} catch (NullPointerException e) {
			throw new Exception(e);
		}
	}

	@Override
	public void deleteFromStock(Integer id) throws Exception {
		try {
			getById(id).setInStock(false);
		} catch (NullPointerException e) {
			throw new Exception(e);
		}

	}

	@Override
	public void submitApplication(Integer id) throws Exception {
		try {
			getById(id).setApplication(true);
		} catch (NullPointerException e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<IBook> sortBooks(TypeBookComparator comparator) throws Exception {
		try {
			books.sort(new BookComparator(comparator));
			return books;
		} catch (NullPointerException e) {
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
			throw new Exception(e);
		}
	}

	public List<IBook> exportBooks() {
		try {
			List<IBook> csvBooks = csvWorker.readBooks();
			return csvBooks;

		} catch (NullPointerException | ClassCastException e) {
			log.error(e);
		}
		return null;
	}

	public IBook importBook(Integer id) throws Exception {
		try {
			List<IBook> csvBooks = csvWorker.readBooks();
			if (csvBooks != null) {
				for (IBook book : csvBooks) {
					if (book.getId().equals(id)) {
						csvBooks.remove(book);
						break;
					}
				}
			} else {
				csvBooks = new ArrayList<IBook>();
			}
			csvBooks.add(getById(id));
			if (csvWorker.writeBooks(csvBooks) != null) {
				return getById(id);
			} else {
				return null;
			}
		} catch (NullPointerException e) {
			throw new Exception(e);

		}
	}

}
