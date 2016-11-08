package com.senla.bookshop.api.manager;

import java.util.List;

import com.senla.bookshop.api.entities.IBuyer;

public interface IBuyerManager extends IBaseManager {
	public List<IBuyer> getBuyers();

	public IBuyer getById(Integer id);

	public IBuyer exportBuyer(Integer id);

	public void importBuyer(Integer id) throws Exception;
}
