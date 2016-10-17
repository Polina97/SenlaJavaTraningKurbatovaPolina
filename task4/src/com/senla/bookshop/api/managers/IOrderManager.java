package com.senla.bookshop.api.managers;

import com.senla.bookshop.entity.Order;

public interface IOrderManager extends IBaseManager {
	void showAllOrders();

	Integer getGeneralPrice();

	void setGeneralPrice(Integer generalPrice);

	void sortDate();

	void sortPrice();

	void sortStatus();

	Order getOrderById(Integer id);

	Order[] getDeliveredOrders();

	void sortDateDelivered();

	void sortPriceDelivered();
}
