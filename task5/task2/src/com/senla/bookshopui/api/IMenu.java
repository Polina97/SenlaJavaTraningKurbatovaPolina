package com.senla.bookshopui.api;

import java.util.List;

import com.senla.bookshopui.menu.TypeMenu;

public interface IMenu {
	public TypeMenu getType();

	public void setType(TypeMenu type);

	public List<IBaseItem> getItems();

	public void setItems(List<IBaseItem> items);

	public void addItem(IBaseItem baseItem);

	public void deleteItem(IBaseItem baseItem);
}
