package com.senla.bookshop.resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class CSVFileWorker {
	private final static String FILE_NOT_FOUND_ERROR = "File not found";
	private final static String DEFAULT_STRING = "";
	private final Logger log = Logger.getLogger(CSVFileWorker.class.getName());
	private final String FILE_PATH;
	private final File file;

	public CSVFileWorker(String filePath) {
		if (filePath == null || filePath.isEmpty() || filePath.equals(DEFAULT_STRING)) {
			throw new IllegalArgumentException(FILE_NOT_FOUND_ERROR);
		}
		this.FILE_PATH = filePath;
		file = new File(FILE_PATH);
	}

	public List<String> readeFromCSV() {
		String line = null;
		List<String> stringList = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				stringList.add(line);
			}
			return stringList;
		} catch (NullPointerException | IOException e) {
			log.error(e);
			return null;
		}
	}

	public void writeToCSV(List<String> objects, String headString) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			objects.add(0, headString);
			for (String object : objects) {
				bw.write(object);
				bw.newLine();
			}
		} catch (NullPointerException | ClassCastException | IOException e) {
			log.error(e);
		}
	}

}
