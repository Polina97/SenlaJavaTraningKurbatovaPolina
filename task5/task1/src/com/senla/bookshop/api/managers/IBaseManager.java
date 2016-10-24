package com.senla.bookshop.api.managers;

import com.senla.bookshop.entity.BaseEntity;

public interface IBaseManager {
	static final String  ADD_EXCEPTION = "Object was not added.";
	static final String  DELETE_EXCEPTION = "Object was not deleted.";

	public Boolean add(BaseEntity entity) throws Exception;

	public Boolean delete(BaseEntity entity) throws Exception;

}
