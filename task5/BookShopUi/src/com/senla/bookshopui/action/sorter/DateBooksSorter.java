package com.senla.bookshopui.action.sorter;

import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class DateBooksSorter implements IAction{
	private String[] books;
	@Override
	public void execute() {
		this.books = shop.sortBooks(TypeBookComparator.DATE);
		Printer.printArray(this.books);
		
	}
	

}
