package com.senla.bookshop.di;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entity.IBook;
import com.senla.bookshop.api.entity.IBuyer;
import com.senla.bookshop.api.entity.IOrder;
import com.senla.bookshop.api.manager.IBookManager;
import com.senla.bookshop.api.manager.IBuyerManager;
import com.senla.bookshop.api.manager.IOrderManager;

public class DIBookShop {

	private DIBookShop bookShop;
	private static Logger log = Logger.getLogger(DIBookShop.class);
	private static Map<String, String> entities = new HashMap<String, String>();
	static{
		entities.put(IBook.class.getName(),"com.senla.bookshop.entity.Book");
		entities.put(IBuyer.class.getName(), "com.senla.bookshop.entity.Buyer");
		entities.put(IOrder.class.getName(), "com.senla.bookshop.entity.Order");
		entities.put(IBookManager.class.getName(), "com.senla.bookshop.manager.BookManager");
		entities.put(IOrderManager.class.getName(), "com.senla.bookshop.manager.OrderManager");
		entities.put(IBuyerManager.class.getName(), "com.senla.bookshop.manager.BuyerManager");
	}

	private DIBookShop() {
		
	}

	public static Object load(String type) {
		try {
			Class<?> clazz = Class.forName(entities.get(type));
			Object serviceObject = clazz.newInstance();
			return serviceObject;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			log.error(e);
			return null;
		}

	}
	

	public DIBookShop getInstance() {
		if (bookShop == null) {
			bookShop = new DIBookShop();
		}
		return bookShop;
	}
}
