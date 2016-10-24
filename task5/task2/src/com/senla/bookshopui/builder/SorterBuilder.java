package com.senla.bookshopui.builder;

import com.senla.bookshopui.api.IBaseBuilder;
import com.senla.bookshopui.api.IBaseItem;
import com.senla.bookshopui.api.IMenu;
import com.senla.bookshopui.menu.Menu;
import com.senla.bookshopui.menu.TypeMenu;

public class SorterBuilder implements IBaseBuilder {
	IMenu rootMenu;
	IBaseItem booksSorter;

	public SorterBuilder() {
		this.rootMenu = new Menu(TypeMenu.BOOKS_SORTER);
	}

	@Override
	public void buildMenu() {
		
	}

	@Override
	public IMenu getRootMenu() {
		return rootMenu;
	}

}
