package com.senla.bookshop.serialization;


import com.senla.bookconfiguration.conf.PropertyWorker;
import com.senla.bookshop.api.manager.IBookManager;
import com.senla.bookshop.api.manager.IBuyerManager;
import com.senla.bookshop.api.manager.IOrderManager;
import com.senla.bookshop.manager.BookManager;
import com.senla.bookshop.manager.BuyerManager;
import com.senla.bookshop.manager.OrderManager;

public class SerialWorker {
	public final String PATH_BOOK;
	private final String PATH_ORDER;
	private final String PATH_BUYER;
	private final String FILE_NOT_FOUND_ERROR = "File not found";
	private Serializator serializator;

	public SerialWorker() {
		PATH_BOOK = PropertyWorker.getBookPath();
		PATH_ORDER = PropertyWorker.getOrderPath();
		PATH_BUYER = PropertyWorker.getBuyerPath();
	}

	public IBookManager getBookManager() {
		try {
			serializator = new Serializator(PATH_BOOK);
			return (IBookManager) serializator.readFromFile();
		} catch (RuntimeException e) {
			return new BookManager();
		}
	}

	public IOrderManager getOrderManager() {
		try {
			serializator = new Serializator(PATH_ORDER);
			return (IOrderManager) serializator.readFromFile();
		} catch (RuntimeException e) {
			return new OrderManager();
		}
	}

	public IBuyerManager getBuyerManager() {
		try {
			serializator = new Serializator(PATH_BUYER);
			return (IBuyerManager) serializator.readFromFile();
		} catch (RuntimeException e) {
			return new BuyerManager();
		}
	}
	public void  writeBookManager(IBookManager bookManager){
		try{
			serializator = new Serializator(PATH_BOOK);
			serializator.writeToFile(bookManager);
		}catch(RuntimeException e){
			throw new RuntimeException(FILE_NOT_FOUND_ERROR);
		}
	}
	public void writeOrderManager(IOrderManager orderManager){
		try{
			serializator = new Serializator(PATH_ORDER);
			serializator.writeToFile(orderManager);
		}catch(RuntimeException e){
			throw new RuntimeException(FILE_NOT_FOUND_ERROR);
		}
	}
	public void writeBuyerManager(IBuyerManager buyerManager){
		try{
			serializator = new Serializator(PATH_BUYER);
			serializator.writeToFile(buyerManager);
		}catch(RuntimeException e){
			throw new RuntimeException(FILE_NOT_FOUND_ERROR);
		}
	}
}
