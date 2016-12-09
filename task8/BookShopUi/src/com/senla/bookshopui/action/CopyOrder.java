package com.senla.bookshopui.action;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class CopyOrder implements IAction {
	private Logger log = Logger.getLogger(CopyOrder.class);
	private Integer index;

	@Override
	public void execute(IClientWorker worker) {
		Printer.print("Choise the order.");
		Printer.printArray(worker.sendToShop("getOrders"));
		try {
			StringBuilder builder = new StringBuilder();
			this.index = MyScanner.positive();
			builder.append("copyOrder").append(SLASH).append(index-1);
			Printer.print(worker.sendToShop(builder.toString()));
		} catch (Exception e) {
			log.error(e);
			Printer.print(MyScanner.MESSAGE);

		}
	}

}
