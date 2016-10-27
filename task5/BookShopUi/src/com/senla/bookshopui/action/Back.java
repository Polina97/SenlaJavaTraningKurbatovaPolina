package com.senla.bookshopui.action;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.api.IMenu;
import com.senla.bookshopui.controller.MenuController;
import com.senla.bookshopui.resources.MyScanner;

public class Back implements IAction{
	IMenu mainMenu;
	

	public Back(IMenu mainMenu) {
		super();
		this.mainMenu = mainMenu;
	}


	@Override
	public void execute() {
		MenuController.navigator.setCurrentMenu(mainMenu);
		MenuController.navigator.printMenu();
		int index = MyScanner.positive();
		MenuController.navigator.navigate(index);
		
	}
	

}
