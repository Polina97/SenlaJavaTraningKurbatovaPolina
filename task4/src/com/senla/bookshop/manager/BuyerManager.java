package com.senla.bookshop.manager;

import com.senla.bookshop.api.managers.IBuyerManager;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.resources.ArrayWorker;
import com.senla.bookshop.resources.FileWorker;

public class BuyerManager implements IBuyerManager {
	private Buyer[] buyers;

	public BuyerManager() {
		this.buyers = FileWorker.readBuyers();
	}

	public BuyerManager(Buyer[] buyers) {
		this();
		for (Buyer buyer : buyers) {
			this.buyers = ArrayWorker.addBuyer(buyer, this.buyers);
		}
		FileWorker.writeBuyer(this.buyers);
	}

	@Override
	public Buyer[] getBuyers() {
		return buyers;
	}

	@Override
	public void setBuyers(Buyer[] buyers) {
		this.buyers = buyers;
	}

	@Override
	public Buyer getById(Integer id) {
		for (int i = 0; i < this.buyers.length; i++) {
			if (this.buyers[i].getId() == id) {
				return this.buyers[i];
			}
		}
		return null;
	}

	@Override
	public void add(BaseEntity entity) {
		this.buyers = ArrayWorker.addBuyer((Buyer) entity, this.buyers);
		FileWorker.writeBuyer(this.buyers);
	}

	@Override
	public void delete(BaseEntity entity) {
		this.buyers = ArrayWorker.deleteBuyer((Buyer) entity, this.buyers);
		FileWorker.writeBuyer(this.buyers);
	}
	@Override
	public void showAllBuyers(){
		ArrayWorker.showArray(this.buyers);
	}

}
