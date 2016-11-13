package com.senla.bookshopui.resources;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class MyScanner {
	private static String MESSAGE = "Try again!";
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
			Printer.print(MESSAGE);
			return scanInt();
		}
	}

	public static int positive() {
		int a = scanInt();
		if (a > 0)
			return a;
		else {
			Printer.print(MESSAGE);
			return positive();
		}
	}

	public static String scanString() {
		if (scn.hasNext()) {
			String a = scn.nextLine();
			scn = new Scanner(System.in);
			return a;
		} else {
			scn = new Scanner(System.in);
			Printer.print(MESSAGE);
			return scanString();
		}
	}

	public static GregorianCalendar scanDate() {
		try {
			String[] date = scn.next().split(SLASH);
			scn = new Scanner(System.in);
			return new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]),
					Integer.parseInt(date[0]));
			
		} catch (Exception e) {
			scn = new Scanner(System.in);
			Printer.print(MESSAGE);
			return scanDate();
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
