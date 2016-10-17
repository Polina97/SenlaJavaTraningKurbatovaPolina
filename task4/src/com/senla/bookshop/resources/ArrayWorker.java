package com.senla.bookshop.resources;

import com.senla.bookshop.api.entities.IBaseEntity;
import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.entity.Order;

public abstract class ArrayWorker {

	public static IBuyer[] addBuyer(IBuyer entity, IBuyer[] array) {
		if (array != null) {
			if (!contains(entity, array)) {
				IBuyer[] array2 = new Buyer[array.length + 1];
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

	public static IOrder[] addOrder(IOrder entity, IOrder[] array) {
		if (array != null) {
			if (!contains(entity, array)) {
				IOrder[] array2 = new Order[array.length + 1];
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

	public static IBook[] addBook(IBook entity, IBook[] array) {
		if (array != null) {
			if (!contains(entity, array)) {
				IBook[] array2 = new Book[array.length + 1];
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

	public static IBuyer[] deleteBuyer(IBuyer entity, IBuyer[] array) {
		if (array != null && contains(entity, array)) {
			IBuyer[] array2 = new Buyer[array.length - 1];
			int i = 0;
			for (IBuyer e : array) {
				if (!e.equals(entity)) {
					array2[i++] = e;
				}
			}
			array = array2;
		}
		//Printer.printDelete(entity);
		return array;
	}

	public static IBook[] deleteBook(IBook entity, IBook[] array) {
		if (array != null && contains(entity, array)) {
			IBook[] array2 = new Book[array.length - 1];
			int i = 0;
			for (IBook e : array) {
				if (!e.equals(entity)) {
					array2[i++] = e;
				}
			}
			array = array2;
		}
		//Printer.printDelete(entity);
		return array;
	}

	public static IOrder[] deleteOrder(IOrder entity, IOrder[] array) {
		if (array != null && contains(entity, array)) {
			IOrder[] array2 = new Order[array.length - 1];
			int i = 0;
			for (IOrder e : array) {
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
