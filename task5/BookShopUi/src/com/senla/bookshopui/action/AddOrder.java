package com.senla.bookshopui.action;

import java.util.List;

import com.senla.bookshop.entity.StatusOrder;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.MyScanner;
import com.senla.bookshopui.resources.Printer;

public class AddOrder implements IAction {
	private String nameBuyer;
	private List<Integer> ids;
	private StatusOrder status;

	@Override
	public void execute() {
		Printer.print("Enter buyer name: ");
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

	}

}
