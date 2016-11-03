package com.senla.bookshopui.action.sorter;

import java.util.List;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class DelOrdersPrinter implements IAction{
	private List<String> orders;

	public DelOrdersPrinter() {
		this.orders = shop.getDeliveredOrders();
	}

	@Override
	public void execute() {
		Printer.printArray(orders);
	}
	
}
