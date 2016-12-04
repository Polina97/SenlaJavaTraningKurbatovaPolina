package com.senla.bookshopui.item;


import com.senla.bookshop.api.client.IClientWorker;
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
	public void doAction(IClientWorker worker) {
		action.execute(worker);
	}

	@Override
	public String getName() {
		return name;
	}


}
