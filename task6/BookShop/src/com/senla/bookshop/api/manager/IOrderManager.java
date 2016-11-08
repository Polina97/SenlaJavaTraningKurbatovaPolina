package com.senla.bookshop.api.manager;

import java.util.List;

import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.comparators.TypeOrderComparator;
import com.senla.bookshop.entity.StatusOrder;

public interface IOrderManager extends IBaseManager {
	public List<IOrder> getOrders();

	public Integer getGeneralPrice();

	public IOrder getOrderById(Integer id);

	public List<IOrder> getDeliveredOrders();

	public void changeStatus(Integer id, StatusOrder statusOrder) throws Exception;

	public List<IOrder> sortOrders(TypeOrderComparator comparator);

	public List<IOrder> sortDeliveredOrders(TypeOrderComparator comparator);

	public void cloneOrder(Integer id) throws Exception;

	public IOrder exportOrder(Integer id);

	public void importOrder(Integer id) throws Exception;

}
