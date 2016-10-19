package com.senla.bookshop.comparators;

import java.util.Comparator;

import com.senla.bookshop.entity.Order;

public class OrderComparator implements Comparator<Order>{
	private TypeOrderComparator type;

	public OrderComparator(TypeOrderComparator type) {
		this.type = type;
	}


	@Override
	public int compare(Order o1, Order o2) {
		switch (this.type) {
		case DATE:
			return o1.getDate().compareTo(o2.getDate());
		case PRICE:
			return o1.getPrice() - o2.getPrice();
		case STATUS:
			return o1.getStatus().compareTo(o2.getStatus());
		default:
			return 0;
		}
	}

}
