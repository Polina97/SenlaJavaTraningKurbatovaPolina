package com.senla.bookshopui.action.scv;

import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class ExsportOrder implements IAction {
	@Override
	public void execute(IClientWorker worker) {
		Printer.print("Exported orders.");
		Printer.printArray(worker.sendToShop("exportOrders"));
	}

}
