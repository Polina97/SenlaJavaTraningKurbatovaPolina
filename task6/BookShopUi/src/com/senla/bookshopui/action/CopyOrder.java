package com.senla.bookshopui.action;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class CopyOrder implements IAction {
	private Integer index;

	@Override
	public void execute() {
		Printer.print("Choise the order.");
		Printer.printArray(shop.getOrders());
		this.index = MyScanner.positive();
		Printer.print(shop.copyOrder(index - 1));
	}

}
