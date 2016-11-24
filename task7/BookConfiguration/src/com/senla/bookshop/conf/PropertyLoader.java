package com.senla.bookshop.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyLoader {
	private Logger log = Logger.getLogger(PropertyLoader.class);
	private Properties property = new Properties();
	private static final String FILE_PATH = "src/configuration.properties";

	public PropertyLoader() {
		load();
	}

	private void load() {
		try {
			File pf = new File(FILE_PATH);
			if (pf.canRead()) {
				property.load(new FileInputStream(pf));
			} 
			
		} catch (IOException | NullPointerException e) {
			log.error(e);
		}
	}
	public String getProperty(String name){
		return property.getProperty(name);
	}
}
