package com.senla.bookshop.api.manager;

import com.senla.bookshop.api.entity.IBaseEntity;

public interface IBaseManager {
	public static final String ADD_EXCEPTION = "Object was not added.";
	public static final String DELETE_EXCEPTION = "Object was not deleted.";


	public Boolean add(IBaseEntity entity) throws Exception;

	public Boolean delete(IBaseEntity entity) throws Exception;
}
