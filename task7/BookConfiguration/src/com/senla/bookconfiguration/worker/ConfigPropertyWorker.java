package com.senla.bookconfiguration.worker;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.senla.bookconfiguration.annotation.ConfigProperty;
import com.senla.bookconfiguration.annotation.PropertyType;
import com.senla.bookconfiguration.conf.PropertyWorker;

public class ConfigPropertyWorker {
	private static Logger log = Logger.getLogger(ConfigPropertyWorker.class.getName());
	private static Object object;
	private static PropertyWorker propertyWorker = PropertyWorker.getInstance();

	private ConfigPropertyWorker() {
	}

	public static void configurate(Object o) throws Exception {
		if (o != null) {
			object = o;
			initializeFields();
		} else {
			throw new NullPointerException("Null object");
		}
	}

	private static void initializeFields() {
		try {
			for (Field f : object.getClass().getFields()) {
				if (f.isAnnotationPresent(ConfigProperty.class)) {
					ConfigProperty ann = f.getAnnotation(ConfigProperty.class);
					switch (ann.propertyName()) {
					case FILE_PATH:
						if (ann.type().equals(PropertyType.STRING) || f.getType().equals(String.class)) {
							f.set(object, propertyWorker.getFilePath());
						}
						break;
					case MONTH_OLD:
						if (f.getType().equals(Integer.class) || ann.type().equals(PropertyType.INT)) {
							f.setInt(object, propertyWorker.getMonthOld());
						}
						break;
					case SWITCH_OFF_APPLICATION:
						if (ann.type().equals(PropertyType.BOOLEAN) || f.getType().equals(Boolean.class)) {
							f.setBoolean(object, propertyWorker.getIsApplication());
						}
						break;
					default:
						break;
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error(e);
		}
	}

}
