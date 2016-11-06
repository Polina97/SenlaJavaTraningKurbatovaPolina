package com.senla.bookshop.entity;

import java.util.List;

import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.entities.IOrder;

public class Buyer extends BaseEntity implements IBuyer {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private List<IOrder> orders;

	public Buyer(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Buyer(Integer id, String name, List<IOrder> orders) {
		this(id, name);
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
	public List<IOrder> getOrders() {
		return orders;
	}

	@Override
	public void setOrders(List<IOrder> orders) {
		this.orders = orders;
	}

	@Override
	public void addOrder(IOrder order) {
		orders.add(order);
	}

	@Override
	public void deleteOrder(IOrder order) {
		orders.remove(order);
	}

	@Override
	public String getDescription() {
		StringBuilder str = new StringBuilder();
		return str.append(" Name: ").append(name).append(" Number of orders: ").append(orders.size())
				.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return this.id == ((Buyer) obj).getId();
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append(id).append(SLASH).append(name).append(SLASH);
		if (orders != null) {
			stb.append(orders.size()).append(SLASH);
			for (IOrder order : orders) {
				stb.append(order.getId()).append(SLASH);
			}
		} else {
			stb.append(0).append(SLASH);
		}
		return stb.toString();
	}

}
