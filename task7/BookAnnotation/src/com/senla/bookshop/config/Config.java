package com.senla.bookshop.config;

import com.senla.bookconfiguration.conf.PropertyName;
import com.senla.bookshop.annotation.ConfigProperty;
import com.senla.bookshop.annotation.PropertyType;

public class Config {
	private static Config config;

	@ConfigProperty(propertyName = PropertyName.SWITCH_OFF_APPLICATION, type = PropertyType.BOOLEAN)
	public boolean SWITCH_OFF_APPLICATION;
	@ConfigProperty(propertyName = PropertyName.MONTH_OLD, type = PropertyType.INT)
	public Integer MONTH_OLD;
	@ConfigProperty(propertyName = PropertyName.FILE_PATH, type = PropertyType.STRING)
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
