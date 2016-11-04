package com.senla.bookconfiguration.conf;

import java.util.List;

import org.apache.log4j.Logger;

public class PropertyWorker {
	private final static String propertyFile = "src/configuration.properties";
	private static Loader loader;
	private static Logger log = Logger.getLogger(PropertyWorker.class.getName());
	private static Integer monthOld;
	private static Boolean isApplication;
	private static List<String> properties;
	static {
		loader = new Loader(propertyFile);
		properties = loader.load();
	}

	public static void main(String[] args) {
		System.out.println(getMonthOld());
		System.out.println(getIsApplication());
	}

	public static Integer getMonthOld() {
		try {
			if (properties != null) {
				monthOld = Integer.parseInt(properties.get(0));
				return monthOld;
			} else {
				return 0;
			}

		} catch (NumberFormatException | NullPointerException e) {
			log.error(e);
			return 0;
		}

	}

	public static Boolean getIsApplication() {
		try {
			if (properties != null) {
				isApplication = Boolean.parseBoolean(properties.get(1));
				return isApplication;
			} else {
				return false;
			}
		} catch (NumberFormatException | NullPointerException e) {
			log.error(e);
			return false;
		}

	}

}
