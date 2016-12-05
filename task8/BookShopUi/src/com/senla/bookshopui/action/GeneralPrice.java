package com.senla.bookshopui.action;

import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class GeneralPrice implements IAction{
	@Override
	public void execute(IClientWorker worker) {
		Printer.print("Amount of money earned: "+ worker.runShop("getPrice"));
		
	}

}
