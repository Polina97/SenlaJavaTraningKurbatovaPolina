package com.senla.bookshopui.api;

import com.senla.bookshop.api.shop.IShop;
import com.senla.bookshopui.connector.ShopConnector;

public interface IAction {
	public static ShopConnector connector = ShopConnector.getInstance();
	public static IShop shop = connector.getShop(); 

	public void execute();
}
