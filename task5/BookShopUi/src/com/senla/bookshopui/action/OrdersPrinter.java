package com.senla.bookshopui.action;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class OrdersPrinter implements IAction {
	String[] orders;

	public OrdersPrinter() {
		this.orders = shop.getOrders();
	}

	@Override
	public void execute() {
		Printer.printArray(orders);
	}
	

}
