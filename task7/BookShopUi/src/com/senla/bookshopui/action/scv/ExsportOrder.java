package com.senla.bookshopui.action.scv;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class ExsportOrder implements IAction {
	@Override
	public void execute() {
		Printer.print("Exported orders.");
		Printer.printArray(shop.exportOrders());

	}

}
