package com.senla.bookshopui.controller;


import com.senla.bookshop.config.Config;
import com.senla.bookshop.main.Shop;
import com.senla.bookshopui.api.IBaseController;
import com.senla.bookshopui.connector.ShopConnector;

public class Runner {
	private static String LOG_PROPERTIES = "src/log4j.properties";
	private static ShopConnector connector = ShopConnector.getInstance();
	public static Shop shop = connector.getShop();
	public static IBaseController controller;

	public static void main(String[] args) {
		Config conf = new Config(LOG_PROPERTIES);
		conf.init();
		System.out.println("Welcome to our book shop!");
		controller = new MainMenuController();
		controller.run();
	}


}
