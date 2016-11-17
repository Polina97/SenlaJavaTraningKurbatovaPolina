package com.senla.bookshop.api.entity;

public interface IBuyer extends IBaseEntity {

	public void setId(Integer id);

	public String getName();

	public void setName(String name);

	public IOrder getOrder();

	public void setOrder(IOrder order);

	public IBuyer clone() throws CloneNotSupportedException;

}
