package com.senla.bookconfiguration.conf;

import java.util.List;

import org.apache.log4j.Logger;

public class PropertyWorker {
	private static PropertyWorker propertyWorker;
	private final static String PROPERTY_FILE = "src/configuration.properties";
	private final static String DEFAULT_CONFIG_NAME = "Book configuration";
	private final static String DEFAULT_STRING = "";
	private final static Integer DEFAULT_NUMBER = 0;
	private static Loader loader;
	private static Logger log = Logger.getLogger(PropertyWorker.class.getName());
	private static List<String> properties;
	static {
		loader = new Loader(PROPERTY_FILE);
		properties = loader.load();
	}

	private PropertyWorker() {
	}

	public static PropertyWorker getInstance() {
		if (propertyWorker == null) {
			propertyWorker = new PropertyWorker();
		}
		return propertyWorker;

	}

	public String getConfigName() {
		try {
			if (properties != null) {
				return properties.get(0);
			} else {
				return DEFAULT_CONFIG_NAME;
			}

		} catch (NumberFormatException | NullPointerException e) {
			log.error(e);
			return DEFAULT_CONFIG_NAME;
		}
	}

	public Integer getMonthOld() {
		try {
			if (properties != null) {
				return Integer.parseInt(properties.get(1));
			} else {
				return DEFAULT_NUMBER;
			}

		} catch (NumberFormatException | NullPointerException e) {
			log.error(e);
			return DEFAULT_NUMBER;
		}

	}

	public Boolean getIsApplication() {
		try {
			if (properties != null) {
				return Boolean.parseBoolean(properties.get(2));
			} else {
				return false;
			}
		} catch (NumberFormatException | NullPointerException e) {
			log.error(e);
			return false;
		}
	}

	public String getFilePath() {
		try {
			if (properties != null) {
				return properties.get(3);
			} else {
				return DEFAULT_STRING;
			}
		} catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
			log.error(e);
			return DEFAULT_STRING;
		}
	}

}
