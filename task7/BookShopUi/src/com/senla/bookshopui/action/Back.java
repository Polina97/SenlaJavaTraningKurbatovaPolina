package com.senla.bookshopui.action;

import org.apache.log4j.Logger;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.api.IMenu;
import com.senla.bookshopui.controller.MenuController;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class Back implements IAction {
	private Logger log = Logger.getLogger(Back.class);
	private IMenu mainMenu;

	public Back(IMenu mainMenu) {
		super();
		this.mainMenu = mainMenu;
	}

	@Override
	public void execute() {
		MenuController.navigator.setCurrentMenu(mainMenu);
		MenuController.navigator.printMenu();
		try {
			int index = MyScanner.positive();
			MenuController.navigator.navigate(index);
		} catch (Exception e) {
			log.error(e);
			Printer.print(MyScanner.MESSAGE);
		}

	}

}
