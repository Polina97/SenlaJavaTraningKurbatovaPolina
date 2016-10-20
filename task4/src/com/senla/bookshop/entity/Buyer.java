package com.senla.bookshop.entity;

import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.resources.ArrayWorker;

public class Buyer extends BaseEntity implements IBuyer {
	private Integer id;
	private String name;
	private IOrder[] orders;

	public Buyer(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Buyer(Integer id, String name, IOrder[] orders) {
		this.id = id;
		this.name = name;
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
	public IOrder[] getOrders() {
		return orders;
	}

	@Override
	public void setOrders(IOrder[] orders) {
		this.orders = orders;
	}

	@Override
	public void addOrder(IOrder order) {
		this.orders = ArrayWorker.addOrder(order, this.orders);
	}

	@Override
	public void deleteOrder(IOrder order) {
		this.orders = ArrayWorker.deleteOrder(order, this.orders);

	}

	@Override
	public void showAllOrders() {
		ArrayWorker.showArray(this.orders);

	}
	private Integer getCountOrders(){
		int counter =0;
		for(IOrder o: this.orders ){
			if(o!=null){
				counter++;
			}
		}
		return counter;
	}

	@Override
	public String getDescription() {
		StringBuilder str = new StringBuilder();
		return str.append(" Name: ").append(this.name).append(" Number of orders: ").append(this.orders.length)
				.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return this.id == ((Buyer) obj).getId();
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append(this.id).append(SLASH).append(this.name).append(SLASH);
		if (this.orders != null) {
			stb.append(getCountOrders()).append(SLASH);
			for (int i = 0; i < this.orders.length; i++) {
				if (this.orders[i] != null) {
					stb.append(this.orders[i].getId()).append(SLASH);
				}
			}
		} else {
			stb.append(0).append(SLASH);
		}
		return stb.toString();
	}

}
