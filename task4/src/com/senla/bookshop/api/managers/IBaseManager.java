package com.senla.bookshop.api.managers;

import com.senla.bookshop.entity.BaseEntity;

public interface IBaseManager {

	public void add(BaseEntity entity);

	public void delete(BaseEntity entity);

	public void showAll();

}
