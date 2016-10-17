package com.senla.bookshop.api.managers;

import com.senla.bookshop.entity.Buyer;

public interface IBuyerManager extends IBaseManager {
	Buyer getById(Integer id);

	Buyer[] getBuyers();

	void setBuyers(Buyer[] buyers);

	void showAllBuyers();
}
