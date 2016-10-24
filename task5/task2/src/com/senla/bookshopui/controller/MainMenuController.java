package com.senla.bookshopui.controller;

import com.senla.bookshopui.api.IBaseBuilder;
import com.senla.bookshopui.api.IBaseController;
import com.senla.bookshopui.api.IBaseNavigator;
import com.senla.bookshopui.builder.MainBuilder;
import com.senla.bookshopui.navigator.Navigator;
import com.senla.bookshopui.resources.MyScanner;

public class MainMenuController implements IBaseController {
	public IBaseNavigator navigator;
	public IBaseBuilder builder;
	public int index;
	

	public MainMenuController() {
		this.builder = new MainBuilder();
	}


	@Override
	public void run() {
		this.builder.buildMenu();
		this.navigator = new Navigator(this.builder.getRootMenu());
		this.navigator.printMenu();
		this.index = MyScanner.positive();
		this.navigator.navigate(index);	
	}

}
