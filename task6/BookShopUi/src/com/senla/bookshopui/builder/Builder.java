package com.senla.bookshopui.builder;


import com.senla.bookshopui.action.*;
import com.senla.bookshopui.action.scv.ExsportBook;
import com.senla.bookshopui.action.scv.ExsportBuyer;
import com.senla.bookshopui.action.scv.ExsportOrder;
import com.senla.bookshopui.action.scv.ImportBook;
import com.senla.bookshopui.action.scv.ImportBuyer;
import com.senla.bookshopui.action.scv.ImportOrder;
import com.senla.bookshopui.action.sorter.*;
import com.senla.bookshopui.api.*;
import com.senla.bookshopui.item.Item;
import com.senla.bookshopui.menu.*;

public class Builder implements IBaseBuilder {
	private IMenu rootMenu;
	private final IItem back ;
	private final IItem exit;

	public Builder() {
		this.rootMenu = new Menu(TypeMenu.MAIN);
		this.back = new Item(new Back(this.rootMenu), "Back");
		this.exit = new Item(new Exit(), "Exit");
	}

	@Override
	public void buildMenu() {
		this.rootMenu.addItem(new Item(new BooksPrinter(), "Print books."));
		this.rootMenu.addItem(new Item(new SubmenuCreater(getSubMenu(TypeMenu.BOOKS_SORTER)),"Sort books."));
		this.rootMenu.addItem(new Item(new OrdersPrinter(), "Print orders."));
		this.rootMenu.addItem(new Item(new SubmenuCreater(getSubMenu(TypeMenu.ORDERS_SORTER)), "Sort orders."));
		this.rootMenu.addItem(new Item(new DelOrdersPrinter(), "Print delivered orders."));
		this.rootMenu.addItem(new Item(new SubmenuCreater(getSubMenu(TypeMenu.DELIBERED_ORDERS_SORTER)), "Sort delivered orders."));
		this.rootMenu.addItem(new Item(new OldBooksPrinter(), "Print old books."));
		this.rootMenu.addItem(new Item(new SubmenuCreater(getSubMenu(TypeMenu.OLD_BOOKS_SORTER)),"Sort old books."));
		this.rootMenu.addItem(new Item(new GeneralPrice(), "General price."));
		this.rootMenu.addItem(new Item(new CountDelOrders(), "Number of delivered orders."));
		this.rootMenu.addItem(new Item(new SubmenuCreater(getSubMenu(TypeMenu.BOOK)),"Work with books."));
		this.rootMenu.addItem(new Item(new SubmenuCreater(getSubMenu(TypeMenu.ORDER)),"Work with orders."));
		this.rootMenu.addItem(new Item(new CopyOrder(), "Copy order."));
		this.rootMenu.addItem(new Item(new SubmenuCreater(getSubMenu(TypeMenu.CSV)),"Export/Import CSV."));
		this.rootMenu.addItem(this.exit);
	}

	@Override
	public IMenu getRootMenu() {
		return rootMenu;
	}

	public IMenu getSubMenu(TypeMenu typeMenu) {
		IMenu subMenu = new Menu(typeMenu);
		switch (typeMenu) {
		case BOOKS_SORTER:
			subMenu.addItem(new Item(new PriceBooksSorter(),"Sort books by price."));
			subMenu.addItem(new Item(new DateBooksSorter(),"Sort books by date."));
			subMenu.addItem(new Item(new AlphBooksSorter(),"Sort books by alphabet."));
			subMenu.addItem(new Item(new StockBooksSorter(),"Sort books by stock availability."));
			subMenu.addItem(back);
			break;
		case OLD_BOOKS_SORTER:
			subMenu.addItem(new Item(new PriceOldBooksSorter(),"Sort old books by price."));
			subMenu.addItem(new Item(new DateOldBooksSorter(),"Sort old books by date."));
			subMenu.addItem(back);
			break;
		case ORDERS_SORTER:
			subMenu.addItem(new Item(new DateOrdersSorter(), "Sort orders by date."));
			subMenu.addItem(new Item(new PriceOrdersSorter(), "Sort orders by price."));
			subMenu.addItem(new Item(new StatusOrdersSorter(), "Sort orders by status."));
			subMenu.addItem(back);
			break;
		case DELIBERED_ORDERS_SORTER:
			subMenu.addItem(new Item(new DateDelOrdersSorter(), "Sort delivered orders by date."));
			subMenu.addItem(new Item(new PriceDelOrdersSorter(), "Sort delivered orders by price."));
			subMenu.addItem(back);
			break;
		case BOOK:
			subMenu.addItem(new Item(new BookDescription(), "Get book description."));
			subMenu.addItem(new Item(new AddToStock(), "Add book to stock."));
			subMenu.addItem(new Item(new DeleteFromStock(), "Delete book from stock."));
			subMenu.addItem(new Item(new SubmitApplication(), "Submit application."));
			subMenu.addItem(back);
			break;
		case ORDER:
			subMenu.addItem(new Item(new OrderDescription(), "Get order description."));
			subMenu.addItem(new Item(new AddOrder(), "Add order."));
			subMenu.addItem(new Item(new CancelOrder(), "Cancel order."));
			subMenu.addItem(new Item(new DeliverOrder(), "Deliver order."));
			subMenu.addItem(back);
			break;
		case CSV:
			subMenu.addItem(new Item(new ExsportBook(), "Export book."));
			subMenu.addItem(new Item(new ExsportOrder(), "Export order."));
			subMenu.addItem(new Item(new ExsportBuyer(), "Export buyer."));
			subMenu.addItem(new Item(new ImportBook(), "Import book."));
			subMenu.addItem(new Item(new ImportOrder(), "Import order."));
			subMenu.addItem(new Item(new ImportBuyer(), "Import buyer."));
			subMenu.addItem(back);
			break;
		default:
			break;
		}
		return subMenu;
	}
}
