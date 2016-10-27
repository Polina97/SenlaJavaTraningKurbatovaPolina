package com.senla.bookshopui.action.sorter;

import com.senla.bookshop.comparators.TypeOrderComparator;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class DateOrdersSorter implements IAction{
	private String[] orders;

	@Override
	public void execute() {
		this.orders = shop.sortOrders(TypeOrderComparator.DATE);
		Printer.printArray(orders);
	}

}
