package com.senla.bookconfiguration.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Loader {
	private Logger log = Logger.getLogger(Loader.class.getName());
	private Properties property = new Properties();
	private String propertyFile;
	private List<String> properties;

	public Loader(String propertyFile) {
		this.propertyFile = propertyFile;
	}

	public List<String> load() {
		try {
			File pf = new File(propertyFile);
			if (pf.canRead()) {
				properties = new ArrayList<String>();
				property.load(new FileInputStream(pf));
				properties.add(property.getProperty(PropertyName.MONTH_OLD.toString()));
				properties.add(property.getProperty(PropertyName.SWITCH_OFF_APPLICATION.toString()));
				properties.add(property.getProperty(PropertyName.BOOK_PATH.toString()));
				properties.add(property.getProperty(PropertyName.ORDER_PATH.toString()));
				properties.add(property.getProperty(PropertyName.BUYER_PATH.toString()));
				
				return properties;
			} else {
				return null;
			}
		} catch (IOException | NumberFormatException | NullPointerException e) {
			log.error(e);
			return null;
		}
	}
}
