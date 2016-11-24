package com.senla.bookshop.di;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DILoader {
	private Logger log = Logger.getLogger(DILoader.class);
	private Properties property;
	private static final String FILE_PATH = "src/di.properties";

	public String load(String propertyName) {
		try {
			File pf = new File(FILE_PATH);
			if (property == null) {
				property = new Properties();
				property.load(new FileInputStream(pf));
			}
			if (pf.canRead()) {

				return property.getProperty(propertyName);
			} else {
				return null;
			}
		} catch (IOException | NumberFormatException | NullPointerException e) {
			log.error(e);
			return null;
		}

	}
}
