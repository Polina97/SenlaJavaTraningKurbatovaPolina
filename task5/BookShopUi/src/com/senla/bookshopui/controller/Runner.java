package com.senla.bookshopui.controller;


import com.senla.bookshop.config.Config;
import com.senla.bookshopui.api.IBaseController;

public class Runner {
	private static String LOG_PROPERTIES = "src/log4j.properties";
	public static IBaseController controller;

	public static void main(String[] args) {
		Config conf = new Config(LOG_PROPERTIES);
		conf.init();
		System.out.println("Welcome to our book shop!");
		controller = new MenuController();
		controller.run();
	}


}
