package com.senla.bookshopui.action.sorter;

import java.util.List;

import com.senla.bookshop.typecomparator.TypeBookComparator;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class DateOldBooksSorter implements IAction{
	private List<String> books;
	@Override
	public void execute() {
		this.books = shop.sortOldBooks(TypeBookComparator.DATE);
		Printer.printArray(this.books);
		
	}

}
