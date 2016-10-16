package com.senla.bookshop.api.managers;

public interface IOrderManager extends IBaseManager {
	void showAllOrders();

	void sortDate();

	void sortPrice();

	void sortStatus();
}
