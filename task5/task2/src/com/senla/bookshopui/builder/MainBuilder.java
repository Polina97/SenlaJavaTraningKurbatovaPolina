package com.senla.bookshopui.builder;

import com.senla.bookshopui.api.IBaseBuilder;
import com.senla.bookshopui.api.IBaseItem;
import com.senla.bookshopui.api.IMenu;
import com.senla.bookshopui.item.BooksPrinterItem;
import com.senla.bookshopui.menu.Menu;
import com.senla.bookshopui.menu.TypeMenu;

public class MainBuilder implements IBaseBuilder {
	IMenu rootMenu;
	IBaseItem booksPrinter;

	public MainBuilder() {
		this.rootMenu = new Menu(TypeMenu.MAIN);
	}

	@Override
	public void buildMenu() {
		this.booksPrinter = new BooksPrinterItem();
		this.rootMenu.addItem(this.booksPrinter);
	}

	@Override
	public IMenu getRootMenu() {
		return rootMenu;
	}

}
