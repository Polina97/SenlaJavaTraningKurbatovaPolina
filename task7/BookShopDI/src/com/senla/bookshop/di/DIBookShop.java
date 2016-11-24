package com.senla.bookshop.di;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class DIBookShop {
	private DIBookShop bookShop;
	private static Logger log = Logger.getLogger(DIBookShop.class);
	private static Map<String, Object> mapObjects = new HashMap<String, Object>();
	private static DILoader loader = new DILoader();

	public static Object load(String name, Boolean isNew) {
		if (mapObjects.containsKey(name) && !isNew) {
			return mapObjects.get(name);
		} else {
			try {
				Class<?> clazz = Class.forName(loader.load(name));
				Object serviceObject = clazz.newInstance();
				if (!isNew) {
					mapObjects.put(name, serviceObject);
				}
				return serviceObject;
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
				log.error(e);
				return null;
			}
		}

	}

	public DIBookShop newInstance() {
		if (bookShop == null) {
			bookShop = new DIBookShop();
		}
		return bookShop;
	}
}
