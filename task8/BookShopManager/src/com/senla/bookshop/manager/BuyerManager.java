package com.senla.bookshop.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entity.IBaseEntity;
import com.senla.bookshop.api.entity.IBuyer;
import com.senla.bookshop.api.manager.IBuyerManager;
import com.senla.bookshop.api.storage.IBookShopStorage;
import com.senla.bookshop.serialization.StorageLoader;

public class BuyerManager extends BaseManager implements IBuyerManager, Serializable {
	private static final Logger log = Logger.getLogger(BuyerManager.class);
	private static final long serialVersionUID = 1L;
	private IBookShopStorage storage;


	public BuyerManager() {
		this.storage =  StorageLoader.getStorage();
	}

	@Override
	public List<IBuyer> getBuyers() {
		return storage.getBuyers();
	}

	@Override
	public IBuyer getById(Integer id) {
		try {
			for (IBuyer buyer : storage.getBuyers()) {
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
	public Boolean add(IBaseEntity entity) {
		return storage.addBuyer((IBuyer) entity);
	}

	@Override
	public Boolean delete(IBaseEntity entity) {
		return storage.deleteBuyer((IBuyer) entity);
	}

	@Override
	public List<IBuyer> exportBuyers() {
		try {
			List<IBuyer> csvBuyers = csvWorker.readBuyers();
			return csvBuyers;

		} catch (NullPointerException | ClassCastException e) {
			log.error(e);
		}
		return null;
	}

	@Override
	public IBuyer importBuyer(Integer id) throws Exception {
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
			
			if( csvWorker.writeBuyers(csvBuyers)!=null){
				return getById(id);
			}else{
				return null;
			}
		} catch (NullPointerException e) {
			throw new Exception(e);
		}
	}


}
