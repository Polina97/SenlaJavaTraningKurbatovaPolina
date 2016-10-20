package com.senla.bookshop.manager;


import java.util.Arrays;

import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.api.managers.IOrderManager;
import com.senla.bookshop.comparators.OrderComparator;
import com.senla.bookshop.comparators.TypeOrderComparator;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.StatusOrder;
import com.senla.bookshop.entity.Order;
import com.senla.bookshop.main.Runner;
import com.senla.bookshop.resources.ArrayWorker;
import com.senla.bookshop.resources.Printer;

public class OrderManager implements IOrderManager {
	private IOrder[] orders;

	public OrderManager() {
		this.orders = Runner.fileWorker.readOrders();
	}

	public OrderManager(Order[] orders) {
		this();
		for (Order order : orders) {
			add(order);
		}
		Runner.fileWorker.writeOrders(this.orders);
	}

	public IOrder[] getOrders() {
		return orders;
	}

	public void setOrders(Order[] orders) {
		this.orders = orders;
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
	public void add(BaseEntity entity) {
		if (!ArrayWorker.contains(entity, this.orders)) {
			this.orders = ArrayWorker.addOrder((Order) entity, this.orders);
			Runner.fileWorker.writeOrders(this.orders);
		}
	}

	@Override
	public void delete(BaseEntity entity) {
		this.orders = ArrayWorker.deleteOrder((Order) entity, this.orders);
		Runner.fileWorker.writeOrders(this.orders);
	}

	@Override
	public void deliverOrder(Integer id) {
		for (int i = 0; i < this.orders.length; i++) {
			if (orders[i]!=null && orders[i].getId().equals(id)) {
				orders[i].setStatus(StatusOrder.DELIVERED);
				Printer.print("Order " + orders[i].getId() + " was delivered");
			}
		}
		Runner.fileWorker.writeOrders(this.orders);
	}

	@Override
	public void cancelOrder(Integer id) {
		for (int i = 0; i < this.orders.length; i++) {
			if (orders[i]!=null && orders[i].getId().equals(id)) {
				orders[i].setStatus(StatusOrder.CANCELED);
				Printer.print("Order " + orders[i].getId() + " was canceled");
			}
		}
		Runner.fileWorker.writeOrders(this.orders);
	}

	@Override
	public IOrder getOrderById(Integer id) {
		for (int i = 0; i < this.orders.length; i++) {
			if (orders[i] != null) {
				if (orders[i].getId().equals(id)) {
					return orders[i];
				}
			}
		}
		return null;
	}

	@Override
	public IOrder[] getDeliveredOrders() {
		IOrder[] deliveredOrders = null;
		for (IOrder order : this.orders) {
			if (order!=null && order.getStatus().equals(StatusOrder.DELIVERED)) {
				deliveredOrders = ArrayWorker.addOrder(order, deliveredOrders);
			}
		}
		return deliveredOrders;
	}

	@Override
	public void sortOrders(TypeOrderComparator comparator) {
		Arrays.sort((Order[]) this.orders, new OrderComparator(comparator));
		showAll();

	}

	@Override
	public void sortDeliveredOrders(TypeOrderComparator comparator) {
		IOrder[] ordersDelivered = getDeliveredOrders();
		Arrays.sort((Order[]) ordersDelivered, new OrderComparator(comparator));
		ArrayWorker.showArray(ordersDelivered);
	}

	@Override
	public void showAll() {
		ArrayWorker.showArray(this.orders);
	}

}
