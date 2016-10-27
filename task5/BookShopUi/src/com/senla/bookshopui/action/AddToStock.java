package com.senla.bookshopui.action;

import java.util.GregorianCalendar;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class AddToStock implements IAction {
	private String name;
	private String author;
	private GregorianCalendar calendar;
	private Integer price;

	@Override
	public void execute() {
		Printer.print("Enter name of book: ");
		this.name = MyScanner.scanString();
		Printer.print("Enter author: ");
		this.author = MyScanner.scanString();
		Printer.print("Enter date publication in dd/mm/yyyy:");
		this.calendar = MyScanner.scanDate();
		Printer.print("Enter price: ");
		this.price = MyScanner.positive();
		Printer.print(shop.addToStock(name, author, calendar, price));

	}

}
