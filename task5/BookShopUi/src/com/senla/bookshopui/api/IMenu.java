package com.senla.bookshopui.api;

import java.util.List;

import com.senla.bookshopui.menu.TypeMenu;

public interface IMenu {
	public TypeMenu getType();

	public void setType(TypeMenu type);

	public List<IItem> getItems();

	public void setItems(List<IItem> items);

	public void addItem(IItem item);

	public void addItems(List<IItem> items);

	public void deleteItem(IItem item);
}
