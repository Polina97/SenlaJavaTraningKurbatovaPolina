package com.senla.bookshopui.action;

import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class AddToStock implements IAction {
	private Logger log = Logger.getLogger(AddToStock.class);
	private String name;
	private String author;
	private GregorianCalendar calendar;
	private Integer price;

	@Override
	public void execute() {
		Printer.print("Enter name of book: ");
		try {
			this.name = MyScanner.scanString();
			Printer.print("Enter date publication in dd/mm/yyyy:");
			this.calendar = MyScanner.scanDate();
			Printer.print("Enter author: ");
			this.author = MyScanner.scanString();
			Printer.print("Enter price: ");
			this.price = MyScanner.positive();
			Printer.print(shop.addToStock(name, author, calendar, price));
		} catch (Exception e) {
			log.error(e);
			Printer.print(MyScanner.MESSAGE);
		}

	}

}
