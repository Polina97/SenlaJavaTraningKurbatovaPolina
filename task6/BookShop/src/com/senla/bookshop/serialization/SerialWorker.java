package com.senla.bookshop.serialization;

import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookconfiguration.conf.PropertyWorker;
import com.senla.bookshop.api.manager.IBaseManager;
import com.senla.bookshop.api.manager.IBookManager;
import com.senla.bookshop.api.manager.IBuyerManager;
import com.senla.bookshop.api.manager.IOrderManager;
import com.senla.bookshop.manager.BookManager;
import com.senla.bookshop.manager.BuyerManager;
import com.senla.bookshop.manager.OrderManager;

public class SerialWorker {
	public final String PATH_FILE;
	private final String FILE_NOT_FOUND_ERROR = "File not found";
	private Logger log = Logger.getLogger(SerialWorker.class.getName());
	private Serializator serializator;
	private List<Object> managers;

	public SerialWorker() {
		PATH_FILE = PropertyWorker.getFilePath();
		try {
			serializator = new Serializator(PATH_FILE);
			managers = serializator.readFromFile();
		} catch (RuntimeException e) {
			log.error(e);
		}
	}

	public IBookManager getBookManager() {
		try {
			return (IBookManager) managers.get(0);
		} catch (RuntimeException e) {
			return new BookManager();
		}
	}

	public IOrderManager getOrderManager() {
		try {
			return (IOrderManager) managers.get(1);
		} catch (RuntimeException e) {
			return new OrderManager();
		}
	}

	public IBuyerManager getBuyerManager() {
		try {
			return (IBuyerManager) managers.get(2);
		} catch (RuntimeException e) {
			return new BuyerManager();
		}
	}

	public void writeManagers(List<IBaseManager> managers) {
		try {
			serializator.writeToFile(managers);
		} catch (RuntimeException e) {
			throw new RuntimeException(FILE_NOT_FOUND_ERROR);
		}
	}


}
