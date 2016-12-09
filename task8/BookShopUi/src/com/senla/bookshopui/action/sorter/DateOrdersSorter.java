package com.senla.bookshopui.action.sorter;


import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshop.typecomparator.TypeOrderComparator;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class DateOrdersSorter implements IAction{

	@Override
	public void execute(IClientWorker worker) {
		StringBuilder builder = new StringBuilder();
		builder.append("sortOrders").append(SLASH).append(TypeOrderComparator.DATE);
		Printer.printArray(worker.sendToShop(builder.toString()));
	}

}
