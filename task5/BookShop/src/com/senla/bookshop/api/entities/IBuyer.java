package com.senla.bookshop.api.entities;

import java.util.List;

public interface IBuyer extends IBaseEntity {

	public void setId(Integer id);

	public String getName();

	public void setName(String name);

	public List<IOrder> getOrders();

	public void setOrders(List<IOrder> orders);

	public void addOrder(IOrder order);

	public void deleteOrder(IOrder order);

}
