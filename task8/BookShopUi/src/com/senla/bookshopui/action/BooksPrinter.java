package com.senla.bookshopui.action;


import com.senla.bookshop.api.shop.ShopMethods;
import com.senla.bookshop.client.ClientThread;
import com.senla.bookshopui.api.IAction;
import com.senla.bookshopui.resources.Printer;

public class BooksPrinter implements IAction {

	@Override
	public void execute(ClientThread thread) {

		Printer.printArray(thread.runShop(ShopMethods.getBooks.toString()));
	}

}
