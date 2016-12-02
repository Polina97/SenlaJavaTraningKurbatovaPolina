package com.senla.bookshop.serialization;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.storage.IBookShopStorage;
import com.senla.bookshop.di.DIBookShop;


public class StorageLoader{
	private static Logger log = Logger.getLogger(StorageLoader.class);
	private static IBookShopStorage bookShopStorage;
	private static SerialWorker serialWorker = new SerialWorker();

	public static IBookShopStorage getStorage(){
		if(bookShopStorage == null){
			try{
				bookShopStorage = serialWorker.getStorage();
			}catch(Exception e){
				log.error(e);
			}
			if(bookShopStorage == null){
				bookShopStorage = (IBookShopStorage) DIBookShop.load(IBookShopStorage.class.getName(), false);
			}
		}
		return bookShopStorage;
	}

}
