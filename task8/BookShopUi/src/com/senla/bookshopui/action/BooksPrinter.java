package com.senla.bookshopui.action;


import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class BooksPrinter implements IAction {

	@Override
	public void execute(IClientWorker worker) {

		Printer.printArray(worker.runShop("getBooks"));
	}

}
