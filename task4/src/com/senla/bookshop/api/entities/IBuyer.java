package com.senla.bookshop.api.entities;

import com.senla.bookshop.entity.Order;

public interface IBuyer extends IBaseEntity {
	Integer getId();

	void setId(Integer id);

	String getName();

	void setName(String name);

	IOrder[] getOrders();

	void setOrders(IOrder[] orders);

	void addOrder(IOrder order);

	void deleteOrder(IOrder order);

	void showAllOrders();

}
