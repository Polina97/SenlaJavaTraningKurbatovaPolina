package com.senla.bookshop.serialization;

import java.io.*;


public class Serializator {
	private final static String FILE_NOT_FOUND_ERROR = "File not found";
	private final static String DEFAULT_STRING = "";
	private final String FILE_PATH;
	private final File file;

	public Serializator(final String filePath) throws IllegalArgumentException {
		if (filePath == null || filePath.isEmpty() || filePath.equals(DEFAULT_STRING)) {
			throw new IllegalArgumentException(FILE_NOT_FOUND_ERROR);
		}
		this.FILE_PATH = filePath;
		file = new File(FILE_PATH);
	}

	public Object readFromFile() {
		Object object = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			object = ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return object;
	}

	public void writeToFile(Object object) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
				oos.writeObject(object);
		} catch (IOException | SecurityException | NullPointerException e) {
			throw new RuntimeException(e);
		}
	}

}
