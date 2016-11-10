package com.senla.bookshop.entity;

import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.resources.IdGenerator;
import com.senla.bookshop.resources.TypeId;

public class Buyer extends BaseEntity implements IBuyer, Cloneable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private IOrder order;

	public Buyer(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Buyer(Integer id, String name, IOrder orders) {
		this(id, name);
		this.order = orders;
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
	public IOrder getOrder() {
		return order;
	}

	@Override
	public void setOrder(IOrder order) {
		this.order = order;
	}

	@Override
	public String getDescription() {
		StringBuilder str = new StringBuilder();
		return str.append(" Name: ").append(name).append(" Number of orders: ").append(order.getId()).toString();
	}

	@Override
	public boolean equals(Object obj) {
		return this.id == ((Buyer) obj).getId();
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append(id).append(SPLITTER).append(name).append(SPLITTER);
		if (order != null) {
			stb.append(order.getId());
		} else {
			stb.append(0);
		}
		return stb.toString();
	}

	@Override
	public IBuyer clone() throws CloneNotSupportedException {
		IBuyer buyer = (IBuyer) super.clone();
		buyer.setId(IdGenerator.getId(TypeId.BUYER) + 1);
		buyer.setOrder(null);
		return buyer;
	}

}
