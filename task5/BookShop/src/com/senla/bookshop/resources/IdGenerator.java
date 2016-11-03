package com.senla.bookshop.resources;

import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entities.IBaseEntity;

public class IdGenerator {
	private static Integer id = 0;
	private static Logger log = Logger.getLogger(IdGenerator.class.getName());

	public static Integer getId(List<?> listEntity) {
		try {
			for (Object entity : listEntity) {
				if (id < ((IBaseEntity) entity).getId()) {
					id = ((IBaseEntity) entity).getId();
				}
			}
		} catch (NullPointerException e) {
			log.error(e);
		}
		return id;

	}

}
