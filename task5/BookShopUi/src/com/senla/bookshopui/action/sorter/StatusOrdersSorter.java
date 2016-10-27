package com.senla.bookshopui.action.sorter;

import com.senla.bookshop.comparators.TypeOrderComparator;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class StatusOrdersSorter implements IAction{
	private String[] orders;

	@Override
	public void execute() {
		this.orders = shop.sortOrders(TypeOrderComparator.STATUS);
		Printer.printArray(orders);
	}


}
