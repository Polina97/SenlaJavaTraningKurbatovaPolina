package com.senla.bookshopui.action;


import org.apache.log4j.Logger;

import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshop.api.entity.StatusOrder;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class AddOrder implements IAction {
	private Logger log = Logger.getLogger(AddOrder.class);
	private String nameBuyer;
	private String ids;
	private StatusOrder status;

	@Override
	public void execute(IClientWorker worker) {
		Printer.print("Enter buyer name: ");
		try {
			this.nameBuyer = MyScanner.scanString();
			Printer.print("Choise the books. When we can stoper, enter \"-1\": ");
			Printer.printArray(worker.sendToShop(new String("getBooks")));
			this.ids = MyScanner.scanIds();
			Printer.print("Choise status: 1) CANCELED, 2) KIT, 3)DELIVERED");
			switch (MyScanner.positive()) {
			case 1:
				this.status = StatusOrder.CANCELED;
				break;
			case 2:
				this.status = StatusOrder.KIT;
				break;
			case 3:
				this.status = StatusOrder.DELIVERED;
				break;
			default:
				this.status = StatusOrder.CANCELED;
				break;
			}
			StringBuilder builder = new StringBuilder();
			builder.append("addOrder").append(SLASH).append(nameBuyer).append(SPLITTER).append(ids).append(SPLITTER).append(status);
			Printer.print(worker.sendToShop(builder.toString()));
		} catch (Exception e) {
			log.error(e);
			Printer.print(MyScanner.MESSAGE);

		}

	}

}
