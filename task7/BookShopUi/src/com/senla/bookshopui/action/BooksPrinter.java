package com.senla.bookshopui.action;


import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class BooksPrinter implements IAction {

	public BooksPrinter() {
	}

	@Override
	public void execute() {
		
		Printer.printArray(shop.getBooks());
	}

}
