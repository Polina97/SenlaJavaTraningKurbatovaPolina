
package com.senla.bookshopui.action.sorter;

import com.senla.bookshop.api.client.IClientWorker;
import com.senla.bookshop.typecomparator.TypeBookComparator;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class PriceOldBooksSorter implements IAction{

	@Override
	public void execute(IClientWorker worker) {
		StringBuilder builder = new StringBuilder();
		builder.append("sortOldBooks").append(SLASH).append(TypeBookComparator.PRICE);
		Printer.printArray(worker.runShop(builder.toString()));
	}
}
