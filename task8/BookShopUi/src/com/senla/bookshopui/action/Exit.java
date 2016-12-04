package com.senla.bookshopui.action;

import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class Exit implements IAction{

	@Override
	public void execute(IClientWorker worker) {
		worker.runShop("exit");
		Printer.print("Au revoir!");
		MyScanner.setIsNext(false);
	}
	

}
