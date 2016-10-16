package com.senla.bookshop.manager;


import java.util.Arrays;

import com.senla.bookshop.api.managers.IOrderManager;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.Order;

public class OrderManager implements IOrderManager{
	private Order[] orders;
	private Integer generalPrice;
	

	public Order[] getOrders() {
		return orders;
	}
	public void setOrders(Order[] orders) {
		this.orders = orders;
	}
	public Integer getGeneralPrice() {
		for(Order o : this.orders){
			this.generalPrice += o.getPrice();
		}
		return generalPrice;
	}
	public void setGeneralPrice(Integer generalPrice) {
		this.generalPrice = generalPrice;
	}
	public OrderManager(){
		this.orders = FileWorker.readOrders();
	}
	public OrderManager(Order[] orders) {
		this.orders = FileWorker.readOrders();
		for (Order order : orders) {
			this.orders = ArrayWorker.addOrder(order, this.orders);
			order.getBuyer().addOrder(order);
		}
		FileWorker.writeOrders(this.orders);
	}

	@Override
	public void add(BaseEntity entity) {
		this.orders = ArrayWorker.addOrder((Order)entity, this.orders);
		((Order)entity).getBuyer().addOrder((Order)entity);
		FileWorker.writeOrders(this.orders);
	}

	@Override
	public void delete(BaseEntity entity) {
		this.orders = ArrayWorker.deleteOrder((Order)entity, this.orders);
		FileWorker.writeOrders(this.orders);
	}
	@Override
	public void showAllOrders(){
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
	

	
}
