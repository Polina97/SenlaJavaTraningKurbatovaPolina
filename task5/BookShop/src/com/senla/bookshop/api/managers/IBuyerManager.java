package com.senla.bookshop.api.managers;

import java.util.List;

import com.senla.bookshop.api.entities.IBuyer;

public interface IBuyerManager extends IBaseManager {
	public List<IBuyer> getBuyers();
	public IBuyer getById(Integer id);
}
