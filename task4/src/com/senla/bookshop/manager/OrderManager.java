package com.senla.bookshop.manager;

import java.util.Arrays;

import com.senla.bookshop.api.managers.IOrderManager;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.EStatusOrder;
import com.senla.bookshop.entity.Order;
import com.senla.bookshop.resources.ArrayWorker;
import com.senla.bookshop.resources.FileWorker;

public class OrderManager implements IOrderManager {
	private Order[] orders;
	private Order[] deliveredOrders;
	private Integer generalPrice;
	private FileWorker fileWorker;

	public OrderManager(FileWorker fileWorker) {
		this.fileWorker = fileWorker;
		this.orders = fileWorker.readOrders();
		this.deliveredOrders = getDeliveredOrders();
	}

	public OrderManager(Order[] orders, FileWorker fileWorker) {
		this(fileWorker);
		for (Order order : orders) {
			this.orders = ArrayWorker.addOrder(order, this.orders);
		}
		this.deliveredOrders = getDeliveredOrders();
		this.fileWorker.writeOrders(this.orders);
	}

	public Order[] getOrders() {
		return orders;
	}

	public void setOrders(Order[] orders) {
		this.orders = orders;
	}

	@Override
	public Integer getGeneralPrice() {
		int sum = 0;
		for (Order o : this.orders) {
			sum += o.getPrice();
		}
		this.generalPrice = sum;
		return generalPrice;

	}

	public void deliverOrder(Integer id) {
		for (int i = 0; i < this.orders.length; i++) {
			if (orders[i].getId() == id) {
				orders[i].deliverOrder();
			}
		}
		this.fileWorker.writeOrders(this.orders);
	}

	public void cancelOrder(Integer id) {
		for (int i = 0; i < this.orders.length; i++) {
			if (orders[i].getId() == id) {
				orders[i].cancelOrder();
			}
		}
		this.fileWorker.writeOrders(this.orders);
	}

	@Override

	public void setGeneralPrice(Integer generalPrice) {
		this.generalPrice = generalPrice;
	}

	@Override
	public void add(BaseEntity entity) {
		BuyerManager buyerManager = new BuyerManager(this.fileWorker);
		this.orders = ArrayWorker.addOrder((Order) entity, this.orders);
		for (int i = 0; i < this.orders.length; i++) {
			buyerManager.getById(((Order) entity).getBuyer().getId()).addOrder(this.orders[i]);
		}
		this.deliveredOrders = getDeliveredOrders();
		this.fileWorker.writeBuyer(buyerManager.getBuyers());
		this.fileWorker.writeOrders(this.orders);
	}

	@Override
	public void delete(BaseEntity entity) {
		BuyerManager buyerManager = new BuyerManager(this.fileWorker);
		this.orders = ArrayWorker.deleteOrder((Order) entity, this.orders);
		for (int i = 0; i < this.orders.length; i++) {
			buyerManager.getById(((Order) entity).getBuyer().getId()).deleteOrder(this.orders[i]);
		}
		this.generalPrice = getGeneralPrice();
		this.deliveredOrders = getDeliveredOrders();
		this.fileWorker.writeBuyer(buyerManager.getBuyers());
		this.fileWorker.writeOrders(this.orders);
	}

	@Override
	public Order getOrderById(Integer id) {
		for (int i = 0; i < this.orders.length; i++) {
			if (orders[i].getId() == id) {
				return orders[i];
			}
		}
		return null;
	}

	@Override
	public Order[] getDeliveredOrders() {
		for (Order order : this.orders) {
			if (order.getStatus().equals(EStatusOrder.DELIVERED)) {
				deliveredOrders = ArrayWorker.addOrder(order, this.deliveredOrders);

			}
		}
		return deliveredOrders;
	}

	@Override
	public void showAllOrders() {
		ArrayWorker.showArray(this.orders);
	}

	@Override
	public void sortDate() {
		Arrays.sort(this.orders, Order.DateComparator);
		ArrayWorker.showArray(this.orders);
		this.fileWorker.writeOrders(this.orders);

	}

	@Override
	public void sortPrice() {
		Arrays.sort(this.orders, Order.PriceComparator);
		ArrayWorker.showArray(this.orders);
		this.fileWorker.writeOrders(this.orders);

	}

	@Override
	public void sortStatus() {
		Arrays.sort(this.orders, Order.StatusComparator);
		ArrayWorker.showArray(this.orders);
		this.fileWorker.writeOrders(this.orders);

	}

	@Override
	public void sortDateDelivered() {
		Arrays.sort(this.deliveredOrders, Order.DateComparator);
		ArrayWorker.showArray(this.deliveredOrders);
	}

	@Override
	public void sortPriceDelivered() {
		Arrays.sort(this.deliveredOrders, Order.PriceComparator);
		ArrayWorker.showArray(this.deliveredOrders);
	}

}
