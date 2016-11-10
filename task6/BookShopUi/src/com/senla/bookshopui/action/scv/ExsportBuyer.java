package com.senla.bookshopui.action.scv;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class ExsportBuyer implements IAction {
	@Override
	public void execute() {
		Printer.print("Exported buyers.");
		Printer.printArray(shop.exportBuyers());
	}

}
