package com.senla.bookshop.entity;

import com.senla.bookshop.api.entity.IBuyer;
import com.senla.bookshop.api.entity.IOrder;
import com.senla.bookshop.idgenerator.IdGenerator;
import com.senla.bookshop.idgenerator.TypeId;

public class Buyer extends BaseEntity implements IBuyer, Cloneable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private IOrder order;

	public Buyer() {
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
		buyer.setId(IdGenerator.getId(TypeId.BUYER));
		buyer.setOrder(null);
		return buyer;
	}

}
