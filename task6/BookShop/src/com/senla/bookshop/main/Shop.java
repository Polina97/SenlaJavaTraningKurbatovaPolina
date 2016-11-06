package com.senla.bookshop.main;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entities.*;
import com.senla.bookshop.api.manager.*;
import com.senla.bookshop.api.shop.IShop;
import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshop.comparators.TypeOrderComparator;
import com.senla.bookshop.entity.*;
import com.senla.bookshop.resources.*;
import com.senla.bookshop.serialization.SerialWorker;

public class Shop implements IShop {
	private static Shop shop;
	private static Logger log = Logger.getLogger(Shop.class.getName());
	private static List<String> ERROR_LIST = new ArrayList<String>();
	private static final GregorianCalendar TODAY = new GregorianCalendar();
	private static final String SPACE = " ";
	private static List<IBaseManager> managers = new ArrayList<IBaseManager>();
	private static IBookManager bookManager;
	private static IOrderManager orderManager;
	private static IBuyerManager buyerManager;
	public static SerialWorker serialWorker;
	static {
		shop = new Shop();
		ERROR_LIST.add(Messages.ERROR);
		serialWorker  =new SerialWorker();
		bookManager = serialWorker.getBookManager();
		orderManager = serialWorker.getOrderManager();
		buyerManager = serialWorker.getBuyerManager();
		
		managers.add(bookManager);
		managers.add(orderManager);
		managers.add(buyerManager);
		
	}

	private Shop() {
	}

	public static Shop getShop() {
		return shop;
	}

	public static void main(String[] args) throws Exception {
		try {
			IBook book = new Book(101, "Anna Karenina", "Leo Tolstoy", new GregorianCalendar(2015, 11, 13), TODAY, 300000);
			bookManager.add((BaseEntity) book);
			IBuyer buyer = new Buyer(10,"Matt");
			List<IBook> books = new ArrayList<IBook>();
			books.add(book);
			IOrder order = new Order(1001, buyer, books, TODAY, StatusOrder.KIT);
			orderManager.add((BaseEntity) order);
			buyerManager.add((BaseEntity) buyer);
			serialWorker.writeManagers(managers);
			System.out.println(shop.getBooks());
		} catch ( Exception e) {
			log.error(Messages.NO_PARAMETERS + e);
		}
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
		}finally{
			serialWorker.writeManagers(managers);
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
		}finally{
			serialWorker.writeManagers(managers);
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
			serialWorker.writeManagers(managers);
			return Messages.BOOK_DELETED;
		} catch (Exception e) {
			return Messages.BOOK_NOT_DELETED;
		}
	}

	@Override
	public String submitApp(Integer index) {
		try {
			bookManager.submitApplication(index);
			serialWorker.writeManagers(managers);
			return Messages.APP_ADD;
		} catch (Exception e) {
			return Messages.APP_NOT_ADD;
		}
	}

	@Override
	public String addOrder(String nameBuyer, List<Integer> ids, StatusOrder status) {
		try {
			IOrder order;
			IBuyer buyer = new Buyer(IdGenerator.getId(buyerManager.getBuyers()) + 1, nameBuyer);
			buyerManager.add((BaseEntity) buyer);
			List<IBook> books = new ArrayList<IBook>();
			List<IBook> listBooks = bookManager.getBooks();
			for(int i=0; i<ids.size();i++){
				books.add(listBooks.get(ids.get(i)-1));
			}
			order = new Order(IdGenerator.getId(orderManager.getOrders()) + 1, buyer, books, TODAY, status);
			orderManager.add((BaseEntity) order);
			serialWorker.writeManagers(managers);
			return Messages.ORDER_ADD;
		} catch (Exception e) {
			return Messages.ORDER_NOT_ADD;
		}
	}

	@Override
	public String deliverOrder(Integer index) {
		try {
			orderManager.changeStatus(index, StatusOrder.DELIVERED);
			serialWorker.writeManagers(managers);
			return Messages.ORDER_DELIVERED;
		} catch (Exception e) {
			return Messages.ORDER_NOT_DELIVERED;
		}

	}

	@Override
	public String cancelOrder(Integer index) {
		try {
			orderManager.changeStatus(index, StatusOrder.CANCELED);
			serialWorker.writeManagers(managers);
			return Messages.ORDER_CANCELED;
		} catch (Exception e) {
			return Messages.ORDER_NOT_CANCELED;
		}
	}

	@Override
	public void exit() {
		serialWorker.writeManagers(managers);
		
	}

}
