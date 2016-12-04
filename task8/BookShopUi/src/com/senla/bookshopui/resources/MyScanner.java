package com.senla.bookshopui.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MyScanner {
	private static final Object COMA = ",";
	public static String MESSAGE = "Try again!";
	private static Scanner scn = new Scanner(System.in);
	private static Boolean isNext = scn.hasNext();

	public static int scanInt() {
		if (scn.hasNextInt()) {
			int a = scn.nextInt();
			scn = new Scanner(System.in);
			return a;
		} else {
			scn = new Scanner(System.in);
			return 0;
		}
	}

	public static int positive() throws Exception {
		try {
			int a = scanInt();
			if (a > 0)
				return a;
			else {
				return 0;
			}
		} catch (NoSuchElementException e) {
			throw new Exception(e);
		}
	}

	public static String scanString() throws Exception {
		if (scn.hasNext()) {
			String a = scn.nextLine();
			scn = new Scanner(System.in);
			return a;
		} else {
			throw new Exception();
		}
	}


	public static String scanIds() {
		List<Integer> ids = new ArrayList<Integer>();
		int id = scanInt();
		while (id != -1) {
			ids.add(id);
			id = scanInt();
		}
		StringBuilder builder = new StringBuilder();
		for (Integer i : ids) {
			builder.append(i).append(COMA);
		}
		builder.delete(builder.length() - 2, builder.length() - 1);
		return builder.toString();
	}

	public static boolean isNext() {
		return isNext;
	}

	public static void setIsNext(Boolean isNext) {
		MyScanner.isNext = isNext;
	}

}
