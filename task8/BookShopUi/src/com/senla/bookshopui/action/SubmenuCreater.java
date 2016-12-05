package com.senla.bookshopui.action;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.api.IMenu;
import com.senla.bookshopui.controller.MenuController;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class SubmenuCreater implements IAction {
	private Logger log = Logger.getLogger(SubmenuCreater.class);
	private IMenu menu;

	public SubmenuCreater(IMenu menu) {
		super();
		this.menu = menu;
	}

	@Override
	public void execute(IClientWorker worker) {
		MenuController.navigator.setCurrentMenu(menu);
		MenuController.navigator.printMenu();
		try {
			Integer index = MyScanner.scanInt();
			MenuController.navigator.navigate(index);
		} catch (Exception e) {
			log.error(e);
			Printer.print(MyScanner.MESSAGE);

		}
	}

}
