package com.senla.bookshop.manager;

import java.util.List;

import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.api.managers.IBuyerManager;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.main.Shop;

public class BuyerManager implements IBuyerManager {
	private List<IBuyer> buyers;

	public BuyerManager()  {
		this.buyers = Shop.fileWorker.readBuyers();
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
	public void setBuyers(List<IBuyer> buyers) {
		this.buyers = buyers;
	}

	@Override
	public IBuyer getById(Integer id) {
		for (IBuyer buyer : this.buyers) {
			if (buyer.getId().equals(id)) {
				return buyer;
			}
		}
		return null;
	}


	@Override
	public Boolean add(BaseEntity entity) {
		Boolean answ = this.buyers.add((IBuyer) entity);
		Shop.fileWorker.writeBuyer(this.buyers);
		return answ;
	}

	@Override
	public Boolean delete(BaseEntity entity) {
		Boolean answ = this.buyers.remove(entity);
		Shop.fileWorker.writeBuyer(this.buyers);
		return answ;
	}

	public void addOrderBuyer(IOrder order) {
		getById(order.getBuyer().getId()).addOrder(order);
		Shop.fileWorker.writeBuyer(this.buyers);
	}

	public void deleteOrderBuyer(IOrder order) {
		order.getBuyer().deleteOrder(order);
		Shop.fileWorker.writeBuyer(this.buyers);
	}
}
