package com.senla.bookshopui.action;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.api.IMenu;
import com.senla.bookshopui.controller.MenuController;
import com.senla.bookshopui.resources.MyScanner;

public class SubmenuCreater implements IAction{
	IMenu menu;
	


	public SubmenuCreater(IMenu menu) {
		super();
		this.menu = menu;
	}


	@Override
	public void execute() {
		MenuController.navigator.setCurrentMenu(menu);
		MenuController.navigator.printMenu();
		Integer index = MyScanner.scanInt();
		MenuController.navigator.navigate(index);
	}

}
