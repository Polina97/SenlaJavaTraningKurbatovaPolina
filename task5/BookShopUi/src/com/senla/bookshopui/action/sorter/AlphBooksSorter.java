package com.senla.bookshopui.action.sorter;

import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class AlphBooksSorter implements IAction{

	private String[] books;
	@Override
	public void execute() {
		books = shop.sortOldBooks(TypeBookComparator.ALPHABET);
		for (String book : books) {
			Printer.print(book);
		}
		
	}

}
