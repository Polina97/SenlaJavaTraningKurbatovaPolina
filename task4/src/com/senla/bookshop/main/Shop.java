package com.senla.bookshop.main;

import java.util.GregorianCalendar;

import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.comparators.TypeBookComparator;
import com.senla.bookshop.comparators.TypeOrderComparator;
import com.senla.bookshop.entity.*;
import com.senla.bookshop.manager.*;
import com.senla.bookshop.resources.Printer;

public class Shop {
	private static final String P = "------------------------";
	private BookManager bookManager = new BookManager();
	private OrderManager orderManager = new OrderManager();
	private BuyerManager buyerManager = new BuyerManager();

	public void sortBooks() {
		Printer.print("List of books: ");
		bookManager.showAll();
		Printer.print(P);
		Printer.print("Sort alphabetically:");
		bookManager.sortBooks(TypeBookComparator.ALPHABET);
		Printer.print(P);
		Printer.print("Sort by price:");
		bookManager.sortBooks(TypeBookComparator.PRICE);
		Printer.print(P);
		Printer.print("Sort by date publication:");
		bookManager.sortBooks(TypeBookComparator.DATE);
		Printer.print(P);
		Printer.print("Sort by stock availability:");
		bookManager.sortBooks(TypeBookComparator.STOCK);
		Printer.print(P);

	}

	public void sortOrders() {
		Printer.print("List of orders sorted by Date: ");
		orderManager.sortOrders(TypeOrderComparator.DATE);
		Printer.print(P);
		Printer.print("List of orders sorted by Price: ");
		orderManager.sortOrders(TypeOrderComparator.PRICE);
		Printer.print(P);
		Printer.print("List of orders sorted by Status: ");
		orderManager.sortOrders(TypeOrderComparator.STATUS);
		Printer.print(P);
	}

	public void sortDeliveredOrders() {
		Printer.print("List of delivered orders sorted by Date: ");
		orderManager.sortDeliveredOrders(TypeOrderComparator.DATE);
		Printer.print(P);
		Printer.print("List of delivered orders sorted by Price: ");
		orderManager.sortDeliveredOrders(TypeOrderComparator.PRICE);
		Printer.print(P);
	}

	public void getGeneralPrice() {
		Printer.print("General price: ");
		Printer.print(orderManager.getGeneralPrice());
		Printer.print(P);
	}

	public void getDeliveredOrders() {
		Printer.print("Number of delivered orders: ");
		int count = 0;
		for (IOrder o : orderManager.getDeliveredOrders()) {
			if (o != null) {
				count++;
			}
		}
		Printer.print(count);
		Printer.print(P);
	}

	public void sortOldBooks() {
		Printer.print("List of old Books sorted by date: ");
		bookManager.sortOldBooks(TypeBookComparator.DATE);
		Printer.print(P);
		Printer.print("List of old Books sorted by price: ");
		bookManager.sortOldBooks(TypeBookComparator.PRICE);
		Printer.print(P);

	}

	public void getOrder() {
		Printer.print("Detalies of order 1002: ");
		Printer.print(orderManager.getOrderById(1002).getDescription());
		Printer.print(P);
	}

	public void getBook() {
		Printer.print("Detalies of book 101: ");
		Printer.print(bookManager.getById(101).getDescription());
		Printer.print(P);
	}

	public void addToStock() {
		Printer.print("Add book 103 to stock: ");
		bookManager.addToStock(103);
		Printer.print(P);
	}

	public void deleteFromStock() {
		Printer.print("Add book 101 to stock: ");
		bookManager.deleteFromStock(101);
		Printer.print(P);
	}

	public void addOrder() {
		Printer.print("Add new order 1005: ");
		Order order = new Order(1005, new Buyer(11, "Matt"), new Book[] { (Book) bookManager.getById(101) },
				new GregorianCalendar(2016, 10, 12), StatusOrder.KIT);
		orderManager.add(order);
		buyerManager.addOrderBuyer(order);
		Printer.print("Order 1005 was added!");
		Printer.print(P);
	}
	public void deliverOrder(){
		Printer.print("Deliver order 1005: ");
		orderManager.deliverOrder(1005);
		Printer.print(P);
	}
	public void cancelOrder(){
		Printer.print("Cancel order 1003: ");
		orderManager.cancelOrder(1003);
		Printer.print(P);
	}
	public void submitApplication(){
		Printer.print("Submit application on book 101");
		bookManager.submitApplication(101);
		Printer.print(P);
	}
}
