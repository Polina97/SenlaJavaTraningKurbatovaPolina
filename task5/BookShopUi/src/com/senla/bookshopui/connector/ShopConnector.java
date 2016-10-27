package com.senla.bookshopui.connector;

import com.senla.bookshop.api.shop.IShop;
import com.senla.bookshop.main.Shop;

public class ShopConnector {
	private static ShopConnector shopConnector;
	private IShop shop;

	public ShopConnector() {
		shop = new Shop();
	}
	public IShop getShop(){
		return shop;
	}
	public static ShopConnector getInstance(){
		if(shopConnector == null){
			shopConnector = new ShopConnector();
		}
		return shopConnector;
	}

}
