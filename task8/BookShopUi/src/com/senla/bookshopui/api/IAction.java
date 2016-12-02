package com.senla.bookshopui.api;

import com.senla.bookshop.api.shop.IShop;
import com.senla.bookshop.client.ClientThread;
import com.senla.bookshop.di.DIBookShop;

public interface IAction {
	public static final String SLASH = "/";
	public static IShop shop = (IShop) DIBookShop.load(IShop.class.getName(), false);
	public void execute(ClientThread thread);
}
