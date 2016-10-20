package com.senla.bookshop.api.managers;

import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.comparators.TypeOrderComparator;

public interface IOrderManager extends IBaseManager {
	public IOrder[] getOrders();

	public Integer getGeneralPrice();

	public IOrder getOrderById(Integer id);

	public IOrder[] getDeliveredOrders();

	public void deliverOrder(Integer id);

	public void cancelOrder(Integer id);

	public void sortOrders(TypeOrderComparator comparator);

	public void sortDeliveredOrders(TypeOrderComparator comparator);
}
