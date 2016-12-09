package com.senla.bookshopui.action;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class SubmitApplication implements IAction {
	private Logger log = Logger.getLogger(SubmitApplication.class);
	private Integer index;

	@Override
	public void execute(IClientWorker worker) {
		Printer.print("Choise the book.");
		Printer.printArray(worker.sendToShop("getBooks"));
		try {
			StringBuilder builder = new StringBuilder();
			this.index = MyScanner.positive();
			builder.append("submitApp").append(SLASH).append(index-1);
			Printer.print(worker.sendToShop(builder.toString()));
		} catch (Exception e) {
			log.error(e);
			Printer.print(MyScanner.MESSAGE);

		}

	}

}
