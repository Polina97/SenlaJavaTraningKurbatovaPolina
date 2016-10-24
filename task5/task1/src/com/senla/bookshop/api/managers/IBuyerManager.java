package com.senla.bookshop.api.managers;

import java.util.List;

import com.senla.bookshop.api.entities.IBuyer;

public interface IBuyerManager extends IBaseManager {
	public IBuyer getById(Integer id);

	public List<IBuyer> getBuyers();

	public void setBuyers(List<IBuyer> buyers);

}
