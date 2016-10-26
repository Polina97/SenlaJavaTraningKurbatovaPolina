package com.senla.bookshopui.item;


import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.api.IItem;

public class Item implements IItem {
	public IAction action;
	public String name;
	

	public Item(IAction action, String name) {
		this.action = action;
		this.name = name;
	}

	@Override
	public void doAction() {
		action.execute();
	}

	@Override
	public String getName() {
		return name;
	}


}
