package com.senla.bookshop.main;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entities.IBaseEntity;
import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.api.managers.IBookManager;
import com.senla.bookshop.api.managers.IBuyerManager;
import com.senla.bookshop.api.managers.IOrderManager;
import com.senla.bookshop.api.shop.IShop;
import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshop.comparators.TypeOrderComparator;
import com.senla.bookshop.config.Config;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.entity.Order;
import com.senla.bookshop.entity.StatusOrder;
import com.senla.bookshop.manager.BookManager;
import com.senla.bookshop.manager.BuyerManager;
import com.senla.bookshop.manager.OrderManager;
import com.senla.bookshop.resources.FileWorker;

public class Shop implements IShop {
	private static String LOG_PROPERTIES = "src/log4j.properties";
	private static Logger log = Logger.getLogger(Shop.class.getName());
	private static final String[] ERROR_ARRAY = new String[] { Messages.NOT_FOUND };
	private static final GregorianCalendar TODAY = new GregorianCalendar();
	private static final String SPACE = " ";
	private static IBookManager bookManager;
	private static IOrderManager orderManager;
	public static FileWorker fileWorker;
	static {
		Config conf = new Config(LOG_PROPERTIES);
		conf.init();
		fileWorker = new FileWorker(null, null, null);
		bookManager = new BookManager();
		orderManager = new OrderManager();
	}

	public static void main(String[] args) {
		Config conf = new Config(LOG_PROPERTIES);
		conf.init();
		try {
			fileWorker = new FileWorker(args[0], args[1], args[2]);
		} catch (ArrayIndexOutOfBoundsException e) {
			log.error(Messages.NO_PARAMETERS + e);
			fileWorker = new FileWorker(null, null, null);
		}
		bookManager = new BookManager();
	}

	@Override
	public String[] getBooks() {
		try {
			String[] books = new String[bookManager.getBooks().size()];
			int i = 0;
			for (IBook book : bookManager.getBooks()) {
				books[i++] = book.getName() + SPACE + book.getAuthor();
			}
			return books;
		} catch (Exception e) {
			return ERROR_ARRAY;
		}
	}

	@Override
	public String[] sortBooks(TypeBookComparator comparator) {
		try {
			return listToString(bookManager.sortBooks(comparator));
		} catch (Exception e) {
			return ERROR_ARRAY;
		}
	}

	@Override
	public String[] getOldBooks() {
		String[] oldBooks = listToString(bookManager.getOldBooks());
		if (oldBooks != null) {
			return listToString(bookManager.getOldBooks());
		} else {
			return ERROR_ARRAY;
		}
	}

	@Override
	public String[] sortOldBooks(TypeBookComparator comparator) {

		try {
			String[] oldBooks = listToString(bookManager.sortOldBooks(comparator));
			if (oldBooks != null)
				return oldBooks;
			else {
				return new String[] { Messages.NOT_FOUND };
			}
		} catch (Exception e) {
			return ERROR_ARRAY;
		}
	}

	@Override
	public String[] getOrders() {
		String[] orders = listToString(orderManager.getOrders());
		if (orders != null) {
			return orders;
		} else {
			return ERROR_ARRAY;
		}
	}

	@Override
	public String[] sortOrders(TypeOrderComparator comparator) {
		String[] orders = listToString(orderManager.sortOrders(comparator));
		if (orders != null) {
			return orders;
		} else {
			return ERROR_ARRAY;
		}
	}

	@Override
	public String[] getDeliveredOrders() {
		String[] orders = listToString(orderManager.getDeliveredOrders());
		if (orders != null) {
			return orders;
		} else {
			return ERROR_ARRAY;
		}
	}

	@Override
	public String[] sortDeliveredOrders(TypeOrderComparator comparator) {
		String[] orders = listToString(orderManager.sortDeliveredOrders(comparator));
		if (orders != null) {
			return orders;
		} else {
			return ERROR_ARRAY;
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
			IBook book = new Book(bookManager.getOldId() + 1, name, author, datePublication, TODAY, price);
			bookManager.add((BaseEntity) book);
			bookManager.addToStock(book.getId());
			return Messages.BOOK_ADD;
		} catch (Exception e) {
			log.error(e);
			return Messages.BOOK_NOT_ADD;
		}
	}

	private static String[] listToString(List<?> entities) {
		if (entities != null) {
			String[] array = new String[entities.size()];
			for (int i = 0; i < entities.size(); i++) {
				array[i] = ((IBaseEntity) entities.get(i)).getDescription();
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
		try{
		IOrder order;
		IBuyerManager buyerManager = new BuyerManager();
		IBuyer buyer = new Buyer(buyerManager.getOldId()+1, nameBuyer);
		List<IBook> books = new ArrayList<>();
		for(Integer id: ids){
			books.add(bookManager.getById(id));
		}
		order= new Order(orderManager.getOldId()+1, buyer, books, TODAY, status);
		orderManager.add((BaseEntity) order);
		return Messages.ORDER_ADD;
		}catch(Exception e){
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
