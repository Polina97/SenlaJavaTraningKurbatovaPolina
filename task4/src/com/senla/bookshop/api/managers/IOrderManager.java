package com.senla.bookshop.api.managers;

import com.senla.bookshop.api.entities.IOrder;

public interface IOrderManager extends IBaseManager {
	IOrder[] getOrders();

	void showAllOrders();

	Integer getGeneralPrice();

	void setGeneralPrice(Integer generalPrice);

	void sortDate();

	void sortPrice();

	void sortStatus();

	IOrder getOrderById(Integer id);

	IOrder[] getDeliveredOrders();

	void sortDateDelivered();

	void sortPriceDelivered();
}
