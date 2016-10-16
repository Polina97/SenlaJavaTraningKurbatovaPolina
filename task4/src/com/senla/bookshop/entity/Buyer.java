package com.senla.bookshop.entity;

import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.manager.ArrayWorker;

public class Buyer extends BaseEntity implements IBuyer {
	private String name;
	private Order[] orders;

	public Buyer(String name) {
		createEntity(name);
	}

	public Buyer(String name, Order[] orders) {
		this(name);
		this.orders = orders;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Order[] getOrders() {
		return orders;
	}

	@Override
	public void setOrders(Order[] orders) {
		this.orders = orders;
	}

	@Override
	public void createEntity(String description) {
		this.name = description;

	}

	@Override
	public boolean equals(Object obj) {
		return this.name == ((Buyer) obj).getName();
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public void addOrder(Order order) {
		this.orders = ArrayWorker.addOrder(order, this.orders);
	}

	@Override
	public void deleteOrder(Order order) {
		this.orders = ArrayWorker.deleteOrder(order, this.orders);
		
	}

	@Override
	public void showAllOrders() {
		ArrayWorker.showArray(this.orders);
		
	}

	
}
