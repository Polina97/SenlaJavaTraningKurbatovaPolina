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
			log.error(e);
			throw new Exception(ADD_EXCEPTION);
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
			log.error(e);
			throw new Exception(e);
		}
	}

	@Override
	public IOrder getOrderById(Integer id) {
		return orders.get(id);
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

}
