package com.senla.bookshop.api.manager;

import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.resources.CSVWorker;

public interface IBaseManager {
	public static final String ADD_EXCEPTION = "Object was not added.";
	public static final String DELETE_EXCEPTION = "Object was not deleted.";
	public static CSVWorker csvWorker = new CSVWorker();

	public Boolean add(BaseEntity entity) throws Exception;

	public Boolean delete(BaseEntity entity) throws Exception;
}
