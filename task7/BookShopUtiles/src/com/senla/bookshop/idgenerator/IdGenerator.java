package com.senla.bookshop.idgenerator;

import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entity.IBaseEntity;
import com.senla.bookshop.api.storage.IBookShopStorage;
import com.senla.bookshop.serialization.StorageLoader;

public class IdGenerator {
	private static Integer id = 0;
	private static Logger log = Logger.getLogger(IdGenerator.class);
	private static IBookShopStorage storage =  StorageLoader.getStorage();

	public static Integer getId(TypeId typeId) {
		List<?> listEntity = null;
		switch (typeId) {
		case BOOK:
			listEntity = storage.getBooks();
			break;
		case ORDER:
			listEntity = storage.getOrders();
			break;
		case BUYER:
			listEntity = storage.getBuyers();
			break;
		}

		try {
			if (listEntity != null) {
				for (Object entity : listEntity) {
					if (id < ((IBaseEntity) entity).getId()) {
						id = ((IBaseEntity) entity).getId();
					}
				}
			}
		} catch (NullPointerException e) {
			log.error(e);
		}
		return ++id;
	}

}
