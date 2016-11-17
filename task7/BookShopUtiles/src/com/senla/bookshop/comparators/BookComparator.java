package com.senla.bookshop.comparators;

import java.util.Comparator;

import com.senla.bookshop.api.entity.IBook;
import com.senla.bookshop.typecomparator.TypeBookComparator;

public class BookComparator implements Comparator<IBook> {
	private TypeBookComparator type;

	public BookComparator(TypeBookComparator type) {
		this.type = type;
	}


	@Override
	public int compare(IBook o1, IBook o2) {
			switch (this.type) {
			case ALPHABET:
				return o1.getName().compareTo(o2.getName());
			case PRICE:
				return o1.getPrice() - o2.getPrice();
			case DATE:
				return o1.getDatePublication().compareTo(o2.getDatePublication());
			case STOCK: {
				int a1 = 0, a2 = 0;
				if (o1.isInStock() == true) {
					a1 = 1;
				}
				if (o2.isInStock() == true) {
					a2 = 1;
				}
				return a1 - a2;
			}
			default:
				return 0;
			}
		}
	

}
