package com.senla.bookshopui.menu;

import java.util.ArrayList;
import java.util.List;

import com.senla.bookshopui.api.IBaseItem;
import com.senla.bookshopui.api.IMenu;

public class Menu implements IMenu {
	public TypeMenu type;
	public List<IBaseItem> items;

	public Menu(TypeMenu type) {
		this.type = type;
		this.items = new ArrayList<IBaseItem>();
	}

	@Override
	public TypeMenu getType() {
		return type;
	}

	@Override
	public void setType(TypeMenu type) {
		this.type = type;
	}

	@Override
	public List<IBaseItem> getItems() {
		return items;
	}

	@Override
	public void setItems(List<IBaseItem> items) {
		this.items = items;
	}

	@Override
	public void addItem(IBaseItem baseItem) {
		items.add(baseItem);
	}

	@Override
	public void deleteItem(IBaseItem baseItem) {
		items.remove(baseItem);
	}

}
