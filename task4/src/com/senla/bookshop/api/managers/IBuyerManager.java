package com.senla.bookshop.api.managers;

import com.senla.bookshop.api.entities.IBuyer;

public interface IBuyerManager extends IBaseManager {
	IBuyer getById(Integer id);

	IBuyer[] getBuyers();

	void setBuyers(IBuyer[] buyers);

	void showAllBuyers();
}
