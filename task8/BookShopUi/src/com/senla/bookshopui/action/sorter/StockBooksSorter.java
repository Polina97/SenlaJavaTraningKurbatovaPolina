package com.senla.bookshopui.action.sorter;

import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshop.typecomparator.TypeBookComparator;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class StockBooksSorter implements IAction{

	@Override
	public void execute(IClientWorker worker) {
		StringBuilder builder = new StringBuilder();
		builder.append("sortBooks").append(SLASH).append(TypeBookComparator.STOCK);
		Printer.printArray(worker.sendToShop(builder.toString()));
	}

}
