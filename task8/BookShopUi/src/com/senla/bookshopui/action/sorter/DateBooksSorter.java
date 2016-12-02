package com.senla.bookshopui.action.sorter;


import com.senla.bookshop.api.shop.ShopMethods;
import com.senla.bookshop.client.ClientThread;
import com.senla.bookshop.typecomparator.TypeBookComparator;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class DateBooksSorter implements IAction{
	@Override
	public void execute(ClientThread thread) {
		StringBuilder builder = new StringBuilder();
		builder.append(ShopMethods.sortBooks).append(SLASH).append(TypeBookComparator.DATE);
		Printer.printArray(thread.runShop(builder.toString()));
		
	}
	

}