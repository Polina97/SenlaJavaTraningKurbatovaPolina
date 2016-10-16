package com.senla.bookshop.api.entities;

import com.senla.bookshop.entity.Order;

public interface IBuyer extends IBaseEntity {

	String getName();

	void setName(String name);

	Order[] getOrders();

	void setOrders(Order[] orders);

	void addOrder(Order order);

	void deleteOrder(Order order);

	void showAllOrders();

}
