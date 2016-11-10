package com.senla.bookshopui.action.scv;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class ExsportBook implements IAction {
	@Override
	public void execute() {
		Printer.print("Exported books.");
		Printer.printArray(shop.exportBooks());
	}

}
