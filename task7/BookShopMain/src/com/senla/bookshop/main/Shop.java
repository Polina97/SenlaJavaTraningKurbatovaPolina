package com.senla.bookshop.main;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entity.*;
import com.senla.bookshop.api.manager.*;
import com.senla.bookshop.api.shop.IShop;
import com.senla.bookshop.api.storage.IBookShopStorage;
import com.senla.bookshop.config.Config;
import com.senla.bookshop.di.DIBookShop;
import com.senla.bookshop.entity.*;
import com.senla.bookshop.idgenerator.IdGenerator;
import com.senla.bookshop.idgenerator.TypeId;
import com.senla.bookshop.serialization.SerialWorker;
import com.senla.bookshop.serialization.StorageLoader;
import com.senla.bookshop.typecomparator.TypeBookComparator;
import com.senla.bookshop.typecomparator.TypeOrderComparator;
import com.senla.bookshop.worker.ConfigPropertyWorker;

public class Shop implements IShop {
	private static Logger log = Logger.getLogger(Shop.class);
	private static List<String> ERROR_LIST = new ArrayList<String>();
	private static final GregorianCalendar TODAY = new GregorianCalendar();
	private static final String SPACE = " ";
	private static SerialWorker serialWorker = new SerialWorker();
	private static IBookManager bookManager = (IBookManager) DIBookShop.load(IBookManager.class.getName(), false);
	private static IOrderManager orderManager = (IOrderManager) DIBookShop.load(IOrderManager.class.getName(), false);
	private static IBuyerManager buyerManager = (IBuyerManager) DIBookShop.load(IBuyerManager.class.getName(), false);
	private static IBookShopStorage storage;

	static {
		try {
			storage = StorageLoader.getStorage();
			ConfigPropertyWorker.configurate(Config.getInstance());
		} catch (Exception e) {
			log.error(e);
		}
		ERROR_LIST.add(Messages.ERROR);
	}


	@Override
	public List<String> getBooks() {
		try {
			StringBuilder builder = new StringBuilder();
			List<String> books = new ArrayList<String>();
			for (IBook book : bookManager.getBooks()) {
				builder.append(book.getName()).append(SPACE).append(book.getAuthor());
				books.add(builder.toString());
				builder.delete(0, builder.length());
			}
			return books;
		} catch (Exception e) {
			return ERROR_LIST;
		}
	}

	@Override
	public List<String> getBuyers() {
		try {
			List<String> buyers = new ArrayList<String>();
			for (IBuyer buyer : buyerManager.getBuyers()) {
				buyers.add(buyer.getName());
			}
			return buyers;
		} catch (Exception e) {
			return ERROR_LIST;
		}

	}

	@Override
	public List<String> sortBooks(TypeBookComparator comparator) {
		try {
			return listToString(bookManager.sortBooks(comparator));
		} catch (Exception e) {
			log.error(e);
			return ERROR_LIST;
		}
	}

	@Override
	public List<String> getOldBooks() {
		List<String> oldBooks = listToString(bookManager.getOldBooks());
		if (oldBooks != null) {
			return listToString(bookManager.getOldBooks());
		} else {
			return ERROR_LIST;
		}
	}

	@Override
	public List<String> sortOldBooks(TypeBookComparator comparator) {
		try {
			List<String> oldBooks = listToString(bookManager.sortOldBooks(comparator));
			if (oldBooks != null)
				return oldBooks;
			else {
				return ERROR_LIST;
			}
		} catch (Exception e) {
			log.error(e);
			return ERROR_LIST;
		}
	}

	@Override
	public List<String> getOrders() {
		List<String> orders = listToString(orderManager.getOrders());
		if (orders != null) {
			return orders;
		} else {
			return ERROR_LIST;
		}
	}

	@Override
	public List<String> sortOrders(TypeOrderComparator comparator) {
		List<String> orders = listToString(orderManager.sortOrders(comparator));
		if (orders != null) {
			return orders;
		} else {
			return ERROR_LIST;
		}
	}

	@Override
	public List<String> getDeliveredOrders() {
		List<String> orders = listToString(orderManager.getDeliveredOrders());
		if (orders != null) {
			return orders;
		} else {
			return ERROR_LIST;
		}
	}

	@Override
	public List<String> sortDeliveredOrders(TypeOrderComparator comparator) {
		List<String> orders = listToString(orderManager.sortDeliveredOrders(comparator));
		if (orders != null) {
			return orders;
		} else {
			return ERROR_LIST;
		}
	}

	@Override
	public Integer getPrice() {
		return orderManager.getGeneralPrice();
	}

	@Override
	public Integer countOrders() {
		try {
			return orderManager.getDeliveredOrders().size();
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public String getDescriptionBook(Integer index) {
		try {
			String description = bookManager.getBooks().get(index).getDescription();
			return description;
		} catch (Exception e) {
			log.error(e);
			return Messages.NOT_FOUND;
		}
	}

	@Override
	public String getDescriptionOrder(Integer index) {
		try {
			String description = orderManager.getOrderById(index).getDescription();
			return description;
		} catch (Exception e) {
			log.error(e);
			return Messages.NOT_FOUND;
		}
	}

	@Override
	public String addToStock(String name, String author, GregorianCalendar datePublication, Integer price) {
		try {
			IBook book = (IBook) DIBookShop.load(IBook.class.getName(), true);
			book.setId(IdGenerator.getId(TypeId.BOOK));
			book.setName(name);
			book.setAuthor(author);
			book.setDatePublication(datePublication);
			book.setDateSupply(TODAY);
			book.setPrice(price);
			bookManager.add((BaseEntity) book);
			bookManager.addToStock(book.getId());
			return Messages.BOOK_ADD;
		} catch (Exception e) {
			log.error(e);
			return Messages.BOOK_NOT_ADD;
		}
	}

	@Override
	public String deleteFromStock(Integer index) {
		try {
			bookManager.deleteFromStock(index);
			return Messages.BOOK_DELETED;
		} catch (Exception e) {
			log.error(e);
			return Messages.BOOK_NOT_DELETED;
		}
	}

	@Override
	public String submitApp(Integer index) {
		try {
			bookManager.submitApplication(index);
			return Messages.APP_ADD;
		} catch (Exception e) {
			log.error(e);
			return Messages.APP_NOT_ADD;
		}
	}

	@Override
	public String addOrder(String nameBuyer, List<Integer> ids, StatusOrder status) {
		try {
			IOrder order = (IOrder) DIBookShop.load(IOrder.class.getName(), true);
			IBuyer buyer = (IBuyer) DIBookShop.load(IBuyer.class.getName(), true);
			buyer.setId(IdGenerator.getId(TypeId.BUYER));
			buyer.setName(nameBuyer);
			buyerManager.add((BaseEntity) buyer);
			List<IBook> books = new ArrayList<IBook>();
			List<IBook> listBooks = bookManager.getBooks();
			for (int i = 0; i < ids.size(); i++) {
				books.add(listBooks.get(ids.get(i) - 1));
			}
			int price = 0;
			try {
				for (IBook book : books) {
					price += book.getPrice();
				}
			} catch (NullPointerException e) {
				log.error(e);
			}
			order.setId(IdGenerator.getId(TypeId.ORDER));
			order.setBuyer(buyer);
			order.setBooks(books);
			order.setDate(TODAY);
			order.setStatus(status);
			order.setPrice(price);
			orderManager.add((BaseEntity) order);
			buyerManager.getById(buyer.getId()).setOrder(order);
			return Messages.ORDER_ADD;
		} catch (Exception e) {
			log.error(e);
			return Messages.ORDER_NOT_ADD;
		}
	}

	@Override
	public String deliverOrder(Integer index) {
		try {
			orderManager.changeStatus(index, StatusOrder.DELIVERED);
			return Messages.ORDER_DELIVERED;
		} catch (Exception e) {
			log.error(e);
			return Messages.ORDER_NOT_DELIVERED;
		}

	}

	@Override
	public String cancelOrder(Integer index) {
		try {
			orderManager.changeStatus(index, StatusOrder.CANCELED);
			return Messages.ORDER_CANCELED;
		} catch (Exception e) {
			log.error(e);
			return Messages.ORDER_NOT_CANCELED;
		}
	}

	@Override
	public void exit() {
		serialWorker.writeStorage(storage);

	}

	@Override
	public String copyOrder(Integer id) {
		try {
			int orderId = orderManager.getOrders().get(id).getId();
			orderManager.cloneOrder(orderId);
			return Messages.ORDER_COPIED;
		} catch (Exception e) {
			log.error(e);
			return Messages.ORDER_NOT_COPIED;
		}
	}

	@Override
	public List<String> exportOrders() {
		try {
			List<IOrder> list = orderManager.exportOrders();
			if (list != null) {
				return listToString(list);
			} else {
				return ERROR_LIST;
			}
		} catch (Exception e) {
			return ERROR_LIST;
		}
	}

	@Override
	public List<String> exportBooks() {
		try {
			List<IBook> list = bookManager.exportBooks();
			if (list != null) {
				return listToString(list);
			} else {
				return ERROR_LIST;
			}
		} catch (Exception e) {
			return ERROR_LIST;
		}
	}

	@Override
	public List<String> exportBuyers() {
		try {
			List<IBuyer> list = buyerManager.exportBuyers();
			if (list != null) {
				return listToString(list);
			} else {
				return ERROR_LIST;
			}
		} catch (Exception e) {
			return ERROR_LIST;
		}
	}

	@Override
	public String importOrder(Integer id) {
		try {
			int orderId = orderManager.getOrders().get(id).getId();
			if (orderManager.importOrder(orderId) != null) {
				return Messages.ORDER_IMPORTED;
			} else {
				return Messages.ORDER_NOT_IMPORTED;
			}
		} catch (Exception e) {
			log.error(e);
			return Messages.ORDER_NOT_IMPORTED;
		}
	}

	@Override
	public String importBook(Integer id) {
		try {
			int bookId = bookManager.getBooks().get(id).getId();
			if (bookManager.importBook(bookId) != null) {
				return Messages.BOOK_IMPORTED;
			} else {
				return Messages.BOOK_NOT_IMPORTED;
			}
		} catch (Exception e) {
			log.error(e);
			return Messages.BOOK_NOT_IMPORTED;
		}
	}

	@Override
	public String importBuyer(Integer id) {
		try {
			int buyerId = buyerManager.getBuyers().get(id).getId();
			if (buyerManager.importBuyer(buyerId) != null) {
				return Messages.BUYER_IMPORTED;
			} else {
				return Messages.BUYER_NOT_IMPORTED;
			}
		} catch (Exception e) {
			log.error(e);
			return Messages.BUYER_NOT_IMPORTED;
		}
	}

	private static List<String> listToString(List<?> entities) {
		if (entities != null) {
			List<String> array = new ArrayList<String>();
			for (Object entity : entities) {
				array.add(((IBaseEntity) entity).getDescription());
			}
			return array;
		} else {
			return null;
		}
	}

}
