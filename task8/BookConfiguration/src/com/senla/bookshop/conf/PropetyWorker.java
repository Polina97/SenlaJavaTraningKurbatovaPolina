package com.senla.bookshop.conf;

import com.senla.bookshop.api.conf.IPropertyWorker;

public class PropetyWorker implements IPropertyWorker {
	public static PropertyLoader loader = new PropertyLoader();

	@Override
	public Object getProperty(String name, String type) {
		String property = loader.getProperty(name);
		switch (type) {
		case "Boolean":
			return new Boolean(property);
		case "Integer":
			return new Integer(property);
		case "String":
			return property;
		default:
			return null;
		}
	}

}
