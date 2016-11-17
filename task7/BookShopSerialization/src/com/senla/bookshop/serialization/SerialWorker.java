package com.senla.bookshop.serialization;

import org.apache.log4j.Logger;

import com.senla.bookshop.config.Config;
import com.senla.bookshop.storage.BookShopStorage;

public class SerialWorker {
	public String PATH_FILE;
	public final String DEFAULT_PATH_FILE = "src/managers.bin";
	private final String FILE_NOT_FOUND_ERROR = "File not found";
	private Logger log = Logger.getLogger(SerialWorker.class);
	private Serializator serializator;

	public SerialWorker() {
		PATH_FILE = Config.getInstance().FILE_PATH;
		if (PATH_FILE == null) {
			PATH_FILE = DEFAULT_PATH_FILE;
		}
		try {
			serializator = new Serializator(PATH_FILE);
		} catch (RuntimeException e) {
			log.error(e);
		}

	}

	public BookShopStorage getStorage() {
		try {
			return (BookShopStorage) serializator.readFromFile();
		} catch (RuntimeException e) {
			throw new RuntimeException(FILE_NOT_FOUND_ERROR);
		}
	}

	public void writeStorage(BookShopStorage storage) {
		try {
			serializator.writeToFile(storage);
		} catch (RuntimeException e) {
			throw new RuntimeException(FILE_NOT_FOUND_ERROR);
		}
	}

}
