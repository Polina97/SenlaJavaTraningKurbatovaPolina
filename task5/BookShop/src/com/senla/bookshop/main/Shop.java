package com.senla.bookshop.main;

import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entities.IBaseEntity;
import com.senla.bookshop.api.managers.IBookManager;
import com.senla.bookshop.api.managers.IOrderManager;
import com.senla.bookshop.api.shop.IShop;
import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshop.comparators.TypeOrderComparator;
import com.senla.bookshop.config.Config;
import com.senla.bookshop.manager.BookManager;
import com.senla.bookshop.manager.OrderManager;
import com.senla.bookshop.resources.FileWorker;

public class Shop implements IShop {
	private static String LOG_PROPERTIES = "src/log4j.properties";
	private static Logger log = Logger.getLogger(Shop.class.getName());
	private static final String[] ERROR_ARRAY = new String[] { Messages.NOT_FOUND };
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
			return listToString(bookManager.getBooks());
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
		return orderManager.getDeliveredOrders().size();
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





}
