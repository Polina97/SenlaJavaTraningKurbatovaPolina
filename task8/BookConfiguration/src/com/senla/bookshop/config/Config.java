package com.senla.bookshop.config;

import com.senla.bookshop.annotation.ConfigProperty;

public class Config {
	private static Config config;

	@ConfigProperty(propertyName = "SWITCH_OFF_APPLICATION", type = "Boolean")
	public Boolean SWITCH_OFF_APPLICATION;
	@ConfigProperty(propertyName = "MONTH_OLD", type = "Integer")
	public Integer MONTH_OLD;
	@ConfigProperty(propertyName ="FILE_PATH", type = "String")
	public String FILE_PATH;

	private Config() {
	}

	public static Config getInstance() {
		if (config == null) {
			config = new Config();
		}
		return config;
	}

}
