package com.senla.bookshopui.resources;

import java.util.List;

import com.senla.bookshopui.api.IItem;

public class Printer {
	public static void print() {
		System.out.println();
	}

	public static void print(Object obj) {
		System.out.println(obj);
	}

	public static void printArray(List<String> books) {
		int i = 1;
		for (Object object : books) {
			System.out.println((i++) + " " + object);
		}
	}

	public static void printItems(List<IItem> items) {
		int i =1;
		for (IItem iItem: items) {
			System.out.println((i++)+". "+iItem.getName());
		}
	}
}
