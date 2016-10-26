package com.senla.bookshopui.action.sorter;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class OldBooksPrinter implements IAction{
	String[] books;

	public OldBooksPrinter() {
		this.books = shop.getOldBooks();
	}

	@Override
	public void execute() {
		Printer.printArray(this.books);
	}
}
