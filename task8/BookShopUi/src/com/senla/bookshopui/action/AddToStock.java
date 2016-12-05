package com.senla.bookshopui.action;


import org.apache.log4j.Logger;

import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class AddToStock implements IAction {
	private Logger log = Logger.getLogger(AddToStock.class);

	@Override
	public void execute(IClientWorker worker) {
		Printer.print("Enter name of book: ");
		StringBuilder builder = new StringBuilder();
		try {
			String name = MyScanner.scanString();
			Printer.print("Enter date publication in dd.mm.yyyy");
			String calendar = MyScanner.scanString();
			Printer.print("Enter author: ");
			String author = MyScanner.scanString();
			Printer.print("Enter price: ");
			Integer price = MyScanner.positive();
			builder.append("addToStock").append(SLASH).append(name).append(SLASH).append(author).append(SLASH).append(calendar).append(SLASH).append(price);
			Printer.print(worker.runShop(builder.toString()));
		} catch (Exception e) {
			log.error(e);
			Printer.print(MyScanner.MESSAGE);
		}

	}

}
