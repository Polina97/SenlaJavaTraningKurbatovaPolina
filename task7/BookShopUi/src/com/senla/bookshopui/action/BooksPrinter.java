package com.senla.bookshopui.action;

import java.util.List;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class BooksPrinter implements IAction {
	List<String> books;

	public BooksPrinter() {
		this.books = shop.getBooks();
	}

	@Override
	public void execute() {
		Printer.printArray(this.books);
	}

}
