package com.senla.bookshop.api.manager;

import java.util.List;

import com.senla.bookshop.api.entity.IOrder;
import com.senla.bookshop.api.entity.StatusOrder;
import com.senla.bookshop.typecomparator.TypeOrderComparator;

public interface IOrderManager extends IBaseManager {
	public List<IOrder> getOrders();

	public Integer getGeneralPrice();

	public IOrder getOrderById(Integer id);

	public List<IOrder> getDeliveredOrders();

	public void changeStatus(Integer id, StatusOrder statusOrder) throws Exception;

	public List<IOrder> sortOrders(TypeOrderComparator comparator);

	public List<IOrder> sortDeliveredOrders(TypeOrderComparator comparator);

	public void cloneOrder(Integer id) throws Exception;

	public List<IOrder> exportOrders();

	public IOrder importOrder(Integer id) throws Exception;

}
