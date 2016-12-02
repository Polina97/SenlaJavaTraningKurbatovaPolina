package com.senla.bookshopui.action.sorter;

import java.util.List;

import com.senla.bookshop.client.ClientThread;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class AlphBooksSorter implements IAction{

	@Override
	public void execute(ClientThread thread) {
		Printer.printArray(shop.sortOldBooks("ALPHABET"));
		
	}

}
