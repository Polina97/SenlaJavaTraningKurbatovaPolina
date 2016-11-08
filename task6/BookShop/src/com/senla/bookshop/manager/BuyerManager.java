package com.senla.bookshop.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.manager.IBuyerManager;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.Buyer;

public class BuyerManager implements IBuyerManager, Serializable {
	private static final Logger log = Logger.getLogger(BuyerManager.class.getName());

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
		try {
			for (IBuyer buyer : buyers) {
				if (buyer.getId().equals(id)) {
					return buyer;
				}
			}
		} catch (NullPointerException e) {
			log.error(e);
		}
		return null;

	}

	@Override
	public Boolean add(BaseEntity entity) {
		Boolean answ = buyers.add((IBuyer) entity);
		return answ;
	}

	@Override
	public Boolean delete(BaseEntity entity) {
		Boolean answ = buyers.remove(entity);
		return answ;
	}

	@Override
	public IBuyer exportBuyer(Integer id) {
		try {
			List<IBuyer> csvBuyers = csvWorker.readBuyers();
			for (IBuyer order : csvBuyers) {
				if (order.getId().equals(id)) {
					csvBuyers.remove(order);
					csvWorker.writeBuyers(csvBuyers);
					return order;
				}
			}

		} catch (NullPointerException | ClassCastException e) {
			log.error(e);
		}
		return null;
	}

	@Override
	public void importBuyer(Integer id) throws Exception {
		try {
			List<IBuyer> csvBuyers = csvWorker.readBuyers();
			if (csvBuyers != null) {
				for (IBuyer order : csvBuyers) {
					if (order.getId().equals(id)) {
						csvBuyers.remove(order);
						break;
					}
				}
			} else {
				csvBuyers = new ArrayList<IBuyer>();
			}
			csvBuyers.add(getById(id));
			csvWorker.writeBuyers(csvBuyers);
		} catch (NullPointerException e) {
			log.error(e);
			throw new Exception(e);
		}
	}

}
