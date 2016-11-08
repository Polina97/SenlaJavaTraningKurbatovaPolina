package com.senla.bookshopui.action.scv;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class ExsportBook implements IAction {
	private Integer index;
	@Override
	public void execute() {
		Printer.print("Choise the book.");
		Printer.printArray(shop.getBooks());
		this.index = MyScanner.positive();
		Printer.print(shop.exportBook(index-1));
	}

}
