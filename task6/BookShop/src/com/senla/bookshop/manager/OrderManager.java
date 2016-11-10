package com.senla.bookshop.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.api.manager.IOrderManager;
import com.senla.bookshop.comparators.OrderComparator;
import com.senla.bookshop.comparators.TypeOrderComparator;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.StatusOrder;

public class OrderManager implements IOrderManager, Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(OrderManager.class.getName());
	private List<IOrder> orders;

	public OrderManager() {
		orders = new ArrayList<IOrder>();
	}

	public OrderManager(List<IOrder> orders) {
		this.orders.addAll(orders);
	}

	@Override
	public List<IOrder> getOrders() {
		return orders;
	}

	@Override
	public Integer getGeneralPrice() {
		int sum = 0;
		for (IOrder o : this.orders) {
			sum += o.getPrice();
		}
		return sum;
	}

	@Override
	public Boolean add(BaseEntity entity) throws Exception {
		try {
			Boolean answ = orders.add((IOrder) entity);
			return answ;
		} catch (ClassCastException | NullPointerException | IllegalArgumentException e) {
			throw new Exception(e);
		}
	}

	@Override
	public Boolean delete(BaseEntity entity) {
		Boolean answ = orders.add((IOrder) entity);
		return answ;
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
			for (IOrder order : orders) {
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
			for (IOrder order : orders) {
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
		orders.sort(new OrderComparator(comparator));
		return orders;

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
			orders.add(order);
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
			if(csvWorker.writeOrders(csvOrders)!= null){
				return getOrderById(id);
			}else{
				return null;
			}

		} catch (NullPointerException e) {
			throw new Exception(e);
		}

	}

}
