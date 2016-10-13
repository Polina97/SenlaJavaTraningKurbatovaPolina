package restaurant;

import cook.Order;
import menu.ADish;

public class Table {
	private int number;
	private Order order;

	public Table(int number) {
		super();
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
	

	public Order getOrder() {
		return order;
	}

	public Order createNewOrder(int numberOfOrder, ADish[] dishes) {
		int counter = 0;
		for (ADish d : dishes) {
			if (d == null) {
				counter = 1;
				break;
			}
		}
		if (counter == 1) {
			System.out.println("Ordered was NOT created for table " + getNumber());
			return order;
		} else {
			order = new Order(numberOfOrder, dishes);
			System.out.println("Ordered was created for table " + getNumber());
			return order;
		}
	}

	public void cancelOrder() {
		order = null;
		System.out.println("Order was delete for table " + getNumber());
	}

	public void printOrder() {
		System.out.println("Table ¹ " + getNumber());
		if (isExistsOrder()) {
			System.out.println("Order number: "+order.getNumberOfOrder());
			order.printPrices();
		} else {
			System.out.println("Order is not exist!");
		}
	}

	public boolean isExistsOrder() {
		return (order != null);
	}

}
