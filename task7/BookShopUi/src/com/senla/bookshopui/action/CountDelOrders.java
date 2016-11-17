package com.senla.bookshopui.action;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class CountDelOrders implements IAction{
	private Integer count;

	@Override
	public void execute() {
		this.count = shop.countOrders();
		Printer.print("The number of delivered orders: "+this.count);
		
	}
	

}
