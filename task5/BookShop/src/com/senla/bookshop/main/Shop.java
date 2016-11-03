package com.senla.bookshop.main;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entities.*;
import com.senla.bookshop.api.managers.*;
import com.senla.bookshop.api.shop.IShop;
import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshop.comparators.TypeOrderComparator;
import com.senla.bookshop.entity.*;
import com.senla.bookshop.manager.*;
import com.senla.bookshop.resources.*;

public class Shop implements IShop {
	private static Shop shop;
	private static Logger log = Logger.getLogger(Shop.class.getName());
	private static List<String> ERROR_LIST = new ArrayList<String>();
	private static final GregorianCalendar TODAY = new GregorianCalendar();
	private static final String SPACE = " ";
	private static IBookManager bookManager;
	private static IOrderManager orderManager;
	public static FileWorker fileWorker;
	static {
		shop = new Shop();
		ERROR_LIST.add(Messages.ERROR);
		fileWorker = new FileWorker(null, null, null);
		bookManager = new BookManager();
		orderManager = new OrderManager();
	}

	private Shop() {
	}

	public static Shop getShop() {
		return shop;
	}

	public static void main(String[] args) {
		try {
			fileWorker = new FileWorker("v", args[1], args[2]);
		} catch (ArrayIndexOutOfBoundsException e) {
			log.error(Messages.NO_PARAMETERS + e);
			fileWorker = new FileWorker(null, null, null);
		}
		bookManager = new BookManager();
	}

	@Override
	public List<String> getBooks() {
		try {
			List<String> books = new ArrayList<String>();
			for (IBook book : bookManager.getBooks()) {
				books.add(book.getName() + SPACE + book.getAuthor());
			}
			return books;
		} catch (Exception e) {
			return ERROR_LIST;
		}
	}

	@Override
	public List<String> sortBooks(TypeBookComparator comparator) {
		try {
			return listToString(bookManager.sortBooks(comparator));
		} catch (Exception e) {
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
			IBook book = new Book(IdGenerator.getId(bookManager.getBooks()) + 1, name, author, datePublication, TODAY,
					price);
			bookManager.add((BaseEntity) book);
			bookManager.addToStock(book.getId());
			return Messages.BOOK_ADD;
		} catch (Exception e) {
			log.error(e);
			return Messages.BOOK_NOT_ADD;
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

	@Override
	public String deleteFromStock(Integer index) {
		try {
			bookManager.deleteFromStock(index);
			return Messages.BOOK_DELETED;
		} catch (Exception e) {
			return Messages.BOOK_NOT_DELETED;
		}
	}

	@Override
	public String submitApp(Integer index) {
		try {
			bookManager.submitApplication(index);
			return Messages.APP_ADD;
		} catch (Exception e) {
			return Messages.APP_NOT_ADD;
		}
	}

	@Override
	public String addOrder(String nameBuyer, List<Integer> ids, StatusOrder status) {
		try {
			IOrder order;
			IBuyerManager buyerManager = new BuyerManager();
			IBuyer buyer = new Buyer(IdGenerator.getId(buyerManager.getBuyers()) + 1, nameBuyer);
			List<IBook> books = new ArrayList<IBook>();
			for (Integer id : ids) {
				books.add(bookManager.getById(id));
			}
			order = new Order(IdGenerator.getId(orderManager.getOrders()) + 1, buyer, books, TODAY, status);
			orderManager.add((BaseEntity) order);
			return Messages.ORDER_ADD;
		} catch (Exception e) {
			return Messages.ORDER_NOT_ADD;
		}
	}

	@Override
	public String deliverOrder(Integer index) {
		try {
			orderManager.changeStatus(index, StatusOrder.DELIVERED);
			return Messages.ORDER_DELIVERED;
		} catch (Exception e) {
			return Messages.ORDER_NOT_DELIVERED;
		}

	}

	@Override
	public String cancelOrder(Integer index) {
		try {
			orderManager.changeStatus(index, StatusOrder.CANCELED);
			return Messages.ORDER_CANCELED;
		} catch (Exception e) {
			return Messages.ORDER_NOT_CANCELED;
		}
	}

}
