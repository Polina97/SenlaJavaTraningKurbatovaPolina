package com.senla.bookshopui.item;

import com.senla.bookshopui.action.BooksPrinter;
import com.senla.bookshopui.api.IBaseItem;

public class BooksPrinterItem implements IBaseItem {
	BooksPrinter printer = new BooksPrinter();
	public String NAME = "Print books.";

	@Override
	public void doAction() {
		System.out.println("Our books: ");
		printer.execute();
	}

	@Override
	public String getName() {
		return NAME;
	}

}
