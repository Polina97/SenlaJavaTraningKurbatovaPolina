package com.senla.bookshop.serialization;

import java.io.*;

import org.apache.log4j.Logger;

public class Serializator {
	private final static String FILE_NOT_FOUND_ERROR = "File not found";
	private final static String DEFAULT_STRING = "";
	private final Logger log = Logger.getLogger(Serializator.class.getName());
	private final String FILE_PATH;
	private final File file;
	private Object object;

	public Serializator(final String filePath) throws IllegalArgumentException {
		if (filePath == null || filePath.isEmpty() || filePath.equals(DEFAULT_STRING)) {
			throw new IllegalArgumentException(FILE_NOT_FOUND_ERROR);
		}
		this.FILE_PATH = filePath;
		file = new File(FILE_PATH);
	}

	public Object readFromFile() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			object = ois.readObject();
			return object;
		} catch (IOException |ClassNotFoundException e) {
			e.printStackTrace();
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	public void writeToFile(Object object) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(object);
		} catch (IOException | SecurityException | NullPointerException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

}
