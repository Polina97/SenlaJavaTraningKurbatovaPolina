package com.senla.bookshopui.action;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class BookDescription implements IAction {
	private Logger log = Logger.getLogger(BookDescription.class);
	private Integer index;

	@Override
	public void execute(IClientWorker worker) {
		Printer.print("Choise the book.");
		Printer.printArray(worker.runShop("getBooks"));
		try {
			this.index = MyScanner.positive();
			StringBuilder builder = new StringBuilder();
			builder.append("getDescriptionBook").append(SLASH).append(index - 1);
			Printer.print(worker.runShop(builder.toString()));
		} catch (Exception e) {
			log.error(e);
			Printer.print(MyScanner.MESSAGE);
		}
	}

}
