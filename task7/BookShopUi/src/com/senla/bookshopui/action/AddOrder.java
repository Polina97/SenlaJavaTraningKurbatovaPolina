package com.senla.bookshopui.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.senla.bookshop.api.entity.StatusOrder;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class AddOrder implements IAction {
	private Logger log = Logger.getLogger(AddOrder.class);
	private String nameBuyer;
	private List<Integer> ids;
	private StatusOrder status;

	@Override
	public void execute() {
		Printer.print("Enter buyer name: ");
		try {
			this.nameBuyer = MyScanner.scanString();
			Printer.print("Choise the books. When we can stoper, enter \"-1\": ");
			Printer.printArray(shop.getBooks());
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
			Printer.print(shop.addOrder(nameBuyer, ids, status));
		} catch (Exception e) {
			log.error(e);
			Printer.print(MyScanner.MESSAGE);

		}

	}

}
