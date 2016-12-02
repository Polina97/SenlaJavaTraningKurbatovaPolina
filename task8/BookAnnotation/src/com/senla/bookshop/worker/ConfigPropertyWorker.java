package com.senla.bookshop.worker;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.senla.bookshop.annotation.ConfigProperty;
import com.senla.bookshop.api.conf.IPropertyWorker;
import com.senla.bookshop.di.DIBookShop;

public class ConfigPropertyWorker {
	private static Logger log = Logger.getLogger(ConfigPropertyWorker.class.getName());
	private static Object object;
	private static IPropertyWorker worker = (IPropertyWorker) DIBookShop.load(IPropertyWorker.class.getName(), false);

	private ConfigPropertyWorker() {
	}

	public static void configurate(Object o) throws Exception {
		if (o != null) {
			object = o;
			initializeFields();
		} else {
			throw new NullPointerException("Null object");
		}
	}

	private static void initializeFields() {
		try {
			for (Field f : object.getClass().getFields()) {
				if (f.isAnnotationPresent(ConfigProperty.class)) {
					ConfigProperty ann = f.getAnnotation(ConfigProperty.class);
					f.set(object, worker.getProperty(ann.propertyName(), ann.type()));
				}

			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error(e);
		}
	}

}
