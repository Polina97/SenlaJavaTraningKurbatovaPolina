package com.senla.bookshopui.navigator;

import org.apache.log4j.Logger;

import com.senla.bookshop.main.Messages;
import com.senla.bookshopui.api.INavigator;
import com.senla.bookshopui.resources.Printer;
import com.senla.bookshopui.api.IMenu;

public class Navigator implements INavigator {
	private static Logger log = Logger.getLogger(Navigator.class.getName());
	public IMenu currentMenu;

	public Navigator(IMenu currentMenu) {
		this.currentMenu = currentMenu;
	}

	public IMenu getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(IMenu currentMenu) {
		this.currentMenu = currentMenu;
	}

	@Override
	public void printMenu() {
		Printer.print("Select an action.");
		Printer.printItems(currentMenu.getItems());
	}

	@Override
	public void navigate(Integer index) {
		try {
			this.currentMenu.getItems().get(index - 1).doAction();
		} catch (Exception e) {
			log.error(e);
			Printer.print(Messages.ERROR);
		}
	}

}
