package com.senla.bookshopui.api;

import com.senla.bookshop.api.client.IClientWorker;

public interface IAction {
	public static final String SLASH = "/";
	public static final String SPLITTER = "&";

	public void execute(IClientWorker worker);
}
