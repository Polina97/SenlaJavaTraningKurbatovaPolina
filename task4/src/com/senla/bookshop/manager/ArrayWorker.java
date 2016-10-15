package com.senla.bookshop.manager;

import com.senla.bookshop.api.entities.IBaseEntity;
import com.senla.bookshop.entity.BaseEntity;
import com.senla.bookshop.main.Printer;

public class ArrayWorker {
	private IBaseEntity[] array;
	

	public ArrayWorker() {
	}

	public ArrayWorker(IBaseEntity[] array) {
		this.array = array;
	}

	public IBaseEntity[] getArray() {
		return array;
	}

	public void setArray(IBaseEntity[] array) {
		this.array = array;
	}

	public void addEntity(IBaseEntity entity) {
		if (this.array != null) {
			if (!contains(entity)) {
				IBaseEntity[] array2 = new BaseEntity[this.array.length + 1];
				for (int i = 0; i < this.array.length; i++) {
					array2[i] = this.array[i];
				}
				array2[array.length] = entity;
				this.array = array2;
			} else {
			}

		} else {
			this.array = new BaseEntity[1];
			this.array[0] = entity;
		}
		Printer.printAdd(entity);
	}

	public void deleteEntity(IBaseEntity entity) {
		if (this.array != null && contains(entity)) {
			IBaseEntity[] array2 = new BaseEntity[this.array.length - 1];
			int i = 0;
			for (IBaseEntity e : this.array) {
				if (!e.equals(entity)) {
					array2[i++] = e;
				}
			}
			this.array = array2;
		}
		Printer.printDelete(entity);
	}

	public boolean contains(IBaseEntity entity) {
		boolean answer = false;
		for (IBaseEntity e : array) {
			if (e.equals(entity)) {
				answer = true;
				break;
			}
		}
		return answer;
	}

	public String[] toStringArray() {
		String[] arrayString = new String[this.array.length];
		for (int i = 0; i < this.array.length; i++) {
			arrayString[i] = this.array[i].toString();
		}
		return arrayString;
	}

}
