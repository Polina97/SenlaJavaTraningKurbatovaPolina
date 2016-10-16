package com.senla.bookshop.manager;

import com.senla.bookshop.api.entities.IBaseEntity;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.Order;
import com.senla.bookshop.main.Printer;

public abstract class ArrayWorker {

	public static BaseEntity[] addEntity(BaseEntity entity, BaseEntity[] array) {
		if (array != null) {
			if (!contains(entity, array)) {
				BaseEntity[] array2 = new BaseEntity[array.length + 1];
				for (int i = 0; i < array.length; i++) {
					array2[i] = array[i];
				}
				array2[array.length] = entity;
				array = array2;
			} else {
			}

		} else {
			array = new BaseEntity[1];
			array[0] = entity;
		}
		Printer.printAdd(entity);
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
		Printer.printAdd(entity);
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
		Printer.printAdd(entity);
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
		Printer.printDelete(entity);
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
		Printer.printDelete(entity);
		return array;
	}

	public static IBaseEntity[] deleteEntity(IBaseEntity entity, IBaseEntity[] array) {
		if (array != null && contains(entity, array)) {
			IBaseEntity[] array2 = new BaseEntity[array.length - 1];
			int i = 0;
			for (IBaseEntity e : array) {
				if (!e.equals(entity)) {
					array2[i++] = e;
				}
			}
			array = array2;
		}
		Printer.printDelete(entity);
		return array;
	}

	public static boolean contains(IBaseEntity entity, IBaseEntity[] array) {
		boolean answer = false;
		for (IBaseEntity e : array) {
			if (e.equals(entity)) {
				answer = true;
				break;
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
			Printer.print(baseEntity.toString());
		}
	}

}
