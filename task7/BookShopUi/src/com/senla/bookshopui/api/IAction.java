package com.senla.bookshopui.api;

import com.senla.bookshop.api.shop.IShop;
import com.senla.bookshop.main.Shop;

public interface IAction {

	public static IShop shop = Shop.getShop();

	public void execute();
}
