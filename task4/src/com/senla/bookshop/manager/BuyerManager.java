package com.senla.bookshop.manager;

import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.managers.IBuyerManager;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.resources.ArrayWorker;
import com.senla.bookshop.resources.FileWorker;

public class BuyerManager implements IBuyerManager {
	private IBuyer[] buyers;
	private FileWorker fileWorker;

	public BuyerManager(FileWorker fileWorker) {
		this.fileWorker = fileWorker;
		this.buyers = fileWorker.readBuyers();
	}

	public BuyerManager(Buyer[] buyers, FileWorker fileWorker) {
		this(fileWorker);
		for (Buyer buyer : buyers) {
			this.buyers = ArrayWorker.addBuyer(buyer, this.buyers);
		}
		fileWorker.writeBuyer(this.buyers);
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

	@Override
	public void add(BaseEntity entity) {
		this.buyers = ArrayWorker.addBuyer((Buyer) entity, this.buyers);
		fileWorker.writeBuyer( this.buyers);
	}

	@Override
	public void delete(BaseEntity entity) {
		this.buyers = ArrayWorker.deleteBuyer((Buyer) entity, this.buyers);
		fileWorker.writeBuyer(this.buyers);
	}

	@Override
	public void showAllBuyers() {
		ArrayWorker.showArray(this.buyers);
	}

}
