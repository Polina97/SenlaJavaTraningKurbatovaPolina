package com.senla.bookshopui.action.sorter;

import java.util.List;

import com.senla.bookshop.typecomparator.TypeOrderComparator;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class DateOrdersSorter implements IAction{
	private List<String> orders;

	@Override
	public void execute() {
		this.orders = shop.sortOrders(TypeOrderComparator.DATE);
		Printer.printArray(orders);
	}

}
