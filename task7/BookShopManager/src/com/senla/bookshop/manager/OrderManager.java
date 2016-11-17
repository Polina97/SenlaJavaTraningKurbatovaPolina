package com.senla.bookshop.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entity.IBaseEntity;
import com.senla.bookshop.api.entity.IOrder;
import com.senla.bookshop.api.entity.StatusOrder;
import com.senla.bookshop.api.manager.IOrderManager;
import com.senla.bookshop.comparators.OrderComparator;
import com.senla.bookshop.serialization.StorageLoader;
import com.senla.bookshop.storage.BookShopStorage;
import com.senla.bookshop.typecomparator.TypeOrderComparator;

public class OrderManager extends BaseManager implements IOrderManager, Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(OrderManager.class);
	private BookShopStorage storage;

	public OrderManager() {
		this.storage =  StorageLoader.getStorage();
	}

	@Override
	public List<IOrder> getOrders() {
		return storage.getOrders();
	}

	@Override
	public Integer getGeneralPrice() {
		int sum = 0;
		try {
			for (IOrder o : storage.getOrders()) {
				sum += o.getPrice();
			}
		} catch (NullPointerException e) {
			log.error(e);
		}
		return sum;
	}

	@Override
	public Boolean add(IBaseEntity entity) throws Exception {
		try {
			return storage.addOrder((IOrder) entity);
		} catch (NullPointerException e) {
			log.error(e);
			return false;
		}
	}

	@Override
	public Boolean delete(IBaseEntity entity) {
		try {
			return storage.deleteOrder((IOrder) entity);
		} catch (NullPointerException e) {
			log.error(e);
			return false;
		}
	}

	@Override
	public void changeStatus(Integer id, StatusOrder statusOrder) throws Exception {
		try {
			getOrderById(id).setStatus(statusOrder);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public IOrder getOrderById(Integer id) {
		try {
			for (IOrder order : storage.getOrders()) {
				if (order.getId().equals(id)) {
					return order;
				}
			}
		} catch (NullPointerException e) {
			log.error(e);
		}
		return null;
	}

	@Override
	public List<IOrder> getDeliveredOrders() {
		List<IOrder> deliveredOrders = new ArrayList<IOrder>();
		try {
			for (IOrder order : storage.getOrders()) {
				if (order.getStatus().equals(StatusOrder.DELIVERED)) {
					deliveredOrders.add(order);
				}
			}
		} catch (NullPointerException e) {
			log.error(e);
		}
		return deliveredOrders;
	}

	@Override
	public List<IOrder> sortOrders(TypeOrderComparator comparator) {
		try {
			storage.getOrders().sort(new OrderComparator(comparator));
			return storage.getOrders();
		} catch (NullPointerException e) {
			log.error(e);
			return null;
		}

	}

	@Override
	public List<IOrder> sortDeliveredOrders(TypeOrderComparator comparator) {
		List<IOrder> ordersDelivered = getDeliveredOrders();
		try {
			ordersDelivered.sort(new OrderComparator(comparator));
			return ordersDelivered;
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	@Override
	public void cloneOrder(Integer id) throws Exception {
		IOrder order;
		try {
			order = getOrderById(id).clone();
			storage.addOrder(order);
		} catch (CloneNotSupportedException | NullPointerException e) {
			throw new Exception(e);
		}

	}

	@Override
	public List<IOrder> exportOrders() {
		try {
			List<IOrder> csvOrders = csvWorker.readOrders();
			return csvOrders;

		} catch (NullPointerException | ClassCastException e) {
			log.error(e);
		}
		return null;
	}

	@Override
	public IOrder importOrder(Integer id) throws Exception {
		try {
			List<IOrder> csvOrders = csvWorker.readOrders();
			if (csvOrders != null) {
				for (IOrder order : csvOrders) {
					if (order.getId().equals(id)) {
						csvOrders.remove(order);
						break;
					}
				}
			} else {
				csvOrders = new ArrayList<IOrder>();
			}
			csvOrders.add(getOrderById(id));
			if (csvWorker.writeOrders(csvOrders) != null) {
				return getOrderById(id);
			} else {
				return null;
			}

		} catch (NullPointerException e) {
			throw new Exception(e);
		}

	}

}
