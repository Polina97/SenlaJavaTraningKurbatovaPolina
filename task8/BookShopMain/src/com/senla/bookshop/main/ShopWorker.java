package com.senla.bookshop.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;

import com.senla.bookshop.api.shop.IShop;
import com.senla.bookshop.api.shop.IShopWorker;
import com.senla.bookshop.di.DIBookShop;

public class ShopWorker implements IShopWorker {
	private Logger log = Logger.getLogger(ShopWorker.class);
	private final String SLASH = "/";
	private static IShop shop = (IShop) DIBookShop.load(IShop.class.getName(), false);

	public ShopWorker() {
		super();
	}

	@Override
	public String workWithShop(String str) {
		String[] list = str.split(SLASH);
		try {
			if (list.length > 1) {
				Method method = shop.getClass().getMethod(list[0], String.class);
				return (String) method.invoke(shop, list[1]);
			} else {
				Method method = shop.getClass().getMethod(list[0]);
				return (String) method.invoke(shop);
			}

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			log.error(e);
			e.printStackTrace();
			return null;
		}
	}

}
