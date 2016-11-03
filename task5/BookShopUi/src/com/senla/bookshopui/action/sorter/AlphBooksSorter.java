package com.senla.bookshopui.action.sorter;

import java.util.List;

import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class AlphBooksSorter implements IAction{

	private List<String> books;
	@Override
	public void execute() {
		books = shop.sortOldBooks(TypeBookComparator.ALPHABET);
		for (String book : books) {
			Printer.print(book);
		}
		
	}

}
