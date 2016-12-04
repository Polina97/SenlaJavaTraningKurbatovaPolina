package com.senla.bookshopui.action.sorter;


import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshop.typecomparator.TypeOrderComparator;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class PriceDelOrdersSorter implements IAction{

	@Override
	public void execute(IClientWorker worker) {
		StringBuilder builder = new StringBuilder();
		builder.append("sortDeliveredOrders").append(SLASH).append(TypeOrderComparator.PRICE);
		Printer.printArray(worker.runShop(builder.toString()));
	}
}
