package com.senla.bookshopui.controller;

import com.senla.bookshopui.api.IBaseController;

public class Runner {
	public static IBaseController controller;

	public static void main(String[] args) {
		System.out.println("Welcome to our book shop!");
		controller = new MenuController();
		controller.run();
	}


}
