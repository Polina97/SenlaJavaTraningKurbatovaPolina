package com.senla.bookshopui.item;


import com.senla.bookshop.client.ClientThread;
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
	public void doAction(ClientThread thread) {
		action.execute(thread);
	}

	@Override
	public String getName() {
		return name;
	}


}
