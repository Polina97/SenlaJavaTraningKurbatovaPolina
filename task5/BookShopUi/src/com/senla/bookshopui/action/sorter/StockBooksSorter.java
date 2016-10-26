package com.senla.bookshopui.action.sorter;

import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class StockBooksSorter implements IAction{
	private String[] books;

	@Override
	public void execute() {
		this.books = shop.sortBooks(TypeBookComparator.STOCK);
		Printer.printArray(this.books);
	}

}
