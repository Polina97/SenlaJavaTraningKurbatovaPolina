package com.senla.bookshopui.api;

import com.senla.bookshop.api.client.IClientWorker;

public interface IItem {

	public String getName();

	public void doAction(IClientWorker worker);
}
