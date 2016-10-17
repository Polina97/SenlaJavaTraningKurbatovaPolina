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
	private BuyerManager buyerManager = new BuyerManager();;

	public OrderManager() {
		this.orders = FileWorker.readOrders();
		this.generalPrice = getGeneralPrice();
		this.deliveredOrders = getDeliveredOrders();
	}

	public OrderManager(Order[] orders) {
		this();
		for (Order order : orders) {
			this.orders = ArrayWorker.addOrder(order, this.orders);
		}
		this.generalPrice = getGeneralPrice();
		this.deliveredOrders = getDeliveredOrders();
		FileWorker.writeOrders(this.orders);
	}

	public Order[] getOrders() {
		return orders;
	}

	public void setOrders(Order[] orders) {
		this.orders = orders;
	}

	@Override
	public Integer getGeneralPrice() {
		for (Order o : this.orders) {
			this.generalPrice += o.getPrice();
		}
		return generalPrice;
	}

	@Override

	public void setGeneralPrice(Integer generalPrice) {
		this.generalPrice = generalPrice;
	}

	@Override
	public void add(BaseEntity entity) {
		this.orders = ArrayWorker.addOrder((Order) entity, this.orders);
		for (int i = 0; i < this.orders.length; i++) {
			buyerManager.getById(((Order) entity).getBuyer().getId()).addOrder(this.orders[i]);
		}
		FileWorker.writeOrders(this.orders);
	}

	@Override
	public void delete(BaseEntity entity) {
		this.orders = ArrayWorker.deleteOrder((Order) entity, this.orders);
		for (int i = 0; i < this.orders.length; i++) {
			buyerManager.getById(((Order) entity).getBuyer().getId()).deleteOrder(this.orders[i]);
		}
		FileWorker.writeOrders(this.orders);
	}

	@Override
	public void showAllOrders() {
		ArrayWorker.showArray(this.orders);
	}

	@Override
	public void sortDate() {
		Arrays.sort(this.orders, Order.DateComparator);
		ArrayWorker.showArray(this.orders);
		FileWorker.writeOrders(this.orders);

	}

	@Override
	public void sortPrice() {
		Arrays.sort(this.orders, Order.PriceComparator);
		ArrayWorker.showArray(this.orders);
		FileWorker.writeOrders(this.orders);

	}

	@Override
	public void sortStatus() {
		Arrays.sort(this.orders, Order.StatusComparator);
		ArrayWorker.showArray(this.orders);
		FileWorker.writeOrders(this.orders);

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
		for(Order order: this.orders){
			if(order.getStatus().equals(EStatusOrder.DELIVERED)){
				deliveredOrders = ArrayWorker.addOrder(order, this.deliveredOrders);
						
			}
		}
		return deliveredOrders;
	}

}
