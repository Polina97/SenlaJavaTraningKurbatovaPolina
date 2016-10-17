package com.senla.bookshop.resources;

import com.senla.bookshop.api.entities.IBaseEntity;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.entity.Order;

public abstract class ArrayWorker {

	public static Buyer[] addBuyer(Buyer entity, Buyer[] array) {
		if (array != null) {
			if (!contains(entity, array)) {
				Buyer[] array2 = new Buyer[array.length + 1];
				for (int i = 0; i < array.length; i++) {
					array2[i] = array[i];
				}
				array2[array.length] = entity;
				array = array2;
			} else {
			}

		} else {
			array = new Buyer[1];
			array[0] = entity;
		}
		//Printer.printAdd(entity);
		return array;
	}

	public static Order[] addOrder(Order entity, Order[] array) {
		if (array != null) {
			if (!contains(entity, array)) {
				Order[] array2 = new Order[array.length + 1];
				for (int i = 0; i < array.length; i++) {
					array2[i] = array[i];
				}
				array2[array.length] = entity;
				array = array2;
			} else {
			}

		} else {
			array = new Order[1];
			array[0] = entity;
		}
		//Printer.printAdd(entity);
		return array;
	}

	public static Book[] addBook(Book entity, Book[] array) {
		if (array != null) {
			if (!contains(entity, array)) {
				Book[] array2 = new Book[array.length + 1];
				for (int i = 0; i < array.length; i++) {
					array2[i] = array[i];
				}
				array2[array.length] = entity;
				array = array2;
			} else {
			}

		} else {
			array = new Book[1];
			array[0] = entity;
		}
		//Printer.printAdd(entity);
		return array;
	}

	public static Buyer[] deleteBuyer(Buyer entity, Buyer[] array) {
		if (array != null && contains(entity, array)) {
			Buyer[] array2 = new Buyer[array.length - 1];
			int i = 0;
			for (Buyer e : array) {
				if (!e.equals(entity)) {
					array2[i++] = e;
				}
			}
			array = array2;
		}
		//Printer.printDelete(entity);
		return array;
	}

	public static Book[] deleteBook(Book entity, Book[] array) {
		if (array != null && contains(entity, array)) {
			Book[] array2 = new Book[array.length - 1];
			int i = 0;
			for (Book e : array) {
				if (!e.equals(entity)) {
					array2[i++] = e;
				}
			}
			array = array2;
		}
		//Printer.printDelete(entity);
		return array;
	}

	public static Order[] deleteOrder(Order entity, Order[] array) {
		if (array != null && contains(entity, array)) {
			Order[] array2 = new Order[array.length - 1];
			int i = 0;
			for (Order e : array) {
				if (!e.equals(entity)) {
					array2[i++] = e;
				}
			}
			array = array2;
		}
		//Printer.printDelete(entity);
		return array;
	}

	public static boolean contains(IBaseEntity entity, IBaseEntity[] array) {
		boolean answer = false;
		if (array != null) {
			for (IBaseEntity e : array) {
				if (e.equals(entity)) {
					answer = true;
					break;
				}
			}
		}
		return answer;
	}

	public static String[] toStringArray(IBaseEntity[] array) {
		String[] arrayString = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			arrayString[i] = array[i].toString();
		}
		return arrayString;
	}

	public static void showArray(IBaseEntity[] array) {
		for (IBaseEntity baseEntity : array) {
			Printer.print(baseEntity.getDescription());
		}
	}

}
