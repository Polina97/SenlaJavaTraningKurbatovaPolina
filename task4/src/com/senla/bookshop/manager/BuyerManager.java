package com.senla.bookshop.manager;


import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.api.managers.IBuyerManager;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.main.Runner;
import com.senla.bookshop.resources.ArrayWorker;

public class BuyerManager implements IBuyerManager {
	private IBuyer[] buyers;

	public BuyerManager() {
		this.buyers = Runner.fileWorker.readBuyers();
	}

	public BuyerManager(Buyer[] buyers) {
		this();
		for (Buyer buyer : buyers) {
			add(buyer);
		}
	}

	@Override
	public IBuyer[] getBuyers() {
		return buyers;
	}

	@Override
	public void setBuyers(IBuyer[] buyers) {
		this.buyers = buyers;
	}

	@Override
	public IBuyer getById(Integer id) {
		for (int i = 0; i < this.buyers.length; i++) {
			if (this.buyers[i].getId() == id) {
				return this.buyers[i];
			}
		}
		return null;
	}
	public void addOrderBuyer(IOrder order){
		getById(order.getBuyer().getId()).addOrder(order);
		Runner.fileWorker.writeBuyer(this.buyers);
	}
	public void deleteOrderBuyer(IOrder order){
		order.getBuyer().deleteOrder(order);
		Runner.fileWorker.writeBuyer(this.buyers);
	}

	@Override
	public void add(BaseEntity entity) {
		if (!ArrayWorker.contains(entity, this.buyers)) {
			this.buyers = ArrayWorker.addBuyer((Buyer) entity, this.buyers);
			Runner.fileWorker.writeBuyer(this.buyers);
		}
	}

	@Override
	public void delete(BaseEntity entity) {
		this.buyers = ArrayWorker.deleteBuyer((Buyer) entity, this.buyers);
		Runner.fileWorker.writeBuyer(this.buyers);
	}

	@Override
	public void showAll() {
		ArrayWorker.showArray(this.buyers);

	}

}
