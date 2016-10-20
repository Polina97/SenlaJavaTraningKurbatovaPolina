package com.senla.bookshop.resources;

import com.senla.bookshop.api.entities.IBaseEntity;
import com.senla.bookshop.api.entities.IBook;
import com.senla.bookshop.api.entities.IBuyer;
import com.senla.bookshop.api.entities.IOrder;
import com.senla.bookshop.entity.Book;
import com.senla.bookshop.entity.Buyer;
import com.senla.bookshop.entity.Order;

public abstract class ArrayWorker {

	private static final Integer CONSTANT = 5;

	public static IBuyer[] addBuyer(IBuyer entity, IBuyer[] array) {
		if (array != null) {
			if (!contains(entity, array)) {
				if (containsNull(array)) {
					for (int i = 0; i < array.length; i++) {
						if (array[i] == null) {
							array[i] = entity;
							break;
						}
					}
				} else {
					IBuyer[] array2 = new Buyer[array.length * 2];
					System.arraycopy(array, 0, array2, 0, array.length);
					array2[array.length] = entity;
					array = array2;
				}
			}

		} else {
			array = new Buyer[CONSTANT];
			array[0] = entity;
		}
		return array;
	}

	public static IOrder[] addOrder(IOrder entity, IOrder[] array) {
		if (array != null) {
			if (!contains(entity, array)) {
				if (containsNull(array)) {
					for (int i = 0; i < array.length; i++) {
						if (array[i] == null) {
							array[i] = entity;
							break;
						}
					}

				} else {
					IOrder[] array2 = new Order[array.length * 2];
					System.arraycopy(array, 0, array2, 0, array.length);
					array2[array.length] = entity;
					array = array2;
				}
			}
		} else {
			array = new Order[CONSTANT];
			array[0] = entity;
		}
		return array;
	}

	public static IBook[] addBook(IBook entity, IBook[] array) {
		if (array != null) {
			if (!contains(entity, array)) {
				if (containsNull(array)) {
					for (int i = 0; i < array.length; i++) {
						if (array[i] == null) {
							array[i] = entity;
							break;
						}
					}

				} else {
					IBook[] array2 = new Book[array.length * 2];
					System.arraycopy(array, 0, array2, 0, array.length);
					array2[array.length] = entity;
					array = array2;
				}
			}
		} else {
			array = new Book[CONSTANT];
			array[0] = entity;
		}
		return array;

	}

	public static IBuyer[] deleteBuyer(IBuyer entity, IBuyer[] array) {
		if (array != null && contains(entity, array)) {
			for (int i = 0; i < array.length; i++) {
				if (array[i].equals(entity)) {
					array[i] = null;
					break;
				}
			}
		}
		return array;
	}

	public static IBook[] deleteBook(IBook entity, IBook[] array) {
		if (array != null && contains(entity, array)) {
			for (int i = 0; i < array.length; i++) {
				if (array[i].equals(entity)) {
					array[i] = null;
					break;
				}
			}
		}
		return array;
	}

	public static IOrder[] deleteOrder(IOrder entity, IOrder[] array) {
		if (array != null && contains(entity, array)) {
			for (int i = 0; i < array.length; i++) {
				if (array[i].equals(entity)) {
					array[i] = null;
					break;
				}
			}
		}
		return array;
	}

	public static Boolean contains(IBaseEntity entity, IBaseEntity[] array) {
		boolean answer = false;
		if (array != null) {
			for (IBaseEntity e : array) {
				if (e != null && e.equals(entity)) {
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
			if (baseEntity != null) {
				Printer.print(baseEntity.getDescription());
			}
		}
	}

	private static Boolean containsNull(IBaseEntity[] entities) {
		boolean answer = false;
		for (IBaseEntity entity : entities) {
			if (entity == null) {
				answer = true;
				break;
			}
		}
		return answer;
	}

}
