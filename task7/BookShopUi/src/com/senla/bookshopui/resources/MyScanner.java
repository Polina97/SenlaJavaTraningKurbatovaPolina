package com.senla.bookshopui.resources;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MyScanner {
	public static String MESSAGE = "Try again!";
	private static String SLASH = "/";
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

	public static GregorianCalendar scanDate() {
		try {
			String[] date = scn.next().split(SLASH);
			scn = new Scanner(System.in);
			return new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]),
					Integer.parseInt(date[0]));

		} catch (Exception e) {
			return (GregorianCalendar) GregorianCalendar.getInstance();
		}
	}

	public static List<Integer> scanIds() {
		List<Integer> ids = new ArrayList<>();
		int id = scanInt();
		while (id != -1) {
			ids.add(id);
			id = scanInt();
		}
		return ids;
	}

	public static boolean isNext() {
		return isNext;
	}

	public static void setIsNext(Boolean isNext) {
		MyScanner.isNext = isNext;
	}

}
