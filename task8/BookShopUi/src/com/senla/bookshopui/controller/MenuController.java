package com.senla.bookshopui.controller;


import com.senla.bookshop.client.ClientThread;
import com.senla.bookshopui.api.IBaseBuilder;
import com.senla.bookshopui.api.IBaseController;
import com.senla.bookshopui.api.INavigator;
import com.senla.bookshopui.builder.Builder;
import com.senla.bookshopui.navigator.Navigator;
import com.senla.bookshopui.resources.MyScanner;

public class MenuController implements IBaseController {
	public static INavigator navigator;
	public IBaseBuilder builder;
	public int index;

	public MenuController() {
		this.builder = new Builder();
	}

	@Override
	public void run(ClientThread thread) throws Exception {
		this.builder.buildMenu();
		navigator = new Navigator(this.builder.getRootMenu(),thread);
		navigator.printMenu();
		while (MyScanner.isNext()) {
			index = MyScanner.positive();
			navigator.navigate(index);
		}
	}

}
