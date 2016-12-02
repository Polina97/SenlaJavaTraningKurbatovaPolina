package com.senla.bookshopui.api;

import com.senla.bookshop.client.ClientThread;

public interface IItem {

	public String getName();

	public void doAction(ClientThread thread);
}
