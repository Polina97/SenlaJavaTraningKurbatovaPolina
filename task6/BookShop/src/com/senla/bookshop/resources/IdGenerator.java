package com.senla.bookshop.resources;

import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entities.IBaseEntity;
import com.senla.bookshop.main.Shop;

public class IdGenerator {
	private static Integer id = 0;
	private static Logger log = Logger.getLogger(IdGenerator.class.getName());


	public static Integer getId(TypeId typeId) {
		List<?> listEntity = null;
		switch (typeId) {
		case BOOK:
			listEntity =  Shop.bookManager.getBooks();
			break;
		case ORDER:
			listEntity = Shop.orderManager.getOrders();
			break;
		case BUYER:
			listEntity = Shop.buyerManager.getBuyers();
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
		return id;
	}

}
