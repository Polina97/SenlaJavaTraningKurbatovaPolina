package com.senla.bookshop.main;

import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.managers.IBookManager;
import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshop.config.Config;
import com.senla.bookshop.manager.BookManager;
import com.senla.bookshop.resources.FileWorker;

public class Shop {
	private static String LOG_PROPERTIES = "src/log4j.properties";
	private static Logger log = Logger.getLogger(Shop.class.getName());
	private static IBookManager bookManager;
	public static FileWorker fileWorker;

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

	public String[] getBooks() {
		return listToString(bookManager.getBooks());
	}

	public String[] sortBooks(TypeBookComparator comparator) {
		try {
			return listToString(bookManager.sortBooks(comparator));
		} catch (Exception e) {
			return new String[] { Messages.NOT_FOUND };
		}
	}

	public String[] getOldBooks() {
		String[] oldBooks = listToString(bookManager.getOldBooks());
		if (oldBooks != null) {
			return listToString(bookManager.getOldBooks());
		} else {
			return new String[] { Messages.NOT_FOUND };
		}
	}

	private String[] listToString(List<?> entities) {
		String[] array = new String[entities.size()];
		for (int i = 0; i < entities.size(); i++) {
			array[i] = entities.get(i).toString();
		}
		return array;
	}
}
