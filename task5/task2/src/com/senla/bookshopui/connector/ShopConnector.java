package com.senla.bookshopui.connector;

import com.senla.bookshop.main.Shop;

public class ShopConnector {
	private static ShopConnector shopConnector;
	private Shop shop;

	public ShopConnector() {
		shop = new Shop();
	}
	public Shop getShop(){
		return shop;
	}
	public static ShopConnector getInstance(){
		if(shopConnector == null){
			shopConnector = new ShopConnector();
		}
		return shopConnector;
	}

}
