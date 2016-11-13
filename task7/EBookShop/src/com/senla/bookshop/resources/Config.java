package com.senla.bookshop.resources;

import com.senla.bookconfiguration.annotation.ConfigProperty;
import com.senla.bookconfiguration.annotation.PropertyName;
import com.senla.bookconfiguration.annotation.PropertyType;

public class Config {
	@ConfigProperty(propertyName = PropertyName.MONTH_OLD, type = PropertyType.STRING)
	public static Integer MONTH_OLD;
	@ConfigProperty(propertyName = PropertyName.SWITCH_OFF_APPLICATION, type = PropertyType.BOOLEAN)
	public static Boolean SWITCH_OFF_APPLICATION;
	@ConfigProperty
	public static String FILE_PATH;

}
