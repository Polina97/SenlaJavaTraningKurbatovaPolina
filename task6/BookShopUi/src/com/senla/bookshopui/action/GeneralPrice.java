package com.senla.bookshopui.action;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class GeneralPrice implements IAction{
	private Integer price;

	@Override
	public void execute() {
		this.price = shop.getPrice();
		Printer.print("Amount of money earned: "+ this.price);
		
	}

}
