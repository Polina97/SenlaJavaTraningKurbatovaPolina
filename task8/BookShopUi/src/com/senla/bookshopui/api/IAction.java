package com.senla.bookshopui.api;

import com.senla.bookshop.api.client.IClientWorker;

public interface IAction {
	public static final String SLASH = "/";

	public void execute(IClientWorker worker);
}
