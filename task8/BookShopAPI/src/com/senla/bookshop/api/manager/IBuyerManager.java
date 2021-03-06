package com.senla.bookshop.api.manager;

import java.util.List;

import com.senla.bookshop.api.entity.IBuyer;

public interface IBuyerManager extends IBaseManager {
	public List<IBuyer> getBuyers();

	public IBuyer getById(Integer id);

	public List<IBuyer> exportBuyers();

	public IBuyer importBuyer(Integer id) throws Exception;
}
