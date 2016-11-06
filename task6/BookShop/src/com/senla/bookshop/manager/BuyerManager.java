package com.senla.bookshop.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.manager.IBuyerManager;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.main.Shop;

public class BuyerManager implements IBuyerManager, Serializable {

	private static final long serialVersionUID = 1L;
	private List<IBuyer> buyers;

	public BuyerManager() {
		buyers = new ArrayList<IBuyer>();
	}

	public BuyerManager(Buyer[] buyers) {
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
		Shop.serialWorker.writeBuyerManager(this);
		return answ;
	}

	@Override
	public Boolean delete(BaseEntity entity) {
		Boolean answ = buyers.remove(entity);
		Shop.serialWorker.writeBuyerManager(this);
		return answ;
	}

}
