package com.senla.bookshop.api.managers;

import com.senla.bookshop.api.entities.IBuyer;

public interface IBuyerManager extends IBaseManager {
	public IBuyer getById(Integer id);

	public IBuyer[] getBuyers();

	public void setBuyers(IBuyer[] buyers);

}
