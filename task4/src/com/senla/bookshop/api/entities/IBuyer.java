package com.senla.bookshop.api.entities;

public interface IBuyer extends IBaseEntity {
	public Integer getId();

	public void setId(Integer id);

	public String getName();

	public void setName(String name);

	public IOrder[] getOrders();

	public void setOrders(IOrder[] orders);

	public void addOrder(IOrder order);

	public void deleteOrder(IOrder order);

	public void showAllOrders();

}
