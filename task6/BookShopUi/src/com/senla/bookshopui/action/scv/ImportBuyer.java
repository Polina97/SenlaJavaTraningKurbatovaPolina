package com.senla.bookshopui.action.scv;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class ImportBuyer implements IAction {
	private Integer index;
	@Override
	public void execute() {
		Printer.print("Choise the buyer.");
		Printer.printArray(shop.getBuyers());
		this.index = MyScanner.positive();
		Printer.print(shop.importBuyer(index-1));
	}
}
