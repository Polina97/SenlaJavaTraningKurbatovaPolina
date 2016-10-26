package com.senla.bookshopui.action;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class BooksPrinter implements IAction {
	String[] books;

	public BooksPrinter() {
		this.books = shop.getBooks();
	}

	@Override
	public void execute() {
		Printer.printArray(this.books);
	}

}
