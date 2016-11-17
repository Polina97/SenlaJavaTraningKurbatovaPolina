package com.senla.bookshopui.action.scv;

import org.apache.log4j.Logger;

import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class ImportBuyer implements IAction {
	private Logger log = Logger.getLogger(ImportBuyer.class);
	private Integer index;

	@Override
	public void execute() {
		Printer.print("Choise the buyer.");
		Printer.printArray(shop.getBuyers());
		try {
			this.index = MyScanner.positive();
			Printer.print(shop.importBuyer(index - 1));
		} catch (Exception e) {
			log.error(e);
			Printer.print(MyScanner.MESSAGE);

		}
	}
}
