package com.senla.bookshopui.action;

import org.apache.log4j.Logger;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class OrderDescription implements IAction {
	private Logger log = Logger.getLogger(OrderDescription.class);
	private Integer index;

	@Override
	public void execute() {
		Printer.print("Choise the order.");
		Printer.printArray(shop.getOrders());
		try {
			this.index = MyScanner.positive();
			Printer.print(shop.getDescriptionOrder(index - 1));
		} catch (Exception e) {
			log.error(e);
			Printer.print(MyScanner.MESSAGE);

		}

	}

}
