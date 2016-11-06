package com.senla.bookshop.serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.manager.IBaseManager;

public class Serializator {
	private final static String FILE_NOT_FOUND_ERROR = "File not found";
	private final static String DEFAULT_STRING = "";
	private final static Integer COUNT_OBJECTS = 3;
	private final Logger log = Logger.getLogger(Serializator.class.getName());
	private final String FILE_PATH;
	private final File file;
	private List<Object> objects;

	public Serializator(final String filePath) throws IllegalArgumentException {
		if (filePath == null || filePath.isEmpty() || filePath.equals(DEFAULT_STRING)) {
			throw new IllegalArgumentException(FILE_NOT_FOUND_ERROR);
		}
		this.FILE_PATH = filePath;
		file = new File(FILE_PATH);
	}

	public List<Object> readFromFile() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			objects = new ArrayList<Object>();
			for (int i = 0; i < COUNT_OBJECTS; i++) {
				objects.add(ois.readObject());
			}
			return objects;
		} catch (IOException | ClassNotFoundException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	public void writeToFile(List<IBaseManager> managers) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			for (int i = 0; i < managers.size(); i++) {
				oos.writeObject(managers.get(i));
			}
		} catch (IOException | SecurityException | NullPointerException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

}
