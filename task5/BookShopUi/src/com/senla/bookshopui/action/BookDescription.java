package com.senla.bookshopui.action;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class BookDescription implements IAction {
	private Integer index;

	@Override
	public void execute() {
		Printer.print("Choise the book.");
		Printer.printArray(shop.getBooks());
		this.index = MyScanner.positive();
		Printer.print(shop.getDescriptionBook(index-1));
	}

}
