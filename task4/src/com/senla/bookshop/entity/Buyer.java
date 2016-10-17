package com.senla.bookshop.entity;

import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.manager.OrderManager;
import com.senla.bookshop.resources.ArrayWorker;

public class Buyer extends BaseEntity implements IBuyer {
	private Integer id;
	private String name;
	private Order[] orders;
	private OrderManager orderManager;


	public Buyer(Integer id, String name){
		this.id = id;
		this.name = name;
		this.orders = new Order[0];
	}

	public Buyer(String description) {
		createEntity(description);
	}

	public Buyer(String name, Order[] orders) {
		this(name);
		this.orders = orders;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		String[] stringBuyer = description.split(SLASH);
		int j = 0;
		this.id = Integer.parseInt(stringBuyer[j++]);
		this.name = stringBuyer[j++];
		this.orders = new Order[Integer.parseInt(stringBuyer[j++])];
		orderManager = new OrderManager();
		for (int i = 0; i < this.orders.length; i++) {
			this.orders[i] = orderManager.getOrderById(Integer.parseInt(stringBuyer[j++]));
		}

	}

	@Override
	public boolean equals(Object obj) {
		return this.id == ((Buyer) obj).getId();
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append(this.id).append(SLASH).append(this.name).append(SLASH).append(this.orders.length).append(SLASH);
		for (int i = 0; i < this.orders.length; i++) {
			stb.append(this.orders[i].getId()).append(SLASH);
		}
		return stb.toString();
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

	@Override
	public String getDescription() {
		StringBuilder str = new StringBuilder();
		return str.append(" Name: ").append(this.name).append(" Number of orders: ").append(this.orders.length)
				.toString();
	}

}
