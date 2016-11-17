package com.senla.bookshop.serialization;

import org.apache.log4j.Logger;

import com.senla.bookshop.storage.BookShopStorage;

public class StorageLoader {
	private static Logger log = Logger.getLogger(StorageLoader.class);
	private static BookShopStorage bookShopStorage;
	private static SerialWorker serialWorker = new SerialWorker();

	public static BookShopStorage getStorage(){
		if(bookShopStorage == null){
			try{
				bookShopStorage = serialWorker.getStorage();
			}catch(Exception e){
				log.error(e);
			}
			if(bookShopStorage == null){
				bookShopStorage = BookShopStorage.getInstance();
			}
		}
		return bookShopStorage;
	}

}
