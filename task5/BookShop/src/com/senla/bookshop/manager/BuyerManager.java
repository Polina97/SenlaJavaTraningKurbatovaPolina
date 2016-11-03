package com.senla.bookshop.manager;

import java.util.List;

import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.managers.IBuyerManager;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.main.Shop;

public class BuyerManager implements IBuyerManager {
	private List<IBuyer> buyers;

	public BuyerManager() {
		buyers = Shop.fileWorker.readBuyers();
	}

	public BuyerManager(Buyer[] buyers) {
		this();
		for (Buyer buyer : buyers) {
			add(buyer);
		}
	}

	@Override
	public List<IBuyer> getBuyers() {
		return buyers;
	}

	@Override
	public IBuyer getById(Integer id) {
		return buyers.get(id);
	}

	@Override
	public Boolean add(BaseEntity entity) {
		Boolean answ = buyers.add((IBuyer) entity);
		Shop.fileWorker.writeBuyer(this.buyers);
		return answ;
	}

	@Override
	public Boolean delete(BaseEntity entity) {
		Boolean answ = buyers.remove(entity);
		Shop.fileWorker.writeBuyer(this.buyers);
		return answ;
	}

}
