package com.senla.bookshop.main;

import com.senla.bookshop.api.entities.IBaseEntity;

public class Printer {
	public static void print(Object obj) {
		System.out.println(obj);
	}

	public static void print() {
		System.out.println();
	}
	public static void printAdd(IBaseEntity entity){
		System.out.println(entity.getType()+" was added!");
	}

	public static void printDelete(IBaseEntity entity){
		System.out.println(entity.getType()+" was deleted!");
	}

}
