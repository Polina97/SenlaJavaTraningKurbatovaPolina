package com.senla.bookshop.comparators;

import java.util.Comparator;

import com.senla.bookshop.api.entities.IOrder;

public class OrderComparator implements Comparator<IOrder> {
	private TypeOrderComparator type;

	public OrderComparator(TypeOrderComparator type) {
		this.type = type;
	}

	@Override
	public int compare(IOrder o1, IOrder o2) {
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
