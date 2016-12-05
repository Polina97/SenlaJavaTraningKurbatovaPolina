package com.senla.bookshopui.navigator;

import org.apache.log4j.Logger;

import com.senla.bookshopui.api.INavigator;
import com.senla.bookshopui.resources.Printer;
import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshopui.api.IMenu;

public class Navigator implements INavigator {
	private static final String ERROR = "Sorry! An error has occurred!";
	private static Logger log = Logger.getLogger(Navigator.class.getName());
	private IClientWorker clientWorker;
	public IMenu currentMenu;

	public Navigator(IMenu currentMenu, IClientWorker clientWorker) {
		this.currentMenu = currentMenu;
		this.clientWorker = clientWorker;
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
			this.currentMenu.getItems().get(index - 1).doAction(clientWorker);
		} catch (Exception e) {
			log.error(e);
			Printer.print(ERROR);
		}
	}

}
