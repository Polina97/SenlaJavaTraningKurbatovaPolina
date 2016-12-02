package com.senla.bookshopui.menu;

import java.util.ArrayList;
import java.util.List;

import com.senla.bookshopui.api.IItem;
import com.senla.bookshopui.api.IMenu;

public class Menu implements IMenu {
	public TypeMenu type;
	public List<IItem> items;

	public Menu(TypeMenu type) {
		this.type = type;
		this.items = new ArrayList<>();
	}
	

	public Menu(TypeMenu type, List<IItem> items) {
		super();
		this.type = type;
		this.items = items;
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
	public List<IItem> getItems() {
		return items;
	}

	@Override
	public void setItems(List<IItem> items) {
		this.items = items;
	}

	@Override
	public void addItem(IItem item) {
		items.add(item);
	}

	@Override
	public void deleteItem(IItem item) {
		items.remove(item);
	}


	@Override
	public void addItems(List<IItem> items) {
			this.items.addAll(items);
		
	}

}
