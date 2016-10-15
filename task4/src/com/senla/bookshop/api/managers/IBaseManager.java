package com.senla.bookshop.api.managers;

import com.senla.bookshop.api.entities.IBaseEntity;
import com.senla.bookshop.api.entities.IBook;

public interface IBaseManager {

	void add(IBaseEntity entity);

	void delete(IBaseEntity entity);

}
