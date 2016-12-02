package com.senla.bookshop.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entity.IBaseEntity;
import com.senla.bookshop.api.entity.IBook;
import com.senla.bookshop.api.manager.IBookManager;
import com.senla.bookshop.api.storage.IBookShopStorage;
import com.senla.bookshop.comparators.BookComparator;
import com.senla.bookshop.config.Config;
import com.senla.bookshop.serialization.StorageLoader;
import com.senla.bookshop.typecomparator.TypeBookComparator;

public class BookManager extends BaseManager implements IBookManager, Serializable {
	private static final long serialVersionUID = 1L;
	private final GregorianCalendar TODAY = new GregorianCalendar();
	private static Logger log = Logger.getLogger(BookManager.class);
	private IBookShopStorage storage;

	public BookManager() {
		this.storage =  StorageLoader.getStorage();
	}


	@Override
	public synchronized List<IBook> getBooks() {
		return storage.getBooks();
	}

	@Override
	public Boolean add(IBaseEntity book) {
		return storage.addBook((IBook) book);
	}

	@Override
	public Boolean delete(IBaseEntity entity) {
		return storage.deleteBook((IBook) entity);
	}

	@Override
	public IBook getById(Integer id) {
		try {
			for (IBook book : storage.getBooks()) {
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
			for (IBook b : storage.getBooks()) {
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
			for (IBook b : storage.getBooks()) {
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
			for (IBook book : storage.getBooks()) {
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
			if (Config.getInstance().SWITCH_OFF_APPLICATION) {
				getById(id).setApplication(false);
			}
		} catch (NullPointerException e) {
			throw new Exception(e);
		}
	}

	@Override
	public void deleteFromStock(Integer index) throws Exception {
		try {
			getById(storage.getBooks().get(index).getId()).setInStock(false);
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
			storage.getBooks().sort(new BookComparator(comparator));
			return storage.getBooks();
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
